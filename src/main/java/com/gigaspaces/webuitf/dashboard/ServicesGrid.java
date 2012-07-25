package com.gigaspaces.webuitf.dashboard;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.util.AjaxUtils;
import com.gigaspaces.webuitf.util.RepetitiveConditionProvider;
import com.thoughtworks.selenium.Selenium;

public class ServicesGrid {
	
	private Selenium selenium;
	private WebDriver driver;
	
	private InfrastructureServicesGrid resourceGrid;
	private ApplicationServicesGrid modulesGrid;
	private DataReplicationGrid highAvGrid;
	private EDSGrid edsGrid;
	private ApplicationsMenuPanel appPanel;
	
	private AjaxUtils helper = new AjaxUtils();

	public InfrastructureServicesGrid getInfrastructureGrid() {
		return resourceGrid;
	}

	public ApplicationServicesGrid getApplicationServicesGrid() {
		return modulesGrid;
	}

	public DataReplicationGrid getDataReplicationGrid() {
		return highAvGrid;
	}

	public EDSGrid getEdsGrid() {
		return edsGrid;
	}
	
	public ApplicationsMenuPanel getApplicationsMenuPanel() {
		return appPanel;
	}

	public ServicesGrid(Selenium selenium, WebDriver driver) {
		this.selenium = selenium;
		this.driver = driver;
		this.edsGrid = new EDSGrid();
		this.highAvGrid = new DataReplicationGrid();
		this.modulesGrid = new ApplicationServicesGrid();
		this.resourceGrid = new InfrastructureServicesGrid();
		this.appPanel = new ApplicationsMenuPanel();
		helper.setDriver(driver);
		helper.setSelenium(selenium);
	}

	public static ServicesGrid getInstance(Selenium selenium, WebDriver driver) {
		return new ServicesGrid(selenium, driver);
	}
	
	private Icon getIcon(String type) {
		type = type.trim();
		if (type.equals(WebConstants.ID.okIcon)) return Icon.OK;
		if (type.equals(WebConstants.ID.criticalIcon)) return Icon.CRITICAL;
		if (type.equals(WebConstants.ID.warningIcon)) return Icon.ALERT;
		if (type.equals(WebConstants.ID.naIcon)) return Icon.NA;
		if (type.equals(WebConstants.ID.niIcon)) return Icon.NI;
		return null;
	}
	
	public enum Icon {
		OK,CRITICAL,ALERT,NA, NI;
	}
	
	public class ApplicationsMenuPanel {
		
		public void selectApplication(final String applicationName) {	
			RepetitiveConditionProvider condition = new RepetitiveConditionProvider() {			
				public boolean getCondition() {
					return (selenium.isElementPresent(WebConstants.Xpath.getPathToApplicationSelectionButton(applicationName)));
				}
			};
			
			AjaxUtils.repetitiveAssertTrue("Application is not present in the applications menu panel", condition,5000);
			selenium.click(WebConstants.Xpath.getPathToApplicationSelectionButton(applicationName));
		}
		
		public boolean isApplicationPresent(String applicationName) {
			return selenium.isElementPresent(WebConstants.Xpath.getPathToApplicationSelectionButton(applicationName));	
		}

		public void showAllApplications() {
			RepetitiveConditionProvider condition = new RepetitiveConditionProvider() {			
				public boolean getCondition() {
					return (selenium.isElementPresent(WebConstants.Xpath.getPathToApplicationSelectionButton("All Apps")));
				}
			};
			
			AjaxUtils.repetitiveAssertTrue("Failed to show all applications", condition,5000);
			selenium.click(WebConstants.Xpath.getPathToApplicationSelectionButton("All Apps"));
			
		}
	}
	
	public class InfrastructureServicesGrid {
		
		public Hosts getHosts() {
			return new Hosts();
		}
		
		public GSAInst getGSAInst() {
			return new GSAInst();
		}
		
		public GSMInst getGSMInst() {
			return new GSMInst();
		}
		
		public GSCInst getGSCInst() {
			return new GSCInst();
		}
		
		public LUSInst getLUSInst() {
			return new LUSInst();
		}
		
		public ESMInst getESMInst() {
			return new ESMInst();
			
		}
		
		public class Hosts {
			
			public Icon getIcon() {
				String style = helper.retrieveAttribute(By.xpath(WebConstants.Xpath.pathToHosts + WebConstants.Xpath.pathToIconInResourceGrid), "class",driver);
				return ServicesGrid.this.getIcon(style);
			}
			
