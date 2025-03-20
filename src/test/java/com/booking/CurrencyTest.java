package com.booking;

import com.booking.steps.CurrencyPageSteps;
import com.booking.steps.LandingPageSteps;
import com.booking.steps.SearchResultsPageSteps;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.booking.data.Constants.*;
import static com.codeborne.selenide.Selenide.open;

@Epic("Currency Selection")
public class CurrencyTest extends BaseTest {

    private final LandingPageSteps landingSteps = new LandingPageSteps();
    private final SearchResultsPageSteps searchResultsSteps = new SearchResultsPageSteps();
    private final CurrencyPageSteps currencySteps = new CurrencyPageSteps();

    @BeforeMethod
    public void openUrl() {
        open(BOOKING_URL);
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }

    @Story("User selects a currency and validates it")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verifies the ability to select and validate the Argentine Peso (AR$)")
    @Test(testName = "currencyTest")
    public void currencyTest() {
        landingSteps
                .validateHomePage()
                .removeGooglePopUp()
                .selectLocation(KAKHETI);
        searchResultsSteps
                .selectDates(SEVEN)
                .specifyTravelers(NUM_OF_ADULTS, NUM_OF_CHILDREN)
                .submitSearch();
        currencySteps
                .clickCurrencyBtn()
                .chooseARSPeso()
                .validateCurrency();
    }
}
