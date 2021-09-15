package com.sqat.webcheck.sqatwebchecker.service.impl;

import com.sqat.webcheck.sqatwebchecker.exceptions.ResponseIsNullException;
import com.sqat.webcheck.sqatwebchecker.service.WebsiteService;
import com.sqat.webcheck.sqatwebchecker.tools.WebsiteTools;
import org.apache.http.Header;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
@Service
public class WebsiteServiceImpl implements WebsiteService {
    @Override
    public List<WebElement> listOfAllLinks(HtmlUnitDriver driver) {
        return WebsiteTools.listOfAllLinks(driver);
    }

    @Override
    public List<String> listOfAllImageExtensions(HtmlUnitDriver driver) {
        return WebsiteTools.listOfAllImageExtensions(driver);
    }


    @Override
    public Long getWebpageSize(HtmlUnitDriver driver) throws IOException {
        return WebsiteTools.getWebpageSize(driver);
    }

    @Override
    public Map<String, String> getAllHeaders(HtmlUnitDriver driver) throws ResponseIsNullException {
        return WebsiteTools.getAllHeaders(driver);
    }

    @Override
    public Header getHeader(HtmlUnitDriver driver, String headerName) {
        return null;
    }

    @Override
    public Boolean isUrlValid(String url) {
        return WebsiteTools.isValidURL(url);
    }
}
