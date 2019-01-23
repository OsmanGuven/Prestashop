package com.prestashop.pages;

import com.prestashop.utilities.ConfigurationReader;
import com.prestashop.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    public HomePage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id="ctl00_MainContent_username")
    public WebElement username;

    @FindBy(id="ctl00_MainContent_password")
    public WebElement password;


    @FindBy(id="ctl00_MainContent_login_button")
    public WebElement loginButton;

    @FindBy(id="ctl00_MainContent_status")
    public WebElement errorMessage;

    //Hover over to the Cart icon
    @FindBy(xpath="//a[@title='View my shopping cart']")
    public WebElement hoverOver;

    //Verify emty message
    @FindBy(xpath="//p[.='Your shopping cart is empty.']")
    public WebElement getVerifyMessageForEmpty;

    //Delete from the Cart
    @FindBy(xpath="//a[@class='ajax_cart_block_remove_link']")
    public WebElement deleteFromCart;

    //Close Pup-up window after adding the product
    @FindBy(xpath="//span[@title='Close window']")
    public WebElement closePupupWindow;

    //Go back to home page
    @FindBy(linkText = "Home")
    public WebElement goToHomePage;

    //Log out //a[@class='logout']
    @FindBy(xpath="//a[@class='logout']")
    public WebElement logout;

    //Continue shopping
    @FindBy(xpath="//span[@title='Continue shopping']")
    public WebElement continueShopping;

    //Proceed to checkout
    @FindBy(xpath="//a[@title='Proceed to checkout']")
    public WebElement proceedToCheckout;



    //Hover over to the add to card
    public WebElement hoverOver(String productName, int position){
        String xpath = "(//h5/a[@title='"+productName+"'])["+position+"]";
        return Driver.getDriver().findElement(By.xpath(xpath));
    }


    public WebElement addToCard(int orderNumber){
        String xpath = "//a[@data-id-product='"+orderNumber+"']";
        return Driver.getDriver().findElement(By.xpath(xpath));
    }

    //Check inside the cart if product  text is there
    public WebElement checkProductInTheCart(String prdName){
        String xpath = "//a[.='"+prdName+"']";
        return Driver.getDriver().findElement(By.xpath(xpath));
    }

    //Cart quantity delete
    public WebElement cartQuantityDelete(int putOrderNumber){
        String xpath = "(//a[@class='cart_quantity_delete'])["+putOrderNumber+"]";
        return Driver.getDriver().findElement(By.xpath(xpath));
    }

    //Your shopping cart contains:
    @FindBy(xpath="//span[@class='heading-counter']")
    public WebElement verifyMessageforProduct;



    public void login(String username, String password){
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        loginButton.click();
    }

    public void open(){
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));

    }


}
