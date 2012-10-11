package com.gigaspaces.webuitf.topology.recipes;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.gigaspaces.webuitf.util.SharedContextConstants;

public class RecipesViewPanel {

	private WebDriver driver;

	public RecipesViewPanel(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Gets the current editor content, using a pre-delay of 2000 milliseconds.
	 * 
	 * @return The recipe file content, as contained within the editor
	 */
	public String getContent() {
		return getContent(2000);
	}

	/**
	 * Gets the current editor content, after a pre-delay.
	 * 
	 * @param delayMillis The delay before getting the content.
	 * @return The recipe file content, as contained within the editor
	 */
	public String getContent(long delayMillis) {
		if (delayMillis > 0) {
			try {
				Thread.sleep(delayMillis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String text = (String)js.executeScript("return this." + SharedContextConstants.NS_CODE_MIRROR_RECIPES + ".getValue()");
		return text;
	}
}
