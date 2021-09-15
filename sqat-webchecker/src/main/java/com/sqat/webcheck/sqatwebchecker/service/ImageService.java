package com.sqat.webcheck.sqatwebchecker.service;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.List;
import java.util.Map;

public interface ImageService {

    public Map<Integer, WebElement> getImagesWithSizesMap (HtmlUnitDriver driver);
    public List<Map.Entry<WebElement, Integer>> getImagesWithSizesList (HtmlUnitDriver driver);
    public boolean isUrlValid(String url);
}
