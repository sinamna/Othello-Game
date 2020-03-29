public class MainPlatform {
    public static void main (String[] args){
        Map map=new Map();
        Player playerOne=new Player(1);
        Player playerTwo=new Player(2);
        boolean turnOne=false;
        while(map.continueCondition()){
            //map.printMap();
            if(turnOne){
                map.printMap(1);
                System.out.println("white's turn: ");
                map.placeDisc(playerOne);
                turnOne=false;
            }else{
                map.printMap(2);
                System.out.println("black's turn: ");
                map.placeDisc(playerTwo);
                turnOne=true;
            }
        }
        System.out.printf("Player 1: %d discs\nPlayer 2: %d discs\n",playerOne.getDiscCount(),playerTwo.getDiscCount());
        System.out.printf("%s",playerOne.getDiscCount()>playerTwo.getDiscCount() ? "Player 1 won" : "Player 2 won");
    }
}
