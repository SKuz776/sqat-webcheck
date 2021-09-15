package com.sqat.webcheck.sqatwebchecker.tools;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;


import java.util.ArrayList;
import java.util.List;

public class SeoTools {

    public static List<WebElement> getAllMetaTags (HtmlUnitDriver driver) {

        return driver.findElementsByTagName("meta");
    }

    public static boolean doesWebsiteContainAllSeoMeta(HtmlUnitDriver driver) {

        boolean containsTitle = false;
        boolean containsDescription = false;
        boolean containsRobots = false;
        boolean containsCharset = false;
        boolean containsViewport = false;

        for (WebElement tag : getAllMetaTags(driver)){
            if (tag.getAttribute("name") != null) {
                containsTitle = tag.getAttribute("name").equals("title") || containsTitle;
                containsDescription = tag.getAttribute("name").equals("description") || containsDescription;
                containsRobots = tag.getAttribute("name").equals("robots") || containsRobots;
                containsCharset = tag.getAttribute("name").equals("charset") || containsCharset;
                containsViewport = tag.getAttribute("name").equals("viewport") || containsViewport;
            }
        }
        return containsTitle && containsDescription && containsRobots && containsCharset && containsViewport;
    }

    public static List<String> whichSeoMetaTagsAreMissing(HtmlUnitDriver driver) {

        boolean containsTitle = false;
        boolean containsDescription = false;
        boolean containsRobots = false;
        boolean containsCharset = false;
        boolean containsViewport = false;

        ArrayList<String> results = new ArrayList<>();

        for (WebElement tag : getAllMetaTags(driver)){
            if (tag.getAttribute("name") != null) {
                containsTitle = tag.getAttribute("name").equals("title") || containsTitle;
                containsDescription = tag.getAttribute("name").equals("description") || containsDescription;
                containsRobots = tag.getAttribute("name").equals("robots") || containsRobots;
                containsCharset = tag.getAttribute("name").equals("charset") || containsCharset;
                containsViewport = tag.getAttribute("name").equals("viewport") || containsViewport;
            }
        }

        if (!containsTitle) {
            results.add("title");
        } if (!containsDescription) {
            results.add("description");
        } if (!containsRobots) {
            results.add("robots");
        } if (!containsCharset) {
            results.add("charset");
        } if (!containsViewport) {
            results.add("viewport");
        }

        return results;
    }

}
