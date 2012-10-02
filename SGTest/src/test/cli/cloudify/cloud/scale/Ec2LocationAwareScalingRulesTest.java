package test.cli.cloudify.cloud.scale;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openspaces.admin.zone.config.AnyZonesConfig;
import org.openspaces.admin.zone.config.AtLeastOneZoneConfig;
import org.openspaces.admin.zone.config.AtLeastOneZoneConfigurer;
import org.openspaces.admin.zone.config.ExactZonesConfig;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import framework.utils.LogUtils;
import framework.utils.TestUtils;

public class Ec2LocationAwareScalingRulesTest extends AbstractScalingRulesCloudTest {
	
	private static final String LOCATION_AWARE_POSTFIX = "-location-aware";
	private static final String NEWLINE = System.getProperty("line.separator");
	private static final long STEADY_STATE_DURATION = 1000 * 120; // 120 seconds
	
	@Override
	protected String getCloudName() {
		return "ec2" + LOCATION_AWARE_POSTFIX;
	}
	
	// bootstrap with the appropriate credentials and cloud driver class
	@BeforeClass(alwaysRun = true)
	protected void bootstrap(final ITestContext testContext) {
		super.bootstrap(testContext);
	}
	
	// create application to be tested under the build folder
	// create the executor service
	@BeforeMethod
	public void startExecutorService() {	
		cloneApplicaitonRecipeAndInjectLocationAware();
		super.startExecutorService();	
	}
		
	@Override
	@Test(timeOut = DEFAULT_TEST_TIMEOUT * 2, enabled = true)
	public void testPetclinicSimpleScalingRules() throws Exception {	
		
		final String applicationPath = getApplicationPath();
		installApplicationAndWait(applicationPath, getApplicationName());
		
		// check that there are two global instances with zone 'petclinic.tomcat'
		AtLeastOneZoneConfig zones = new AtLeastOneZoneConfigurer().addZone(getAbsoluteServiceName()).create();
		repititiveAssertNumberOfInstances(getAbsoluteServiceName(),zones, 2);

		Set<ExactZonesConfig> puExactZones = getProcessingUnitExactZones(getAbsoluteServiceName());
		ExactZonesConfig zonesToPerformAutoScaling = puExactZones.iterator().next(); // just take the first zone
		
		// increase web traffic for the instance of the specific zone, wait for scale out
		startThreads(zonesToPerformAutoScaling);
		repititiveAssertNumberOfInstances(getAbsoluteServiceName(),zonesToPerformAutoScaling, 2);
		// assert that we reach a steady state. number of instances should not increase any further since 2 is the maximum per zone
		repetitiveNumberOfInstancesHolds(getAbsoluteServiceName(), zonesToPerformAutoScaling, 2, STEADY_STATE_DURATION, TimeUnit.MILLISECONDS);

		// stop web traffic, wait for scale in
		stopThreads();
		repititiveAssertNumberOfInstances(getAbsoluteServiceName(), zonesToPerformAutoScaling, 1);
		// assert that we reach a steady state. number of instances should not decrease any further since 1 is the minimum per zone
		repetitiveNumberOfInstancesHolds(getAbsoluteServiceName(), zonesToPerformAutoScaling, 1, STEADY_STATE_DURATION, TimeUnit.MILLISECONDS);

		LogUtils.log("stopping threads");
		stopThreads();
		uninstallApplicationAndWait(getApplicationName());
	}
	
	@Test(timeOut = DEFAULT_TEST_TIMEOUT * 2, enabled = true)
	public void testScaleOutCancelation() throws Exception {	
		
		LogUtils.log("installing application " + getApplicationName());

		final String applicationPath = getApplicationPath();
		installApplicationAndWait(applicationPath, getApplicationName());
		
		// check that there are two global instances with zone 'petclinic.tomcat'
		AtLeastOneZoneConfig zones = new AtLeastOneZoneConfigurer().addZone(getAbsoluteServiceName()).create();
		repititiveAssertNumberOfInstances(getAbsoluteServiceName(),zones, 2, OPERATION_TIMEOUT, TimeUnit.MILLISECONDS);

		Set<ExactZonesConfig> puExactZones = getProcessingUnitExactZones(getAbsoluteServiceName());
		ExactZonesConfig zonesToPerformAutoScaling = puExactZones.iterator().next(); // just take the first zone

		// Try to start a new machine and then cancel it.
		LogUtils.log("starting threads");
		startThreads(zonesToPerformAutoScaling);
		LogUtils.log("after start threads");
		executor.schedule(new Runnable() {

			@Override
			public void run() {
				LogUtils.log("before stop threads");
				stopThreads();
				LogUtils.log("after threads stop");

			}
		}, 30, TimeUnit.SECONDS);
	
		LogUtils.log("Before repetitive number of instances == 1 on zone " + zonesToPerformAutoScaling);
		repetitiveNumberOfInstancesHolds(getAbsoluteServiceName(),zonesToPerformAutoScaling, 1, 500, TimeUnit.SECONDS);
		LogUtils.log("After repetitive number of instances == 1 on zone " + zonesToPerformAutoScaling);
	
		LogUtils.log("stopping threads");
		stopThreads();
		LogUtils.log("uninstalling application");
		uninstallApplicationAndWait(getApplicationName());
	}
	
	private Set<ExactZonesConfig> getProcessingUnitExactZones(
			String absoluteServiceName) throws Exception {
		List<InstanceDetails> detailss = getInstancesDetails(absoluteServiceName, new AnyZonesConfig());
		
		Set<ExactZonesConfig> zones = new HashSet<ExactZonesConfig>();
		for (InstanceDetails details : detailss) {
			zones.add(details.getAgentZones());
		}
		
		return zones;
	}
	

	@AfterMethod(alwaysRun = true)
	public void cleanup() {
		super.cleanup();
	}
	
	// teardown the cloud
	@AfterClass(alwaysRun = true)
	protected void teardown() {
		super.teardown();
	}
	
	private void cloneApplicaitonRecipeAndInjectLocationAware() {
		try {
			FileUtils.copyDirectory(new File(super.getApplicationPath()), new File(this.getApplicationPath()));
			final File newServiceFile = new File(this.getApplicationPath(),"tomcat/tomcat-service.groovy");
			TestUtils.writeTextFile(newServiceFile,
					"service {" +NEWLINE +
						"\textend \"../../../services/tomcat\""+NEWLINE+
						"\tlocationAware true"+NEWLINE+
						"\tnumInstances 2"+NEWLINE+        // initial total number of instances 2
						"\tminAllowedInstances 1"+NEWLINE+ // total
						"\tmaxAllowedInstances 4"+NEWLINE+ // total
						"\tminAllowedInstancesPerLocation 1"+NEWLINE+ // per zone
						"\tmaxAllowedInstancesPerLocation 2"+NEWLINE+ // per zone
					"}");
		} catch (final IOException e) {
			Assert.fail("Failed to create " + this.getApplicationPath(),e);
		}
	}


	@Override
	protected String getApplicationPath() {
		final File applicationPath = new File(super.getApplicationPath());
		final File newApplicationPath = new File(applicationPath.getParentFile(), applicationPath.getName()+LOCATION_AWARE_POSTFIX);
		return newApplicationPath.getAbsolutePath();
	}

}
