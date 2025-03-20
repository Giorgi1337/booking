package com.booking.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class SearchResultsPage {
    public SelenideElement
            searchButton = $x("//button[span[text()='Search']]"),
            datePickerOpenButton = $("[data-testid='searchbox-datepicker-calendar']"),
            adultDecreaseButton = $x("//div[label[text()='Adults']]/following-sibling::div/button[1]"),
            removeChild = $x("//div[label[text()='Children']]/following-sibling::div/button[1]"),
            adultCounter = $x("//div[label[text()='Adults']]/following-sibling::div/span"),
            childCounter = $x("//div[label[text()='Children']]/following-sibling::div/span"),
            adultIncreaseButton = $x("//div[label[text()='Adults']]/following-sibling::div/button[last()]"),
            addChildButton = $x("//div[label[text()='Children']]/following-sibling::div/button[last()]"),
            occupantConfigButton = $("[data-testid='occupancy-config-icon']"),
            occupantPopup = $("[data-testid='occupancy-popup']");

    public SelenideElement getDateElement(String dateString) {
        return $("[data-date='" + dateString + "']");
    }

    public ElementsCollection childAgesSelect = $$("[data-testid='kids-ages-select'] select");
}
