package com.gigaspaces.webuitf.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

/**
 * Utility to execute native JS scripts.
 *
 * User: eliranm
 * Date: 8/20/13
 * Time: 1:20 PM
 */
public class JsScripts {

    private JsScripts() {
    }

    /**
     * Gets a property value from a node in the application map graph. Note: this method may only be used to return
     * wrapper types, not primitives.
     *
     * @param driver   A web driver to be used as the JavaScript executor.
     * @param nodeId   The node id in the application map.
     * @param propName The node's property name.
     * @param <T>      A return type from the executor, will be inferred automatically.
     * @return The value of the node's property.
     */
    public static <T> T getApplicationMapNodeProp(WebDriver driver, String nodeId, String propName) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {

            return (T) js.executeScript(
                    "return window.GsUtils.findBy(" + SharedContextConstants.NS_GRAPH_APPLICATION_MAP + ".data.nodes, 'id', '" + nodeId + "')['" + propName + "']");
        } catch (NoSuchElementException e) {
            return null;
        } catch (WebDriverException e) {
            return null;
        }
    }
}
