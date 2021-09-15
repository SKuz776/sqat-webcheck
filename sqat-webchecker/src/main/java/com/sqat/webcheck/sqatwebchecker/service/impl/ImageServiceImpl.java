package com.sqat.webcheck.sqatwebchecker.service.impl;

import com.sqat.webcheck.sqatwebchecker.service.ImageService;
import com.sqat.webcheck.sqatwebchecker.tools.ImageTools;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class ImageServiceImpl implements ImageService {
    @Override
    public Map<Integer, WebElement> getImagesWithSizesMap(HtmlUnitDriver driver) {
        return ImageTools.getImagesWithSizesMap(driver);
    }

    @Override
    public List<Map.Entry<WebElement, Integer>> getImagesWithSizesList(HtmlUnitDriver driver) {
        return ImageTools.getImagesWithSizesList(driver);
    }

    @Override
    public boolean isUrlValid(String url) {
        return ImageTools.isUrlValid(url);
    }
}
