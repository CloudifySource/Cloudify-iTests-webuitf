package com.gigaspaces.webuitf;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.gigaspaces.webuitf.util.AjaxUtils;
import com.gigaspaces.webuitf.util.RepetitiveConditionProvider;
import com.thoughtworks.selenium.Selenium;

public abstract class BaseApplicationContextPanel extends MainNavigation {

	private AjaxUtils helper;
	
	public BaseApplicationContextPanel(Selenium selenium, WebDriver driver) {
		super(selenium, driver);
		this.helper = new AjaxUtils(driver);
	}

	protected void selectApplication(final String applicationName, final String comboboxId) {
		
		RepetitiveConditionProvider condition = new RepetitiveConditionProvider() {			
			public boolean getCondition() {
				helper.clickWhenPossible(5, TimeUnit.SECONDS, By.id(comboboxId), By.className("icon"));
				List<WebElement> allApps = driver.findElement(By.id(comboboxId)).findElements(By.className("visible"));
				WebElement app = null;
				for (WebElement e : allApps) {
					if (e.getText().equals(applicationName)) {
						app = e;
						break;
					}
				}
				if ((app != null) && app.isDisplayed()) {
					app.click();
					return true;
				}
				else {
					return false;
				}
			}
		};

		AjaxUtils.repetitiveAssertTrue("Application is not present in the applications menu panel", condition,10000);
	}

}
