package com.booking.steps;

import com.booking.pages.FilterPage;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.booking.data.Constants.FILTERS;
import static com.codeborne.selenide.Condition.visible;

public class FilterPageSteps {
    private final FilterPage filterPage = new FilterPage();

    @Step("Select filters: {filterNames}")
    public FilterPageSteps selectFilters(String... filterNames) {
        for (String filterName : filterNames) {
            SelenideElement section = getSectionForFilter(filterName);
            SelenideElement checkbox = getCheckboxForFilter(filterName);

            // Scroll to the section and ensure it's visible
            section.scrollIntoView("{block: 'center'}");
            section.shouldBe(visible);

            // Check if the checkbox is not selected and click it
            if (checkbox.exists() && !checkbox.isSelected()) {
                checkbox.scrollIntoView("{block: 'center'}");
                checkbox.click();
            }
        }

        return this;
    }

    @Step("Validate labels for filters: {filters}")
    public FilterPageSteps validateLabels() {
        FILTERS.forEach((filterName, expectedText) -> {
            SelenideElement label = filterPage.getLabel(filterName);
            label.shouldBe(visible);
            String actualText = label.getText();
            if (!actualText.equals(expectedText)) {
                throw new AssertionError("Expected: " + expectedText + ", but got: " + actualText);
            }
        });

        return this;
    }

    private SelenideElement getSectionForFilter(String filterName) {
        return switch (filterName.toLowerCase()) {
            case "5 stars" -> filterPage.propertyRatingSection;
            case "8+ rating" -> filterPage.reviewScoreSection;
            default -> throw new IllegalArgumentException("Unsupported filter: " + filterName);
        };
    }

    private SelenideElement getCheckboxForFilter(String filterName) {
        return switch (filterName.toLowerCase()) {
            case "5 stars" -> filterPage.fiveStarsCheckbox;
            case "8+ rating" -> filterPage.veryGoodCheckbox;
            default -> throw new IllegalArgumentException("Unsupported filter: " + filterName);
        };
    }


}