			public int getCount() {
				return Integer.parseInt(selenium.getText(WebConstants.Xpath.pathToHosts + WebConstants.Xpath.pathToNumberInResourceGrid));	
			}
			
			public String getName() {
				return selenium.getText(WebConstants.Xpath.pathToHosts + WebConstants.Xpath.pathToNameInResourceGrid);	
			}
			
		}
		
		public class GSAInst {

			public Icon getIcon() {
				String style = helper.retrieveAttribute(By.xpath(WebConstants.Xpath.pathToGSA + WebConstants.Xpath.pathToIconInResourceGrid), "class",driver);
				return ServicesGrid.this.getIcon(style);

			}

			public int getCount() {
				return Integer.parseInt(selenium.getText(WebConstants.Xpath.pathToGSA + WebConstants.Xpath.pathToNumberInResourceGrid));
			}
			
			public String getName() {
				return selenium.getText(WebConstants.Xpath.pathToGSA + WebConstants.Xpath.pathToNameInResourceGrid);	
			}

		}
		
		public class ESMInst {

			public Icon getIcon() {
				String style = helper.retrieveAttribute(By.xpath(WebConstants.Xpath.pathToESM + WebConstants.Xpath.pathToIconInResourceGrid), "class",driver);
				return ServicesGrid.this.getIcon(style);

			}

			public int getCount() {
				return Integer.parseInt(selenium.getText(WebConstants.Xpath.pathToESM + WebConstants.Xpath.pathToNumberInResourceGrid));
			}

			public String getName() {
				return selenium.getText(WebConstants.Xpath.pathToESM + WebConstants.Xpath.pathToNameInResourceGrid);	
			}
		}
		
		public class GSMInst {
			
			public Icon getIcon() {
				String style = helper.retrieveAttribute(By.xpath(WebConstants.Xpath.pathToGSM + WebConstants.Xpath.pathToIconInResourceGrid), "class",driver);
				return ServicesGrid.this.getIcon(style);
					
			}

			public int getCount() {
				return Integer.parseInt(selenium.getText(WebConstants.Xpath.pathToGSM + WebConstants.Xpath.pathToNumberInResourceGrid));
			}
			
			public String getName() {
				return selenium.getText(WebConstants.Xpath.pathToGSM + WebConstants.Xpath.pathToNameInResourceGrid);	
			}
		}
		
		public class GSCInst {
			
			public Icon getIcon() {
				String style = helper.retrieveAttribute(By.xpath(WebConstants.Xpath.pathToGSC + WebConstants.Xpath.pathToIconInResourceGrid), "class",driver);
				return ServicesGrid.this.getIcon(style);
					
			}
			
			public int getCount() {
				return Integer.parseInt(selenium.getText(WebConstants.Xpath.pathToGSC + WebConstants.Xpath.pathToNumberInResourceGrid));
			}
			
			public String getName() {
				return selenium.getText(WebConstants.Xpath.pathToGSC + WebConstants.Xpath.pathToNameInResourceGrid);	
			}
			
		}
		
		public class LUSInst {
			
			public Icon getIcon() {
				String style = helper.retrieveAttribute(By.xpath(WebConstants.Xpath.pathToLUS + WebConstants.Xpath.pathToIconInResourceGrid), "class",driver);
				return ServicesGrid.this.getIcon(style);
					
			}
			
			public int getCount() {
				return Integer.parseInt(selenium.getText(WebConstants.Xpath.pathToLUS + WebConstants.Xpath.pathToNumberInResourceGrid));
			}
			
			public String getName() {
				return selenium.getText(WebConstants.Xpath.pathToLUS + WebConstants.Xpath.pathToNameInResourceGrid);	
			}
		}
		
	}
	
	public class ApplicationServicesGrid {
		
		public WebModule getWebModule() {
			return new WebModule();
		}

		public StatefullModule getStatefullModule() {
			return new StatefullModule();
		}

		public StatelessModule getStatelessModule() {	
			return new StatelessModule();
		}

		public MirrorModule getMirrorModule() {
			return new MirrorModule();
		}

		public LoadBalancerModule getLoadBalancerModule() {		
			return new LoadBalancerModule();
		}

		public AppServerModule getAppServerModule() {
			return new AppServerModule();
		}

		public WebServerModule getWebServerModule() {
			return new WebServerModule();
		}

		public EsbServerModule getEsbServerModule() {
			return new EsbServerModule();
		}

		public SecurityServerModule getSecurityServerModule() {
			return new SecurityServerModule();
		}

