package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SavedArticlePageObject;

public class IOSSavedArticlePageObject extends SavedArticlePageObject
{
    static {
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeStaticText[@name='{TITLE}']";
        CLOSE_SYNC_BUTTON = "id:Close";
    }

    public IOSSavedArticlePageObject (AppiumDriver driver)
    {
        super(driver);
    }
}
