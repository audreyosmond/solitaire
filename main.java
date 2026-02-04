import java.util.*;

public class main {
    public static void main(String[] args) {
        //game setup
        gameState game = new gameState();
        deck cards=new deck();
        game.newGame(cards.newGame());
        boolean playing=true;

        //input
        Scanner input=new Scanner(System.in);
        while(playing){
            game.printBoard();

            System.out.print("Move card, Draw card, or Score card? ");
            String choice=input.next();
            System.out.println();
            choice=choice.toLowerCase();

            if(choice.equals("move")){
                System.out.print("Which column are you moving from? ");
                int first = input.nextInt();
                System.out.println();
                System.out.print("Which card are you moving? ");
                int cardNumber = input.nextInt();
                System.out.println();
                System.out.print("Which column are you moving to? ");
                int second = input.nextInt();
                System.out.println();

                game.validateMoveCard(first-1, second-1, cardNumber-1);
            }
            if(choice.equals("draw")){
                game.draw();
            }
            if(choice.equals("score")){
                System.out.print("Which column are you scoring from? ");
                int column=input.nextInt();
                System.out.println();
                System.out.print("Which card are you scoring? ");
                int cardNumber=input.nextInt();
                game.score(column , cardNumber);
            }
        }
    }
}