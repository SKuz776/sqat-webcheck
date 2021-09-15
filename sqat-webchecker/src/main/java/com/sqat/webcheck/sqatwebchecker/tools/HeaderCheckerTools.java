package com.sqat.webcheck.sqatwebchecker.tools;


import com.sqat.webcheck.sqatwebchecker.exceptions.HeaderNotFoundException;
import com.sqat.webcheck.sqatwebchecker.exceptions.HttpStrictTransportSecurityTooShortException;
import com.sqat.webcheck.sqatwebchecker.exceptions.ResponseIsNullException;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HeaderCheckerTools {

    //is it set to more than 6 months (== 15768000 seconds)
    public static boolean isHTTPStrictTransportSecurityHeaderOk (HtmlUnitDriver driver)
            throws ResponseIsNullException, HeaderNotFoundException, HttpStrictTransportSecurityTooShortException {
        if (!WebsiteTools.getHeader(driver, "Strict-Transport-Security").getValue().isBlank()
                && !WebsiteTools.getHeader(driver, "Strict-Transport-Security").getValue().isEmpty()) {
//            return Long.parseLong(WebsiteTools.getHeader(driver, "Strict-Transport-Security")
//                    .getValue().split("=")[1]) >= 15768000;
            return extractFirstNumberFromString(WebsiteTools.getHeader(driver, "Strict-Transport-Security").getValue()) >= 15768000;
        }
        else {
            throw new HttpStrictTransportSecurityTooShortException();
        }
    }

    private static Long extractFirstNumberFromString (String string) {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(string);
        if(m.find()) {
            return Long.parseLong(m.group(0));
        }
        return (long) -1;
    }

}
