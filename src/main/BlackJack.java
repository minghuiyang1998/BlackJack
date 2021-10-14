package main;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

import static main.PlayerActionType.*;

class BlackJack extends AbstractCardGame{
   private static BlackJack INSTANCE = null;
   private final BJPlayer[] bjPlayers;
   private final BJDealer dealer;
   private final BJReferee referee;

   private BlackJack(
           BJPlayer[] bjPlayers,
           BJDealer bjDealer,
           BJReferee bjReferee
   ) {
      super();
      this.bjPlayers = bjPlayers;
      this.dealer = bjDealer;
      this.referee = bjReferee;
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

   @Override
   void startGame() {
      //TODO: all player set bet
      boolean isRoundEnd = false;
      while (isRoundEnd) {//all player win/lose/push referee decide isRoundEnd
         for (BJPlayer p : bjPlayers) {
            //TODO:referee 判断player能不能玩不能就 continue；
            // Whole player all hanged: isStand or isBust: getHands() -> referee

            ArrayList<PlayerActionType> actions = renderActionList(p);
            PlayerActionType a = chooseAction(actions);
            switch (a) {
               case HIT:
                  // dealer
                  // player
                  break;
               case STAND:
                   p.standCurr();
                   p.changeHand();
                  break;
               case SPLIT:
                  // TODO: before player.split(cards) 判断一下balance够不够
                  break;
               case DOUBLEUP:
                  // TODO: before player.doub(cards) 判断一下balance够不够
                  break;
               case DEAL:
                  break;
               default:
                  break;
            }
            //TODO:boolean result = referee.judge()
            /**
             * referee's work
             * bust:
             * calc player.getHand() -> isBust()
             * if(bust) set true + change currrent hand
             * */
             //TODO: printTable()
            /**
             * dealer.cardsset（有一张牌没有展示）
             *
             * for playes
             *   for player.hands
             *      cardset-> card
             *
             * */
         }
         // TODO: referee if all player unavail, 如果是dealer就开始操作
         /**
          *
          * for all players currIndex = -1 -> dealer
          * while (!isExceeded) { //17
          *    dealer.addCard()
          * }
          *
          * printTable: 全部的牌展示
          *
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
       /**
        * for players
        *   player.reset()
        *
        * dealer.reset() //换一副牌
        *
        * */
   }
}
