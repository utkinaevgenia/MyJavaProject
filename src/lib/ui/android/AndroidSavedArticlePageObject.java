package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SavedArticlePageObject;

public class AndroidSavedArticlePageObject extends SavedArticlePageObject
{
    static {
        NAME_OF_FOLDER_TPL = "xpath://*[@text='{FOLDER_NAME}']";
        ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{TITLE}']";
    }

    public AndroidSavedArticlePageObject (AppiumDriver driver)
    {
        super(driver);
    }
}
