package main;

import java.util.ArrayList;

class BJDealer extends AbstractDealer {
    Hand hand;

    BJDealer(Poker deck) {
        super(deck);
        hand = new Hand(new Money(0));
    }

    void addCard(Card c) {
        /* dealer get cards after all players stand/bust */
        hand.hit(c);
    }

    public Hand getHand() {
        return hand;
    }
}
