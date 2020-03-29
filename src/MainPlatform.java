public class MainPlatform {
    public static void main (String[] args){
        Map map=new Map();
        Player playerOne=new Player(1);
        Player playerTwo=new Player(2);
        boolean turnOne=false;
        while(map.continueCondition()){
            map.printMap();
            if(turnOne){
                System.out.println("white's turn: ");
                map.placeDisc(playerOne);
                turnOne=false;
            }else{
                System.out.println("black's turn: ");
                map.placeDisc(playerTwo);
                turnOne=true;
            }
        }
    }
}
