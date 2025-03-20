package com.booking.utils;

import com.codeborne.selenide.SelenideElement;

public final class Utils {

    private Utils() { }

    /**
     * Adjusts the number of travelers by clicking the appropriate buttons.
     *
     * @param currentCount   The current count of travelers.
     * @param desiredCount   The desired count of travelers.
     * @param increaseButton The button to increase the count.
     * @param decreaseButton The button to decrease the count.
     */
    public static void adjustTravelerCount(int currentCount, int desiredCount, SelenideElement increaseButton, SelenideElement decreaseButton) {
        if (currentCount != desiredCount) {
            SelenideElement buttonToClick = (desiredCount > currentCount) ? increaseButton : decreaseButton;
            int difference = Math.abs(desiredCount - currentCount);
            for (int i = 0; i < difference; i++) {
                buttonToClick.click();
            }
        }
    }
}
