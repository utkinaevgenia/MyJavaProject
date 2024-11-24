package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

abstract public class SavedArticlePageObject extends MainPageObject
{
    protected static String
    NAME_OF_FOLDER_TPL,
    ARTICLE_BY_TITLE_TPL,
    CLOSE_SYNC_BUTTON;

    private static String getFolderXpathByName (String name_of_folder)
    {
        return NAME_OF_FOLDER_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByName (String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    public SavedArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void openFolderByName (String name_of_folder)
    {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                (folder_name_xpath),
                "Cannot find folder by name" + name_of_folder,
                5
        );
    }

    public void waitForArticleToDisappearByTitle (String article_title)
    {
        String article_title_xpath = getSavedArticleXpathByName(article_title);
        this.waitForElementNotPresent(
                (article_title_xpath),
                "Saved article still present with title" + article_title,
                15
        );
    }

    public void waitForArticleToAppearByTitle (String article_title)
    {
        String article_title_xpath = getSavedArticleXpathByName(article_title);
        this.waitForElementPresent(
                (article_title_xpath),
                "Cannot find saved article with title" + article_title,
                15
        );
    }

    public void swipeByArticleToDelete (String article_title)
    {
        this.waitForArticleToAppearByTitle(article_title);
        String article_title_xpath = getSavedArticleXpathByName(article_title);
        if (Platform.getInstance().isAndroid()) {
            this.swipeElementToLeft(
                    (article_title_xpath),
                    "Cannot find saved article"
            );
        } else {
            this.waitForElementAndClick(CLOSE_SYNC_BUTTON,"Cannot find X button", 20);
            this.swipeElementToLeft(article_title_xpath, "Cannot find saved article");
            this.clickElementToTheRightUpperCorner(article_title_xpath,"Cannot find saved article");
        }

        this.waitForArticleToDisappearByTitle(article_title);
    }
}
