package com.sqat.webcheck.sqatwebchecker.service;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public interface XCheckerService {
    public boolean doesWebsiteContainXFrameOptions (HtmlUnitDriver driver);
    public boolean doesWebsiteContainXContentTypeOptions (HtmlUnitDriver driver);
    public boolean doesWebsiteContainXXSSProtection (HtmlUnitDriver driver);
    public boolean doesWebsiteContainAllXMetaHeaders (HtmlUnitDriver driver);
}
