package main;

import java.util.ArrayList;

final class TEPlayer extends AbstractPlayer implements Comparable<TEPlayer> {
    private Hand hand;

    TEPlayer(String name, int balance) {
            super(name, balance);
    }

    @Override
    public void reset() {

    }

    public Hand getHand() {
        return hand;
    }

    public void setBet(Money bet) {
        hand = new Hand(bet);
    }

    public void hit(Card c) {
        hand.hit(c);
    }

    public void deal(ArrayList<Card> cards) {
        hand.deal(cards);
    }

    public void standCurr() {
        hand.stand();
    }

    public void doubleUp(Card c) {
        hand.doubleUp(c);
    }

    public void setCurrBust(boolean bust) {
        hand.setBust(bust);
    }

    public boolean isCurrBust() {
        return hand.isBust();
    }
    public boolean isCurrStand() {
        return hand.isStand();
    }

    @Override
    public int compareTo(TEPlayer p) {
        // Descending
        return p.getBalance() - this.getBalance();
    }
}