		public DatabaseModule getDatabaseModule() {	
			return new DatabaseModule();
		}

		public NoSqlDbModule getNoSqlDbModule() {
			return new NoSqlDbModule();
		}

		public MessageBusModule getMessageBusModule() {
			return new MessageBusModule();
		}
		
		public class WebModule {
			
			private static final String RELATIVE_PATH_TO_OPS = "/table/tbody/tr/td[5]";
			
			public Icon getIcon() {
				if (selenium.isElementPresent(WebConstants.ID.moduleWeb)) {
					String style = helper.retrieveAttribute(By.xpath(WebConstants.Xpath.pathToWebModule + WebConstants.Xpath.pathToIconInModulesGrid), "class",driver);
					return ServicesGrid.this.getIcon(style);
				}
				else return null;
				
			}
			
			public int getCount() {
				String xpath = WebConstants.Xpath.pathToWebModule + WebConstants.Xpath.pathToNumberInModulesGrid;
				if (selenium.isElementPresent(xpath)) {
					return Integer.parseInt(selenium.getText(xpath));
				}
				return 0;
			}
			
			
			public double getRequestsPerSecond() {
				String xpath = WebConstants.Xpath.pathToWebModule + RELATIVE_PATH_TO_OPS;
				String ops = helper.waitForTextToBeExctractable(5, TimeUnit.SECONDS, By.xpath(xpath));
				return Double.valueOf(ops.split(" ")[0].substring(1));
				
			}			
		}
		
		public class StatefullModule {
			
			private static final String RELATIVE_PATH_TO_OPS = "/table/tbody/tr/td[5]";
			
			public Icon getIcon() {
				if (selenium.isElementPresent(WebConstants.ID.moduleStateful)) {
					String style = helper.retrieveAttribute(By.xpath(WebConstants.Xpath.pathToStatefullModule + WebConstants.Xpath.pathToIconInModulesGrid), "class",driver);
					return ServicesGrid.this.getIcon(style);
				}
				else return null;
			}
			
			public int getCount() {
				String xpath = WebConstants.Xpath.pathToStatefullModule + WebConstants.Xpath.pathToNumberInModulesGrid;
				if (selenium.isElementPresent(xpath)) {
					return Integer.parseInt(selenium.getText(xpath));
				}
				return 0;
			}
			
			public double getOperationsPerSecond() {
				String xpath = WebConstants.Xpath.pathToStatefullModule + RELATIVE_PATH_TO_OPS;
				String ops = helper.waitForTextToBeExctractable(5, TimeUnit.SECONDS, By.xpath(xpath));
				return Double.valueOf(ops.split(" ")[0].substring(1));
				
			}
		}
		
		public class StatelessModule {
			
			public Icon getIcon() {
				if (selenium.isElementPresent(WebConstants.ID.moduleStateless)) {
					String style = helper.retrieveAttribute(By.xpath(WebConstants.Xpath.pathToStatelessModule + WebConstants.Xpath.pathToIconInModulesGrid), "class",driver);
					return ServicesGrid.this.getIcon(style);
				}
				return null;
				
			}
			
			public int getCount() {
				String xpath = WebConstants.Xpath.pathToStatelessModule + WebConstants.Xpath.pathToNumberInModulesGrid;
				if (selenium.isElementPresent(xpath)) {
					return Integer.parseInt(selenium.getText(xpath));
				}
				return 0;
			}
		}
		
		public class MirrorModule {
		
			public Icon getIcon() {
				if (selenium.isElementPresent(WebConstants.ID.moduleMirror)) {
					String style = helper.retrieveAttribute(By.xpath(WebConstants.Xpath.pathToMirrorModule + WebConstants.Xpath.pathToIconInModulesGrid), "class",driver);
					return ServicesGrid.this.getIcon(style);
				}
				return null;
				
			}
			
			public int getCount() {
				String xpath = WebConstants.Xpath.pathToMirrorModule + WebConstants.Xpath.pathToNumberInModulesGrid;
				if (selenium.isElementPresent(xpath)) {
					return Integer.parseInt(selenium.getText(xpath));
				}
				return 0;
			}
		}
		
		public class LoadBalancerModule {
			
			public Icon getIcon() {
				if (selenium.isElementPresent(WebConstants.ID.moduleLoadBalancer)) {
					String style = helper.retrieveAttribute(By.xpath(WebConstants.Xpath.pathToLoadBalancerModule + WebConstants.Xpath.pathToIconInModulesGrid), "class",driver);
					return ServicesGrid.this.getIcon(style);
				}
				return null;
				
			}
			
