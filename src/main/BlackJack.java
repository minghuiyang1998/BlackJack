package main;

import java.util.Scanner;

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

   private PlayerActions chooseAction() {
      Scanner scanner = new Scanner(System.in);
      System.out.println("Please select a game id: ");
      PlayerActions[] actions = PlayerActions.values();
      for (PlayerActions a : actions) {
         System.out.println(a.getId() + ": " + t.getName());
      }

      int id = -1;
      while (id > types.length || id < 0) {
         String gameID = scanner.nextLine();
         try {
            id = Integer.parseInt(gameID);
         } catch (NumberFormatException e) {
            System.out.println("invalid input, please enter an integer!");
         }

         if (id > types.length || id < 0) {
            System.out.println("invalid input, please enter a valid integer!");
         }
      }

      return ;
   }


   @Override
   void startGame() {
      System.out.println("BlackJack starts");

   }
}
