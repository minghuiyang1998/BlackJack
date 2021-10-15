package main;

import java.util.ArrayList;

class TEReferee implements Referee<TEPlayer> {
    final int BUST_VAL = 31;

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
    public boolean isExceed(CardSet hand, int value) {
        ArrayList<Card> cards = hand.getDeck();
        int count = 0;
        for (Card c: cards) {
            count += c.getValue();
        }
        return count >= value;
    }

    @Override
    public boolean isPlayerStop(TEPlayer player) {
        // TODO
        if (player.getHand().isStand() || player.getHand().isBust()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isAllPlayersStop(ArrayList<TEPlayer> players) {
        for (TEPlayer p : players) {
            if (!isPlayerStop(p)) {
                return false;
            }
        }
        return true;
    }
}
