package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class IOSArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "id:Java (programming language)";
        ADD_TO_LIST_SAVE_BUTTON = "id:Save for later";
    }

    public IOSArticlePageObject (AppiumDriver driver) {
        super(driver);
    }
}
