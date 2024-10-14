package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

import static java.lang.System.getenv;

public class CoreTestCase extends TestCase {
    private static final String
    PLATFORM_IOS = "ios",
    PLATFORM_ANDROID = "android";

    protected AppiumDriver driver;

    private static String AppiumUrl = "http://127.0.0.1:4723/";

    @Override
    protected void setUp() throws Exception {

        super.setUp();

        driver = this.getDriverByEnv();
        this.rotateScreenPortrait();
    }

    @Override
    protected void tearDown() throws Exception {

        driver.quit();

        super.tearDown();
    }

    protected void rotateScreenPortrait()
    {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape()
    {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp(int seconds)
    {
        driver.runAppInBackground(Duration.ofSeconds(seconds));
    }

    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception
    {
        String platform = getenv("PLATFORM");

        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (platform.equals(PLATFORM_ANDROID)){
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("appium:deviceName", "AndroidTestDevice");
            capabilities.setCapability("appium:platformVersion", "8.0");
            capabilities.setCapability("appium:automationName", "UiAutomator2");
            capabilities.setCapability("appium:appPackage", "org.wikipedia");
            capabilities.setCapability("appium:appActivity", ".main.MainActivity");
            capabilities.setCapability("appium:app", "/Users/evgenia/Desktop/JavaAppiumAutomation/MyJavaProject/apks/org.wikipedia.apk");
        } else if (platform.equals(PLATFORM_IOS)) {
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("appium:deviceName", "iPhone 16");
            capabilities.setCapability("appium:platformVersion", "18.0");
            capabilities.setCapability("appium:automationName", "XCUITest");
            capabilities.setCapability("appium:app", "/Users/evgenia/Desktop/JavaAppiumAutomation/MyJavaProject/apks/Википедия.app");
        } else {
            throw new Exception("Cannot get run platform from env variable. Platform value" + platform);
        }
        return capabilities;
    }

    private AppiumDriver getDriverByEnv () throws Exception
    {
        String driver = System.getenv("DRIVER");
        DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv();
        if (driver.equals("AndroidDriver"))
        {
            return new AndroidDriver(new URL(AppiumUrl), capabilities);
        } else if (driver.equals ("iOSDriver")) {
            return new IOSDriver(new URL(AppiumUrl), capabilities);
        } else {
            throw new IllegalArgumentException("Cannot run driver from env. Driver" + driver);
        }
    }
}
