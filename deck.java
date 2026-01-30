import java.util.*;

public class deck {
    private ArrayList<card> cardDeck;

    public deck() {
        cardDeck = new ArrayList<>();
        int cnt = 1;
        while (cnt < 14) {
            cardDeck.add(new card(cnt, "s", "r"));
            cardDeck.add(new card(cnt, "h", "r"));
            cardDeck.add(new card(cnt, "s", "b"));
            cardDeck.add(new card(cnt, "c", "b"));
        }
    }

    public card draw() {
        int ran = (int) (Math.random() * cardDeck.size());
        card temp = cardDeck.get(ran);
        cardDeck.remove(ran);
        return temp;
    }

    public void shuffle() {
        for (int lcv = 0; lcv < cardDeck.size(); lcv++) {
            int ran = (int) (Math.random() * cardDeck.size());
            card temp = cardDeck.get(ran);
            card ind = cardDeck.get(lcv);
            cardDeck.set(lcv, temp);
            cardDeck.set(ran, ind);
        }
    }

    public void shuffledraw(card[] cards) {
        for (card c : cards) {
            cardDeck.add(c);
        }
        shuffle();
    }

    public card[] getdeck() {
        return (card[]) cardDeck.toArray();
    }

}