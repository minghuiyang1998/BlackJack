package main;

class BJReferee implements Referee {
    final int bustVal = 21;
    //TODO: BUST_VAL

    @Override
    public int getValue(Card c) {
        return c.getValue();
    }

    @Override
    public int getValue(CardSet hand) {
        /* get sum of all card values of given card set */
        int value = 0;
        for (Card c : hand.getDeck()) {
            value += c.getValue();
        }
        return value;
    }

    @Override
    public boolean isAllStand(AbstractPlayer[] players) {
        for (AbstractPlayer p : players) {
            if (!p.isStand()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isBust(CardSet hand) {
        if (getValue(hand) > this.bustVal) {
            return true;
        }
        return false;
    }

    @Override
    public boolean hasExceed(CardSet hand, int value) {
        if (getValue(hand) >= value) {
            return true;
        }
        return false;
    }


}
