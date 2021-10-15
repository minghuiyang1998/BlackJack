package main;

import java.util.ArrayList;

class BJReferee implements Referee<BJPlayer> {
    final int BUST_VAL = 21;

    @Override
    public boolean isBust(Hand hand) {
        ArrayList<Card> cards = hand.getDeck();
        int sum = 0;
        for (Card c: cards) {
            sum += c.getValue();
        }
        return sum > BUST_VAL;
    }

    @Override
    public boolean hasExceed(CardSet hand, int value) {
        return false;
    }

    @Override
    public boolean isAllStop(ArrayList<BJPlayer> players) {
        return false;
    }


}
