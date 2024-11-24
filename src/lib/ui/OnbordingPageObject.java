package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class OnbordingPageObject extends MainPageObject
{
    protected static String
            START_BUTTON,
            SKIP_BUTTON,
            SCREEN_TITLE,
            ERROR_MESSAGE_FOR_GETTING_ATTRIBUTE,
            FIRST_TITLE,
            SECOND_TITLE,
            THIRD_TITLE,
            FORTH_TITLE,
            NEXT_BUTTON;

    public OnbordingPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void waitToSkip()
    {
        this.waitForElementPresent(
                (SKIP_BUTTON),
                "Cannot find Skip button",
                5);
    }

    public void clickToSkip()
    {
        this.waitForElementAndClick(
                (SKIP_BUTTON),
                "Cannot find Skip button",
                5);
    }

    public void swipeOnbording ()
    {
        this.swipeLeftToFindElement(
                (START_BUTTON),
                "Cannot find 'Get started' text",
                20
        );
    }

    public void clickToStartOnOnbording ()
    {
        this.waitForElementAndClick(
                (START_BUTTON),
                "Cannot find 'Get started' text",
                20
        );
    }

    public void oneSwipeLeft(int timeOfSwipe)
    {
        this.swipeLeft(timeOfSwipe);
    }

    public String getScreenTitleAttribute()
    {
            return this.waitForElementAndGetAttribute(
                    (SCREEN_TITLE),
                    "text",
                    ERROR_MESSAGE_FOR_GETTING_ATTRIBUTE,
                    15);
    }

    public void waitForFirstPageTitle()
    {
        this.waitForElementPresent((FIRST_TITLE), "Cannot find first title" + FIRST_TITLE, 10);
    }

    public void waitForSecondPageTitle()
    {
        this.waitForElementPresent((SECOND_TITLE), "Cannot find second title" + SECOND_TITLE, 10);
    }

    public void waitForThirdPageTitle()
    {
        this.waitForElementPresent((THIRD_TITLE), "Cannot find second title" + THIRD_TITLE, 10);
    }

    public void waitForForthPageTitle()
    {
        this.waitForElementPresent((FORTH_TITLE), "Cannot find second title" + FORTH_TITLE, 10);
    }

    public void clickNextButton()
    {
        this.waitForElementAndClick((NEXT_BUTTON), "Cannot find Next button", 10);
    }

    public void clickStartButton()
    {
        this.waitForElementAndClick((START_BUTTON), "Cannot find Start button", 10);
    }

    public String getFirstScreenTitleAttribute()
    {
        return this.waitForElementAndGetAttribute(
                (FIRST_TITLE),
                "name",
                ERROR_MESSAGE_FOR_GETTING_ATTRIBUTE,
                15);
    }

    public String getSecondScreenTitleAttribute()
    {
        return this.waitForElementAndGetAttribute(
                (SECOND_TITLE),
                "name",
                ERROR_MESSAGE_FOR_GETTING_ATTRIBUTE,
                15);
    }

    public String getThirdScreenTitleAttribute()
    {
        return this.waitForElementAndGetAttribute(
                (THIRD_TITLE),
                "name",
                ERROR_MESSAGE_FOR_GETTING_ATTRIBUTE,
                15);
    }

    public String getForthScreenTitleAttribute()
    {
        return this.waitForElementAndGetAttribute(
                (FORTH_TITLE),
                "name",
                ERROR_MESSAGE_FOR_GETTING_ATTRIBUTE,
                15);
    }

}
