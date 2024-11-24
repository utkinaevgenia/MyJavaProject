package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.NavigationUIPageObject;
import lib.ui.android.AndroidNavigationUIPageObjectPageObject;
import lib.ui.ios.IOSNavigationUIPageObjectPageObject;

public class NavigationUIPageObjectFactory
{
    public static NavigationUIPageObject get(AppiumDriver driver)
    {
        if (Platform.getInstance().isAndroid()){
            return new AndroidNavigationUIPageObjectPageObject(driver);
        }
        else {
            return new IOSNavigationUIPageObjectPageObject(driver);
        }
    }
}