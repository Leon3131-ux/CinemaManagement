package com.bigbrain.cinema.domain;

public enum AgeRatingName {

    ADULT("18+"),
    OLD_TEENAGER("16+"),
    TEENAGER("12+"),
    CHILD("6+"),
    ANY("0");


    public final String label;

    AgeRatingName(String label) {
        this.label = label;
    }
}
