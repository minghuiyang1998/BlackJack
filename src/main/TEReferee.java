package main;

class TEReferee implements Referee {
    // TODO: Override interface methods

    @Override
    public int getValue(Card c) {
        return 0;
    }

    @Override
    public int getValue(CardSet hand) {
        return 0;
    }

    @Override
    public boolean isBust(CardSet hand) {
        return false;
    }

    @Override
    public boolean hasExceed(CardSet hand, int value) {
        return false;
    }

    @Override
    public boolean isAllStand(AbstractPlayer[] players) {
        return false;
    }

}
