package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPageObject {
    protected AppiumDriver driver;

    public MainPageObject (AppiumDriver driver)
    {
        this.driver = driver;
    }

    public WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSentValue(By by, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public void swipeUp(int timeOfSwipe)
    {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int y = size.height/ 2;
        int start_x = (int) (size.width * 0.8);
        int end_x = (int) (size.width * 0.2);

        action
                .press(start_x, y)
                .waitAction(timeOfSwipe)
                .moveTo(end_x, y)
                .release()
                .perform();
    }

    public void swipeUpQuick ()
    {
        swipeUp(200);
    }

    public void swipeUpToFindElement (By by, String error_message, int max_swipes)
    {
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {
            if (already_swiped>max_swipes) {
                waitForElementPresent(by, "Cannot find element by swiping up \n" + error_message, 0);
                return;
            }
            swipeUpQuick();
            ++ already_swiped;
        }
    }

    public void swipeElementToLeft (By by, String error_message)
    {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y)/2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();

    }

    public void asserElementHasText(By by, String error_message, String expected_text, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        WebElement element = wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );

        String actual_text = element.getText();

        Assert.assertEquals(error_message, expected_text, actual_text);
    }

    public void swipeLeft(int timeOfSwipe)
    {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int y = size.height/ 2;
        int start_x = (int) (size.width * 0.8);
        int end_x = (int) (size.width * 0.2);

        action
                .press(start_x, y)
                .waitAction(timeOfSwipe)
                .moveTo(end_x, y)
                .release()
                .perform();
    }

    public void swipeLeftQuick()
    {
        swipeLeft(200);
    }

    public void swipeLeftToFindElement(By by, String error_message, int max_swipes)
    {
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {
            if (already_swiped>max_swipes) {
                waitForElementPresent(by, "Cannot find element by swiping up \n" + error_message, 0);
                return;
            }
            swipeLeftQuick();
            ++ already_swiped;
        }
    }

    public int getAmountOfElements (By by)
    {
        List elements = driver.findElements(by);
        return elements.size();
    }

    public void assertElementNotPresent (By by, String error_message)
    {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements>0) {
            String default_message = "An element '" + by.toString() + "'supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    public String waitForElementAndGetAttribute (By by, String attribute, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    public void assertElementPresent (By by, String error_message)
    {
        String article_title = driver.findElement(by).getAttribute("text") ;
        if (article_title == "//*[contains(@text,'Java (programming language)')]") {
            String default_message = "An element '" + by.toString() + "'supposed to be present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }
}
