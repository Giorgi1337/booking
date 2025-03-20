package com.booking.steps;

import com.booking.pages.LandingPage;
import io.qameta.allure.Step;

import static com.booking.data.Constants.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.switchTo;

public class LandingPageSteps {
    private final LandingPage landingPage = new LandingPage();

    @Step("Validate home page")
    public LandingPageSteps validateHomePage() {
        landingPage.homePageText.shouldBe(visible).shouldHave(text(OFFERS_TEXT));
        return this;
    }

    @Step("Remove Google Popup if present")
    public LandingPageSteps removeGooglePopUp() {
        if (landingPage.googleIframe.exists()) {
            switchTo().frame(landingPage.googleIframe);
            landingPage.closeGooglePopup.shouldBe(visible).click();
            switchTo().defaultContent();
        }
        return this;
    }


    @Step("Enter location: {place}")
    public LandingPageSteps selectLocation(String place) {
        landingPage.searchInput.setValue(place);
        landingPage.dropdown.shouldBe(appear);
        landingPage.dropdown.$$("[role='option']").findBy(text(place)).click();
        return this;
    }

}
