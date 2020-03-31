import java.util.Scanner;

public class MainPlatform {
    public static void main (String[] args){
        Scanner input=new Scanner(System.in);
        Map map=new Map();
        System.out.println("Which one you want to play with ? :");
        System.out.printf("1) Another player \n2) AI\n");
        Player playerOne;
        if(input.nextInt()==1)
            playerOne=new Player(1,map);
        else
            playerOne=new Ai(1,map);
        Player playerTwo=new Player(2,map);
        /*
        lastPlayer is used to determine last player played before game end print the map
        after that
         */
        int lastPlayer=2;
        //used for making turns for players
        boolean turnOne=false;
        while(map.continueCondition()){
            //map.printMap();
            if(turnOne){
                map.printMap(1);
                System.out.println("white's turn: ");
                playerOne.placeDisc();
                lastPlayer=1;
                turnOne=false;
            }else{
                map.printMap(2);
                System.out.println("black's turn: ");
                playerTwo.placeDisc();
                lastPlayer=2;
                turnOne=true;
            }
        }
        map.printMap(lastPlayer);
       System.out.printf("Player 1: %d discs\nPlayer 2: %d discs\n",map.getWhiteDiscCount(),map.getBlackDiscCount());
        System.out.printf("%s",map.getWhiteDiscCount()>map.getBlackDiscCount() ? "Player 1 won" : "Player 2 won");
    }
}
