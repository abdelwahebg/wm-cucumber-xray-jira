package com.java.mobility.tests;

import cucumber.api.java.en.*;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import java.util.Map;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.*;

import com.qainfotech.ta.framework.TestSession;

import com.walmart.mobility.test.dsl.mobileui.myproductivity.*;
import com.walmart.mobility.test.dsl.mobileui.applications.*;
import com.walmart.mobility.test.dsl.mobileui.applications.availability.*;


/**
 *
 * @author Ramandeep <RamandeepSingh@QAInfoTech.com>
 */
public class StepDefinitions {

    TestSession session;
    LandingPage myProductivityLandingPage;
    LandingPageErrorPrompt myProductivityLandingPageErrorPrompt;
    ApplicationMenu applicationMenu;
    AvailabilityApp availabilityApp;
    
    @Before
    public void setUp() throws Exception{
        Map<String, Object> options = new HashMap();
        options.put("APPIUM_APP_NO_RESET", true);
        session = new TestSession(options);        
    }
    
    @After
    public void tearDown() throws Exception{
        session.quit();
    }
    
    @Given("^I am on My Productivity application landing page$")
    public void i_am_on_My_Productivity_application_landing_page() throws Throwable {
        myProductivityLandingPage = new LandingPage(session);
    }

    @When("^I sign in with invalid credentials$")
    public void i_sign_in_with_invalid_credentials(Map<String,String> credentials) throws Throwable {
        myProductivityLandingPageErrorPrompt = myProductivityLandingPage.submitSignInFormWithInvalidCredentials(credentials);
    }

    @Then("^error message is displayed$")
    public void error_message_is_displayed() throws Throwable {
        assertThat(myProductivityLandingPageErrorPrompt.isDisplayed()).isTrue();
    }

    @Then("^error text contains \"([^\"]*)\"$")
    public void error_text_contains(String arg1) throws Throwable {
        assertThat(myProductivityLandingPageErrorPrompt.getMessage()).contains(arg1);
    }

    @When("^I confirm the error message$")
    public void i_confirm_the_error_message() throws Throwable {
        myProductivityLandingPage = myProductivityLandingPageErrorPrompt.confirmPrompt();
    }

    @Then("^error message disappears$")
    public void error_message_disappears() throws Throwable {
        assertThat(myProductivityLandingPage.isDisplayed()).isTrue();
    }
    
    
    @When("^I sign in with valid credentials$")
    public void i_sign_in_with_valid_credentials(Map<String, String> credentials) throws Throwable {
        applicationMenu = myProductivityLandingPage.signInWithValidCredentials(credentials);
    }

    @When("^I have logged on My Productivity application using$")
    public void i_have_logged_on_My_Productivity_application_using_credentials(Map<String, String> credentials) throws Throwable {
        myProductivityLandingPage = new LandingPage(session);
        applicationMenu = myProductivityLandingPage.signInWithValidCredentials(credentials);
    }    
    
    @Then("^Application Menu is displayed$")
    public void application_Menu_is_displayed() throws Throwable {
        assertThat(applicationMenu.isDisplayed()).isTrue();
    }

    @Then("^Application Menu has \"([^\"]*)\" applications$")
    public void application_Menu_has_applications(String arg1) throws Throwable {
        assertThat(applicationMenu.apps().size()).isEqualTo(Integer.parseInt(arg1));
    }

    @When("^I sign out$")
    public void i_sign_out() throws Throwable {
        myProductivityLandingPage = applicationMenu.signOut();
    }

    @Then("^My Productivity application landing page is displayed$")
    public void my_Productivity_application_landing_page_is_displayed() throws Throwable {
        assertThat(myProductivityLandingPage.isDisplayed()).isTrue();
    }
    

    @When("^I launch \"([^\"]*)\" app$")
    public void i_launch_app(String arg1) throws Throwable {
        availabilityApp = (AvailabilityApp)applicationMenu.launchApp(arg1);
    }

    @Then("^Landing page is displayed$")
    public void landing_page_is_displayed() throws Throwable {
        assertThat(availabilityApp.isDisplayed()).isTrue();
    }

    @Then("^My OSCA Score is displayed on Landing Page$")
    public void my_OSCA_Score_is_displayed_on_Landing_Page() throws Throwable {
        assertThat(availabilityApp.myOSCAScoreCard().title()).isEqualTo("Your OSCA Score");
    }

    @Then("^first market card is for store ([^\"]*)$")
    public void first_market_card_is_for_store(String arg1) throws Throwable {
        assertThat(availabilityApp.marketCards().get(0).title()).endsWith(arg1);
    }

    @When("^I exit app$")
    public void i_exit_app() throws Throwable {
        availabilityApp.exit();
    }
}