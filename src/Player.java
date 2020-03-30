import java.util.*;
public class Player {
    //the list is used to determine the number of discs each player has
    protected int playerId;
    protected Map map;
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
    public int getPlayerId() {
        return playerId;
    }
    public void placeDisc(){
        Scanner input=new Scanner(System.in);
        if(map.placesToChoice(playerId)!=0){
           while(true){
               try{
                   int x=Integer.parseInt(input.next());
                   char yChar=input.next().charAt(0);
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
            System.out.println("Pass");
    }
    /**
     *
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
