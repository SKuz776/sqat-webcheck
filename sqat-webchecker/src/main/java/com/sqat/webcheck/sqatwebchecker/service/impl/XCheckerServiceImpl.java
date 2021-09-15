package com.sqat.webcheck.sqatwebchecker.service.impl;

import com.sqat.webcheck.sqatwebchecker.service.XCheckerService;
import com.sqat.webcheck.sqatwebchecker.tools.XCheckerTools;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.stereotype.Service;

@Service
public class XCheckerServiceImpl implements XCheckerService {
    @Override
    public boolean doesWebsiteContainXFrameOptions(HtmlUnitDriver driver) {
        return XCheckerTools.doesWebsiteContainXFrameOptions(driver);
    }

    @Override
    public boolean doesWebsiteContainXContentTypeOptions(HtmlUnitDriver driver) {
        return XCheckerTools.doesWebsiteContainXContentTypeOptions(driver);
    }

    @Override
    public boolean doesWebsiteContainXXSSProtection(HtmlUnitDriver driver) {
        return XCheckerTools.doesWebsiteContainXXSSProtection(driver);
    }

    @Override
    public boolean doesWebsiteContainAllXMetaHeaders(HtmlUnitDriver driver) {
        return XCheckerTools.doesWebsiteContainAllXMetaHeaders(driver);
    }
}
