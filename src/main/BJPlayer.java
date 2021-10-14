package main;

import java.util.ArrayList;

final class BJPlayer extends AbstractPlayer {
    private ArrayList<Hand> hands = new ArrayList<>();
    private int currIndex = 0;

    BJPlayer(String name, int balance) {
        super(name, balance);
    }

    public boolean changeHand() {
        //TODO:一定要换一只没问题的手，如果没有可用的手，currentIndex -1

    }

    public ArrayList<Hand> getHands() {
        return hands;
    }


    public Hand getHand() {
        return hands.get(currIndex);
    }

    public void reset() {
        this.hands = new ArrayList<>();
        currIndex = 0;
    }

    public void setBet(Money bet) {
        if (hands.size() <= 0) {
            Hand temp = new Hand(bet);
            hands.add(temp);
        } else {
            Hand curr = hands.get(currIndex);
            curr.setBet(bet);
        }
    }

    public void hit(Card c) {
        Hand curr = hands.get(currIndex);
        curr.hit(c);
    }

    public void split(Card[] cards) {
        if (cards.length < 2) {
            System.out.println("invalid value");
            return;
        }
        Hand curr = hands.get(currIndex);
        Hand splitedHand = curr.split();
        curr.deal(new Card[]{cards[0]});
        splitedHand.deal(new Card[]{cards[1]});
        splitedHand.setBet(curr.getBet());
        hands.add(splitedHand);
    }

    public void deal(Card[]cards) {
        Hand curr = hands.get(currIndex);
        curr.deal(cards);
    }

    public void standCurr() {
        Hand curr = hands.get(currIndex);
        curr.stand();
    }

    public void doubleUp(Card c) {
        Hand curr = hands.get(currIndex);
        curr.doubleUp(c);
    }

    public void setCurrBust(boolean bust) {
        Hand curr = hands.get(currIndex);
        curr.setBust(bust);
    }

    public boolean isCurrBust() {
        Hand curr = hands.get(currIndex);
        return curr.isBust();
    }
    public boolean isCurrStand() {
        Hand curr = hands.get(currIndex);
        return curr.isStand();
    }

}
