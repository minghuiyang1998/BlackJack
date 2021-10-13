package main;

final class BJPlayer extends AbstractPlayer {
    private boolean isSplit = false;
    private Splits splits = null;
    private Split currentSplit;

    BJPlayer(String name, int balance) {
        super(name, balance);
    }

    // 每次都选出当前操作的split然后用setCurrentSplit更新当前player的操作数据
    private void split() {
        // 1st time to split
        if (!isSplit) {
            isSplit = true;
            splits = new Splits(getCardsInHand(), getBet());
            Split firstSplit = splits.getSplit(0);
            setCurrentSplit(firstSplit); // TODO: set the 1st split as default?
        } else {
            // 2nd+ split, split curr and append
            currentSplit = splits.splitAndAppend(currentSplit);
        }
    }

    // 监听currentSplit变了更新手上的内容
    private void setCurrentSplit(Split s) {
        setCardsInHand(s.getCardSet());
        setBet(s.getBet());
        currentSplit = s;
    }

    public Split getCurrentSplit() {
        return currentSplit;
    }

    // 监听手上的内容变了更新currentSplit的内容
    private void setCurrent() {
        currentSplit.setCardSet(getCardsInHand());
        currentSplit.setBet(getBet());
    }

    private void doubleUp(Card c) {
        getBet().doubleUp();
        hit(c);
    }

    @Override
    public void hit(Card c) {
        // 更新手上的牌
        super.hit(c);
        // 更新splits里面的数据
        if (isSplit) {
            setCurrent();
        }
    }

    @Override
    public void deal(Card[] cards) {
        super.deal(cards);
        if (isSplit) {
            setCurrent();
        }
    }

    public void stand() {
        if (!isSplit) {
            /* if player has not split, setStand(true) for cardsInHand */
            setStand(true);
        }
        else {
            /* if and only player's all splits has stand */
            for(Split s : this.splits.getSplits()) {
                if (!s.isStand()) {
                    return;
                }
            }
            setStand(true);
        }
    }

    public boolean isSplit() {
        return this.isSplit;
    }

    public Splits getSplits() {
        return splits;
    }

    public void stand(Split s) {
        s.setStand(true);
    }
}
