package com.bgc.test.snapcards.model;

import com.bgc.test.snapcards.actions.Actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pack implements Actions<Pack> {

    public static final int CARD_COUNT = 52;
    List<Card> cards = new ArrayList<>(CARD_COUNT);
    public Pack(){
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                Card card = new Card(rank, suit, cardValue(rank, suit));
                cards.add(card);
            }
        }
    }

    private int cardValue(Rank rank, Suit suit) {
        return rank.value * 100 + suit.value;
    }

    @Override
    public Pack shuffle() {
        Collections.shuffle(cards);
        return this;
    }

    @Override
    public Card dealCard() {
        return cards.size() > 0 ? cards.remove(cards.size()-1): null;
    }

    @Override
    public int cardsLeft() {
        return cards.size();
    }

}
