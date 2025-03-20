package com.booking.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class FilterPage {
    public SelenideElement
            propertyRatingSection = $x("//span[text()='Property rating']/ancestor::fieldset"),
            fiveStarsCheckbox = $x("//fieldset//div[@data-filters-item='class:class=5']//input[contains(@aria-label, '5 stars')]"),
            reviewScoreSection = $x("//span[text()='Review score']/ancestor::fieldset"),
            veryGoodCheckbox = $x("//fieldset//div[@data-filters-item='review_score:review_score=80']//input[contains(@aria-label, 'Very Good: 8+')]");

    public SelenideElement getLabel(String filterName) {
        return $x("//button[@data-testid='filter:" + filterName + "']/span/span");
    }
}
