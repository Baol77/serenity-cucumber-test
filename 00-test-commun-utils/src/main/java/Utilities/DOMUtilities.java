package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.function.Function;

public final class DOMUtilities {

    private static boolean checkElementConditionBy(WebDriver driver, Integer timeout, By byCondition, Function<By, ExpectedCondition<WebElement>> conditionFunction) {
        Boolean result;

        // Wait a bit so that the element status AND without raising an exception
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        try {
            wait.until(conditionFunction.apply(byCondition));

            result = true;
        } catch (org.openqa.selenium.TimeoutException e) {
            result = false;
        }

        return result;
    }

    public static boolean isClassInWebElement(WebElement element, String className) {
        if (element != null && className != null && !className.trim().isEmpty()) {
            String classes = element.getAttribute("class");
            for (String c : classes.split(" ")) {
                if (c.equals(className)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static WebElement getParent(WebElement element) {
        if (element != null) {
            return element.findElement(By.xpath("./.."));
        } else
            return null;
    }

    public static String getHiddenText(WebElement element) {
        return element != null ? element.getAttribute("innerText").trim() : null;
    }

    public static boolean checkElementPresenceBy(WebDriver driver, Integer timeout, By byCondition) {
        return checkElementConditionBy(driver, timeout, byCondition, ExpectedConditions::presenceOfElementLocated);
    }

    public static boolean checkElementVisibilityBy(WebDriver driver, Integer timeout, By byCondition){
        return checkElementConditionBy(driver, timeout, byCondition, ExpectedConditions::visibilityOfElementLocated);
    }

    public static Boolean checkSingleElementPresenceByClass(WebDriver driver, Integer timeout, String className) {

        Boolean result;

        // Wait a bit so that the element is displayed AND without raising an exception
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        try {
            List<WebElement> foundElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className(className)));
            result = foundElements.size() == 1;
        } catch (org.openqa.selenium.TimeoutException e) {
            result = false;
        }

        return result;
    }

    public static void jsClick(WebDriver driver, WebElement element) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
    }

    public static void jsScrollTo(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void setDateValue(WebDriver driver, WebElement dateElement, String fieldDateValue) {

        // Clean field to be sure
        dateElement.clear();

        // Write date
        dateElement.sendKeys(fieldDateValue);

        // Wait for the text to be present
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.textToBePresentInElementValue(dateElement, fieldDateValue));

        ((JavascriptExecutor)driver).executeScript("arguments[0].dispatchEvent(new Event('input'));", dateElement);
    }
}
