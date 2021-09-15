package com.sqat.webcheck.sqatwebchecker.service.impl;

import com.sqat.webcheck.sqatwebchecker.service.SeoService;
import com.sqat.webcheck.sqatwebchecker.tools.SeoTools;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SeoServiceImpl implements SeoService {

    @Override
    public List<WebElement> getAllMetaTags(HtmlUnitDriver driver) {
        return SeoTools.getAllMetaTags(driver);
    }

    @Override
    public boolean doesWebsiteContainAllSeoMeta(HtmlUnitDriver driver) {
        return SeoTools.doesWebsiteContainAllSeoMeta(driver);
    }

    @Override
    public List<String> whichSeoMetaTagsAreMissing(HtmlUnitDriver driver) {
        return SeoTools.whichSeoMetaTagsAreMissing(driver);
    }

}
