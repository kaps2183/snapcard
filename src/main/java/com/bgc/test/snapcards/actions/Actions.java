package com.bgc.test.snapcards.actions;

import com.bgc.test.snapcards.model.Card;

public interface Actions<T> {
    T shuffle();
    Card dealCard();

    int cardsLeft();
}
