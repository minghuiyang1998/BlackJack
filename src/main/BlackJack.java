package main;

class BlackJack extends AbstractCardGame{
   private static BlackJack INSTANCE = null;
   private BlackJack(
           BJPlayer[] players
   ) {
      super(players);
   }

   static public BlackJack getInstance() {
      if (INSTANCE == null) {
         BJPlayer[] bjPlayers = new BJPlayer[2];
         INSTANCE = new BlackJack(bjPlayers);
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


      }
   }

   @Override
   void resetGame() {

   }
}
