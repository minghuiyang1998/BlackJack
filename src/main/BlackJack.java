package main;

import java.util.ArrayList;

import static main.PlayerActionType.*;

class BlackJack extends AbstractCardGame{
   private static BlackJack INSTANCE = null;
   private final ArrayList<BJPlayer> bjPlayers;
   private final BJDealer bjDealer;
   private final BJReferee bjReferee;

   private BlackJack(
           ArrayList<BJPlayer> bjPlayers,
           BJDealer bjDealer,
           BJReferee bjReferee
   ) {
      super();
      this.bjPlayers = bjPlayers;
      this.bjDealer = bjDealer;
      this.bjReferee = bjReferee;
   }

   static public BlackJack getInstance() {
      if (INSTANCE == null) {
         final int LEN = 2;
         ArrayList<BJPlayer> bjPlayers = new ArrayList<>();
         for (int i = 0; i < LEN; i++) {
            bjPlayers.add(new BJPlayer("BJPlayer " + i, 100));
         }
         Poker poker = new Poker();
         BJDealer bjDealer = new BJDealer(poker);
         BJReferee bjReferee = new BJReferee();
         INSTANCE = new BlackJack(bjPlayers, bjDealer, bjReferee);
      }
      return INSTANCE;
   }

   private ArrayList<PlayerActionType> renderActionList(BJPlayer p) {
      // default list
      ArrayList<PlayerActionType> def = new ArrayList<>();
      Hand hand = p.getHand();
      ArrayList<Card> cards = hand.getDeck();
      final int CARDS_LEN = cards.size();

      if (CARDS_LEN <= 0) {
          def.add(DEAL);
      } else {
         def.add(HIT);
         def.add(STAND);
         if (CARDS_LEN == 2) {
            def.add(DOUBLEUP);
            boolean isSame = cards.get(0).getValue() == cards.get(1).getValue();
            if (isSame) {
               def.add(SPLIT);
            }
         }
      }

      return def;
   }

   private boolean playAction(BJPlayer p) {
      boolean isActionSucceed = false;
      ArrayList<PlayerActionType> actions = renderActionList(p);
      System.out.println();
      System.out.print(p.getName() + " ");
      PlayerActionType a = chooseAction(actions);
      switch (a) {
         case HIT:
            Card card = bjDealer.getRandomCard();
            p.hit(card);
            isActionSucceed = true;
            break;
         case STAND:
            p.standCurr();
            p.changeHand();
            isActionSucceed = true;
            break;
         case SPLIT:
             if (p.getBalance() < p.getBet().getValue()) {
                 isActionSucceed = false;
             } else {
                ArrayList<Card> cards = bjDealer.deal(2);
                p.split(cards);
                isActionSucceed = true;
             }
            break;
         case DOUBLEUP:
            if (p.getBalance() < p.getBet().getValue()) {
               isActionSucceed = false;
            } else {
               Card newCard = bjDealer.getRandomCard();
               p.doubleUp(newCard);
               isActionSucceed = true;
            }
            break;
         case DEAL:
            ArrayList<Card> playerCards = bjDealer.deal(2);
            p.deal(playerCards);
            // dealer add cards
            if (bjDealer.getHand().getDeck().size() <= 0) {
               ArrayList<Card> dealerCards = bjDealer.deal(2);
               int DEALER_CARDS_LEN = dealerCards.size();
               for (int i = 0; i < DEALER_CARDS_LEN; i++) {
                  Card c = dealerCards.get(i);
                  if (i == DEALER_CARDS_LEN - 1) {
                     c.setShown(false);
                  }
                  bjDealer.addCard(c);
               }
            }
            isActionSucceed = true;
            break;
         default:
            break;
      }
      return isActionSucceed;
   }

