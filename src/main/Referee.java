package main;

interface Referee {
    int getValue(Card c);
    int getValue(CardSet hand);
    boolean isBust(CardSet hand);
    boolean hasExceed(CardSet hand, int value);
    boolean isAllStand(AbstractPlayer[] players);

}
