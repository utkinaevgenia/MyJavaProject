package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject
{
    private static final String
    SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]",
    SEARCH_INPUT = "id:org.wikipedia:id/search_src_text",
    SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[contains(@text,'{SUBSTRING}')]",
    SEARCH_CANSEL_BUTTON = "id:org.wikipedia:id/search_close_btn",
    SEARCH_RESULT_LOCATOR = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']",
    SEARCH_EMPTY_RESULTS_LABEL = "xpath://*[@text='No results']";

    public SearchPageObject (AppiumDriver driver)
    {
        super(driver);
    }

    /*TEMPLATES METHODS */
    private static String getResultSearchElement (String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /*TEMPLATES METHODS */

    public void initSearchInput()
    {
        this.waitForElementPresent((SEARCH_INIT_ELEMENT),"Cannot find search input after clicking search init element", 5);
        this.waitForElementAndClick((SEARCH_INIT_ELEMENT),"Cannot find and click search init element", 5);
    }

    public void checkSearchInput()
    {
        this.waitForElementPresent((SEARCH_INIT_ELEMENT),"Cannot find search input", 5);
    }

    public void waitForCanselButtonToAppear ()
        {
            this.waitForElementPresent((SEARCH_CANSEL_BUTTON),"Cancel button is still present on page", 5);
        }

    public void clickCancelButton ()
    {
        this.waitForElementAndClick((SEARCH_CANSEL_BUTTON), "Cannot find and click cancel button", 5);
    }

    public void waitForCanselButtonToDisappear ()
    {
        this.waitForElementNotPresent((SEARCH_CANSEL_BUTTON),"Cannot find cancel button", 5);
    }

    public void typeSearchLine (String search_line)
    {
        this.waitForElementAndSentValue((SEARCH_INPUT), search_line, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent((search_result_xpath),"Cannot find search result with substring" + substring,5);
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(
                (search_result_xpath),
                "Cannot find and click search result with substring" + substring,
                15);
    }

    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent(
                (SEARCH_RESULT_LOCATOR),
                "Cannot find anything by the request ",
                25
        );
        return this.getAmountOfElements((SEARCH_RESULT_LOCATOR));
    }

    public void waitForEmptyResultsLabel()
    {
        this.waitForElementPresent(
                (SEARCH_EMPTY_RESULTS_LABEL),
                "Cannot find empty result label",
                15
        );
    }

    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent(
                (SEARCH_RESULT_LOCATOR),
                "We've found some results by request "
        );
    }

    public void assertSearchInputHasText()
    {
        this.asserElementHasText(
                (SEARCH_INPUT),
                "Field does not contain expected text",
                "Search Wikipedia",
                15
        );
    }
}
