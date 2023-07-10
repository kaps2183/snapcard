package com.bgc.test.snapcards.model;

public enum Suit {
    CLUBS(1),
    DIAMONDS(2),
    HEARTS(3),
    SPADES(4);

    public final int value;
    public final String label;

    Suit(int value) {
        this.value=value;
        this.label=this.name();
    }
}
