package com.sqat.webcheck.sqatwebchecker.controller;

import com.sqat.webcheck.sqatwebchecker.exceptions.HeaderNotFoundException;
import com.sqat.webcheck.sqatwebchecker.exceptions.HttpStrictTransportSecurityTooShortException;
import com.sqat.webcheck.sqatwebchecker.exceptions.ResponseIsNullException;
import com.sqat.webcheck.sqatwebchecker.service.*;
import com.sqat.webcheck.sqatwebchecker.tools.WebsiteTools;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping
public class HomeController {

    private static HtmlUnitDriver driver;
    private final SeoService seoService;
    private final HeaderCheckerService headerCheckerServicer;
    private final ImageService imageService;
    private final WebsiteService websiteService;
    private final XCheckerService xCheckerService;
    private static String webSiteName;
    public HomeController(SeoService seoService,
                          HeaderCheckerService headerCheckerServicer,
                          ImageService imageService,
                          WebsiteService websiteService,
                          XCheckerService xCheckerService) {
        webSiteName = "";
        this.seoService = seoService;
        this.headerCheckerServicer = headerCheckerServicer;
        this.imageService = imageService;
        this.websiteService = websiteService;
        this.xCheckerService = xCheckerService;
    }

    @GetMapping({"/", "/home"})
    public String showHomePage(@RequestParam(required = false) String url,
                               @RequestParam(required = false) String error,
                               Model model){
        model.addAttribute("url", url);
        model.addAttribute("error", error);
        return "home";
    }

    @GetMapping("/result")
    public String showResults(Model model){
        driver = new HtmlUnitDriver();
        driver.get(webSiteName);
        boolean containsAllSeoMeta = seoService.doesWebsiteContainAllSeoMeta(driver);
        List<WebElement> allMetaTags = seoService.getAllMetaTags(driver);
        boolean httpStrictTransportSecurityHeaderOk = false;
        try {
            httpStrictTransportSecurityHeaderOk = headerCheckerServicer.isHTTPStrictTransportSecurityHeaderOk(driver);
        } catch (HeaderNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (ResponseIsNullException e) {
            System.out.println(e.getMessage());
        } catch (HttpStrictTransportSecurityTooShortException e) {
            System.out.println(e.getMessage());
        }
        List<Map.Entry<WebElement, Integer>> imagesWithSizesList = imageService.getImagesWithSizesList(driver);
        Map<Integer, WebElement> imagesWithSizesMap = imageService.getImagesWithSizesMap(driver);
        Map<String, String> allHeaders = null;
        try {
            allHeaders = websiteService.getAllHeaders(driver);
        } catch (ResponseIsNullException e) {
            System.out.println(e.getMessage());
        }
        Long webpageSize = null;
        try {
            webpageSize = websiteService.getWebpageSize(driver);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        List<String> allImgExt = websiteService.listOfAllImageExtensions(driver);
        List<WebElement> allLinks = websiteService.listOfAllLinks(driver);
        boolean containsAllXMetaHeaders = xCheckerService.doesWebsiteContainAllXMetaHeaders(driver);
        boolean containsXContentTypeOptions = xCheckerService.doesWebsiteContainXContentTypeOptions(driver);
        boolean containsXXSSProtection = xCheckerService.doesWebsiteContainXXSSProtection(driver);
        boolean containsXFrameOptions = xCheckerService.doesWebsiteContainXFrameOptions(driver);


        imagesWithSizesList = imagesWithSizesList
                .stream()
                .sorted((first,second) -> Integer.compare(second.getValue(), first.getValue()))
                .collect(Collectors.toList());

        model.addAttribute("containsAllSeoMeta", containsAllSeoMeta);
        model.addAttribute("allMetaTags", allMetaTags);
        model.addAttribute("httpStrictTransportSecurityHeaderOk", httpStrictTransportSecurityHeaderOk);
        model.addAttribute("imagesWithSizesList", imagesWithSizesList);
        model.addAttribute("imagesWithSizesMap", imagesWithSizesMap);
        model.addAttribute("allHeaders", allHeaders);
        model.addAttribute("webpageSize", webpageSize);
        model.addAttribute("allImgExt", allImgExt);
        model.addAttribute("allLinks", allLinks);
        model.addAttribute("containsAllXMetaHeaders", containsAllXMetaHeaders);
        model.addAttribute("containsXContentTypeOptions", containsXContentTypeOptions);
        model.addAttribute("containsXXSSProtection", containsXXSSProtection);
        model.addAttribute("containsXFrameOptions", containsXFrameOptions);
        double imageSize = imageService.getImagesWithSizesList(driver)
                .stream().mapToInt(Map.Entry::getValue).sum()*1.0/1024/1024;
        model.addAttribute("totalImgSize", String.format("%.2f", imageSize) +"MB");
        model.addAttribute("webSiteName", driver.getCurrentUrl());
        model.addAttribute("missingSeoTags", seoService.whichSeoMetaTagsAreMissing(driver));
        return "result";

    }


    @PostMapping("/home")
    public String displayResults(@RequestParam String websiteName){
        webSiteName= websiteName;

        if (!websiteService.isUrlValid(websiteName)) {
            return "redirect:/home?error=Invalid URL.";
        }

        return "redirect:/result";
    }







}
