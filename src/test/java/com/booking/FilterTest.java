package com.booking;

import com.booking.steps.FilterPageSteps;
import com.booking.steps.LandingPageSteps;
import com.booking.steps.SearchResultsPageSteps;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.booking.data.Constants.*;
import static com.codeborne.selenide.Selenide.open;

public class FilterTest extends BaseTest {

    private final LandingPageSteps landingSteps = new LandingPageSteps();
    private final SearchResultsPageSteps searchResultsSteps = new SearchResultsPageSteps();
    private final FilterPageSteps filterSteps = new FilterPageSteps();

    @BeforeMethod
    public void openUrl() {
        open(BOOKING_URL);
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }

    @Story("User applies filters to refine search results by rating")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verifies the ability to select and validate filters for refining search results based on rating")
    @Test(testName = "filterTest")
    public void filterTest() {
        landingSteps
                .validateHomePage()
                .removeGooglePopUp()
                .selectLocation(BERLIN);
        searchResultsSteps
                .selectDates()
                .specifyTravelers(NUM_OF_ADULTS)
                .submitSearch();
        filterSteps
                .selectFilters(STARS, RATING)
                .validateLabels();
    }
}
