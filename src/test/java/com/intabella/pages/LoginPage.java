package com.intabella.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LoginPage extends BasePage{

  @FindBy(id = "prependedInput")
  public WebElement usernameInput;

  @FindBy(id = "prependedInput2")
  public WebElement passwordInput;

  @FindBy(id = "_submit")
  public WebElement clickLogin;

  @FindBy(className = "breadcrumb")
  public WebElement breadcrumb;

  @FindBy(css = "#user-menu > a")
  public WebElement userName;

  @FindBy(linkText = "Logout")
  public WebElement logOutLink;

  @FindBy(xpath = "//div[@class='alert alert-error']")
  public WebElement invalidWarning;

  @FindBy(linkText = "Forgot your password?")
  public WebElement forgotPassword;

  @FindBy(css = "button[type='submit']")
  public WebElement request;

  @FindBy(xpath = "//div[@class='alert alert-warn']")
  public WebElement verifyMessage;

  @FindBy(id = "prependedInput")
  public WebElement requestInput;

  @FindBy(css = "span[class='custom-checkbox__icon']")
  public WebElement checkBox;



  public void login(String username, String password){
    usernameInput.sendKeys(username);
    passwordInput.sendKeys(password);

    }

  public void login2(String password){
    passwordInput.sendKeys(password);
  }

  public void login3(String userNameStr, String passwordStr) {
    usernameInput.sendKeys(userNameStr);
    passwordInput.sendKeys(passwordStr);
    clickLogin.click();
  }
}
