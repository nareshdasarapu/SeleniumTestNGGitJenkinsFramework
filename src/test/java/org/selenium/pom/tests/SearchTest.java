package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    @Test(description = "User should be able to search products using partial text")
    public void searchWithPartialText(){
        String searchFor = "Blue";

        StorePage storePage = new StorePage(getDriver()).load()
                .search(searchFor);
        Assert.assertTrue(storePage.waitForPageToLoadUsingURLContains("Blue&post_type=product"));
        Assert.assertEquals(storePage.getTitle(), "Search results: “" + searchFor + "”");
    }
}