   private void printTable() {
      System.out.println();
      System.out.println("Dealer:");
      Hand dealerHand = bjDealer.getHand();
      ArrayList<Card> dealerCards = dealerHand.getDeck();
      for (Card c: dealerCards) {
         if (c.isShown()) {
            System.out.print(c.getSuit() + c.getName()+ " ");
         } else {
            System.out.print("-- ");
         }
      }
      if (dealerHand.isBust()) {
         System.out.print("<Bust> ");
      }
      System.out.println();

      for (BJPlayer p: bjPlayers) {
         System.out.println(p.getName() + " " + "Balance:" + p.getBalance() + " ");
         ArrayList<Hand> pHands = p.getHands();
         for (int i = 0; i < pHands.size(); i++) {
            Hand h = pHands.get(i);
            System.out.print("Hand" + i + " " + "Bet:" + h.getBet().getValue() + " Cards: ");
            ArrayList<Card> pCards = h.getDeck();
            for (Card c: pCards) {
               if (c.isShown()) {
                  System.out.print(c.getSuit() + c.getName()+ " ");
               } else {
                  System.out.print("--");
               }
            }
            if (pCards.size() > 0 && i == p.getCurrIndex()) {
               System.out.print("<Current Hand> ");
            }
            if (h.isBust()) {
               System.out.print("<Bust> ");
            }
            if (h.isStand()) {
               System.out.print("<Stand> ");
            }
            System.out.println();
         }
      }
   }

   @Override
   void startGame() {
      for (BJPlayer p: bjPlayers) {
         System.out.print(p.getName() + "(Balance: " + p.getBalance() + ") " );
         Money bet = inquireBet(p.getBalance());
         p.setBalance(p.getBalance() - bet.getValue());
         p.setBet(bet);// if hands is null, it will add a new hand and set bet
      }
      boolean isRoundEnd = false;
      while (!isRoundEnd) {//all player win/lose/push referee decide isRoundEnd
         for (BJPlayer p : bjPlayers) {
            boolean isPlayerStop = bjReferee.isPlayerStop(p);
            if (isPlayerStop) continue;

            boolean isActionSucceed = false;
            while (!isActionSucceed) {
               isActionSucceed = playAction(p);
            }

            // referee
            if (p.getCurrIndex() >= 0 && bjReferee.isBust(p.getHand())) {
               p.setCurrBust(true);
               p.changeHand();
            }
            printTable();
         }
         boolean isAllPlayerStop = bjReferee.isAllPlayersStop(bjPlayers);
         if (!isAllPlayerStop) continue;

         // dealer
         int EXCEED_VAL = 17;
         for (Card c: bjDealer.getHand().getDeck()) {
            if (!c.isShown()) {
               c.setShown(true);
            }
         }
         boolean isExceed = bjReferee.isExceed(bjDealer.getHand(), EXCEED_VAL);;
         while (!isExceed) {
            Card c = bjDealer.getRandomCard();
            bjDealer.addCard(c);
            isExceed = bjReferee.isExceed(bjDealer.getHand(), EXCEED_VAL);
         }

         if (bjReferee.isBust(bjDealer.getHand())) {
            bjDealer.getHand().setBust(true);
         }
         printTable();
         if (bjDealer.getHand().isBust()) {
            System.out.println("All stand hands win!");
            for (BJPlayer p: bjPlayers) {
               int win = 0;
               ArrayList<Hand> hands = p.getHands();
               for (Hand h: hands) {
                  if (h.isStand()) {
                     win += h.getBet().getValue() * 2;
                  }
               }
               p.setBalance(p.getBalance() + win);
               System.out.println(p.getName() + " Balance: " + p.getBalance());
            }
         } else {
            System.out.println("All stand and exceed dealer hands win!");
            int dealerVal = bjReferee.getHandValue(bjDealer.getHand());
            for (BJPlayer p: bjPlayers) {
               int win = 0;
               ArrayList<Hand> hands = p.getHands();
               for (Hand h: hands) {
                  if (h.isStand() && bjReferee.getHandValue(h) > dealerVal) {
                     win += h.getBet().getValue() * 2;
                  }
               }
               p.setBalance(p.getBalance() + win);
            }
         }
         isRoundEnd = true;
         System.out.println();
      }
   }

   @Override
   void resetGame() {
      for (BJPlayer p: bjPlayers) {
         p.reset();
      }
      //TODO: dealer.reset() //换一副牌
   }
}
