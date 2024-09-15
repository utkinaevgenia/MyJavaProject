import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class SecondTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/evgenia/Desktop/JavaAppiumAutomation/MyJavaProject/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testSearchFieldContainsText() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find Skip button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );

        asserElementHasText(
                By.id("org.wikipedia:id/search_src_text"),
                "Field does not contain expected text",
                "Search Wikipedia",
                15
        );
    }

    @Test
    public void testFindArticlesAndCanselSearch() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find Skip button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );
        waitForElementAndSentValue(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find search input",
                5
        );
        waitForElementPresent(
                By.xpath("//*[contains(@text,'Java')]"),
                "Cannot find articles with title Java",
                15
        );

        waitForElementPresent(
                By.xpath("//*[contains(@text,'Java (programming language)')]"),
                "Cannot find articles with title Java (programming language)",
                15
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                15
        );

        waitForElementNotPresent(
                By.xpath("//*[contains(@text,'Java')]"),
                "Articles with Java are still present on page",
                15
        );
    }

    @Test
    public void testSwipeLeftOnbording()
    {
        waitForElementPresent(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find Skip button",
                5
        );

        swipeLeftToFindElement(
                By.xpath("//*[contains(@text,'Get started')]"),
                "Cannot find 'Get started' text",
                15
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Get started')]"),
                "Cannot find 'Get started' text",
                15
        );
    }

    @Test
    public void swipeLeftAndCheckTitle() {
        waitForElementPresent(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find Skip button",
                5
        );

        String error_message_for_get_attribute = "Cannot find title on the screen";

        swipeLeft(200);

        String title_first_screen = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/primaryTextView"),
                "text",
                error_message_for_get_attribute,
                15
        );

        Assert.assertEquals(
                "Unexpected title of first screen",
                "New ways to explore",
                title_first_screen
        );

        swipeLeft(200);

        String title_second_screen = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/primaryTextView"),
                "text",
                error_message_for_get_attribute,
                15
        );

        Assert.assertEquals(
                "Unexpected title of second screen",
                "Reading lists with sync",
                title_second_screen
        );

        swipeLeft(200);

        String title_third_screen = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/primaryTextView"),
                "text",
                error_message_for_get_attribute,
                15
        );

        Assert.assertEquals(
                "Unexpected title of third screen",
                "Data & Privacy",
                title_third_screen
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Get started')]"),
                "Cannot find 'Get started' text",
                15
        );

        waitForElementPresent(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSentValue(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private void asserElementHasText(By by, String error_message, String expected_text, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        WebElement element = wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );

        String actual_text = element.getText();

        Assert.assertEquals(error_message, expected_text, actual_text);
    }

    protected void swipeLeft(int timeOfSwipe)
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

    protected void swipeLeftQuick()
    {
        swipeLeft(200);
    }

    protected void swipeLeftToFindElement(By by, String error_message, int max_swipes)
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

    public String waitForElementAndGetAttribute (By by, String attribute, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }
}