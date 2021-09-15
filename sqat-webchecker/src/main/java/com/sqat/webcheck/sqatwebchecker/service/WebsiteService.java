package com.sqat.webcheck.sqatwebchecker.service;

import com.sqat.webcheck.sqatwebchecker.exceptions.ResponseIsNullException;
import org.apache.http.Header;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface WebsiteService {
    public List<WebElement> listOfAllLinks (HtmlUnitDriver driver);
    public List<String> listOfAllImageExtensions (HtmlUnitDriver driver);
    public Long getWebpageSize (HtmlUnitDriver driver) throws IOException;
    public Map<String, String> getAllHeaders (HtmlUnitDriver driver) throws ResponseIsNullException;
    public Header getHeader (HtmlUnitDriver driver, String headerName);
    public Boolean isUrlValid (String url);
}
