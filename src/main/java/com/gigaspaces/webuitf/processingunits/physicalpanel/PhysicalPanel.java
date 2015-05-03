package com.gigaspaces.webuitf.processingunits.physicalpanel;

import com.gigaspaces.webuitf.util.AjaxUtils;
import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.WebDriver;

/**
 * This class is a mapping of the Physical Tabular tab in the topology tab
 * It offers methods for all data retrieval possible in this panel
 * @author elip
 *
 */
public class PhysicalPanel {

    private final Selenium selenium;
    private final WebDriver driver;
    private final AjaxUtils helper;

	public PhysicalPanel(Selenium selenium, WebDriver driver) {
        this.selenium = selenium;
        this.driver = driver;
        this.helper = new AjaxUtils(driver);
	}
	
	/**
	 * @param name - host name to be retrieved
	 * @return a table row of the physical tab containing all possible information about a specific host
	 */
	public HostData getHostData(String name) {
		HostData host = new HostData(name, driver);
		if (host.getName() != null) return host;
		return null;
	}
}
