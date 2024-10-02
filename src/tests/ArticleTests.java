package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.OnbordingPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase
{
    @Test
    public void testCompareArticleTitle ()
    {
        OnbordingPageObject OnbordingPageObject = new OnbordingPageObject(driver);
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        OnbordingPageObject.clickToSkip();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Java (programming language)");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        String article_title = ArticlePageObject.getArticleTitle();

        assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                article_title
        );
    }

    @Test
    public void testFindArticlesAndCanselSearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        OnbordingPageObject OnbordingPageObject = new OnbordingPageObject(driver);
        OnbordingPageObject.clickToSkip();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Java");
        SearchPageObject.waitForSearchResult("Java (programming language)");
        SearchPageObject.waitForCanselButtonToAppear();
        SearchPageObject.clickCancelButton();
        SearchPageObject.waitForCanselButtonToDisappear();
    }

    @Test
    public void testAssertTitle()
    {
        OnbordingPageObject OnbordingPageObject = new OnbordingPageObject(driver);
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        OnbordingPageObject.clickToSkip();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Java (programming language)");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
//        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.assertTitleArticlePresent();
    }
}
