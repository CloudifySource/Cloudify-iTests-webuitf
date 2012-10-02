package test.cli.cloudify;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openspaces.admin.pu.ProcessingUnit;
import org.openspaces.admin.pu.ProcessingUnitInstance;
import org.openspaces.pu.service.ServiceDetails;
import org.openspaces.pu.service.ServiceMonitors;
import org.testng.annotations.Test;

import test.usm.USMTestUtils;

import org.cloudifysource.dsl.internal.CloudifyConstants;
import org.cloudifysource.dsl.internal.DSLException;
import org.cloudifysource.dsl.internal.ServiceReader;
import org.cloudifysource.dsl.internal.packaging.PackagingException;
import org.cloudifysource.dsl.utils.ServiceUtils;


public class LifecycleDetailsMonitorMapClosureTest extends AbstractLocalCloudTest{

	final private String RECIPE_DIR_PATH = CommandTestUtils
	.getPath("apps/USM/usm/simple");
	private static final String EXPECTED_DETAILS_FIELDS[] = { "1", "2"};
	private static final String EXPECTED_MONITORS_FIELDS[] = {"3", "4" };

	@Test(timeOut = DEFAULT_TEST_TIMEOUT, groups = "1", enabled = true)
	public void testDetailsAndMonitorsMap() throws FileNotFoundException, PackagingException, IOException, InterruptedException, DSLException{
		installService();
		ProcessingUnitInstance puInstance = getPUInstance();
		checkDetails(puInstance);
		checkMonitors(puInstance);
	}
	
	private ProcessingUnitInstance getPUInstance() {
		ProcessingUnit pu = this.admin.getProcessingUnits().waitFor(
				ServiceUtils.getAbsolutePUName("default", "simple"), 30, TimeUnit.SECONDS);
		assertNotNull("Could not find processing unit for installed service",
				pu);
		boolean found = pu.waitFor(1, 30, TimeUnit.SECONDS);
		assertTrue("Could not find instance of deployed service", found);

		ProcessingUnitInstance pui = pu.getInstances()[0];
		return pui;
		
	}
	
	private void installService() throws PackagingException, IOException, InterruptedException, DSLException {
		File serviceDir = new File(RECIPE_DIR_PATH);
		ServiceReader.getServiceFromDirectory(serviceDir, CloudifyConstants.DEFAULT_APPLICATION_NAME).getService();
		runCommand("connect " + this.restUrl + ";install-service --verbose " + RECIPE_DIR_PATH);
		assertTrue("Could not find LUS of local cloud", admin.getLookupServices().waitFor(1, 10, TimeUnit.SECONDS));
		assertTrue("USM Service State is not RUNNING", USMTestUtils.waitForPuRunningState("default.simple", 20, TimeUnit.SECONDS, admin));
	}
	
	private void checkDetails(ProcessingUnitInstance pui) {
		Collection<ServiceDetails> allServiceDetails = pui
				.getServiceDetailsByServiceId().values();
		Map<String, Object> allDetails = new HashMap<String, Object>();
		for (ServiceDetails serviceDetails : allServiceDetails) {
			allDetails.putAll(serviceDetails.getAttributes());
		}
		for (String detailKey : EXPECTED_DETAILS_FIELDS) {
			assertTrue("Missing details entry: " + detailKey,
					allDetails.containsKey(detailKey));
		}
	}
	
	private void checkMonitors(ProcessingUnitInstance pui) {
		// verify monitors
		Collection<ServiceMonitors> allSserviceMonitors = pui.getStatistics()
				.getMonitors().values();
		Map<String, Object> allMonitors = new HashMap<String, Object>();
		for (ServiceMonitors serviceMonitors : allSserviceMonitors) {
			allMonitors.putAll(serviceMonitors.getMonitors());
		}

		for (String monitorKey : EXPECTED_MONITORS_FIELDS) {
			assertTrue("Missing Monitor Key: " + monitorKey,
					allMonitors.containsKey(monitorKey));
		}
	}
}
