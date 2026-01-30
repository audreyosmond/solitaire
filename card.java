public class card {
    private int value;
    private String suit;
    private String color;
    public card(int v,String s, String c){
        value=v;
        suit=s;
        color=c;
    }
    public int getValue(){
        return value;
    }
    public String getSuit(){
        return suit;
    }
    public String getColor(){
        return color;
    }
}
