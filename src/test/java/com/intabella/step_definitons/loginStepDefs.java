package com.intabella.step_definitons;

import com.intabella.pages.LoginPage;
import com.intabella.utilities.BrowserUtils;
import com.intabella.utilities.ConfigurationReader;
import com.intabella.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class loginStepDefs {
    LoginPage loginPage = new LoginPage();
    String copiedUrl;

    @Given("User on the Login Page")
    public void user_on_the_Login_Page() {
        String url = ConfigurationReader.get("url");
        Driver.get().get(url);
    }

    @When("User enters valid credentials for {string}")
    public void userEntersValidCredentialsFor(String UserType) {
        BrowserUtils.waitFor(2);
        String username =null;
        String password =null;

        if(UserType.equals("Driver")){
            username = ConfigurationReader.get("driver_username");
            password = ConfigurationReader.get("driver_password");
        }else if(UserType.equals("Sales Manager")){
            username = ConfigurationReader.get("sales_manager_username");
            password = ConfigurationReader.get("sales_manager_password");
        }else if(UserType.equals("Store Manager")){
            username = ConfigurationReader.get("store_manager_username");
            password = ConfigurationReader.get("store_manager_password");
        }
            loginPage.login(username,password);
    }

    @When("the user logs in using following credentials")
    public void the_user_logs_in_using_following_credentials(Map<String,String> userInfo) {
        System.out.println(userInfo);
        //use map information to login and also verify firstname and lastname
        //login with map info
        loginPage.login3(userInfo.get("username"),userInfo.get("password"));
        //verify firstname and lastname
        String actualName = loginPage.getUserName();
        String expectedName = userInfo.get("firstname")+" "+ userInfo.get("lastname");
        System.out.println("expectedName = " + expectedName);
        System.out.println("actualName = " + actualName);
        Assert.assertEquals(expectedName,actualName);


    }

    @When("User clicks Login Button")
    public void user_clicks_Login_Button() {
        loginPage.clickLogin.click();

    }

    @Then("User should be able to login and {string} should be displayed")
    public void user_should_be_able_to_login_and_should_be_displayed(String Subtitle) {
        BrowserUtils.waitFor(2);
        String actualSubtitle = loginPage.pageSubTitle.getText();
        Assert.assertEquals(Subtitle,actualSubtitle);
    }


    @Given("the user logged in as {string}")
    public void theUserLoggedInAs(String StoreManager) {
        Driver.get().get(ConfigurationReader.get("url"));
        String username = ConfigurationReader.get("store_manager_username");
        String password = ConfigurationReader.get("store_manager_password");
        loginPage.login(username,password);
        user_clicks_Login_Button();
    }

    @Then("the user should see following modules")
    public void theUserShouldSeeFollowingModules(List<String> menuOptions) {
        BrowserUtils.waitFor(2);
        //get the list of webelement and convert them to list of string and assert
        List<String> actualOptions = BrowserUtils.getElementsText(loginPage.menuOptions);

        Assert.assertEquals(menuOptions,actualOptions);
        System.out.println("menuOptions = " + menuOptions);
        System.out.println("actualOptions = " + actualOptions);



    }

    @And("verify the {string}, {string} {string}, {string}")
    public void verifyThe(String Breadcrumb,String PageSubtitle, String pageUrl, String pageTitle) {
        BrowserUtils.waitFor(2);
        Assert.assertEquals(Breadcrumb,loginPage.breadcrumb.getText());
        Assert.assertEquals(PageSubtitle,loginPage.pageSubTitle.getText());
        Assert.assertEquals(pageUrl,Driver.get().getCurrentUrl());
        Assert.assertEquals(pageTitle,Driver.get().getTitle());
    }

    @Then("the user navigates to {string} {string}")
    public void the_user_navigates_to(String tab, String module) {
        loginPage.navigateToModule(tab,module);
    }

    @When("the user get the current url")
    public void the_user_get_the_current_url() {
        copiedUrl=Driver.get().getCurrentUrl();
    }

    @Then("the user paste url")
    public void theUserPasteUrl() {
        BrowserUtils.waitFor(2);
        Driver.get().navigate().to(copiedUrl);
    }

    @When("the user logs out")
    public void the_user_logs_out() {
        loginPage.logOut();
    }

    @Then("the user paste url and should not get in")
    public void the_user_paste_url_and_should_not_get_in() {
        Driver.closeDriver();
        Driver.get().get(copiedUrl);
        BrowserUtils.waitFor(2);
        System.out.println("copiedUrl = " + copiedUrl);
        String currentUrl = Driver.get().getCurrentUrl();
        System.out.println("currentUrl = " + currentUrl);
        Assert.assertNotEquals(copiedUrl,currentUrl);
    }

    @And("open a new tab and close the old one and paste the url and get in")
    public void openANewTabAndCloseTheOldOne() {
        System.out.println("copiedUrl = " + copiedUrl);
        ((JavascriptExecutor)Driver.get()).executeScript("window.open()");
        BrowserUtils.waitFor(3);
        Driver.get().close();
        ArrayList<String> tabs = new ArrayList<String>(Driver.get().getWindowHandles());
        Driver.get().switchTo().window(tabs.get(0));
        Driver.get().navigate().to(copiedUrl);
        BrowserUtils.waitFor(2);
        String currentUrl = Driver.get().getCurrentUrl();
        System.out.println("currentUrl = " + currentUrl);
        Assert.assertEquals(copiedUrl,currentUrl);
    }

    @When("the user logs in with {string} and {string}")
    public void theUserLogsInWithAnd(String username, String password) {
        loginPage.login(username,password);
    }

    @Then("the user should able to login to {string} page")
    public void theUserShouldAbleToLoginToPage(String Subtitle) {
        BrowserUtils.waitFor(2);
        String actualSubtitle = loginPage.pageSubTitle.getText();
        System.out.println("actualSubtitle = " + actualSubtitle);
        System.out.println("Subtitle = " + Subtitle);
        Assert.assertEquals(Subtitle,actualSubtitle);
    }

    @When("input box has {string} and password box has {string} as placeholder")
    public void inputBoxHasAndPasswordBoxHasAsPlaceholder(String inputPlaceholder, String passwordPlaceholder) {

        Assert.assertEquals(inputPlaceholder,loginPage.usernameInput.getAttribute("placeholder"));
        Assert.assertEquals(passwordPlaceholder,loginPage.passwordInput.getAttribute("placeholder"));

    }

    @When("the user enters invalid or {string} and {string}")
    public void theUserEntersInvalidOrAnd(String username, String password) {
    loginPage.login(username,password);

    }

    @Then("warning {string} should be displayed")
    public void warningShouldBeDisplayed(String message) {
        if(message.equals("Invalid user name or password.")){
            String actualInputMessage= loginPage.invalidWarning.getText();
            System.out.println("actualInputMessage = " + actualInputMessage);
            Assert.assertEquals(message,loginPage.invalidWarning.getText());
        }else if((message.equals("Please fill out this field."))){
            String inputWarning = loginPage.usernameInput.getAttribute("validationMessage");
            String passwordWarning = loginPage.passwordInput.getAttribute("validationMessage");
            System.out.println("inputWarning = " + inputWarning);
            System.out.println("passwordWarning = " + passwordWarning);

            if(loginPage.usernameInput.getAttribute("value")==null && loginPage.passwordInput.getAttribute("value")==null)
                Assert.assertEquals(message,inputWarning);
            else if(loginPage.usernameInput.getAttribute("value")==null)
                Assert.assertEquals(message,inputWarning);
            else if(loginPage.passwordInput.getAttribute("value")==null)
                Assert.assertEquals(message,passwordWarning);
        }

    }

    @Then("the password box should hide the text with dots")
    public void thePasswordBoxShouldHideTheTextWithDots() {
        String passwordText = loginPage.passwordInput.getAttribute("type");
        System.out.println("passwordText = " + passwordText);
        Assert.assertEquals("password",passwordText);

    }

    @Then("the Password is not visible in the Page Source")
    public void thePasswordIsNotVisibleInThePageSource() {
        String pageSource = Driver.get().getPageSource();
        System.out.println("pageSource = " + pageSource);
        Assert.assertFalse(pageSource.contains("UserUser123"));
    }

    @When("the user enters password {string}")
    public void theUserEntersPassword(String password) {
        loginPage.login2(password);
    }

    @Then("the Password can not be copied")
    public void thePasswordCanNotBeCopied() {
        loginPage.passwordInput.click();
        loginPage.passwordInput.sendKeys(Keys.CONTROL,"a");
        System.out.println(loginPage.passwordInput.getAttribute("value"));
        BrowserUtils.waitFor(3);
        loginPage.passwordInput.sendKeys(Keys.CONTROL,"c");
        BrowserUtils.waitFor(3);
        loginPage.usernameInput.click();
        loginPage.usernameInput.clear();
        loginPage.usernameInput.sendKeys(Keys.CONTROL,"v");
        Assert.assertNotEquals(loginPage.passwordInput.getAttribute("value"),loginPage.usernameInput.getAttribute("value"));

    }


    @When("the user clicks on the Forgot your password link")
    public void theUserClicksOnTheForgotYourPasswordLink() {
        loginPage.forgotPassword.click();
    }

    @Then("the user should navigate to {string} page")
    public void theUserShouldNavigateToPage(String expectedTitle) {
        System.out.println(Driver.get().getTitle());
        Assert.assertEquals(expectedTitle, Driver.get().getTitle());
    }

    @And("the user enter username {string} into the box and click Request button")
    public void theUserEnterUsernameIntoTheBoxAndClickRequestButton(String username) {
        loginPage.requestInput.sendKeys(username);
        loginPage.request.click();
    }

    @Then("the user should be able to change the password")
    public void theUserShouldBeAbleToChangeThePassword() {
        String verifyMessage = loginPage.verifyMessage.getText();
        Assert.assertEquals("New password send to the email.",verifyMessage);
    }

    @Then("Remember me on this computer link is clickable")
    public void rememberMeOnThisComputerLinkIsClickable() {
        loginPage.checkBox.click();
        try {
            new WebDriverWait(Driver.get(), 5).until(ExpectedConditions.elementToBeClickable(loginPage.checkBox));
            System.out.println("Element is clickable");
        }
        catch(TimeoutException e) {
            System.out.println("Element isn't clickable");
        }

    }

    @Then("verify hex code of the background color of the log in button is {string}")
    public void verifyHexCodeOfTheBackgroundColorOfTheLogInButtonIs(String expected) {
        String backgroundColor = loginPage.clickLogin.getCssValue("background-color");
        System.out.println("backgroundColor = " + backgroundColor);
        String HexCodeBackground = Color.fromString(backgroundColor).asHex();
        System.out.println("HexCodeBackground = " + HexCodeBackground);
        Assert.assertEquals(expected,HexCodeBackground);
    }

    @When("the user enters username {string} and hit the Enter key")
    public void theUserEntersUsernameAndHitTheEnterKey(String username) {
        loginPage.usernameInput.sendKeys(username,Keys.ENTER);
    }

    @When("the user enters password {string} and hit the Enter key")
    public void theUserEntersPasswordAndHitTheEnterKey(String password) {
        loginPage.passwordInput.sendKeys(password,Keys.ENTER);
    }

    @When("the user enters username {string} and hit the Tab key")
    public void theUserEntersUsernameAndHitTheTabKey(String username) {
        loginPage.usernameInput.sendKeys(username,Keys.TAB);
    }

}