			public int getCount() {
				String xpath = WebConstants.Xpath.pathToLoadBalancerModule + WebConstants.Xpath.pathToNumberInModulesGrid;
				if (selenium.isElementPresent(xpath)) {
					return Integer.parseInt(selenium.getText(xpath));
				}
				return 0;
			}
		}
		
		public class AppServerModule {
			
			public Icon getIcon() {
				if (selenium.isElementPresent(WebConstants.ID.moduleAppServer)) {
					String style = helper.retrieveAttribute(By.xpath(WebConstants.Xpath.pathToAppServerModule + WebConstants.Xpath.pathToIconInModulesGrid), "class",driver);
					return ServicesGrid.this.getIcon(style);
				}
				return null;
				
			}
			
			public int getCount() {
				String xpath = WebConstants.Xpath.pathToAppServerModule + WebConstants.Xpath.pathToNumberInModulesGrid;
				if (selenium.isElementPresent(xpath)) {
					return Integer.parseInt(selenium.getText(xpath));
				}
				return 0;
			}
		}
		
		public class WebServerModule {
			
			public Icon getIcon() {
				if (selenium.isElementPresent(WebConstants.ID.moduleWebServer)) {
					String style = helper.retrieveAttribute(By.xpath(WebConstants.Xpath.pathToWebServerModule + WebConstants.Xpath.pathToIconInModulesGrid), "class",driver);
					return ServicesGrid.this.getIcon(style);
				}
				return null;
				
			}
			
			public int getCount() {
				String xpath = WebConstants.Xpath.pathToWebServerModule + WebConstants.Xpath.pathToNumberInModulesGrid;
				if (selenium.isElementPresent(xpath)) {
					return Integer.parseInt(selenium.getText(xpath));
				}
				return 0;
			}
		}
		
		public class SecurityServerModule {
			
			public Icon getIcon() {
				if (selenium.isElementPresent(WebConstants.ID.moduleSecurityServer)) {
					String style = helper.retrieveAttribute(By.xpath(WebConstants.Xpath.pathToSecurityServerModule + WebConstants.Xpath.pathToIconInModulesGrid), "class",driver);
					return ServicesGrid.this.getIcon(style);
				}
				return null;
				
			}
			
			public int getCount() {
				String xpath = WebConstants.Xpath.pathToSecurityServerModule + WebConstants.Xpath.pathToNumberInModulesGrid;
				if (selenium.isElementPresent(xpath)) {
					return Integer.parseInt(selenium.getText(xpath));
				}
				return 0;
			}
		}
		
		public class EsbServerModule {
			
			public Icon getIcon() {
				if (selenium.isElementPresent(WebConstants.ID.moduleEsbServer)) {
					String style = helper.retrieveAttribute(By.xpath(WebConstants.Xpath.pathToEsbServerModule + WebConstants.Xpath.pathToIconInModulesGrid), "class",driver);
					return ServicesGrid.this.getIcon(style);
				}
				return null;
				
			}
			
			public int getCount() {
				String xpath = WebConstants.Xpath.pathToEsbServerModule + WebConstants.Xpath.pathToNumberInModulesGrid;
				if (selenium.isElementPresent(xpath)) {
					return Integer.parseInt(selenium.getText(xpath));
				}
				return 0;
			}
		}
		
		public class MessageBusModule {
			
			public Icon getIcon() {
				if (selenium.isElementPresent(WebConstants.ID.moduleMessageBus)) {
					String style = helper.retrieveAttribute(By.xpath(WebConstants.Xpath.pathToMessageBusModule + WebConstants.Xpath.pathToIconInModulesGrid), "class",driver);
					return ServicesGrid.this.getIcon(style);
				}
				else return null;
				
			}
			
			public int getCount() {
				String xpath = WebConstants.Xpath.pathToMessageBusModule + WebConstants.Xpath.pathToNumberInModulesGrid;
				if (selenium.isElementPresent(xpath)) {
					return Integer.parseInt(selenium.getText(xpath));
				}
				return 0;
			}
		}
		
		public class DatabaseModule {
			
			public Icon getIcon() {
				if (selenium.isElementPresent(WebConstants.ID.moduleDatabase)) {
					String style = helper.retrieveAttribute(By.xpath(WebConstants.Xpath.pathToDatabaseModule + WebConstants.Xpath.pathToIconInModulesGrid), "class",driver);
					return ServicesGrid.this.getIcon(style);
				}
				return null;
				
			}
			
