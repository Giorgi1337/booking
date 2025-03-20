package com.booking.steps;

import com.booking.pages.CurrencyPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class CurrencyPageSteps {
    private final CurrencyPage currencyPage = new CurrencyPage();

    @Step("Click currency button")
    public CurrencyPageSteps clickCurrencyBtn() {
        executeJavaScript("arguments[0].click();", currencyPage.currencyBtn);
        currencyPage.currencyPopUp.shouldBe(visible);
        return this;
    }

    @Step("Choose Argentine Peso (AR$)")
    public CurrencyPageSteps chooseARSPeso() {
        currencyPage.argentinePeso.shouldBe(clickable).click();
        return this;
    }

    @Step("Validate currency display: AR$")
    public CurrencyPageSteps validateCurrency() {
        currencyPage.prizeList.forEach(element ->
                element.shouldBe(visible).shouldHave(text("AR$"))
        );

        return this;
    }
}
