package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.OnbordingPageObject;
import lib.ui.android.AndroidOnbordingPageObject;
import lib.ui.ios.IOSOnborgingPageObject;

public class OndordingPageObjectFactory
{
    public static OnbordingPageObject get(AppiumDriver driver)
    {
        if (Platform.getInstance().isAndroid()){
            return new AndroidOnbordingPageObject(driver);
        } else {
            return new IOSOnborgingPageObject(driver);
        }
    }
}
