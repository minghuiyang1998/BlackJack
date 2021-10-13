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
         BJPlayer[] bjPlayers = new BJPlayer[2];
         Poker poker = new Poker();
         BJDealer bjDealer = new BJDealer(poker);
         BJReferee bjReferee = new BJReferee();
         INSTANCE = new BlackJack(bjPlayers, bjDealer, bjReferee);
      }
      return INSTANCE;
   }


   @Override
   void startGame() {
      boolean isRoundEnd = false;
      while (!isRoundEnd) {
         // referee decide isRoundEnd
         //TODO: get player status to decide player's actionlist
         PlayerActionType[] actions = new PlayerActionType[] {};
         PlayerActionType a = chooseAction(actions);
         //TODO: player.play(a)
         //TODO: dealer.react(a)
         //TODO:boolean result = referee.judge()
         //TODO: printTable()
         /**
          * if(result) {
          *    isRoundEnd = true;
          * }
          * */
      }
   }

   @Override
   void resetGame() {

   }
}
