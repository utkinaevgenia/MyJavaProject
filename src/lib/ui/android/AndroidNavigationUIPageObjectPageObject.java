package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUIPageObject;

public class AndroidNavigationUIPageObjectPageObject extends NavigationUIPageObject
{
    static {
        NAVIGATION_BACK_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
        SAVED_LINK = "xpath://android.widget.FrameLayout[@content-desc='Saved']/android.widget.FrameLayout";
    }
    public AndroidNavigationUIPageObjectPageObject(AppiumDriver driver) {
        super(driver);
    }
}
