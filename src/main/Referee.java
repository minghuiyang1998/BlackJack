package main;

import java.util.ArrayList;

interface Referee<T extends AbstractPlayer> {
    boolean isBust(Hand hand);
    boolean isExceed(CardSet hand, int value);
    boolean isPlayerStop(T players);
    boolean isAllPlayersStop(ArrayList<T> players);
    default int getHandValue(Hand hand, int bust_val) {
        int sum = 0;
        ArrayList<Card> cards = hand.getDeck();
        int aceCount = 0;
        for (Card c: cards) {
            // TODO: if card = Ace
            if (c.getName().equals("A")) {
                aceCount++;
                sum += 10;
            }
            sum += c.getValue();
            // if current hand bust, retreat to Ace = 1
            if (aceCount > 0 && sum > bust_val) {
                sum -= 10;
                aceCount--;
            }
        }
        return sum;
    }
}
