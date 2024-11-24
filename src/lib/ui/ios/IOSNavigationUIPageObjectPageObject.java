package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUIPageObject;

public class IOSNavigationUIPageObjectPageObject extends NavigationUIPageObject
{
    static {
        NAVIGATION_BACK_BUTTON = "id:Back";
        SAVED_LINK = "id:Saved";
        CANCEL_BUTTON = "xpath://XCUIElementTypeStaticText[@name='Cancel']";
    }
    public IOSNavigationUIPageObjectPageObject(AppiumDriver driver) {
        super(driver);
    }
}
