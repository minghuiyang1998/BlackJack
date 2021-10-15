package main;

import java.util.ArrayList;

interface Referee<T extends AbstractPlayer> {
    boolean isBust(Hand hand);
    boolean isExceed(CardSet hand, int value);
    boolean isPlayerStop(T players);
    boolean isAllPlayersStop(ArrayList<T> players);
    default int getHandValue(Hand hand) {
        int sum = 0;
        ArrayList<Card> cards = hand.getDeck();
        for (Card c: cards) {
            // TODO: if card = Ace
            sum += c.getValue();
        }
        return sum;
    }
}
