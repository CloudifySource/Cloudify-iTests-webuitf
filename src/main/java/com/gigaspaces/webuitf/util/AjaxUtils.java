package com.gigaspaces.webuitf.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;

import com.google.common.base.Predicate;
import com.thoughtworks.selenium.Selenium;

public class AjaxUtils {

    private Logger _logger = Logger.getLogger(AjaxUtils.class.getName());

    private WebDriver driver;
    private Selenium selenium;

    public static int ajaxWaitingTime = 5;

    /**
     * Default initialization: invoke only when using methods that don't require the use of
     * a {@link WebDriver} or a {@link Selenium} instance.
     */
    public AjaxUtils() {
    }

    public AjaxUtils(WebDriver driver) {
        this(driver, null);
    }

    public AjaxUtils(WebDriver driver, Selenium selenium) {
        this.driver = driver;
        this.selenium = selenium;
    }

    /**
     * Creates an instance with the specified driver instance, and can be
     * used to instantiate a {@link WebDriverBackedSelenium} instance, if no
     * selenium instance is available.
     *
     * @param driver                        The used driver.
     * @param createWebDriverBackedSelenium {@code true} to create a
     *                                      {@link WebDriverBackedSelenium} instance from the passed driver,
     *                                      {@code false} otherwise.
     */
    public AjaxUtils(WebDriver driver, boolean createWebDriverBackedSelenium) {
        this(driver);
        if (createWebDriverBackedSelenium) {
            this.selenium = new WebDriverBackedSelenium(driver, driver.getCurrentUrl());
        }
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setSelenium(Selenium selenium) {
        this.selenium = selenium;
    }


    /**
     * Waits for an element to be available in DOM before retrieving its inner text.
     *
     * @param timeout  The timeout.
     * @param timeUnit The time unit.
     * @param bys      Element lookup.
     * @return The extracted text. If the element was found and text was extracted, the returned
     *         string value will contain the extracted text, otherwise (element not exist, or
     *         a stale reference returned by the driver) this method will fail silently
     *         and simply return an empty string.
     */
    public String waitForTextToBeExctractable(int timeout, TimeUnit timeUnit, final By... bys) {

        final StringBuilder sb = new StringBuilder();

        FluentWait<By> fluentWait = new FluentWait<By>(bys[0]);
        fluentWait.pollingEvery(100, TimeUnit.MILLISECONDS);
        fluentWait.withTimeout(timeout, timeUnit);
        fluentWait.until(new Predicate<By>() {
            public boolean apply(By by) {
                try {
                    WebElement element = driver.findElement(bys[0]);
                    for (int i = 1; i < bys.length; i++) {
                        element = element.findElement(bys[i]);
                    }
                    sb.append(element.getText());
                    return true;
                } catch (NoSuchElementException ex) {
                    return true;
                } catch (StaleElementReferenceException ex) {
                    return true;
                }
            }
        });

        return sb.toString();
    }

    public void clickWhenPossible(int timeout, TimeUnit timeUnit, final By... bys) {

        FluentWait<By> fluentWait = new FluentWait<By>(bys[0]);
        fluentWait.pollingEvery(50, TimeUnit.MILLISECONDS);
        fluentWait.withMessage("Could not click on " + Arrays.toString(bys));
        fluentWait.withTimeout(timeout, timeUnit);
        fluentWait.until(new Predicate<By>() {
            public boolean apply(By by) {
                try {
                    _logger.info("Before find first element [" + bys[0] + "]");
                    WebElement element = waitForElement(bys[0], 10);
                    _logger.info("After find first element " + element);
                    for (int i = 1; i < bys.length; i++) {
                        _logger.info("Before find element [" + bys[i] + "]");
                        element = element.findElement(bys[i]);
                    }

                    _logger.info("After for element and before if, element=" + element);
                    if (element != null) {
                        _logger.info("Before click");
                        element.click();
                        _logger.info("After click");
                        return true;
                    }
                    return false;
                } catch (NoSuchElementException ex) {
                    _logger.info("caught a NoSuchElementException while trying to click on element");
                    _logger.info("trying again");
                    return false;
                } catch (StaleElementReferenceException ex) {
                    _logger.info("caught a StaleElementReferenceException while trying to click on element");
                    _logger.info("trying again");
                    return false;
                } catch (WebDriverException e) {
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
            } else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                }
            }
        }
        if (condition.getCondition() == false) {
            throw new TimeoutException(message);
        }
    }

    public WebElement waitForElement(final By by, int timeoutInSeconds) {
        By[] bys = {by};
        return waitForElement(TimeUnit.SECONDS, timeoutInSeconds, bys);
    }

    public WebElement waitForElement(TimeUnit timeUnit, int timeout, final By... bys) {

        final AtomicReference<WebElement> atEl = new AtomicReference<WebElement>();

        FluentWait<By> fluentWait = new FluentWait<By>(bys[0]);
        fluentWait.pollingEvery(100, TimeUnit.MILLISECONDS);
        fluentWait.withTimeout(timeout, timeUnit);
        fluentWait.until(new Predicate<By>() {
            public boolean apply(By by) {
                try {
                    WebElement element = driver.findElement(bys[0]);
                    for (int i = 1; i < bys.length; i++) {
                        element = element.findElement(bys[i]);
                    }
                    atEl.set(element);
                    return true;
                } catch (NoSuchElementException ex) {
                    return false;
                } catch (StaleElementReferenceException ex) {
                    return false;
                }
            }
        });

        return atEl.get();
    }

    /**
     * list of elements serached by last by element
     *
     * @param timeUnit
     * @param timeout
     * @param bys
     * @return
     */
    public List<WebElement> waitForElements(TimeUnit timeUnit, int timeout, final By by) {

/*
        final AtomicReference<List<WebElement>> atEl = new AtomicReference<List<WebElement>>();

        FluentWait<By> fluentWait = new FluentWait<By>(bys[0]);
        fluentWait.pollingEvery(100, TimeUnit.MILLISECONDS);
        fluentWait.withTimeout(timeout, timeUnit);
        fluentWait.until(new Predicate<By>() {
            public boolean apply(By by) {
                try {
                    // fetch an element with the first By locator
                    WebElement element = driver.findElement(bys[0]);
                    List<WebElement> elementsTemp = new ArrayList<WebElement>();
                    List<WebElement> elementsResult = new ArrayList<WebElement>();
                    // for each subsequent By locator
                    for (int i = 1; i < bys.length; i++) {
                        System.out.println("- - - index: " + i);
                        // there is more than 1 locator, and this is the item before the last one -
                        // put all found elements in a list
                        if (bys.length > 1 && i == bys.length - 2) {
                            System.out.println("- - - if 1");
                            List<WebElement> elements = element.findElements(bys[i]);
                            elementsTemp.addAll(elements);
                        }
                        // (there is only one locator, or there is more than one but this is not
                        // the item before the last one) and this is the last item - put all elements
                        // from the temporary list in the result list
                        else if (i == bys.length - 1) {
                            System.out.println("- - - if 2");
                            for (WebElement el : elementsTemp) {
                                elementsResult.addAll(el.findElements(bys[i]));
                            }
                        }
                        //
                        else {
                            System.out.println("- - - if 3");
                            element = element.findElement(bys[i]);
                        }
                    }
                    if (elementsResult == null || elementsResult.isEmpty()) {
                        return false;
                    }
                    atEl.set(elementsResult);
                    return true;
                } catch (NoSuchElementException ex) {
                    return false;
                } catch (StaleElementReferenceException ex) {
                    return false;
                } catch (Throwable t) {
                    System.out.println("- - - caught = " + t);
                    return false;
                }
            }
        });

        return atEl.get();
*/

        final AtomicReference<List<WebElement>> atEl = new AtomicReference<List<WebElement>>();

        FluentWait<By> fluentWait = new FluentWait<By>(by);
        fluentWait.pollingEvery(100, TimeUnit.MILLISECONDS);
        fluentWait.withTimeout(10, TimeUnit.SECONDS);
        fluentWait.until(new Predicate<By>() {
            public boolean apply(By by) {
                try {
                    List<WebElement> element = driver.findElements(by);
                    if (element == null) { // TODO add this also in the old waitForElement
                        return false;
                    }
                    atEl.set(element);
                    return true;
                } catch (NoSuchElementException ex) {
                    return false;
                } catch (StaleElementReferenceException ex) {
                    return false;
                }
            }
        });

        return atEl.get();

    }

    public void waitForElementToDisappear(TimeUnit timeUnit, int timeout, final By... bys) {

        FluentWait<By> fluentWait = new FluentWait<By>(bys[0]);
        fluentWait.pollingEvery(100, TimeUnit.MILLISECONDS);
        fluentWait.withTimeout(timeout, timeUnit);
        fluentWait.until(new Predicate<By>() {
            public boolean apply(By by) {
                try {
                    WebElement element = driver.findElement(bys[0]);
                    for (int i = 1; i < bys.length; i++) {
                        element = element.findElement(bys[i]);
                    }
                    return false;
                } catch (NoSuchElementException ex) {
                    return true;
                } catch (StaleElementReferenceException ex) {
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
                } catch (StaleElementReferenceException e) {
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

    public String retrieveAttribute(By by, String attribute, WebDriver driver) throws ElementNotVisibleException {
        double seconds = 0;
        while (seconds < ajaxWaitingTime) {
            try {
                WebElement element = driver.findElement(by);
                return element.getAttribute(attribute);
            } catch (StaleElementReferenceException e) {
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
            } catch (StaleElementReferenceException e) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                seconds += 0.1;
            }
        }
        throw new ElementNotVisibleException("Could not retrieve attribute from DOM");
    }

    /**
     * Checks the masking state of an element.
     *
     * @return {@code true} if the element is masked, {@code false} otherwise.
     */
    public boolean isElementMasked(WebElement el) {
        String classNameValue = retrieveAttribute(el, "class");
        return classNameValue.contains("x-masked");
    }

    /**
     * Utilizes {@link #isElementMasked(WebElement)} as well as tests against
     * a specific mask message.
     *
     * @return {@code true} if the element is masked and the mask contains
     *         the passed text as a message, {@code false} otherwise.
     */
    public boolean isElementMaskedWithMessage(String maskMessage, boolean ignoreCase, By... bys) {

        WebElement el = waitForElement(TimeUnit.SECONDS, 5, bys);

        if (!isElementMasked(el)) {
            return false;
        }

        int length = bys.length;
        By[] msgBys = new By[length + 1];
        System.arraycopy(bys, 0, msgBys, 0, length);
        msgBys[msgBys.length - 1] = By.className("ext-el-mask-msg");

        String extractedText = waitForTextToBeExctractable(5, TimeUnit.SECONDS, msgBys);

        boolean messageFound;
        if (ignoreCase) {
            messageFound = extractedText.equalsIgnoreCase(maskMessage);
        } else {
            messageFound = extractedText.equals(maskMessage);
        }

        return messageFound;
    }

}
