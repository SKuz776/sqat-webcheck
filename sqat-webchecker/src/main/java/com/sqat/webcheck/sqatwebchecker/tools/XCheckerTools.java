package com.sqat.webcheck.sqatwebchecker.tools;


import com.sqat.webcheck.sqatwebchecker.exceptions.HeaderNotFoundException;
import com.sqat.webcheck.sqatwebchecker.exceptions.ResponseIsNullException;
import org.apache.http.Header;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class XCheckerTools {

    public static boolean doesWebsiteContainXFrameOptions (HtmlUnitDriver driver) {
        try {
            Header header = WebsiteTools.getHeader(driver, "X-Frame-Options");
        } catch (ResponseIsNullException | HeaderNotFoundException e) {
            return false;
        }
        return true;
    }

    public static boolean doesWebsiteContainXContentTypeOptions (HtmlUnitDriver driver) {
        try {
            Header header = WebsiteTools.getHeader(driver, "X-Content-Type-Options");
        } catch (ResponseIsNullException | HeaderNotFoundException e) {
            return false;
        }
        return true;
    }

    public static boolean doesWebsiteContainXXSSProtection (HtmlUnitDriver driver) {
        try {
            Header header = WebsiteTools.getHeader(driver, "X-XSS-Protection");
        } catch (ResponseIsNullException | HeaderNotFoundException e) {
            return false;
        }
        return true;
    }

    public static boolean doesWebsiteContainAllXMetaHeaders (HtmlUnitDriver driver) {
        return doesWebsiteContainXContentTypeOptions(driver)
                && doesWebsiteContainXFrameOptions(driver)
                && doesWebsiteContainXXSSProtection(driver);
    }

}
