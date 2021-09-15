package com.sqat.webcheck.sqatwebchecker.service;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.List;

public interface SeoService {

    public List<WebElement> getAllMetaTags (HtmlUnitDriver driver);
    public boolean doesWebsiteContainAllSeoMeta (HtmlUnitDriver driver);
    public List<String> whichSeoMetaTagsAreMissing (HtmlUnitDriver driver);

}
