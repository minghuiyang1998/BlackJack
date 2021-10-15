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
    public boolean isPlayerStop(BJPlayer player) {
        int count = 0;
        ArrayList<Hand> hands = player.getHands();
        for (Hand h: hands) {
            if (h.isBust() || h.isStand()) {
                count += 1;
            };
        }
        return count == hands.size();
    }

    @Override
    public boolean isAllPlayersStop(ArrayList<BJPlayer> players) {
        return false;
    }
}
