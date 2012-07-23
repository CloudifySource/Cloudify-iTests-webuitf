package com.gigaspaces.webuitf.util;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;
import com.thoughtworks.selenium.Selenium;

public class AjaxUtils {

	private Logger _logger = Logger.getLogger(AjaxUtils.class.getName());

	private WebDriver driver;
	private Selenium selenium;

	public static int ajaxWaitingTime = 5;

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	public void setSelenium(Selenium selenium) {
		this.selenium = selenium;
	}

	public String waitForTextToBeExctractable(int timeout, TimeUnit timeUnit, final By ... bys) {

		final StringBuilder sb = new StringBuilder();

		FluentWait<By> fluentWait = new FluentWait<By>(bys[0]);
		fluentWait.pollingEvery(100, TimeUnit.MILLISECONDS);
		fluentWait.withTimeout(timeout, timeUnit);
		fluentWait.until(new Predicate<By>() {
			public boolean apply(By by) {
				try {
					WebElement element = driver.findElement(bys[0]);
					for (int i = 1 ; i < bys.length ; i++) {
						element = element.findElement(bys[i]); 
					}
					sb.append(element.getText());
					return true;
				} catch (NoSuchElementException ex) {
					return false;
				}
				catch (StaleElementReferenceException ex) {
					return false;
				}
			}
		});

		return sb.toString();
	}

	public void clickWhenPossible(int timeout, TimeUnit timeUnit, final By ... bys) {

		FluentWait<By> fluentWait = new FluentWait<By>(bys[0]);
		fluentWait.pollingEvery(50, TimeUnit.MILLISECONDS);
		fluentWait.withTimeout(timeout, timeUnit);
		fluentWait.until(new Predicate<By>() {
			public boolean apply(By by) {
				try {
					WebElement element = driver.findElement(bys[0]);
					for (int i = 1 ; i < bys.length ; i++) {
						element = element.findElement(bys[i]); 
					}
					element.click();
					return true;
				} catch (NoSuchElementException ex) {
					_logger.info("caught a NoSuchElementException while trying to click on element");
					_logger.info("trying again");
					return false;
				}
				catch (StaleElementReferenceException ex) {
					_logger.info("caught a StaleElementReferenceException while trying to click on element");
					_logger.info("trying again");
					return false;
				}
				catch (WebDriverException e) {
					_logger.info("caught a WebDriverException while trying to click on element");
					_logger.info("trying again");
					return false;
				}
			}
		});

	}

	public static void repetitiveAssertTrue(String message, RepetitiveConditionProvider condition, long timeoutMilliseconds) {
		long end = System.currentTimeMillis() + timeoutMilliseconds;
		while (System.currentTimeMillis() < end) {
			if (condition.getCondition() == true) {
				return;					
			}
			else {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) { }					
			}
		}
		if (condition.getCondition() == false) {
			throw new TimeoutException(message);
		}
	}  

	public WebElement waitForElement(By by, int timeout) {
		Wait<WebDriver> wait = new WebDriverWait(driver, timeout);
		return wait.until(visibilityOfElementLocated(by));    
	}

	public void waitForElement(TimeUnit timeUnit, int timeout,final By...bys) {

		FluentWait<By> fluentWait = new FluentWait<By>(bys[0]);
		fluentWait.pollingEvery(100, TimeUnit.MILLISECONDS);
		fluentWait.withTimeout(timeout, timeUnit);
		fluentWait.until(new Predicate<By>() {
			public boolean apply(By by) {
				try {
					WebElement element = driver.findElement(bys[0]);
					for (int i = 1 ; i < bys.length ; i++) {
						element = element.findElement(bys[i]); 
					}
					return true;
				} catch (NoSuchElementException ex) {
					return false;
				}
				catch (StaleElementReferenceException ex) {
					return false;
				}
			}
		});
	}

	public void waitForElementToDisappear(TimeUnit timeUnit, int timeout,final By...bys) {

		FluentWait<By> fluentWait = new FluentWait<By>(bys[0]);
		fluentWait.pollingEvery(100, TimeUnit.MILLISECONDS);
		fluentWait.withTimeout(timeout, timeUnit);
		fluentWait.until(new Predicate<By>() {
			public boolean apply(By by) {
				try {
					WebElement element = driver.findElement(bys[0]);
					for (int i = 1 ; i < bys.length ; i++) {
						element = element.findElement(bys[i]); 
					}
					return false;
				} catch (NoSuchElementException ex) {
					return true;
				}
				catch (StaleElementReferenceException ex) {
					return true;
				}
			}
		});
	}


	public ExpectedCondition<WebElement> visibilityOfElementLocated(final By by) {
		return new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver driver) {
				WebElement element = driver.findElement(by);
				try {
					return element.isDisplayed() ? element : null;	
				}
				catch (StaleElementReferenceException e) {
					return null;
				}
			}
		};
	}

	public ExpectedCondition<String> textIsExtractable(final By by) {
		return new ExpectedCondition<String>() {
			public String apply(WebDriver driver) {
				return driver.findElement(by).getText();
			}	
		};
	}

	public ExpectedCondition<WebElement> elementIsNotStale(final By by) {
		return new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(by);
			}	
		};
	}

	public String retrieveAttribute(By by, String attribute,WebDriver driver) throws ElementNotVisibleException {
		double seconds = 0;
		while (seconds < ajaxWaitingTime) {
			try {
				WebElement element = driver.findElement(by);
				return element.getAttribute(attribute);
			}
			catch (StaleElementReferenceException e) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				seconds += 0.5;
			}
		}
		throw new ElementNotVisibleException("Could not retrieve attribute from DOM");
	}


	public String retrieveAttribute(WebElement element, String attribute) throws ElementNotVisibleException {
		double seconds = 0;
		while (seconds < ajaxWaitingTime) {
			try {
				return element.getAttribute(attribute);
			}
			catch (StaleElementReferenceException e) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				seconds += 0.5;
			}
		}
		throw new ElementNotVisibleException("Could not retrieve attribute from DOM");
	}

}
