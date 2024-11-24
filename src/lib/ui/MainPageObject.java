package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {
    protected AppiumDriver driver;

    public MainPageObject (AppiumDriver driver)
    {
        this.driver = driver;
    }

    public WebElement waitForElementAndClick(String locator, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSentValue(String locator, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public WebElement waitForElementPresent(String locator, String error_message, long timeoutInSeconds)
    {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public boolean waitForElementNotPresent(String locator, String error_message, long timeoutInSeconds)
    {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    protected void swipe(Point start, Point end, Duration duration)
    {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start.x, start.y));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(duration, PointerInput.Origin.viewport(), end.x, end.y));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        this.driver.perform(Arrays.asList(swipe));
    }

    public void swipeUp(int timeOfSwipe)
    {
        Dimension size = driver.manage().window().getSize();
        int y = size.height/ 2;
        int start_x = (int) (size.width * 0.8);
        int end_x = (int) (size.width * 0.2);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");
        Sequence swipe = new Sequence(finger,1);
        swipe
                //Двигаем палец на начальную позицию
                .addAction(finger.createPointerMove(Duration.ofSeconds(0),
                        PointerInput.Origin.viewport(), y, start_x))
                //Палец прикасается к экрану
                .addAction(finger.createPointerDown(0))
                //Палец двигается к конечной точке
                .addAction(finger.createPointerMove(Duration.ofMillis(timeOfSwipe),
                        PointerInput.Origin.viewport(), end_x, y))
                //Убираем палец с экрана
                .addAction(finger.createPointerUp(0));

        //Выполняем действия
        driver.perform(Arrays.asList(swipe));
    }

    public void swipeUpQuick ()
    {
        swipeUp(200);
    }

    public void swipeUpToFindElement (String locator, String error_message, int max_swipes)
    {
        By by = this.getLocatorByString(locator);
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {
            if (already_swiped>max_swipes) {
                waitForElementPresent(locator, "Cannot find element by swiping up \n" + error_message, 0);
                return;
            }
            swipeUpQuick();
            ++ already_swiped;
        }
    }

    public void clickElementToTheRightUpperCorner(String locator, String error_message)
    {
        WebElement element = this.waitForElementPresent(locator + "//..", error_message,20);
        int right_x = element.getLocation().getX();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y)/2;
        int width = element.getSize().getWidth();

        int point_to_click_x = (right_x + width) - 3;
        int point_to_click_y = middle_y;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        // Создаем последовательность действий (тап)
        Sequence tap = new Sequence(finger, 0)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), point_to_click_x, point_to_click_y)) // Наводим "палец" на координаты
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())) // Нажатие
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg())); // Отпускание

        // Выполняем действия
        driver.perform(Collections.singletonList(tap));
    }

    public void swipeElementToLeft (String locator, String error_message) {
        WebElement element = waitForElementPresent(
                locator,
                error_message,
                10);

        Point location = element.getLocation();
        Dimension size = element.getSize();

        int left_x = element.getLocation().getX();
        int right_x = left_x + size.getWidth();
        int upper_y = location.getY();
        int lower_y = upper_y + size.getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        if (Platform.getInstance().isAndroid()) {
            this.swipe(
                    new Point(right_x, middle_y),
                    new Point(left_x, middle_y),
                    Duration.ofMillis(550)
            );
        } else {
            int offset_x = (-1 * element.getSize().getWidth());
            this.swipe(
                    new Point(right_x, middle_y),
                    new Point(offset_x, 0),
                    Duration.ofMillis(550)
            );
        }
    }

    public void asserElementHasText(String locator, String error_message, String expected_text, long timeoutInSeconds)
    {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        WebElement element = wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );

        String actual_text = element.getText();

        Assert.assertEquals(error_message, expected_text, actual_text);
    }

    public void swipeLeft(int timeOfSwipe)
    {
        Dimension size = driver.manage().window().getSize();
        int y = size.height/ 2;
        int start_x = (int) (size.width * 0.8);
        int end_x = (int) (size.width * 0.2);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe(
                new Point(start_x, y),
                new Point(end_x, y),
                Duration.ofMillis(550)
        );
    }

    public void swipeLeftQuick()
    {
        swipeLeft(200);
    }

    public void swipeLeftToFindElement(String locator, String error_message, int max_swipes)
    {
        By by = this.getLocatorByString(locator);
        int already_swiped = 0;
        while (driver.findElements(by).isEmpty()) {
            if (already_swiped>max_swipes) {
                waitForElementPresent(locator, "Cannot find element by swiping up \n" + error_message, 0);
                return;
            }
            swipeLeftQuick();
            ++ already_swiped;
        }
    }

    public int getAmountOfElements (String locator)
    {
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }

    public void assertElementNotPresent (String locator, String error_message)
    {
        int amount_of_elements = getAmountOfElements(locator);
        if (amount_of_elements>0) {
            String default_message = "An element '" + locator + "'supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    public String waitForElementAndGetAttribute (String locator, String attribute, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    public void assertElementPresent (String locator, String error_message) {
        By by = this.getLocatorByString(locator);
        if (Platform.getInstance().isAndroid()) {
            String article_title = driver.findElement(by).getAttribute("text");
            if (article_title == "//*[contains(@text,'Java (programming language)')]") {
                String default_message = "An element '" + locator + "'supposed to be present";
                throw new AssertionError(default_message + " " + error_message);
            }
        } else {
            String article_title = driver.findElement(by).getAttribute("name");
            if (article_title == "//*[contains(@name,'Java (programming language)')]") {
                String default_message = "An element '" + locator + "'supposed to be present";
                throw new AssertionError(default_message + " " + error_message);
            }
        }
    }

    private By getLocatorByString (String locator_with_type)
    {
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];

        if (by_type.equals("xpath")){
            return By.xpath(locator);
        } else if (by_type.equals("id")) {
            return By.id(locator);
        } else {
            throw new IllegalArgumentException("Cannot find type of locator. Locator " + locator_with_type);
        }
    }
}
