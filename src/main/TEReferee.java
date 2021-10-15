package main;

import java.util.ArrayList;

class TEReferee implements Referee<TEPlayer> {
    @Override
    public boolean isBust(Hand hand) {
        return false;
    }

    @Override
    public boolean isExceed(CardSet hand, int value) {
        return false;
    }

    @Override
    public boolean isPlayerStop(TEPlayer player) {
        return false;
    }

    @Override
    public boolean isAllPlayersStop(ArrayList<TEPlayer> players) {
        return false;
    }
}
