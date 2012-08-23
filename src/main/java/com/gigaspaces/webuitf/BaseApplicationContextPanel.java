package com.gigaspaces.webuitf;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.gigaspaces.webuitf.util.AjaxUtils;
import com.gigaspaces.webuitf.util.RepetitiveConditionProvider;
import com.j_spaces.kernel.PlatformVersion;
import com.thoughtworks.selenium.Selenium;

public abstract class BaseApplicationContextPanel extends MainNavigation {

	private AjaxUtils helper;

	public BaseApplicationContextPanel(Selenium selenium, WebDriver driver) {
		super(selenium, driver);
		this.helper = new AjaxUtils(driver);
	}

	protected boolean isMasked(final String tabItemId) {

		WebElement el = driver.findElement(By.id(tabItemId)).findElement(
				By.className(WebConstants.ClassNames.applicationContextPanel));
		return helper.isElementMasked(el);
	}

	/*
	 * div.gs-window-app-context.x-masked-relative.x-masked.x-panel.x-component
	 * div.ext-el-mask-msg No Processing Units are deployed
	 */
	protected boolean isMaskedNoContext(final String tabItemId) {

		String maskMessage;
		if (PlatformVersion.getEdition().equals(
				PlatformVersion.EDITION_CLOUDIFY)) {
			maskMessage = "No services are deployed";
		} else {
			maskMessage = "No processing units are deployed";
		}

		return helper.isElementMaskedWithMessage(maskMessage, true,
				By.id(tabItemId),
				By.className(WebConstants.ClassNames.applicationContextPanel));
	}

	/**
	 * stub! empty implementation! do not use!
	 * 
	 * @param tabItemId
	 * @return
	 */
	protected boolean isMaskedLoading(final String tabItemId) {

		return false;
	}

	protected String getSelectedApplication(final String comboBoxId) {

		String appName = null;

		WebElement el = driver.findElement(By.id(comboBoxId)).findElement(
				By.tagName("input"));
		if (el != null) {
			appName = el.getAttribute("value");
		}

		return appName;
	}

	protected void selectApplication(final String applicationName,
			final String comboBoxId) {

		RepetitiveConditionProvider condition = new RepetitiveConditionProvider() {
			public boolean getCondition() {
				helper.clickWhenPossible(5, TimeUnit.SECONDS,
						By.id(comboBoxId), By.className("icon"));
				List<WebElement> allApps = driver
						.findElement(By.id(comboBoxId)).findElements(
								By.className("visible"));
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
				} else {
					return false;
				}
			}
		};

		AjaxUtils.repetitiveAssertTrue(
				"Application is not present in the applications combo box.",
				condition, 10000);
	}

}
