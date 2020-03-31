import java.util.*;
public class Player {
    //the list is used to determine the number of discs each player has
    protected int playerId;
    protected Map map;

    /**
     *
     * @param playerId the player id of the player
     * @param map the map which player is playing based on it
     *            constructor also place starting discs
     */
    public Player(int playerId,Map map){
        this.map=map;
        this.playerId=playerId;
        if(playerId==1){
            map.placeDisc(1,4-1,convertChar('D'));
            map.placeDisc(1,5-1,convertChar('E'));
        }else{
            map.placeDisc(2,4-1,convertChar('E'));
            map.placeDisc(2,5-1,convertChar('D'));
        }
    }

    /**
     *
     * @return returns playerID
     */
    public int getPlayerId() {
        return playerId;
    }

    /**
     * this method takes coordinates from user and if its possible place discs in map
     */
    public void placeDisc(){
        Scanner input=new Scanner(System.in);
        //it checks if is there any spot for discs to be placed
        if(map.placesToChoice(playerId)!=0){
            //continue taking inputs until user enters correct input
           while(true){
               try{
                   int x=Integer.parseInt(input.next());
                   char yChar=input.next().charAt(0);
                   //checks if this place in map is available or not
                   if(map.checkPlace(playerId,x-1,convertChar(yChar))){
                       map.placeDisc(playerId,x-1,convertChar(yChar));
                       break;
                   }else
                       System.out.println("You can not place disc at this place");
               }catch(Exception e){
                   System.out.println("Enter correct place");
               }

           }
        }
        else
            //of there be no place to choose player pass the turn
            System.out.println("Pass");
    }
    /**
     * @param mapSide the character of map position
     * @return returns the equal in Map second dimension
     */
    protected int convertChar(char mapSide){
        switch(mapSide){
            case 'A':
                return 0;
            case 'B':
                return 1;
            case 'C':
                return 2;
            case 'D':
                return 3;
            case 'E':
                return 4;
            case 'F':
                return 5;
            case 'G':
                return 6;
            case 'H':
                return 7;
            default:
                System.out.println("You have entered wrong character");
        }
        return -1;
    }
}
