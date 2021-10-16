To start a BlackJack/TriantaEna game, run Boot.java

====================Structure====================
Classes:

--Boot		Main file for players to start the program.

--MainGame	A java class that provides a menu for players to select
		a game.

--Card		A java class represents  gaming cards with suits and 
		value.

--CardSet		A set of cards including some operations for updating
		a hand of cards.

--Poker		Extends CardSet, the constructor is used for create a 
		deck of poker.

--Hand		A subclass of CardSet, representing a hand of cards of 
		some player (since players are allowed to split cards in
		BlackJack).

--Money		A class for setting balance and bet.

--Suits		An enum containing the suits of poker.

--PlayerActionType	Another enum represent player actions such as hit, split
		and stand.

--AbstractPlayer	An abstract class which declares basic fields of a card
		game player: name and balance.

--BJPlayer	

--TEPlayer	Extends AbstractPlayer, add some specific player action
		methods for TriantaEna.

--AbstractDealer	An abstract class that declares multiple basic operations 
		of a dealer.

--BJDealer	

--TEDealer	A subclass of AbstractDealer, add 'bank' attribute to record
		dealer's balance.

--Referee		A java interface for betting card game, including basic
		methods to determine game state. 

--BJReferee	

--TEReferee	Implements Referee interface and specifies BUST_VAL
		(31) for TriantaEna.

--AbstractCardGame Abstract class for casino banking game.

--BlackJack

--TriantaEna	Subclass of AbstractCardGame, main program for TriantaEna.

