package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject
{
    static {
        TITLE = "xpath://*[contains(@text,'Java (programming language)')]";
        ADD_TO_LIST_SAVE_BUTTON = "id:org.wikipedia:id/page_save";
        ADD_TO_LIST_BUTTON = "xpath://*[@text='Add to list']";
        MY_LIST_TEXT_INPUT = "id:org.wikipedia:id/text_input";
        MY_LIST_OK_BUTTON = "xpath://*[@text='OK']";
    }

    public AndroidArticlePageObject (AppiumDriver driver) {
        super(driver);
    }
}
