package com.gigaspaces.webuitf.util;

import org.apache.commons.lang.ClassUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

/**
 * User: eliranm
 * Date: 8/20/13
 * Time: 1:20 PM
 */
public class JsScripts {

    private JsScripts() {
    }

    public static <T> T getApplicationMapNodeProp(WebDriver driver, String nodeName, String propName) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            return (T) js.executeScript(
                    "return this." + SharedContextConstants.NS_GRAPH_APPLICATION_MAP + ".nodes['" + nodeName + "']['" + propName + "']");

/*
            return (T) js.executeScript(
                    "return window.GsUtils.findBy(" + SharedContextConstants.NS_GRAPH_APPLICATION_MAP + ".data.nodes, 'id', " + name + ").id");
*/
        } catch (NoSuchElementException e) {
            return null;
        } catch (WebDriverException e) {
            return null;
        }
    }
}
