# Solitare
## Programmers note:
This was a group project of three programmers to make the card game Solitare. In order to become more comfterable with Git/Githubs ability to create branches and work on group projects.
In the end it turned out pretty good. It is littered with edge cases so play carefully.
I spent way more time on this that I would have like to but maybe one day I'll come back to it to fix some cases and flush out gameplay. 
Possibly even one of my other fellow programmers would like to add something to this in the future, but I doubt they even remember this project enough to find out I wrote a ReadME for it.

### Rules
Setup: Seven columns are arranged, with one card in the first, two in the second, up to seven in the last, with the top cards face up. Remaining cards form the stock pile.
Foundation: Cards must be placed in foundation piles by suit, starting with Ace and ending with King.
Tableau Moves: Cards can be moved to another column if they are one rank lower and of the opposite color (e.g., black 10 on red Jack).
Empty Spaces: Only a King (or a stack starting with a King) can fill an empty column.
Stock Pile: Cards are drawn from the stock to a waste pile to be used in the game.
Winning: The game is won when all 52 cards are moved to the foundation piles. 

### Move option
Move: moves a card/Stack of cards to the desired location
mdraw: Moves card from draw pile to desired location
draw: draws card
score: will score the desired card
