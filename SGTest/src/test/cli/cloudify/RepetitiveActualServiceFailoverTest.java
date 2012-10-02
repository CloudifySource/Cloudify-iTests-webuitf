package test.cli.cloudify;

import static org.testng.AssertJUnit.fail;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.cassandra.io.util.FileUtils;
import org.apache.http.HttpStatus;
import org.cloudifysource.dsl.internal.packaging.PackagingException;
import org.cloudifysource.dsl.utils.ServiceUtils;
import org.cloudifysource.usm.USMException;
import org.cloudifysource.usm.shutdown.DefaultProcessKiller;
import org.openspaces.admin.machine.Machine;
import org.openspaces.admin.pu.DeploymentStatus;
import org.openspaces.admin.pu.ProcessingUnit;
import org.openspaces.admin.pu.ProcessingUnitInstance;
import org.openspaces.admin.pu.events.ProcessingUnitInstanceLifecycleEventListener;
import org.openspaces.pu.service.CustomServiceMonitors;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import test.usm.USMTestUtils;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import framework.tools.SGTestHelper;
import framework.utils.AssertUtils;
import framework.utils.AssertUtils.RepetitiveConditionProvider;
import framework.utils.LogUtils;
import framework.utils.ProcessingUnitUtils;
import framework.utils.ScriptUtils;
import groovy.util.ConfigObject;
import groovy.util.ConfigSlurper;

/**
 * 1. install tomcat service
 * 2. kill tomcat(the actual process, not the USM pu)
 * 3. assert tomcat is recovered by USM
 * 4. repeat steps 1-3
 * 5. kill tomcat in such a way the USM pu cannot launch it again
 * 6. assert USM pu shuts itself down, 
 * 	  allowing the gsm to redeploy it and thus recovering the tomcat
 * @author elip
 *
 */
public class RepetitiveActualServiceFailoverTest extends AbstractLocalCloudTest {
	
	Long tomcatPId;
	Machine machineA;
	WebClient client;
	private final String TOMCAT_URL = "http://127.0.0.1:8080";
	private String tomcatServiceDir = ScriptUtils.getBuildPath() + "/recipes/services/tomcat";
	
	@Override
	@BeforeMethod
	public void beforeTest() {
		super.beforeTest();	
		client = new WebClient(BrowserVersion.getDefault());
	}
	
