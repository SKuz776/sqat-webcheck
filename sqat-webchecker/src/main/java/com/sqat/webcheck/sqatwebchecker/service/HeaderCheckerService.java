package com.sqat.webcheck.sqatwebchecker.service;

import com.sqat.webcheck.sqatwebchecker.exceptions.HeaderNotFoundException;
import com.sqat.webcheck.sqatwebchecker.exceptions.HttpStrictTransportSecurityTooShortException;
import com.sqat.webcheck.sqatwebchecker.exceptions.ResponseIsNullException;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public interface HeaderCheckerService {

    public boolean isHTTPStrictTransportSecurityHeaderOk(HtmlUnitDriver driver) throws HeaderNotFoundException, ResponseIsNullException, HttpStrictTransportSecurityTooShortException;

}
