package test.cli.cloudify.cloud.services.azure;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.cloudifysource.esc.driver.provisioning.azure.client.MicrosoftAzureRestClient;
import org.cloudifysource.esc.driver.provisioning.azure.model.ConfigurationSet;
import org.cloudifysource.esc.driver.provisioning.azure.model.ConfigurationSets;
import org.cloudifysource.esc.driver.provisioning.azure.model.Deployment;
import org.cloudifysource.esc.driver.provisioning.azure.model.Deployments;
import org.cloudifysource.esc.driver.provisioning.azure.model.HostedService;
import org.cloudifysource.esc.driver.provisioning.azure.model.HostedServices;
import org.cloudifysource.esc.driver.provisioning.azure.model.NetworkConfigurationSet;
import org.cloudifysource.esc.driver.provisioning.azure.model.Role;
import org.openqa.selenium.TimeoutException;

import test.cli.cloudify.cloud.services.AbstractCloudService;

import com.gigaspaces.webuitf.util.LogUtils;

import framework.tools.SGTestHelper;
import framework.utils.IOUtils;

public class MicrosoftAzureCloudService extends AbstractCloudService {

	private static final String USER_NAME = System.getProperty("user.name");

	private final MicrosoftAzureRestClient azureClient;
	private static final String AZURE_SUBSCRIPTION_ID = "3226dcf0-3130-42f3-b68f-a2019c09431e";
	private static final String PATH_TO_PFX = SGTestHelper.getSGTestRootDir() + "/apps/cloudify/cloud/azure/azure-cert.pfx";
	private static final String PFX_PASSWORD = "1408Rokk";

	private static final String ADDRESS_SPACE = "10.4.0.0/16";

	private static final long ESTIMATED_SHUTDOWN_TIME = 5 * 60 * 1000;

	private static final long SCAN_INTERVAL = 10 * 1000; // 10 seconds. long time since it takes time to shutdown the machine

	private static final long SCAN_TIMEOUT = 5 * 60 * 1000; // 5 minutes

	public MicrosoftAzureCloudService(String uniqueName) {
		super(uniqueName, "azure");
		azureClient = new MicrosoftAzureRestClient(AZURE_SUBSCRIPTION_ID, 
				PATH_TO_PFX, PFX_PASSWORD, 
				null, null, null);
	}


	@Override
	public void injectServiceAuthenticationDetails() throws IOException {
		copyCustomCloudConfigurationFileToServiceFolder();
		copyPrivateKeyToUploadFolder();
		final Map<String, String> propsToReplace = new HashMap<String, String>();
		propsToReplace.put("cloudify_agent_", this.machinePrefix.toLowerCase() + "cloudify-agent");
		propsToReplace.put("cloudify_manager", this.machinePrefix.toLowerCase() + "cloudify-manager");
		propsToReplace.put("ENTER_SUBSCRIPTION_ID", AZURE_SUBSCRIPTION_ID);
		propsToReplace.put("ENTER_USER_NAME", USER_NAME);
		propsToReplace.put("ENTER_PASSWORD", PFX_PASSWORD);
		propsToReplace.put("ENTER_AVAILABILITY_SET", USER_NAME);
		propsToReplace.put("ENTER_DEPLOYMENT_SLOT", "Staging");
		propsToReplace.put("ENTER_PFX_FILE", "azure-cert.pfx");
		propsToReplace.put("ENTER_PFX_PASSWORD", PFX_PASSWORD);
		propsToReplace.put("ENTER_VIRTUAL_NETWORK_SITE_NAME", USER_NAME + "networksite");
		propsToReplace.put("ENTER_ADDRESS_SPACE", ADDRESS_SPACE);
		propsToReplace.put("ENTER_AFFINITY_GROUP", USER_NAME + "cloudifyaffinity");
		propsToReplace.put("ENTER_LOCATION", "East US");
		propsToReplace.put("ENTER_STORAGE_ACCOUNT", USER_NAME + "cloudifystorage");
		IOUtils.replaceTextInFile(getPathToCloudGroovy(), propsToReplace);	
	}

	@Override
	public String getUser() {
		return "sgtest";
	}

	@Override
	public String getApiKey() {
		throw new UnsupportedOperationException("Microsoft Azure Cloud Driver does not have an API key concept. this method should have never been called");
	}

	@Override
	public void beforeBootstrap() throws Exception {		

	}

