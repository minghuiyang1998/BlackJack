package main;

import java.util.ArrayList;
import java.util.HashMap;

abstract class AbstractPlayer {
    Money balance;
    Money bet;
    int betnum;         // if a player splits, betnum++
    String name;
    boolean isStand;
    //TODO: would it be better using map?
    //HashMap<CardSet, Money> hand;
    ArrayList<CardSet> cardsInHand;
//    CardSet cardsInHand = new CardSet();

    public Money getBalance() {
        return balance;
    }

    public void setBalance(int val) {
        this.balance = balance;
    }

    public Money getBet() {
        return bet;
    }

    public void setBet(Money bet) {
        this.bet.setValue(bet.getValue());
    }

    AbstractPlayer(String name, int balance) {
        this.name = name;
        this.balance = new Money(balance);
        this.bet = new Money(0);
        this.cardsInHand = new ArrayList<CardSet>();

    }

    void newBet(int amount) {
        this.bet.setValue(amount);
    }

    void addBet() {
        // TODO: throws noMoreBalanceException
        this.balance.deduct(this.bet);
        this.betNum++;
    }

    void clearCardsHold() {
        cardsInHand.clear();
    }

    void init() {
        /* each round init player's cardsInHand and action status */
        if (this.cardsInHand.size() == 0) {
            return;
        }
        clearCardsHold();
        this.isStand = false;

    }

    void deal(Card[] cards) {
        /* Player get n cards at the start of each round. */
        for(Card c : cards) {
            this.cardsInHand.get(0).addCard(c);
        }
    }

    void hit(CardSet hand, Card c) {
        // TODO: dealer randomly send out 1 card & player get the card
        hand.addCard(c);
    }


    void stand() {
        /* if players stand, they cannot take any action in this round */
        this.isStand = true;
    }




}
