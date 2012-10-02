package test.cli.cloudify.cloud.ec2;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.jclouds.compute.domain.NodeMetadata;
import org.jclouds.compute.domain.NodeState;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import test.cli.cloudify.cloud.JcloudsUtils;
import test.cli.cloudify.cloud.NewAbstractCloudTest;
import test.cli.cloudify.cloud.services.ec2.Ec2CloudService;

import com.gigaspaces.webuitf.util.LogUtils;

import framework.tools.SGTestHelper;
import framework.utils.AssertUtils;
import framework.utils.AssertUtils.RepetitiveConditionProvider;

/**
 * This test makes a bootstrap on ec2 fail by changing the JAVA_HOME path to a bad one in the bootstrap-management.sh file.
 * <p>After the bootstrap fails, the test checks if the management machine was shutdown.
 * 
 * @author nirb
 *
 */
public class BootstrapFailureEc2Test extends NewAbstractCloudTest {

	private Ec2CloudService service;
	private NodeMetadata managementMachine;
	private static final long TIME_TO_TERMINATE_IN_MILLS = 60000;
	private boolean managementMachineTerminated = false;

	@BeforeClass
	public void bootstrap(ITestContext iTestContext) {	
		service = new Ec2CloudService(this.getClass().getName());
		//replace the bootstrap-management with a bad version, to fail the bootstrap.
		File standardBootstrapManagement = new File(service.getPathToCloudFolder() + "/upload", "bootstrap-management.sh");
		File badBootstrapManagement = new File(SGTestHelper.getSGTestRootDir() + "/apps/cloudify/cloud/ec2/bad-bootstrap-management.sh");
		Map<File, File> filesToReplace = new HashMap<File, File>();
		filesToReplace.put(standardBootstrapManagement, badBootstrapManagement);
		service.addFilesToReplace(filesToReplace);
		try {
			super.bootstrap(iTestContext, service);
		} catch (AssertionError ae) {
			LogUtils.log(ae.getMessage());
		}
		
	}

	@Test(timeOut = DEFAULT_TEST_TIMEOUT * 2, enabled = true)
	public void failedBootstrapTest() throws IOException, InterruptedException {
		
		JcloudsUtils.createContext(service);
		Set<? extends NodeMetadata> machines = JcloudsUtils.getServersByName(getService().getMachinePrefix());
		Assert.assertTrue(machines != null);
		managementMachine = machines.iterator().next();

		RepetitiveConditionProvider condition = new RepetitiveConditionProvider() {
			@Override
			public boolean getCondition() {
				Set<? extends NodeMetadata> machines = JcloudsUtils.getServersByName(getService().getMachinePrefix());
				managementMachine = machines.iterator().next();
				if (managementMachine.getState() == NodeState.TERMINATED) {
					managementMachineTerminated = true;
				}
				return managementMachineTerminated;
			}
		};

		AssertUtils.repetitiveAssertTrue("management machine was not terminated", condition, TIME_TO_TERMINATE_IN_MILLS);
	}

	@AfterClass
	public void teardown() {
		JcloudsUtils.closeContext();		
		if (!managementMachineTerminated) {
			super.teardown();			
		}		
	}

	@Override
	protected String getCloudName() {
		return "ec2";
	}

	@Override
	protected boolean isReusableCloud() {
		return false;
	}

	@Override
	protected void customizeCloud() throws Exception {
		// TODO Auto-generated method stub
	}
}
