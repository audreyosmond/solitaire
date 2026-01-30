public class card {
    private int value;
    private String suit;
    private String color;
    private boolean visible;
    public card(int v,String s, String c, boolean vis){
        value=v;
        suit=s;
        color=c;
        visible=vis;
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
    public boolean getVisibiliy(){
        return visible;
    }
    public void flip(){
        visible=!visible;
    }
}
