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
                if(j+1 == i){ //Possible no worky :(
                    gameBoard[i][j].flip();
                }
                ++cnt;
            }
        }
    }

    public void draw(card card) {
        drawPile.add(card);
    }

    public void moveCard(int first, int second, int cardNumber) {
        //Validate move
        card current = gameBoard[first][gameBoard.length-cardNumber]; // fix edge case

        //King case
        if(gameBoard[second][0] == null && current.getValue() != 13){
            //change print location
            System.out.println("Invalid move");
            return;
        }
        //Check card is flipped up
        if(!current.getVisibiliy()){
            System.out.println("Invalid move");
            return;
        }
        //Check if suits are oppisite
        for(int i = 0; i< gameBoard.length; ++i){
            if(gameBoard[second][i+1] == null){

            }
        }

        // Find and grab all cards asked for and put into ArrayList
        ArrayList<card> moveCards = new ArrayList<>();
        for (int i = gameBoard.length; i >= 0; --i) {
            if (gameBoard[i][first] != null && gameBoard[i][first].getVisibiliy() && cardNumber > 0) {
                moveCards.add(gameBoard[i][first]);
                gameBoard[i][first] = null;
                --cardNumber;
            }
        }

        for (int i = 0; i < gameBoard.length; ++i) {
            if (gameBoard[i + 1][first] == null && gameBoard[i][first] != null) {
                gameBoard[i][first].flip();
            }
        }

        int cnt = 0;
        for (int i = 0; i < gameBoard.length; ++i) {
            if (gameBoard[i][second] == null) {
                gameBoard[i][second] = moveCards.get(cnt);
                ++cnt;
            }
        }
    }

}
