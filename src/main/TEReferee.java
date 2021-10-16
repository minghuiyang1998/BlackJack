package main;

import java.util.ArrayList;

class TEReferee implements Referee<TEPlayer> {
    final int BUST_VAL = 31;

    @Override
    public boolean isBust(Hand hand) {
        return getHandValue(hand, BUST_VAL) > BUST_VAL;
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
