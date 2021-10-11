package main;

class BlackJack extends AbstractCardGame{
   private static BlackJack INSTANCE = null;
   private BlackJack() {
      super();
   }
   static public BlackJack getInstance() {
      if (INSTANCE == null) {
         INSTANCE = new BlackJack();
      }
      return INSTANCE;

   }

   @Override
   void startGame() {
      System.out.println("BlackJack starts");

   }
}
