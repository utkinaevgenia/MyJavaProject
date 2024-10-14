package tests.iOS;

import lib.CoreTestCase;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest  extends CoreTestCase
{
    @Test
    public void testPassThroughWelcome ()
    {
        WelcomePageObject WelcomePage = new WelcomePageObject(driver);

        WelcomePage.waitForFirstPageTitle();
        WelcomePage.clickNextButton();

        WelcomePage.waitForSecondPageTitle();
        WelcomePage.clickNextButton();

        WelcomePage.waitForThirdPageTitle();
        WelcomePage.clickNextButton();

        WelcomePage.waitForForthPageTitle();
        WelcomePage.clickStartButton();
    }
}
