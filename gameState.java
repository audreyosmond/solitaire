import java.util.*;
public class gameState {
    private deck deck = new deck();
    private Stack<card> drawPile = new Stack<card>();
    private card[][] scoring = new card[13][4];
    private card[][] gameBoard = new card[20][7];

    public gameState(){

    }

    public void newGame(card[] cards){
        int cnt = 0;
        for(int i = 0; i<gameBoard[0].length; ++i){
            for(int j = 0; j<=i; ++j){
                gameBoard[i][j] = cards[cnt];
                ++cnt;
            }
        }
    }

    public void draw(card card){
        drawPile.add(card);
    }

    public void moveCard(int first, int second){
        ArrayList<card> moveCards = new ArrayList<>();
        for(int i = 0; i<gameBoard.length; ++i){
            if(gameBoard[i][first].getVisible){
                moveCards.add(gameBoard[i][first]);
                gameBoard[i][first] = null;
            }
        }

        int cnt = 0;
        for(int i = 0; i<gameBoard.length; ++i){
            if(gameBoard[i][second] == null){
                gameBoard[i][second] = moveCards.get(cnt);
                ++cnt;
            }
        }
    }

}
