public class MainPlatform {
    public static void main (String[] args){
        Map map=new Map();
        //Player playerOne=new Player(1,map);
        AI playerOne=new AI(1,map);
        Player playerTwo=new Player(2,map);

        boolean turnOne=false;
        while(map.continueCondition()){
            //map.printMap();
            if(turnOne){
                map.printMap(1);
                System.out.println("white's turn: ");
                playerOne.placeDisc();
                turnOne=false;
            }else{
                map.printMap(2);
                System.out.println("black's turn: ");
                playerTwo.placeDisc();
                turnOne=true;
            }
        }
       System.out.printf("Player 1: %d discs\nPlayer 2: %d discs\n",map.getWhiteDiscCount(),map.getBlackDiscCount());
        System.out.printf("%s",map.getWhiteDiscCount()>map.getWhiteDiscCount() ? "Player 1 won" : "Player 2 won");
    }
}
