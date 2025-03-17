package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.User;

import java.time.Duration;
import java.util.List;

public class CheckoutPage extends BasePage {

    private final By firstNameFld = By.id("billing_first_name");
    private final By lastNameFld = By.id("billing_last_name");
    private final By countryDropdown = By.id("billing_country");
    private final By addressline1Fld = By.id("billing_address_1");
    private final By cityFld = By.id("billing_city");
    public final By stateDropdown = By.id("billing_state");
    private final By postalCodeFld = By.id("billing_postcode");
    private final By emailFld = By.id("billing_email");
    private final By placeOrderBtn = By.id("place_order");
    private final By successNotice = By.cssSelector(".woocommerce-notice");
    private final By showLoginLink = By.className("showlogin");
    private final By userNameFld = By.id("username");
    private final By passwordFld = By.id("password");
    private final By loginBtn = By.name("login");
    private final By overlay = By.cssSelector(".blockUI.blockOverlay");
    private final By directBankTransferRadioBtn = By.xpath("//input[@id='payment_method_bacs'][@value='bacs']");
    private final By productName = By.cssSelector("td[class='product-name']");


    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage load(){
        load("/checkout/");
        return this;
    }

    public CheckoutPage inputFirstNameFld(String firstName){
        WebElement firstNameFldElement = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameFld));
        firstNameFldElement.clear();
        firstNameFldElement.sendKeys(firstName);
        return this;
    }

    public CheckoutPage inputLastNameFld(String lastName){
        WebElement lastNameFldElement = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameFld));
        lastNameFldElement.clear();
        lastNameFldElement.sendKeys(lastName);
        return this;
    }

    public CheckoutPage selectCountry(String countryName){
        WebElement countryDropdownFldElement = wait.until(ExpectedConditions.elementToBeClickable(countryDropdown));
        Select countryDropdownFld = new Select(countryDropdownFldElement);
        countryDropdownFld.selectByVisibleText(countryName);
        return this;
    }

    public CheckoutPage inputAddressLine1Fld(String addressLine1){
        waitForOverlaysToDisappear(overlay);
        WebElement addressLineOneFldElement = wait.until(ExpectedConditions.visibilityOfElementLocated(addressline1Fld));
        addressLineOneFldElement.clear();
        addressLineOneFldElement.sendKeys(addressLine1);
        return this;
    }

    public CheckoutPage inputCity(String city){
        WebElement cityFldElement = wait.until(ExpectedConditions.visibilityOfElementLocated(cityFld));
        cityFldElement.clear();
        cityFldElement.sendKeys(city);
        return this;
    }

    public CheckoutPage selectState(String stateName){
        WebElement stateDropdownFldElement = wait.until(ExpectedConditions.elementToBeClickable(stateDropdown));
        Select stateDropdownFld = new Select(stateDropdownFldElement);
        stateDropdownFld.selectByVisibleText(stateName);
        return this;
    }

    public CheckoutPage inputPostalCodeFld(String postalCode){
        WebElement postalCodeFldElement = wait.until(ExpectedConditions.visibilityOfElementLocated(postalCodeFld));
        postalCodeFldElement.clear();
        postalCodeFldElement.sendKeys(postalCode);
        return this;
    }

    public CheckoutPage inputEmailFld(String email){
        waitForOverlaysToDisappear(overlay);
        WebElement emailFldElement = wait.until(ExpectedConditions.visibilityOfElementLocated(emailFld));
        emailFldElement.clear();
        emailFldElement.sendKeys(email);
        return this;
    }

    public CheckoutPage setBillingAddress(BillingAddress billingAddress){
        inputFirstNameFld(billingAddress.getFirstName())
                .inputLastNameFld(billingAddress.getLastName())
                .selectCountry(billingAddress.getCountryName())
                .inputAddressLine1Fld(billingAddress.getAddressLineOne())
                .inputCity(billingAddress.getCity())
                .selectState(billingAddress.getStateName())
                .inputEmailFld(billingAddress.getEmail())
                .inputPostalCodeFld(billingAddress.getPostalCode());

        return this;
    }

    public CheckoutPage placeOrder(){
        waitForOverlaysToDisappear(overlay);
        wait.until(ExpectedConditions.visibilityOfElementLocated(placeOrderBtn));
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderBtn));
        driver.findElement(placeOrderBtn).click();
        return this;
    }

    public String getSuccessNotice(){
        waitForOverlaysToDisappear(overlay);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successNotice)).getText();
    }

    public CheckoutPage clickLoginLink(){
        wait.until(ExpectedConditions.elementToBeClickable(showLoginLink)).click();
        return this;
    }

    public CheckoutPage inputUserNameFld(String userName){
        wait.until(ExpectedConditions.visibilityOfElementLocated(userNameFld)).sendKeys(userName);
        return this;
    }

    public CheckoutPage inputPasswordFld(String password){
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordFld)).sendKeys(password);
        return this;
    }

    public CheckoutPage clickLoginBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
        return this;
    }

    private CheckoutPage waitForLoginButtonToDisappear(){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loginBtn));
        return this;
    }

    public CheckoutPage login(User user){
        inputUserNameFld(user.getUserName());
        inputPasswordFld(user.getPassword());
        clickLoginBtn().waitForLoginButtonToDisappear();
        return this;
    }

    public CheckoutPage selectDirectBankTransfer(){
        WebElement directBankTransferElement = wait.until(ExpectedConditions.elementToBeClickable(directBankTransferRadioBtn));
        if(!directBankTransferElement.isSelected()){
            directBankTransferElement.click();
        }
        return this;
    }

    public String getProductName(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productName)).getText();
    }
}
