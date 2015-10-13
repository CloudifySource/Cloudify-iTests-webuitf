package com.gigaspaces.webuitf;

import com.gigaspaces.webuitf.util.AjaxUtils;
import com.gigaspaces.webuitf.util.RepetitiveConditionProvider;
import com.gigaspaces.webuitf.util.components.deployment.AbstractDeployWindow;
import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.WebDriver;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ProcessingUnitDeployWindow extends AbstractDeployWindow {
	
	String puName;

	static public Logger logger = Logger.getLogger(ProcessingUnitDeployWindow.class.getName());

	/**
	 * constructs an instance with deployment parameters. you should not explicitly use thi
	 * constructor. in order to open a deployment window of some sort use the openDeployWindow 
	 * methods from the topology tab instance. 
	 */
	public ProcessingUnitDeployWindow(Selenium selenium, WebDriver driver, String puName,
			String isSecured, String userName, String password,
			String numberOfInstances, String numberOfBackups,
			String clusterSchema, String maxInstPerVM, String maxInstPerMachine) {
		super(selenium, driver, isSecured, userName, password, numberOfInstances,
				numberOfBackups, clusterSchema, maxInstPerVM, maxInstPerMachine);
		this.puName = puName;
	}
	
	@Override
	public void sumbitDeploySpecs() {

		RepetitiveConditionProvider condition = new RepetitiveConditionProvider() {
			public boolean getCondition() {
				try {
					selenium.click(WebConstants.Xpath.deoployFromListRadioButton);
					selenium.click(WebConstants.Xpath.existingPuCombo);
					selenium.click(WebConstants.Xpath.getPathToComboSelection(puName));
					ProcessingUnitDeployWindow.super.sumbitDeploySpecs();
				}
				catch( Exception exc ){
					logger.log(Level.WARNING, exc.toString(), exc);
					return false;
				}

				return true;
			}
		};
		AjaxUtils.repetitiveAssertTrue( "Failed to invoke sumbitDeploySpecs()", condition, 15 * 1000 );
	}

    @Override
    public AbstractDeployWindow prev() {
        return this;
    }
}