package main;

interface Referee {
    int getValue(Card c);
    int getValue(CardSet hand);
    boolean isBust(CardSet hand);
    boolean hasExceed(CardSet hand, int value);
    boolean isStand(AbstractPlayer player);
    boolean isBust(AbstractPlayer player);
//    boolean judge();

}
