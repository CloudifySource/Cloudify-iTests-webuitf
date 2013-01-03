package com.gigaspaces.webuitf.topology.physicalpanel;

import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.util.AjaxUtils;

public class MorePopUp {
	
	private String hostName;
	private WebDriver driver;
	private WebElement morePopup;
	protected Logger logger = Logger.getLogger(this.getClass().getName());

	public MorePopUp(String name, WebDriver driver) {
		this.driver = driver;
		this.hostName = name;
	}
	
	public void show(String ip) {
		
		int seconds = 0;
		while (seconds < AjaxUtils.ajaxWaitingTime) {
			try {
				WebElement moreButton = driver.findElement(By.id(WebConstants.ID.getMoreButtonId(ip)));
				moreButton.click();
				morePopup = driver.findElement(By.id(WebConstants.ID.morePopup));
				break;
			}
			catch (StaleElementReferenceException e) {
				logger.severe("Failed to discover element due to statistics update, retyring...");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				seconds++;
			}
			catch (ElementNotVisibleException e) {
				logger.severe("Failed to discover element due to statistics update, retyring...");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				seconds++;
			}
		}
		
	}
	
	public Inst getInst(int i) {
		
		WebElement instRow = morePopup.findElement(By.xpath(WebConstants.Xpath.getPathToInst(i)));
		List<WebElement> list = instRow.findElements(By.tagName("td"));
		String puName = list.get(1).getText();
		String instName = list.get(2).getText();
		return new Inst(instName, puName);
		
	}
	
	public class Inst {
		
		private String instName;
		private String puName;
		
		public Inst(String instName, String puName) {
			this.instName = instName;
			this.puName = puName;
		}
		
		public String getInstName() {
			return instName;
		}
		
		public String getPuName() {
			return puName;
		}
		
	}

}
