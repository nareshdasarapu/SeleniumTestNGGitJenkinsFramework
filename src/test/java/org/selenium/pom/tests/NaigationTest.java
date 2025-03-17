package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NaigationTest extends BaseTest {

    @Test(description = "User should be able to navigate to Store from Home using main menu")
    public void navigateFromHomeToStoreUsingMainMenu() {
        HomePage homePage = new HomePage(getDriver());
        homePage.load();
        StorePage storePage = homePage.getMyHeader().navigateToStoreUsingMenu();
        storePage.isLoaded();
        Assert.assertEquals(storePage.getTitle(), "Store");
    }

}
