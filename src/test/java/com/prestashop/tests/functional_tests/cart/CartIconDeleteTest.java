package com.prestashop.tests.functional_tests.cart;

import com.prestashop.pages.*;
import com.prestashop.utilities.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.prestashop.pages.ProductPage.viewCard;
import static com.prestashop.utilities.BrowserUtilities.open;

public class CartIconDeleteTest extends TestBase {

    public String cartExpected;
    public String cartActual;

    LoginPage loginPage = new LoginPage();
    ProductPage productPage = new ProductPage();
    MyStorePage index = new MyStorePage();
    MyAccountPage myAccountPage = new MyAccountPage();
    HomePage homePage = new HomePage();

    @Test(priority = 1)
    public void iconDelete() {
        open();

        index.blouseLink.click();
        productPage.addToCartButton.click();
        productPage.continueShop.click();
        myAccountPage.home.click();
        actions.moveToElement(viewCard).perform();

        productPage.removeBox.click();

        cartActual= homePage.checkProductInTheCart("Blouse").getText();
        cartExpected= "Blouse";
        Assert.assertEquals(cartActual, cartExpected);

    }

    @Test (priority = 2)
    public void cartIconDeleteTest(){
        open();

        actions.moveToElement(homePage.hoverOver("Printed Dress", 1)).perform();
        homePage.addToCard(3).click();

        homePage.continueShopping.click();

        actions.moveToElement(homePage.hoverOver).perform();

        homePage.deleteFromCart.click();

        homePage.hoverOver.click();
        cartExpected="empty";
        cartActual=homePage.hoverOver.getText();
        Assert.assertTrue(cartActual.contains(cartExpected));

    }
    @Test(priority = 3)
    public void cartCheckouteleteTest() {
        open();

        actions.moveToElement(homePage.hoverOver("Printed Dress", 1)).perform();
        homePage.addToCard(3).click();

        homePage.continueShopping.click();

        actions.moveToElement(homePage.hoverOver("Printed Summer Dress", 1)).perform();
        homePage.addToCard(5).click();

        homePage.proceedToCheckout.click();

        cartExpected = "Your shopping cart contains: 2 Products";
        cartActual = homePage.verifyMessageforProduct.getText();
        Assert.assertEquals(cartActual, cartExpected);

        homePage.cartQuantityDelete(1).click();

        homePage.hoverOver.click();
        cartExpected = "Your shopping cart contains: 1 Product";
        cartActual = homePage.verifyMessageforProduct.getText();
        Assert.assertEquals(cartActual, cartExpected);

        homePage.cartQuantityDelete(1).click();

        homePage.hoverOver.click();
        cartExpected = "Your shopping cart is empty.";
        cartActual = homePage.getVerifyMessageForEmpty.getText();
        Assert.assertTrue(cartActual.contains(cartExpected));
    }
}


