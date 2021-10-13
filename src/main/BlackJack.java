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



   @Override
   void startGame() {
      System.out.println("BlackJack starts");

   }
}
