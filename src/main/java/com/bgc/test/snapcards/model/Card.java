package com.bgc.test.snapcards.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;

@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class Card implements Comparable<Card>{

    private final Rank rank;
    private final Suit suit;
    private final int cardValue;

    @Override
    public int compareTo(Card other) {
        return cardValue - other.cardValue;
    }

}
