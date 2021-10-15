package main;

import java.util.ArrayList;

interface Referee<T extends AbstractPlayer> {
    boolean isBust(Hand hand);
    boolean hasExceed(CardSet hand, int value);
    boolean isPlayerStop(T players);
    boolean isAllPlayersStop(ArrayList<T> players);
}
