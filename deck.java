import java.util.*;

public class deck {
    private ArrayList<card> cardDeck;

    public deck() {
        cardDeck = new ArrayList<>();
        int cnt = 1;
        while (cnt < 14) {
            cardDeck.add(new card(cnt, "Diamonds", "r", false));
            cardDeck.add(new card(cnt, "Hearts", "r", false));
            cardDeck.add(new card(cnt, "Spades", "b", false));
            cardDeck.add(new card(cnt, "Clubs", "b", false));
            cnt++;
        }
    }

    public card draw() {
        card temp = cardDeck.remove(0);
        return temp;
    }

    public void shuffle() {
        for (int i = 0; i < 100; ++i) {
            for (int lcv = 0; lcv < cardDeck.size(); lcv++) {
                int ran = (int) (Math.random() * cardDeck.size());
                card temp = cardDeck.get(ran);
                card ind = cardDeck.get(lcv);
                cardDeck.set(lcv, temp);
                cardDeck.set(ran, ind);
            }
        }
    }

    //Not needed based on draw method in gameState class
    /* 
    public void shuffledraw(card[] cards) {
        for (card c : cards) {
            cardDeck.add(c);
        }
        shuffle();
    }
        */
    public void add(card card){
        cardDeck.add(card);
    }

    public ArrayList<card> getdeck() {
        return cardDeck;
    }

    public card[] newGame() {
        card[] gameCards = new card[28];
        for (int i = 0; i < gameCards.length; ++i) {
            gameCards[i] = draw();
        }
        return gameCards;
    }

    public boolean isEmpty(){
        if(cardDeck.size() > 0){
            return false;
        }
        else{
            return true;
        }
    }

}