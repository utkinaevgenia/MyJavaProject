package tests;

import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;

public class SavedArticle extends CoreTestCase
{
    @Test
    public void testSaveFirstArticleToMyList ()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        OnbordingPageObject OnbordingPageObject = new OnbordingPageObject(driver);
        OnbordingPageObject.clickToSkip();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Java (programming language)");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Test";
        ArticlePageObject.addArticleToMyList(name_of_folder);

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.navigation_back();
        NavigationUI.navigation_back();
        NavigationUI.clickSaved();

        SavedArticlePageObject SavedArticlePageObject = new SavedArticlePageObject(driver);
        SavedArticlePageObject.openFolderByName(name_of_folder);
        SavedArticlePageObject.swipeByArticleToDelete(article_title);
    }
}
