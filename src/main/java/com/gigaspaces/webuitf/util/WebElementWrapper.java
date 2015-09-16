package com.gigaspaces.webuitf.util;

import org.openqa.selenium.WebElement;

import java.util.Map;

/**
 * Created by evgenyf on 9/13/2015.
 */
public class WebElementWrapper {

    private WebElement element;
    private Map<String,String> attributesMap;

    public WebElementWrapper( WebElement element, Map<String,String> attributesMap ){
        this.element = element;
        this.attributesMap = attributesMap;

    }

    public WebElement getElement() {
        return element;
    }

    public Map<String, String> getAttributesMap() {
        return attributesMap;
    }
}