package com.prestashop.tests.functional_tests.cart;

import com.prestashop.pages.LoginPage;
import com.prestashop.pages.MyStorePage;
import com.prestashop.pages.ProductPage;
import com.prestashop.utilities.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.prestashop.pages.ProductPage.cartLogo;
import static com.prestashop.utilities.BrowserUtilities.open;

public class CartLoginTest extends TestBase {

    MyStorePage index = new MyStorePage();
    ProductPage productPage = new ProductPage();
    LoginPage loginPage = new LoginPage();
//    Actions actions = new Actions (Driver.getDriver());

    @Test
    public void loginTest (){
        open ();
        index.blouseLink.click();
        productPage.addToCartButton.click();
       productPage.crossButton.click();
//        move on the cart icon
        actions.moveToElement(cartLogo);
        String expected = "Blouse";
        String actual = ProductPage.productNameinCart.getAttribute("title");
//        System.out.println(productPage.productNameinCart.getAttribute("title"));
//        5.Verify that cart contains the product
        Assert.assertEquals(actual,expected,"please check if the item is in the cart");
//        sig in
        productPage.signInButton.click();
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
//        faker.finance().creditCard();
//        faker.number().numberBetween(500,999);
//        faker.number().digits(16);
//        faker.address().streetAddress();



        int options = loginPage.chooseState(loginPage.stateBox).getOptions().size();
        loginPage.chooseState(loginPage.stateBox).selectByIndex(faker.number().numberBetween(1,options));
        loginPage.registerButton.click();
        actions.moveToElement(cartLogo);
        //        Verify that cart information is same as step 5
        Assert.assertEquals(actual,expected,"please check if the item is in the cart");








    }



}