			public int getCount() {
				String xpath = WebConstants.Xpath.pathToDatabaseModule + WebConstants.Xpath.pathToNumberInModulesGrid;
				if (selenium.isElementPresent(xpath)) {
					return Integer.parseInt(selenium.getText(xpath));
				}
				return 0;
			}
		}
		
		public class NoSqlDbModule {
			
			public Icon getIcon() {
				if (selenium.isElementPresent(WebConstants.ID.moduleNoSqlDb)) {
					String style = helper.retrieveAttribute(By.xpath(WebConstants.Xpath.pathToNoSqlDbModule + WebConstants.Xpath.pathToIconInModulesGrid), "class",driver);
					return ServicesGrid.this.getIcon(style);
				}
				return null;
				
			}
			
			public int getCount() {
				String xpath = WebConstants.Xpath.pathToNoSqlDbModule + WebConstants.Xpath.pathToNumberInModulesGrid;
				if (selenium.isElementPresent(xpath)) {
					return Integer.parseInt(selenium.getText(xpath));
				}
				return 0;
			}
		}
	}
	
	public class DataReplicationGrid {
		
		public BytesPerSecond getBytesPerSecond() {
			return new BytesPerSecond();
		}
		
		public PacketsPerSecond getPacketsPerSecond() {
			return new PacketsPerSecond();
		}
		
		public class BytesPerSecond {
			
			public Icon getIcon() {
				String style = helper.retrieveAttribute(By.xpath(WebConstants.Xpath.pathTohaBytesPerSecond + WebConstants.Xpath.pathToIconInHighAvGrid), "class",driver);
				return ServicesGrid.this.getIcon(style);
				
			}
			
			public Double getCount() {
				return Double.parseDouble(selenium.getText(WebConstants.Xpath.pathTohaBytesPerSecond + WebConstants.Xpath.pathToCountInHighAvGrid));
				
			}
		}
		
		public class PacketsPerSecond {
			
			public Icon getIcon() {
				String style = helper.retrieveAttribute(By.xpath(WebConstants.Xpath.pathTohaPacketsPerSecond + WebConstants.Xpath.pathToIconInHighAvGrid), "class",driver);
				return ServicesGrid.this.getIcon(style);
				
			}
			
			public Double getCount() {
				return Double.parseDouble(selenium.getText(WebConstants.Xpath.pathTohaPacketsPerSecond + WebConstants.Xpath.pathToCountInHighAvGrid));
				
			}
		}
	}
	
	public class EDSGrid {
		
		public class PacketsPerSecond {
			
			public Icon getIcon() {
				String style = helper.retrieveAttribute(By.xpath(WebConstants.Xpath.pathToEDSPacketsPerSecond + WebConstants.Xpath.pathToIconInEDSGrid), "class",driver);
				return ServicesGrid.this.getIcon(style);
				
			}
			
			public Double getCount() {
				return Double.parseDouble(selenium.getText(WebConstants.Xpath.pathToEDSPacketsPerSecond + WebConstants.Xpath.pathToNumberInEDSGrid));	

				
			}
		}
		
		public class BytesPerSecond {
			
			public Icon getIcon() {
				String style = helper.retrieveAttribute(By.xpath(WebConstants.Xpath.pathToEDSBytesPerSecond + WebConstants.Xpath.pathToIconInEDSGrid), "class",driver);
				return ServicesGrid.this.getIcon(style);
				
			}
			
			public Double getCount() {
				return Double.parseDouble(selenium.getText(WebConstants.Xpath.pathToEDSBytesPerSecond + WebConstants.Xpath.pathToNumberInEDSGrid));	
				
			}
		}
		
		public class OpPerSecond {
			
			public Icon getIcon() {
				String style = helper.retrieveAttribute(By.xpath(WebConstants.Xpath.pathToEDSOpPerSecond + WebConstants.Xpath.pathToIconInEDSGrid), "class",driver);
				return ServicesGrid.this.getIcon(style);
				
			}
			
			public Double getCount() {
				return Double.parseDouble(selenium.getText(WebConstants.Xpath.pathToEDSOpPerSecond + WebConstants.Xpath.pathToNumberInEDSGrid));	
				
			}
		}
		
		public BytesPerSecond getBytesPerSecond() {
			return new BytesPerSecond();
		}
		
		public PacketsPerSecond getPacketsPerSecond() {
			return new PacketsPerSecond();
		}
		
		public OpPerSecond getOpPerSecond() {
			return new OpPerSecond();
		}
	}
	
}
