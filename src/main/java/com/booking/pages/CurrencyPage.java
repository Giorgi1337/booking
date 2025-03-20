package com.booking.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class CurrencyPage {
    public SelenideElement
            currencyBtn = $x("//button[@data-testid='header-currency-picker-trigger' and @type='button' and @aria-haspopup='dialog']"),
            currencyPopUp = $x("//div[@class='ffd93a9ecb dc19f70f85']//h2[text()='Select your currency']"),
            argentinePeso = $x("//li[button//span[contains(text(), 'Argentine Peso')]//div[contains(text(), 'ARS')]]");

    public ElementsCollection
            prizeList = $$x("//span[@data-testid='price-and-discounted-price']");
}