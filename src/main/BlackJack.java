package main;

import java.util.ArrayList;

import static main.PlayerActionType.*;

class BlackJack extends AbstractCardGame{
   private static BlackJack INSTANCE = null;
   private final BJPlayer[] bjPlayers;
   private final BJDealer bjDealer;
   private final BJReferee bjReferee;

   private BlackJack(
           BJPlayer[] bjPlayers,
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
         BJPlayer[] bjPlayers = new BJPlayer[LEN];
         for (int i = 0; i < LEN; i++) {
            bjPlayers[i] = new BJPlayer("Player " + i, 100);
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
      def.add(HIT);
      def.add(STAND);

      Hand hand = p.getHand();
      ArrayList<Card> cards = hand.getDeck();
      if (cards.size() == 1) {
          def.add(DOUBLEUP);
      }
      if (cards.size() == 2) {
         boolean isSame = cards.get(0).getValue() == cards.get(1).getValue();
         if (isSame) {
             def.add(SPLIT);
         }
      }

      return def;
   }

   private boolean playAction(BJPlayer p) {
      boolean isActionSucceed = false;
      ArrayList<PlayerActionType> actions = renderActionList(p);
      PlayerActionType a = chooseAction(actions);
      switch (a) {
         case HIT:
            Card card = bjDealer.getRandomCard();
            p.hit(card);
            isActionSucceed = true;
            break;
         case STAND:
            p.standCurr();
            isActionSucceed = p.changeHand();
            break;
         case SPLIT:
             if (p.getBalance().getValue() < p.getBet().getValue()) {
                 isActionSucceed = false;
             } else {
                p.split();
                isActionSucceed = true;
             }
            break;
         case DOUBLEUP:
            if (p.getBalance().getValue() < p.getBet().getValue()) {
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
            ArrayList<Card> dealerCards = bjDealer.deal(2);
            int DEALER_CARDS_LEN = dealerCards.size();
            for (int i = 0; i < DEALER_CARDS_LEN; i++) {
               Card c = dealerCards.get(i);
               if (i == DEALER_CARDS_LEN - 1) {
                  c.setShown(false);
               }
               bjDealer.addCard(c);
            }
            break;
         default:
            break;
      }
      return isActionSucceed;
   }

   private void printTable() {
      System.out.println("Dealer:");
      ArrayList<Card> dealerCards = bjDealer.getCards();
      for (Card c: dealerCards) {
         if (c.isShown()) {
            System.out.print(c.getSuit() + c.getName()+ " ");
         }
      }
      System.out.println();

      for (BJPlayer p: bjPlayers) {
         System.out.println(p.getName()+":");
         ArrayList<Hand> pHands = p.getHands();
         for (int i = 0; i < pHands.size(); i++) {
            Hand h = pHands.get(i);
            ArrayList<Card> pCards = h.getDeck();
            for (Card c: pCards) {
               if (c.isShown()) {
                  System.out.print(c.getSuit() + c.getName()+ " ");
               }
            }
            if (i == p.getCurrIndex()) {
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
         Money bet = inquireBet();
         p.setBet(bet);// if hands is null, it will add a new hand and set bet
      }
      boolean isRoundEnd = false;
      while (isRoundEnd) {//all player win/lose/push referee decide isRoundEnd
         for (BJPlayer p : bjPlayers) {
            //TODO:referee 判断player能不能玩不能就 continue；
            // Whole player all hanged: isStand or isBust: getHands() -> referee
            boolean isActionSucceed = false;
            while (!isActionSucceed) {
               isActionSucceed = playAction(p);
            }
            // referee
            if (bjReferee.isBust(p.getHand())) {
               p.setCurrBust(true);
               p.changeHand();
            }
            printTable();
         }
         // TODO: referee if all player unavail, 如果是dealer就开始操作
         /**
          *
          * for all players currIndex = -1 -> dealer
          * while (!isExceeded) { //17
          *    dealer.addCard()
          * }
          */
          printTable();
          /**
          * isDealerBust = referee.isBust(dealer hand) //dealer
          * if (isDealerBust) {
          *    all players' hands win
          *    play.setBalance
          * } else {
          *    referee计算所有的player和dealer的hands， 1
          *    所有没有bust且超过dealer's hand的hand获胜 2
          *    play.setBalance
          * }
          *
          * set isRoundEnd = true
          */
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
