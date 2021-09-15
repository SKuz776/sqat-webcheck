package com.sqat.webcheck.sqatwebchecker.tools;

import com.sqat.webcheck.sqatwebchecker.exceptions.HeaderNotFoundException;
import com.sqat.webcheck.sqatwebchecker.exceptions.ResponseIsNullException;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class WebsiteTools {

    public static List<WebElement> listOfAllLinks (HtmlUnitDriver driver) {

        return driver.findElementsByTagName("a")
                .stream()
                .filter(webElement -> webElement.getAttribute("href") != null)
                .filter(webElement ->
                        (!webElement.getAttribute("href").isBlank()
                                && !webElement.getAttribute("href").equals("#")
                                && isValidURL(webElement.getAttribute("href"))))
                .collect(Collectors.toList());
    }

    public static List<String> listOfAllImageExtensions (HtmlUnitDriver driver) {
        ArrayList<String> extensions = new ArrayList<>();
        List<WebElement> images = driver.findElementsByTagName("img");

        if (images == null || images.size() <= 0) {
            return extensions;
        }

        images.forEach(element -> {

            if (element.getAttribute("src") != null && element.getAttribute("src").length()>0) {
                String[] parts = element.getAttribute("src").split("\\.");
                int len = parts.length;

                extensions.add(filterExtension(parts[len - 1]));
            }

        });

        return extensions;
    }

    private static String filterExtension (String extension) {
        if (extension.contains("?")) {
            return extension.split("\\?")[0];
        } else {
            return extension;
        }
    }

    //Not reliable!
    public static Long getWebpageSize (HtmlUnitDriver driver) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(driver.getCurrentUrl());
        HttpResponse response = null;
        try {
            response = client.execute(request);
            return response.getEntity().getContentLength();
        } catch (IOException e) {
            throw new IOException("Request connection failed");
        }
    }

    public static Map<String, String> getAllHeaders (HtmlUnitDriver driver) throws ResponseIsNullException {

        HashMap<String, String> resultHeaders = new HashMap<>();

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(driver.getCurrentUrl());
        HttpResponse response = null;
        try {
            response = client.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //get all headers
        if (response != null) {
            Optional<Header[]> headers = Optional.of(response.getAllHeaders());
            if (headers.get().length > 0) {
                for (Header header : headers.get()) {
                    resultHeaders.put(header.getName(), header.getValue());
                }
            }
        } else {
            throw new ResponseIsNullException();
        }

        return resultHeaders;
    }

    public static Header getHeader (HtmlUnitDriver driver, String headerName) throws ResponseIsNullException, HeaderNotFoundException {

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(driver.getCurrentUrl());
        HttpResponse response = null;
        try {
            response = client.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (response != null) {
            return Arrays.stream(response.getAllHeaders())
                    .filter(header -> header.getName().equals(headerName))
                    .findFirst()
                    .orElseThrow(HeaderNotFoundException::new);

        } else {
            throw new ResponseIsNullException();
        }

    }

    public static boolean isValidURL(String url) {

        try {
            new URL(url).toURI();
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }

        return true;
    }

}