	@Test(timeOut = DEFAULT_TEST_TIMEOUT * 2, groups = "1", enabled = true)
	public void tomcatServiceDownAndCorruptedTest() throws IOException, InterruptedException, PackagingException {
		
		String command = "connect " + this.restUrl + ";" + "install-service " + "--verbose -timeout 10 " + tomcatServiceDir;
		try {
			LogUtils.log("installing tomcat service using Cli");
			CommandTestUtils.runCommandAndWait(command);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		ProcessingUnit tomcat = admin.getProcessingUnits().waitFor(ServiceUtils.getAbsolutePUName("default", "tomcat"), 10, TimeUnit.SECONDS);
		assertTrue("tomcat processing unit was not found.", tomcat != null);
		boolean machineFound = admin.getGridServiceContainers().waitFor(1, 30, TimeUnit.SECONDS);
		if (machineFound){
			machineA = admin.getGridServiceContainers().getContainers()[0].getMachine();
		}else{
			AssertFail("No GSC's were found in the givan time-frame.");
		}
		
		final CountDownLatch removed = new CountDownLatch(1);
		final CountDownLatch added = new CountDownLatch(2);
		
		LogUtils.log("adding a lifecycle listener to tomcat pu");
		ProcessingUnitInstanceLifecycleEventListener eventListener = new ProcessingUnitInstanceLifecycleEventListener() {
			
			@Override
			public void processingUnitInstanceRemoved(
					ProcessingUnitInstance processingUnitInstance) {
				LogUtils.log("USM processing unit instance has been removed due to tomcat failure");
				removed.countDown();	
			}
			
			@Override
			public void processingUnitInstanceAdded(
					ProcessingUnitInstance processingUnitInstance) {
				LogUtils.log("USM processing unit instance has been added");
				added.countDown();	
			}
		};
		tomcat.addLifecycleListener(eventListener);
		
		for (int i = 0 ; i < 2 ; i++) {
			tomcatPId = getTomcatPId();
			LogUtils.log("Killing tomcat process with pid " + tomcatPId + ". " + i + "iteration.");
			DefaultProcessKiller dpk = new DefaultProcessKiller();
			try {
				dpk.killProcess(tomcatPId);
			} catch (USMException e) {
				AssertFail("failed to kill tomcat process with pid: " + tomcatPId);
			}
			
			int responseCode = getResponseCode(TOMCAT_URL);
			assertTrue("Tomcat service is still running. Request returned response code: " + responseCode, HttpStatus.SC_NOT_FOUND == responseCode);
			LogUtils.log("Waiting for tomcat to recover");
			RepetitiveConditionProvider condition = new RepetitiveConditionProvider() {	
				@Override
				public boolean getCondition() {
					return isTomcatPageExists(client);
				}
			};
			AssertUtils.repetitiveAssertTrue("cannot connect to tompage", condition, DEFAULT_TEST_TIMEOUT);
		}
		
		LogUtils.log("Killing tomcat process and corrupting its install folder");
		String pathToTomcat;
		LogUtils.log("deleting catalina.sh/bat from pu folder");
		ConfigObject tomcatConfig = new ConfigSlurper().parse(new File(this.tomcatServiceDir, "tomcat-service.properties").toURI().toURL());
		String tomcatVersion = (String) tomcatConfig.get("version");
		// TODO - this test will fail when we upgrade tomcat!
		// It is better to write a simple scanner that looks for catalina.* under /ext
		String catalinaPath = "/" + SGTestHelper.getWorkDirName() + "/processing-units/default_tomcat_1/ext/apache-tomcat-" + tomcatVersion + "/bin/catalina.";
		String filePath = ScriptUtils.getBuildPath()+ catalinaPath;
		if (ScriptUtils.isWindows()) {
			pathToTomcat = filePath + "bat";
		}
		else {
			pathToTomcat = filePath + "sh";
		}
		assertTrue("Catalina file was not found in path " + pathToTomcat, (FileUtils.isExists(pathToTomcat)));
		
		File tomcatRun = new File(pathToTomcat);
		assertTrue(tomcatRun.delete());
		tomcatPId = getTomcatPId();
		LogUtils.log("killing tomcat process with pid " + tomcatPId);
		DefaultProcessKiller dpk = new DefaultProcessKiller();
		try {
			dpk.killProcess(tomcatPId);
		} catch (USMException e) {
			AssertFail("failed to kill tomcat process with pid: " + tomcatPId);
		}

		LogUtils.log("waiting for tomcat pu instances to decrease");
		assertTrue("Tomcat PU instance was not decresed", removed.await(240, TimeUnit.SECONDS));
		assertTrue("ProcessingUnitInstanceRemoved event has not been fired", removed.getCount() == 0);
		LogUtils.log("waiting for tomcat pu instances to increase");
		assertTrue("Tomcat instance was not increased.", added.await(240, TimeUnit.SECONDS));
		LogUtils.log("waiting for USM service to reach RUNNING state");
		assertTrue("Processing unit instance did not reach running state in the defined time frame.",
				USMTestUtils.waitForPuRunningState(ServiceUtils.getAbsolutePUName("default", "tomcat"), 60, TimeUnit.SECONDS, admin));
		LogUtils.log("verifiying tomcat service in running");
		isTomcatPageExists(client);	
		LogUtils.log("all's well that ends well :)");
	}
	
	private boolean isTomcatPageExists(WebClient client) {
		
        HtmlPage page = null;
        try {
            page = client.getPage("http://" + machineA.getHostAddress() + ":8080");
        } catch (IOException e) {
            fail(e.getMessage());
        }
        return "OK".equals(page.getWebResponse().getStatusMessage());
		
	}
	
	private Long getTomcatPId() throws UnknownHostException {
		String absolutePUName = ServiceUtils.getAbsolutePUName("default", "tomcat");
		ProcessingUnit tomcat = admin.getProcessingUnits().getProcessingUnit(absolutePUName);
		assertNotNull(tomcat);
		ProcessingUnitUtils.waitForDeploymentStatus(tomcat, DeploymentStatus.INTACT);
		assertTrue(tomcat.getStatus().equals(DeploymentStatus.INTACT));
		
		ProcessingUnitInstance tomcatInstance = tomcat.getInstances()[0];
		assertTrue("USM Service State is not RUNNING", USMTestUtils.waitForPuRunningState(absolutePUName, 60, TimeUnit.SECONDS, admin));
		CustomServiceMonitors customServiceDetails = (CustomServiceMonitors) tomcatInstance.getStatistics().getMonitors().get("USM");
		return (Long) customServiceDetails.getMonitors().get("USM_Actual Process ID");
	}
	
	
	//if service is down, this method will return a 404 not found exception.
	private int getResponseCode(String urlString) throws IOException{
		URL url = new URL ( urlString );
		URLConnection connection = url.openConnection();
		try {
			connection.connect();
		}catch (ConnectException e){
			LogUtils.log("The connection to " + urlString + " has failed.");
			return HttpStatus.SC_NOT_FOUND;
		}
		HttpURLConnection httpConnection = (HttpURLConnection) connection;
		int code = httpConnection.getResponseCode();
		return code;

	}
}
