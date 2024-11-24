package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class NavigationUIPageObject extends MainPageObject
{
    protected static String
    NAVIGATION_BACK_BUTTON,
    SAVED_LINK,
    CANCEL_BUTTON;

    public NavigationUIPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void navigation_back ()
    {
        this.waitForElementAndClick(
                (NAVIGATION_BACK_BUTTON),
                "Cannot find 'Back' button",
                5
        );
    }

    public void clickSaved()
    {
        this.waitForElementAndClick(
                (SAVED_LINK),
                "Cannot find 'Saved' button",
                5
        );
    }

    public void clickCancel()
    {
        this.waitForElementAndClick(CANCEL_BUTTON, "Cannot find cancel button in search line",5);
    }
}
