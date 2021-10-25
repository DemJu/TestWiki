package Exceptions;

import org.openqa.selenium.WebElement;

import java.util.List;

public class ErrorSiteProcessing {

    public static String numberQueriesFound(List<WebElement> webElements) throws CheckingSiteResults {
        if (webElements.size() > 0) {
            return "Quantity: " + webElements.size();
        } else {
            throw new CheckingSiteResults("Found quantity not found");
        }
    }
}
