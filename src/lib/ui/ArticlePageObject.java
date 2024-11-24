package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject
{
    protected static String
    TITLE,
    ADD_TO_LIST_SAVE_BUTTON,
    ADD_TO_LIST_BUTTON,
    MY_LIST_TEXT_INPUT,
    MY_LIST_OK_BUTTON;

    public ArticlePageObject (AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement ()
    {
        return this.waitForElementPresent(
                (TITLE),
                "Cannot find article title",
                20);
    }

    public String getArticleTitle ()
    {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else {
            return title_element.getAttribute("name");
        }
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

    public void addArticleToSaved ()
    {
        this.waitForElementAndClick(ADD_TO_LIST_SAVE_BUTTON, "Cannot find Saved button", 5);
    }

    public void assertTitleArticlePresent()
    {
        this.assertElementPresent(
                (TITLE),
                "Cannot find article title"
        );
    }
}
