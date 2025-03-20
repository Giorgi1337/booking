package com.booking.steps;

import com.booking.pages.SearchResultsPage;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static com.booking.utils.Utils.adjustTravelerCount;
import static com.codeborne.selenide.Condition.*;

public class SearchResultsPageSteps {
    private final SearchResultsPage searchResultsPage = new SearchResultsPage();

    @Step("Submit search")
    public SearchResultsPageSteps submitSearch() {
        searchResultsPage.searchButton.shouldBe(clickable).click();
        return this;
    }

    @Step("Select check-in and check-out dates (Check-in: Today, Check-out: {daysToAddForCheckOut} days later)")
    public SearchResultsPageSteps selectDates() {
        return selectDates(10); // Default check-out is 10 days from today
    }

    @Step("Select check-in and check-out dates (Check-in: Today, Check-out: {daysToAddForCheckOut} days later)")
    public SearchResultsPageSteps selectDates(int daysToAddForCheckOut) {
        LocalDate today = LocalDate.now();

        // Format today's date for check-in and check-out
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault());
        String checkInDateString = today.format(dateFormatter);
        String checkOutDateString = today.plusDays(daysToAddForCheckOut).format(dateFormatter);

        searchResultsPage.datePickerOpenButton.shouldBe(clickable).click();

        // Select check-in and check-out dates
        searchResultsPage.getDateElement(checkInDateString).click();
        searchResultsPage.getDateElement(checkOutDateString).click();
        return this;
    }

    @Step("Specify travelers: {adultCount} adults, {childCount} children")
    public SearchResultsPageSteps specifyTravelers(int adultCount, int childCount) {
        searchResultsPage.occupantConfigButton.shouldBe(clickable).click();
        searchResultsPage.occupantPopup.shouldBe(visible);

        // Adjust the number of adults
        searchResultsPage.adultCounter.shouldBe(visible);
        int currentAdultCount = Integer.parseInt(searchResultsPage.adultCounter.getText().trim());
        adjustTravelerCount(currentAdultCount, adultCount, searchResultsPage.adultIncreaseButton, searchResultsPage.adultDecreaseButton);


        // Ensure the popup remains open after setting the number of adults
        if (!searchResultsPage.occupantPopup.is(visible)) {
            searchResultsPage.occupantConfigButton.shouldBe(clickable).click();
            searchResultsPage.occupantPopup.shouldBe(visible);
        }

        // Adjust the number of children
        searchResultsPage.childCounter.shouldBe(visible);
        int currentChildCount = Integer.parseInt(searchResultsPage.childCounter.getText().trim());
        adjustTravelerCount(currentChildCount, childCount, searchResultsPage.addChildButton, searchResultsPage.removeChild);

        if (childCount > 0) {
            searchResultsPage.childAgesSelect.forEach(dropdown -> {
                List<SelenideElement> validOptions = dropdown.$$("option").stream()
                        .filter(option -> !"-1".equals(option.getDomAttribute("value")))
                        .toList();

                if (!validOptions.isEmpty()) {
                    int randomIndex = new Random().nextInt(validOptions.size());
                    SelenideElement selectedOption = validOptions.get(randomIndex);
                    String randomAge = selectedOption.getText();

                    dropdown.selectOption(randomAge);
                }
            });
        }

        return this;
    }

    @Step("Specify travelers: {adultCount} adults")
    public SearchResultsPageSteps specifyTravelers(int adultCount) {
        searchResultsPage.occupantConfigButton.shouldBe(clickable).click();
        searchResultsPage.occupantPopup.shouldBe(visible);

        // Adjust the number of adults
        searchResultsPage.adultCounter.shouldBe(visible);
        int currentAdultCount = Integer.parseInt(searchResultsPage.adultCounter.getText().trim());
        adjustTravelerCount(currentAdultCount, adultCount, searchResultsPage.adultIncreaseButton, searchResultsPage.adultDecreaseButton);

        // Ensure the popup remains open after setting the number of adults
        if (!searchResultsPage.occupantPopup.is(visible)) {
            searchResultsPage.occupantConfigButton.shouldBe(clickable).click();
            searchResultsPage.occupantPopup.shouldBe(visible);
        }

        return this;
    }

}