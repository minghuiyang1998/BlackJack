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
    public boolean isExceed(CardSet hand, int value) {
        ArrayList<Card> cards = hand.getDeck();
        int count = 0;
        for (Card c: cards) {
            count += c.getValue();
        }
        return count >= value;
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
        int count = 0;
        for (BJPlayer p: players) {
            if (p.getCurrIndex() < 0) {
                count += 1;
            }
        }
        return count == players.size();
    }
}
