package main;

class BlackJack extends AbstractCardGame{
   private static BlackJack INSTANCE = null;
   private BlackJack(
           BJPlayer[] players,
           BJDealer bjDealer,
           BJReferee bjReferee
   ) {
      super(players, bjDealer, bjReferee);
   }

   static public BlackJack getInstance() {
      if (INSTANCE == null) {
         final int LEN = 2;
         BJPlayer[] bjPlayers = new BJPlayer[LEN];
         //TODO: init player set balance
         Poker poker = new Poker();
         BJDealer bjDealer = new BJDealer(poker);
         BJReferee bjReferee = new BJReferee();
         INSTANCE = new BlackJack(bjPlayers, bjDealer, bjReferee);
      }
      return INSTANCE;
   }

   @Override
   void startGame() {
      //TODO: all player set bet
      boolean isRoundEnd = false;
      while (isRoundEnd) {//all player win/lose/push referee decide isRoundEnd
         for (AbstractPlayer p : getPlayers()) {
            //TODO:referee 判断player能不能玩不能就 continue；
            // Whole player all hanged: isStand or isBust: getHands() -> referee

            // TODO：get player status to decide player's actionlist
            //  根据player现在的状态
            //  超过两张不能doubleup，
            //  如果两张是一样且就能split
            PlayerActionType[] actions = new PlayerActionType[]{};
            PlayerActionType a = chooseAction(actions);
            switch (a) {
               case HIT:
                  //dealer
                  // player
                  break;
               case STAND:
                  //standCurr true
                  //change current hand
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
