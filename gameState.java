import java.util.*;

public class gameState {
    private deck deck = new deck();
    private Stack<card> drawPile = new Stack<card>();
    private card[][] scoring = new card[13][4];
    private card[][] gameBoard = new card[20][7];

    public gameState() {
    }

    public void newGame(card[] cards) {
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

    public void draw(card card) {
        drawPile.add(card);
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

        if (!currentCard.getColor().equals(futureCard.getColor())
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



    
}
