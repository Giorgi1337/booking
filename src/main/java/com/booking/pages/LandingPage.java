package com.booking.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class LandingPage {
    public SelenideElement
            searchInput = $("[name='ss']"),
            dropdown = $("#autocomplete-results"),
            homePageText = $x("//h2/div[text()='Offers']"),
            googleIframe = $("iframe[src*='accounts.google.com/gsi/iframe/select']"),
            closeGooglePopup = $("#close");
}
