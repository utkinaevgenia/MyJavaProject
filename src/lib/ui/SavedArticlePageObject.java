package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SavedArticlePageObject extends MainPageObject
{
    public static final String
    NAME_OF_FOLDER_TPL = "//*[@text='{FOLDER_NAME}']",
    ARTICLE_BY_TITLE_TPL = "//*[@text='{TITLE}']";

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
                By.xpath(folder_name_xpath),
                "Cannot find folder by name" + name_of_folder,
                5
        );
    }

    public void waitForArticleToDisappearByTitle (String article_title)
    {
        String article_title_xpath = getFolderXpathByName(article_title);
        this.waitForElementNotPresent(
                By.xpath(article_title_xpath),
                "Saved article still present with title" + article_title,
                15
        );
    }

    public void waitForArticleToAppearByTitle (String article_title)
    {
        String article_title_xpath = getFolderXpathByName(article_title);
        this.waitForElementPresent(
                By.xpath(article_title_xpath),
                "Cannot find saved article with title" + article_title,
                15
        );
    }

    public void swipeByArticleToDelete (String article_title)
    {
        this.waitForArticleToAppearByTitle(article_title);
        String article_title_xpath = getFolderXpathByName(article_title);
        this.swipeElementToLeft(
                By.xpath(article_title_xpath),
                "Cannot find saved article"
        );
        this.waitForArticleToDisappearByTitle(article_title);
    }
}
