package com.booking.data;

import java.util.Map;

public class Constants {
    public static final String
            BOOKING_URL = "https://booking.com",
            KAKHETI = "Kakheti",
            BERLIN = "Berlin",
            OFFERS_TEXT = "Offers",
            STARS = "5 Stars",
            RATING = "8+ Rating";

    public static final Integer
            SEVEN = 7,
            NUM_OF_ADULTS = 1,
            NUM_OF_CHILDREN = 5;

    public static final Map<String, String> FILTERS = Map.of(
            "class=5", "5 stars",
            "review_score=80", "Very Good: 8+"
    );
}
