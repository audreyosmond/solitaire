import java.util.*;

public class gameState {
    private deck deck = new deck();
    private Stack<card> drawPile = new Stack<card>();
    private card[][] scoring = new card[13][4];
    private card[][] gameBoard = new card[20][7];

    public gameState() {
    }

    public void newGame() {
        card[] cards = deck.newGame();
        deck.shuffle();
        int cnt = 0;
        for (int i = 0; i < gameBoard[0].length; ++i) {
            for (int j = 0; j <= i; ++j) {
                gameBoard[j][i] = cards[cnt];
                if (j == i) { // Possible no worky :(
                    gameBoard[j][i].flip();
                }
                ++cnt;
            }
        }
        draw();
    }

    public void draw() {
        drawPile.add(deck.draw());
    }

    public void validateMoveCard(int first, int second, int cardNumber) {
        // Crating the cards we are using for checks
        card currentCard = gameBoard[cardNumber][first]; // fix edge case
        if(currentCard == null){
            System.out.println("Invalid move");
            return;
        }

        card futureCard = null;
        for (int i = 0; i < gameBoard.length - 1; ++i) {
            if (gameBoard[i + 1][second] == null) {
                futureCard = gameBoard[i][second];
                break;
            }
        }

        // King case
        if (currentCard.getValue() == 13 && futureCard == null) {
            moveCard(first, second, cardNumber);
            return;
        }

        
        System.out.println(currentCard.getVisibiliy());
        System.out.println(!currentCard.getColor().equals(futureCard.getColor()));
        System.out.println(currentCard.getValue() == futureCard.getValue() - 1);
        if (currentCard.getVisibiliy() && !currentCard.getColor().equals(futureCard.getColor())
                && currentCard.getValue() == futureCard.getValue() - 1) {
            moveCard(first, second, cardNumber);
        } else {
            System.out.println("Invalid move");
        }
    }

    public void moveCard(int first, int second, int cardNumber) {
        // Find and grab all cards asked for and put into ArrayList
        ArrayList<card> moveCards = new ArrayList<>();
        for (int i = 0; i < gameBoard.length; i++) {
            if (gameBoard[i][first] != null && gameBoard[i][first].getVisibiliy() && i >= cardNumber) {
                moveCards.add(gameBoard[i][first]);
                gameBoard[i][first] = null;
            }
        }

        // Flip the next Card in list
        for (int i = 0; i < gameBoard.length-1; ++i) {
            if (gameBoard[i + 1][first] == null && gameBoard[i][first] != null) {
                gameBoard[i][first].flip();
            }
        }

        // Put the cards into desired location
        int cnt = 0;
        for (int i = 0; i < gameBoard.length; ++i) {
            if (gameBoard[i][second] == null && moveCards.size() > 0) {
                gameBoard[i][second] = moveCards.remove(cnt);
                ++cnt;
            }
        }
    }

    public void validateMoveFromDraw(int second){
        // Crating the cards we are using for checks
        card currentCard = drawPile.peek(); // fix edge case
        if(currentCard == null){
            System.out.println("Invalid move");
            return;
        }

        card futureCard = null;
        for (int i = 0; i < gameBoard.length - 1; ++i) {
            if (gameBoard[i + 1][second] == null) {
                futureCard = gameBoard[i][second];
                break;
            }
        }

        // King case
        if (currentCard.getValue() == 13 && futureCard == null) {
            moveDrawCard(second);
            return;
        }

        
        System.out.println(currentCard.getVisibiliy());
        System.out.println(!currentCard.getColor().equals(futureCard.getColor()));
        System.out.println(currentCard.getValue() == futureCard.getValue() - 1);
        if (currentCard.getVisibiliy() && !currentCard.getColor().equals(futureCard.getColor())
                && currentCard.getValue() == futureCard.getValue() - 1) {
            moveDrawCard(second);
        } else {
            System.out.println("Invalid move");
        }
    }

    public void moveDrawCard(int second){
        // Find and grab all cards asked for and put into ArrayList
        ArrayList<card> moveCards = new ArrayList<>();
        moveCards.add(drawPile.pop());

        // Put the cards into desired location
        int cnt = 0;
        for (int i = 0; i < gameBoard.length; ++i) {
            if (gameBoard[i][second] == null && moveCards.size() > 0) {
                gameBoard[i][second] = moveCards.remove(cnt);
                ++cnt;
            }
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    // PRINTING METHODS
    public void printBoard() {
        String[] cards = Constants.cardFaces;
        String cardToPrint = "";

        // Draw pile card to print
        card drawCard = drawPile.peek();
        String drawnCard = drawCard.getValue() + drawCard.getSuit();
        for (int i = 0; i < cards.length; ++i) {
            if (cards[i].equals(drawnCard)) {
                drawnCard = cards[i + 1];
                break;
            }
        }

        for (int i = 0; i < gameBoard.length; ++i) {
            int cnt = 0;
            for (int c = 0; c < 9; c++) {
                if (i >= 1) {
                    System.out.print("           ");
                }
                if (i < 1) {
                    System.out.print(drawnCard.substring(cnt, cnt + 11));
                }
                for (int j = 0; j < gameBoard[0].length; ++j) {
                    if (gameBoard[i][j] == null) {
                        System.out.print("           ");
                        continue;
                    }
                    // The card we need to print
                    String cardOn = gameBoard[i][j].getValue() + gameBoard[i][j].getSuit();
                    if (!gameBoard[i][j].getVisibiliy()) {
                        cardOn = "Empty";
                    }
                    // Going through all cards in cards array to find proper card to print out and
                    // assign it to variable
                    for (int k = 0; k < cards.length - 1; ++k) {
                        if (cards[k].equals(cardOn)) {
                            cardToPrint = cards[k + 1];
                        }
                    }
                    // Print only section we on rn

                    System.out.print(cardToPrint.substring(cnt, cnt + 11));
                }

                System.out.println();
                cnt += 11;
            }

        }
    }

    public void tempPrint() {
        for (int i = 0; i < gameBoard.length; ++i) {
            for (int j = 0; j < gameBoard[i].length; ++j) {
                if (gameBoard[i][j] != null) {
                    if (gameBoard[i][j].getValue() > 9) {
                        System.out.print(gameBoard[i][j].getVisibiliy() + "" + gameBoard[i][j].getValue()
                                + gameBoard[i][j].getSuit().substring(0, 1) + "  ");
                    } else {
                        System.out.print(gameBoard[i][j].getVisibiliy() + "" + gameBoard[i][j].getValue()
                                + gameBoard[i][j].getSuit().substring(0, 1) + "   ");
                    }
                } else {
                    System.out.print("//////   ");
                }
            }
            System.out.println();
        }
    }
}
