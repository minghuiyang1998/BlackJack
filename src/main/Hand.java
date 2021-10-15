package main;

import java.util.ArrayList;

class Hand extends CardSet {
    private Money bet;
    private boolean isStand;
    private boolean isBust;

    Hand(Money bet) {
        super();
        this.bet = bet;
        isBust = false;
        isStand = false;
    }

    public void setBet(Money bet) {
        this.bet = bet;
    }

    public Money getBet() {
        return bet;
    }

    public void setBust(boolean bust) {
        isBust = bust;
    }

    public void setStand(boolean stand) {
        isStand = stand;
    }

    public boolean isBust() {
        return isBust;
    }

    public boolean isStand() {
        return isStand;
    }

    public void hit(Card c) {
        getDeck().add(c);
    }

    public Hand split() {
        ArrayList<Card> temp = getDeck();
        Card secondCard = temp.remove(temp.size() - 1);
        Hand newHand = new Hand(new Money(bet.getValue()));
        ArrayList<Card> newCards = new ArrayList<>();
        newCards.add(secondCard);
        newHand.deal(newCards);
        return newHand;
    }

    public void deal(ArrayList<Card> cards) {
        for (Card c: cards) {
            getDeck().add(c);
        }
    }

    public void stand() {
        this.isStand = true;
    }

    public void doubleUp(Card c) {
        getDeck().add(c);
        setBet(new Money(bet.getValue() * 2));
    }
}
