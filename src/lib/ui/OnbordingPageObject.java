package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class OnbordingPageObject extends MainPageObject
{
    private static final String
            START_BUTTON = "//*[contains(@text,'Get started')]",
            SKIP_BUTTON = "//*[contains(@text,'Skip')]",
            SCREEN_TITLE = "org.wikipedia:id/primaryTextView",
            ERROR_MESSAGE_FOR_GETTING_ATRIBUTE = "Cannot find title on the screen";

    public OnbordingPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void waitToSkip()
    {
        this.waitForElementPresent(
                By.xpath(SKIP_BUTTON),
                "Cannot find Skip button",
                5);
    }

    public void clickToSkip()
    {
        this.waitForElementAndClick(By.xpath(SKIP_BUTTON), "Cannot find Skip button",5);
    }

    public void swipeOnbording ()
    {
        this.swipeLeftToFindElement(
                By.xpath(START_BUTTON),
                "Cannot find 'Get started' text",
                20
        );
    }

    public void clickToStartOnOnbording ()
    {
        this.waitForElementAndClick(
                By.xpath(START_BUTTON),
                "Cannot find 'Get started' text",
                20
        );
    }

    public void oneSwipeLeft(int timeOfSwipe)
    {
        this.swipeLeft(timeOfSwipe);
    }

    public String getScreenTitleAtribute()
    {
        return this.waitForElementAndGetAttribute(
                By.id(SCREEN_TITLE),
                "text",
                ERROR_MESSAGE_FOR_GETTING_ATRIBUTE,
                15);
    }

}
