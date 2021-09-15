package com.sqat.webcheck.sqatwebchecker.service.impl;

import com.sqat.webcheck.sqatwebchecker.exceptions.HeaderNotFoundException;
import com.sqat.webcheck.sqatwebchecker.exceptions.HttpStrictTransportSecurityTooShortException;
import com.sqat.webcheck.sqatwebchecker.exceptions.ResponseIsNullException;
import com.sqat.webcheck.sqatwebchecker.service.HeaderCheckerService;
import com.sqat.webcheck.sqatwebchecker.tools.HeaderCheckerTools;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.stereotype.Service;

@Service
public class HeaderCheckerServiceImpl implements HeaderCheckerService {



    @Override
    public boolean isHTTPStrictTransportSecurityHeaderOk(HtmlUnitDriver driver) throws HeaderNotFoundException, ResponseIsNullException, HttpStrictTransportSecurityTooShortException {
        return HeaderCheckerTools.isHTTPStrictTransportSecurityHeaderOk(driver);
    }
}
