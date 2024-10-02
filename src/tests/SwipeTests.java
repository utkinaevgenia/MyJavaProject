package tests;

import lib.CoreTestCase;
import lib.ui.OnbordingPageObject;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;

public class SwipeTests extends CoreTestCase
{
    @Test
    public void testSwipeOnbording () {
        OnbordingPageObject OnbordingPageObject = new OnbordingPageObject(driver);
        OnbordingPageObject.waitToSkip();
        OnbordingPageObject.swipeOnbording();
    }

    @Test
    public void testSwipeOnbordingLeftAndCheckTitle() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        OnbordingPageObject OnbordingPageObject = new OnbordingPageObject(driver);
        OnbordingPageObject.waitToSkip();
        OnbordingPageObject.oneSwipeLeft(200);
        String title_first_screen = OnbordingPageObject.getScreenTitleAtribute();
        Assert.assertEquals(
                "Unexpected title of first screen",
                "New ways to explore",
                title_first_screen
        );
        OnbordingPageObject.oneSwipeLeft(200);
        String title_second_screen = OnbordingPageObject.getScreenTitleAtribute();
        Assert.assertEquals(
                "Unexpected title of second screen",
                "Reading lists with sync",
                title_second_screen
        );
        OnbordingPageObject.oneSwipeLeft(200);
        String title_third_screen = OnbordingPageObject.getScreenTitleAtribute();
        Assert.assertEquals(
                "Unexpected title of third screen",
                "Data & Privacy",
                title_third_screen
        );
        OnbordingPageObject.clickToStartOnOnbording();
        SearchPageObject.checkSearchInput();
    }
}
