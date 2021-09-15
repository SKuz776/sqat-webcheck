package com.sqat.webcheck.sqatwebchecker.tools;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class ImageTools {

    public static Map<Integer, WebElement> getImagesWithSizesMap (HtmlUnitDriver driver) {
        TreeMap<Integer, WebElement> result = new TreeMap<>();

        driver.findElementsByTagName("img")
                .forEach(image -> {
                    URLConnection urlConnection = null;
                    try {
                        if (isUrlValid(image.getAttribute("src"))) {
                            urlConnection = new URL(image.getAttribute("src")).openConnection();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (urlConnection != null) {
                        int size = urlConnection.getContentLength();

                        result.put(size, image);
                    }
                });

        return result;
    }

    public static List<Map.Entry<WebElement, Integer>> getImagesWithSizesList (HtmlUnitDriver driver) {
        ArrayList<Map.Entry<WebElement, Integer>> imageList = new ArrayList<>();

        ImageTools.getImagesWithSizesMap(driver)
                .forEach((size, imageElement) -> imageList.add(Map.entry(imageElement, size)));

        return imageList;
    }

    public static boolean isUrlValid(String url) {
        try {
            URL obj = new URL(url);
            obj.toURI();
            return true;
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }
    }

}
