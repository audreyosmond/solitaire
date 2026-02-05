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
        int cnt = 0;
        for (int i = 0; i < gameBoard[0].length; ++i) {
            for (int j = 0; j <= i; ++j) {
                gameBoard[i][j] = cards[cnt];
                if (j + 1 == i) { // Possible no worky :(
                    gameBoard[i][j].flip();
                }
                ++cnt;
            }
        }
    }

    public void draw() {
        drawPile.add(deck.draw());
    }

    public void validateMoveCard(int first, int second, int cardNumber) {
        // Crating the cards we are using for checks
        card currentCard = gameBoard[first][gameBoard.length - cardNumber]; // fix edge case

        card futureCard = null;
        for (int i = 0; i < gameBoard.length - 1; ++i) {
            if (gameBoard[i + 1][second] == null) {
                futureCard = gameBoard[i][second];
            }
        }

        // King case
        if (currentCard.getValue() == 13 && futureCard == null) {
            moveCard(first, second, cardNumber);
            return;
        }

        if (currentCard.getVisibiliy() && !currentCard.getColor().equals(futureCard.getColor())
                && currentCard.getValue() < futureCard.getValue() - 1) {
            moveCard(first, second, cardNumber);
        } else {
            System.out.println("Invalid move");
        }
    }

    public void moveCard(int first, int second, int cardNumber) {
        // Find and grab all cards asked for and put into ArrayList
        ArrayList<card> moveCards = new ArrayList<>();
        for (int i = gameBoard.length; i >= 0; --i) {
            if (gameBoard[i][first] != null && gameBoard[i][first].getVisibiliy() && cardNumber > 0) {
                moveCards.add(gameBoard[i][first]);
                gameBoard[i][first] = null;
                --cardNumber;
            }
        }

        // Flip the next Card in list
        for (int i = 0; i < gameBoard.length; ++i) {
            if (gameBoard[i + 1][first] == null && gameBoard[i][first] != null) {
                gameBoard[i][first].flip();
            }
        }

        // Put the cards into desired location
        int cnt = 0;
        for (int i = 0; i < gameBoard.length; ++i) {
            if (gameBoard[i][second] == null) {
                gameBoard[i][second] = moveCards.get(cnt);
                ++cnt;
            }
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    // PRINTING METHODS

    public void printBoard() {
        String[] cards = Constants.cardFaces;
        String cardToPrint = "";
        int cnt = 0;

        for (int i = 0; i < gameBoard.length; ++i) {
            for (int c = 0; c < 9; c++) {
                for (int j = 0; j < gameBoard[0].length; ++j) {
                    if (gameBoard[i][j] == null) {
                        continue;
                    }
                    // The card we need to print
                    String cardOn = gameBoard[i][j].getValue() + gameBoard[i][j].getSuit();
                    if(!gameBoard[i][j].getVisibiliy()){
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

        /*
         * int cnt = 0;
         * //increment through the layers of the cards
         * for(int k = 0; k < 9; ++k){
         * //increment through the columns of the board
         * for(int i = 0; i<gameBoard[0].length; ++i){
         * //increment through the Cards in the array
         * for(int j = 0; j < cards.length-1; ++j){
         * if(cards[j].equals(Player.get(i).getCard())){
         * String me = cards[j+1].substring(cnt, cnt + 11);
         * System.out.print(me);
         * }
         * }
         * }
         * System.out.println();
         * cnt += 11;
         * }
         */
    }
}
