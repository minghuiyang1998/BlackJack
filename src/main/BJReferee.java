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

    public boolean isStand(BJPlayer p) {
        for (Hand h : p.getHands()) {
            if (!h.isStand()) {
                return false;
            }
        }
        return true;
    }

    public boolean isBust(BJPlayer p) {
        for (Hand h : p.getHands()) {
            if (!h.isBust()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean hasExceed(CardSet hand, int value) {
        if (getValue(hand) >= value) {
            return true;
        }
        return false;
    }


}
