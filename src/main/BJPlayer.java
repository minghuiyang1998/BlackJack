package main;

import java.util.ArrayList;

final class BJPlayer extends AbstractPlayer {
    private ArrayList<Hand> hands = new ArrayList<>();
    private int currIndex = 0;

    BJPlayer(String name, int balance) {
        super(name, balance);
    }

    public boolean changeHand() {
        int result = -1;
        for (int i = 0; i < hands.size() ; i++) {
            Hand temp = hands.get(i);
            boolean isStand = temp.isStand();
            boolean isBust = temp.isBust();
            if (!isBust && !isStand) {
                result = i;
                break;
            }
        }
        currIndex = result; // when this var == -1, hands are all unavail
        boolean returnVal = true;
        if (result == -1) {
            returnVal = false;
        }
        return returnVal;
    }

    public ArrayList<Hand> getHands() {
        return hands;
    }

    public int getCurrIndex() {
        return currIndex;
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
            currIndex = 0;
        } else {
            Hand curr = hands.get(currIndex);
            curr.setBet(bet);
        }
    }

    public Money getBet() {
        Hand curr = hands.get(currIndex);
        return curr.getBet();
    }

    public void hit(Card c) {
        Hand curr = hands.get(currIndex);
        curr.hit(c);
    }

    public void split(ArrayList<Card> cards) {
        Hand curr = hands.get(currIndex);
        ArrayList<Card> cardsInHand = curr.getDeck();
        if (cardsInHand.size() < 2) {
            System.out.println("invalid value");
            return;
        }
        Hand splitedHand = curr.split();
        curr.hit(cards.get(0));
        splitedHand.hit(cards.get(1));
        splitedHand.setBet(curr.getBet());
        setBalance(getBalance() - curr.getBet().getValue());
        hands.add(splitedHand);
    }

    public void deal(ArrayList<Card> cards) {
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
        setBalance(getBalance() - curr.getBet().getValue());
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
