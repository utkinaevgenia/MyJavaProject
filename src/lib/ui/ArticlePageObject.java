package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject
{
    private static final String
    TITLE = "xpath://*[contains(@text,'Java (programming language)')]",
    ADD_TO_LIST_SAVE_BUTTON = "id:org.wikipedia:id/page_save",
    ADD_TO_LIST_BUTTON = "xpath://*[@text='Add to list']",
    MY_LIST_TEXT_INPUT = "id:org.wikipedia:id/text_input",
    MY_LIST_OK_BUTTON = "xpath://*[@text='OK']";

    public ArticlePageObject (AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement ()
    {
        return this.waitForElementPresent(
                (TITLE),
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
                (ADD_TO_LIST_SAVE_BUTTON),
                "Cannot find 'Save' button",
                15
        );

        this.waitForElementAndClick(
                (ADD_TO_LIST_BUTTON),
                "Cannot find 'Add to list' button",
                12
        );


        this.waitForElementAndSentValue(
                (MY_LIST_TEXT_INPUT),
                name_of_folder,
                "Cannot find input to set name of article folder",
                15
        );

        this.waitForElementAndClick(
                (MY_LIST_OK_BUTTON),
                "Cannot press 'OK' button",
                5
        );
    }
    public void assertTitleArticlePresent()
    {
        this.assertElementPresent(
                (TITLE),
                "Cannot find article title"
        );
    }
}
