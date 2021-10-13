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

   private PlayerActionType chooseAction(PlayerActionType[] actions) {
      Scanner scanner = new Scanner(System.in);
      System.out.println("Please select a action id: ");
      for (int i = 0; i < actions.length; i++) {
         System.out.println(i + ": " + actions[i].getName());
      }

      int id = -1;
      while (id > actions.length || id < 0) {
         String actionID = scanner.nextLine();
         try {
            id = Integer.parseInt(actionID);
         } catch (NumberFormatException e) {
            System.out.println("invalid input, please enter an integer!");
         }

         if (id > actions.length || id < 0) {
            System.out.println("invalid input, please enter a valid integer!");
         }
      }
      return actions[id];
   }


   @Override
   void startGame() {
      System.out.println("BlackJack starts");

   }
}
