package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.*;
import org.junit.Test;

public class SavedArticle extends CoreTestCase
{
    private static final String name_of_folder = "Test";

    @Test
    public void testSaveFirstArticleToMyList ()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        OnbordingPageObject OnbordingPageObject = OndordingPageObjectFactory.get(driver);
        OnbordingPageObject.clickToSkip();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Java (programming language)");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String article_title = ArticlePageObject.getArticleTitle();
        if (Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticleToSaved();
        }
        NavigationUIPageObject NavigationUIPageObject = NavigationUIPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()){
            NavigationUIPageObject.navigation_back();
            NavigationUIPageObject.navigation_back();
        } else {
            NavigationUIPageObject.navigation_back();
            NavigationUIPageObject.clickCancel();
        }
        NavigationUIPageObject.clickSaved();

        SavedArticlePageObject SavedArticlePageObject = SavedArticlePageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid())
        {
            SavedArticlePageObject.openFolderByName(name_of_folder);
        }
        SavedArticlePageObject.swipeByArticleToDelete(article_title);
    }
}
