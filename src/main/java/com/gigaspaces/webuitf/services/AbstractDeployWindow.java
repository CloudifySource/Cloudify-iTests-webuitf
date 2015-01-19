package com.gigaspaces.webuitf.services;

import com.gigaspaces.webuitf.WebConstants;
import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * represents a generic deployment window
 * @author elip
 *
 */
public abstract class AbstractDeployWindow implements IDeployWindow {

    protected Selenium selenium;
    WebDriver driver;

    String clusterSchema, numberOfInst, numberOfBackups, maxInstPerVm, maxInstPerMachine, userName, password, isSecured;

    protected AdvancedDeployment advancedDeployment;

    /**
     * constructs an instance with deployment parameters.
     */
    public AbstractDeployWindow (Selenium selenium, WebDriver driver, String isSecured, String userName,
                                 String password, String numberOfInstances,
                                 String numberOfBackups, String clusterSchema, String maxInstPerVM,
                                 String maxInstPerMachine) {
        this.selenium = selenium;
        this.driver = driver;
        this.clusterSchema = clusterSchema;
        this.numberOfInst = numberOfInstances;
        this.numberOfBackups = numberOfBackups;
        this.maxInstPerVm = maxInstPerVM;
        this.maxInstPerMachine = maxInstPerMachine;
        this.userName = userName;
        this.password = password;
        this.isSecured = isSecured;
    }

    public void sumbitDeploySpecs() {
        if (isSecured == "true") {
            selenium.click(WebConstants.Xpath.isSecuredCheckbox);
            selenium.type(WebConstants.ID.passwordInput, password);
            selenium.type(WebConstants.ID.usernameInput, userName);
        }
        selenium.click(WebConstants.Xpath.clusterSchemaCombo);
        selenium.click(WebConstants.Xpath.getPathToComboSelectionInServicesTab(clusterSchema));
        //selenium.type(WebConstants.ID.numberOfInstInput,numberOfInst);
        WebElement numOfInstancesElement = driver.findElement(By.id(WebConstants.ID.numberOfInstInput));

        boolean selected = numOfInstancesElement.isSelected();
        boolean displayed = numOfInstancesElement.isDisplayed();
        boolean enabled = numOfInstancesElement.isEnabled();

        numOfInstancesElement.sendKeys( numberOfInst );


        if (clusterSchema == "partitioned-sync2backup") {
            for (int i = 0 ; i < Integer.parseInt(numberOfBackups) ; i++) {
                selenium.click(WebConstants.Xpath.numberOfBackupsInc);
            }
        }
        selenium.type(WebConstants.ID.maxInsPerVmInput, maxInstPerVm);
        selenium.type(WebConstants.ID.maxInstPerMachineInput, maxInstPerMachine);

    }

    @Override
    public void deploy() {
        WebElement deployButton = driver.findElement(By.id(WebConstants.ID.deployPUButton));
        deployButton.click();
    }

    @Override
    public void closeWindow() {
        WebElement cancelButton = driver.findElement(By.id(WebConstants.ID.cancelDeploymentButton));
        cancelButton.click();
    }

    @Override
    public AdvancedDeployment next() {
        WebElement nextButton = driver.findElement(By.id(WebConstants.ID.nextDeploymentButton));
        nextButton.click();
        if( advancedDeployment == null ){
            advancedDeployment = new AdvancedDeployment( selenium, driver );
        }

        return advancedDeployment;
    }
}