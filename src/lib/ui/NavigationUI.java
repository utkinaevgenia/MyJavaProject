package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject
{
    public static final String
    NAVIGATION_BACK_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']",
    SAVED_LINK = "//android.widget.FrameLayout[@content-desc='Saved']/android.widget.FrameLayout";

    public NavigationUI (AppiumDriver driver)
    {
        super(driver);
    }

    public void navigation_back ()
    {
        this.waitForElementAndClick(
                By.xpath(NAVIGATION_BACK_BUTTON),
                "Cannot find 'Back' button",
                5
        );
    }

    public void clickSaved()
    {
        this.waitForElementAndClick(
                By.xpath(SAVED_LINK),
                "Cannot find 'Saved' button",
                5
        );
    }
}
