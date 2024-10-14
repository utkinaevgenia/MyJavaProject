package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePageObject extends MainPageObject
{
    private static final String
    FIRST_TITLE = "id:Свободная энциклопедия",
    SECOND_TITLE = "id:Новые способы изучения",
    THIRD_TITLE = "id:Искать на более чем 300 языках",
    FORTH_TITLE = "id:Данные и конфиденциальность",
    NEXT_BUTTON = "xpath://XCUIElementTypeButton[@name=\"Далее\"]",
    START_BUTTON = "xpath://XCUIElementTypeButton[@name=\"Начать\"]";

    public WelcomePageObject(AppiumDriver driver)
    {
        super(driver);
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
}
