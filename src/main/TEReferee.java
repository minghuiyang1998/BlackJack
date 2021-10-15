package main;

import java.util.ArrayList;

class TEReferee implements Referee<TEPlayer> {
    @Override
    public boolean isBust(Hand hand) {
        return false;
    }

    @Override
    public boolean hasExceed(CardSet hand, int value) {
        return false;
    }

    @Override
    public boolean isAllStop(ArrayList<TEPlayer> players) {
        return false;
    }
}
