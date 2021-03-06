package com.prestashop.tests.functional_tests.cart;

import com.prestashop.pages.LoginPage;
import com.prestashop.pages.MyAccountPage;
import com.prestashop.pages.MyStorePage;
import com.prestashop.pages.ProductPage;
import com.prestashop.utilities.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.prestashop.pages.ProductPage.cartLogo;
import static com.prestashop.pages.ProductPage.viewCard;
import static com.prestashop.utilities.BrowserUtilities.open;

public class CartLogOutTests extends TestBase {
    /*
    1.Open browser
    2.Go to http://automationpractice.com/index.php
    3.Login as any valid user
    4.Go back to home page
    5.Add any product in the homepage to the cart
    6.Hover over the cart icon
    7.Verify that cart contains the product
    8.Log out
    9.Verify word empty is displayed in the Cart icon
    */
    LoginPage loginPage = new LoginPage();
    MyStorePage index = new MyStorePage();
    MyAccountPage myAccountPage = new MyAccountPage();
    ProductPage productPage = new ProductPage();
    @Test
    public void logOut () {
        //      task   1-2
        open ();
//      task   3
        index.signInButton.click();
        String emailAddress = faker.internet().emailAddress();
        loginPage.emailBox.sendKeys(emailAddress);
        loginPage.createAccountButton.click();
//        creating account details
        String firstName = faker.name().firstName();
        loginPage.firstNameBox.sendKeys(firstName);
//        to choose random gender
        int genderNumber = faker.number().numberBetween(1,2);
        loginPage.gender(genderNumber).click();
        loginPage.lastNameBox.sendKeys(faker.name().lastName());
        loginPage.passwordBox.sendKeys(faker.internet().password(5,8));
        loginPage.streetBox.sendKeys(faker.address().streetAddress());
        loginPage.cityBox.sendKeys(faker.address().city());
        loginPage.zipBox.sendKeys(""+faker.number().numberBetween(10000,99999));
        loginPage.mobilePhNumber.sendKeys(faker.phoneNumber().cellPhone());
        loginPage.aliasBox.sendKeys(faker.address().streetAddress());
        int options = loginPage.chooseState(loginPage.stateBox).getOptions().size();
        loginPage.chooseState(loginPage.stateBox).selectByIndex(faker.number().numberBetween(1,options));
        loginPage.registerButton.click();
//          4.Go back to home page
        myAccountPage.home.click();
//        tasks 5-6-7
        index.blouseLink.click();
        productPage.addToCartButton.click();
        productPage.crossButton.click();
//        move on the cart icon
        actions.moveToElement(cartLogo);
        String expected = "Blouse";
        String actual = productPage.productNameinCart.getAttribute("title");
        Assert.assertEquals(actual,expected,"please check if the item is in the cart");
//         8.Log out
        productPage.signOutButton.click();

        String expectedEmpty = "empty";
        String actualEmpty = viewCard.getText();
        System.out.println(actualEmpty);
        Assert.assertTrue(actualEmpty.contains(expectedEmpty),"please check if the item is in the cart it must be empty");



    }
}