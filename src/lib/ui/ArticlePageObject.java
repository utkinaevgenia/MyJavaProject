package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject
{
    private static final String
    TITLE = "//*[contains(@text,'Java (programming language)')]",
    ADD_TO_LIST_SAVE_BUTTON = "org.wikipedia:id/page_save",
    ADD_TO_LIST_BUTTON = "//*[@text='Add to list']",
    MY_LIST_TEXT_INPUT = "org.wikipedia:id/text_input",
    MY_LIST_OK_BUTTON = "//*[@text='OK']";

    public ArticlePageObject (AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement ()
    {
        return this.waitForElementPresent(
                By.xpath(TITLE),
                "Cannot find article title",
                10);
    }

    public String getArticleTitle ()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void addArticleToMyList (String name_of_folder)
    {
        this.waitForElementAndClick(
                By.id(ADD_TO_LIST_SAVE_BUTTON),
                "Cannot find 'Save' button",
                15
        );

        this.waitForElementAndClick(
                By.xpath(ADD_TO_LIST_BUTTON),
                "Cannot find 'Add to list' button",
                12
        );


        this.waitForElementAndSentValue(
                By.id(MY_LIST_TEXT_INPUT),
                name_of_folder,
                "Cannot find input to set name of article folder",
                15
        );

        this.waitForElementAndClick(
                By.xpath(MY_LIST_OK_BUTTON),
                "Cannot press 'OK' button",
                5
        );
    }
    public void assertTitleArticlePresent()
    {
        this.assertElementPresent(
                By.xpath(TITLE),
                "Cannot find article title"
        );
    }
}
