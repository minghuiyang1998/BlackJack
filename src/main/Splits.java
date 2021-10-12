package main;

import java.util.ArrayList;

class Splits {
    private static int splitId = 0;
    private final ArrayList<Split> splits;

    Splits(CardSet cardSet) {
        splits = splitCards(cardSet);
    }

    private static ArrayList<Split> splitCards(CardSet cardSet) {
        ArrayList<Split> arrayList = new ArrayList<>();
        for (int i = 0; i < cardSet.getSize(); i++) {
            Card c = cardSet.getCard(i);
            CardSet newCardSet = new CardSet();
            newCardSet.addCard(c);
            Split s = new Split(splitId, newCardSet, new Money(0)); // TODO: initial bet for each split?
            splitId += 1; //不能直接用序号，因为多次split序号会重复
            arrayList.add(s);
        }
        return arrayList;
    }

    public Split splitAndAppend(Split currSplit) {
        Split temp = null;
        for (int i = 0; i < splits.size(); i++) {
            if (splits.get(i).getId() == currSplit.getId()) {
                temp = splits.remove(i);
                break;
            }
        }
        ArrayList<Split> list = Splits.splitCards(temp.getCardSet());
        int currIndex = splits.size();
        splits.addAll(list);
        return splits.get(currIndex); // TODO: catch error
    }

    public Split getSplit(int id) {
        Split temp = null;
        for (Split s: splits) {
            if (s.getId() == id) {
                temp = s;
                break;
            }
        }
        return temp;
    }

    public ArrayList<Split> getSplits() {
        return splits;
    }
}