	@Override
	public boolean scanLeakedAgentNodes() {
		if (azureClient == null) {
			LogUtils.log("Microsoft Azure client was not initialized, therefore a bootstrap never took place, and no scan is needed.");
			return true;
		}
		
		long scanEndTime = System.currentTimeMillis() + SCAN_TIMEOUT;

		try {

			List<String> leakingAgentNodesPublicIps = new ArrayList<String>();

			HostedServices listHostedServices = azureClient.listHostedServices();
			Deployments deploymentsBeingDeleted = new Deployments();

			do {
				if (System.currentTimeMillis() > scanEndTime) {
					throw new TimeoutException("Timed out waiting for deleting nodes to finish. last status was : " + deploymentsBeingDeleted);
				}
				Thread.sleep(SCAN_INTERVAL);
				LogUtils.log("waiting for all deployments to reach a non 'Deleting' state");
				for (HostedService hostedService : listHostedServices) {
					List<Deployment> deploymentsForHostedSerice = azureClient.getHostedService(hostedService.getServiceName(), true).getDeployments().getDeployments();
					if (deploymentsForHostedSerice.size() > 0) {
						Deployment deployment = deploymentsForHostedSerice.get(0); // each hosted service will have just one deployment.
						if (deployment.getStatus().equals("Deleting")) {
							LogUtils.log("Found a deployment with name : " + deployment.getName() + " and status : " + deployment.getStatus());
							deploymentsBeingDeleted.getDeployments().add(deployment);
						}
					}
				}

			}

			while (deploymentsBeingDeleted.getDeployments().size() != 0);


			// now all deployment have reached a steady state.
			// scan again to find out if there are any agents still running

			LogUtils.log("scanning all remaining hosted services for running agent nodes");
			for (HostedService hostedService : listHostedServices) {
				List<Deployment> deploymentsForHostedSerice = azureClient.getHostedService(hostedService.getServiceName(), true).getDeployments().getDeployments();
				if (deploymentsForHostedSerice.size() > 0) {
					Deployment deployment = deploymentsForHostedSerice.get(0); // each hosted service will have just one deployment.
					Role role = deployment.getRoleList().getRoles().get(0);
					String hostName = role.getRoleName(); // each deployment will have just one role.
					if (hostName.contains("agent")) {
						String publicIpFromDeployment = getPublicIpFromDeployment(deployment);
						LogUtils.log("Found an agent with public ip : " + publicIpFromDeployment + " and hostName " + hostName);
						leakingAgentNodesPublicIps.add(publicIpFromDeployment);
					}
				}
			}


			if (!leakingAgentNodesPublicIps.isEmpty()) {
				for (String ip : leakingAgentNodesPublicIps) {
					LogUtils.log("attempting to kill agent node : " + ip);
					long endTime = System.currentTimeMillis() + ESTIMATED_SHUTDOWN_TIME;
					try {
						azureClient.deleteVirtualMachineByIp(ip, false, endTime);
					} catch (final Exception e) {
						LogUtils.log("Failed deleting node with ip : " + ip + ". reason --> " + e.getMessage());
					}
				}
				return false;
			} else {
				return true;
			}
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}

	} 

	private String getPublicIpFromDeployment(Deployment deployment) {		
		String publicIp = null;
		Role role = deployment.getRoleList().getRoles().get(0);
		String hostName = role.getRoleName();
		if (hostName.contains("agent")) {
			ConfigurationSets configurationSets = role.getConfigurationSets();
			for (ConfigurationSet configurationSet : configurationSets) {
				if (configurationSet instanceof NetworkConfigurationSet) {
					NetworkConfigurationSet networkConfigurationSet = (NetworkConfigurationSet) configurationSet;
					publicIp = networkConfigurationSet.getInputEndpoints()
							.getInputEndpoints().get(0).getvIp();
				}
			}
		}
		return publicIp;		
	}

	private void copyCustomCloudConfigurationFileToServiceFolder() throws IOException {

		// copy custom cloud driver configuration to test folder
		String cloudServiceFullPath = SGTestHelper.getBuildDir() + "/tools/cli/plugins/esc/" + this.getServiceFolder();

		File originalCloudDriverConfigFile = new File(cloudServiceFullPath, "azure-cloud.groovy");
		File customCloudDriverConfigFile = new File(SGTestHelper.getSGTestRootDir() + "/apps/cloudify/cloud/azure", "azure-cloud.groovy");

		Map<File, File> filesToReplace = new HashMap<File, File>();
		filesToReplace.put(originalCloudDriverConfigFile, customCloudDriverConfigFile);

		if (originalCloudDriverConfigFile.exists()) {
			originalCloudDriverConfigFile.delete();
		}
		FileUtils.copyFile(customCloudDriverConfigFile, originalCloudDriverConfigFile);

	}

	private void copyPrivateKeyToUploadFolder() throws IOException {
		File pfxFilePath = new File(SGTestHelper.getSGTestRootDir() + "/apps/cloudify/cloud/azure/azure-cert.pfx"); 	
		File uploadDir = new File(getPathToCloudFolder() + "/upload");
		FileUtils.copyFileToDirectory(pfxFilePath, uploadDir);
	}
}
