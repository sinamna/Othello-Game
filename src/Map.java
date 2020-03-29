import java.util.Scanner;

public class Map {

    private Disc[][]map;
    public Map(){
        map=new Disc[8][8];
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                map[i][j]=new Disc();
            }
        }
        //this part sets the first four disc in the map
        this.placeDisc(1,4-1,convertChar('D'));
        this.placeDisc(1,5-1,convertChar('E'));
        this.placeDisc(2,4-1,convertChar('E'));
        this.placeDisc(2,5-1,convertChar('D'));
    }
    public void placeDisc(Player player){
        //takes mokhtasat -> checks it can be placed -> new a disc and adds to players disc
        //changes map discs player Id
        Scanner input=new Scanner(System.in);
        // continue taking inputs until user enters correct input.
        if(this.placesToChoice(player.getPlayerId())!=0){
            while(true) {
                try {
                    int x=Integer.parseInt(input.next());
                    char yChar=input.next().charAt(0);
                    if (checkPlace(player.getPlayerId(), x - 1, convertChar(yChar))) {
                        this.placeDisc(player.getPlayerId(), x - 1, convertChar(yChar));
                        player.setDiscCount(player.getDiscCount() + 1);
                        break;
                    } else {
                        System.out.println("You can not place disc at this place");
                    }
                }catch (Exception e){
                    System.out.println("Please enter correct form ");
                }


            }
        }else {
            System.out.println("pass");
        }

    }
    /**
     * method goes throw all 8 direction and checks if it can be placed based on opposite compontent discs or not
     * @param player is the id of player.1 for white disc and 2 for black discs
     * @param x place in the map
     * @param y place in the map
     * @return whether can be placed in (x,y) or not
     */
    private boolean checkPlace(int player,int x,int y){
        int opPlayer=player==1?2:1;
        if (map[x][y].getPlayer()!=0) return false;
        //horizontal line
        int counter=0;
        for(int i=y;i<8;i++){
            if(i!=y && map[x][i].getPlayer()==0)break;
            if(map[x][i].getPlayer()==opPlayer) counter++;
            if(i!=y && map[x][i].getPlayer()==player && counter!=0)return true;
        }
        counter=0;
        for(int i=y;i>=0;i--){
            if(i!=y && map[x][i].getPlayer()==0)break;
            if(map[x][i].getPlayer()==opPlayer)counter++;
            if(i!=y && map[x][i].getPlayer()==player && counter!=0)return true;
        }
        //vertical line
        counter=0;
        for(int j=x;j>=0;j--){
            if(j!=x &&map[j][y].getPlayer()==0)break;
            if(map[j][y].getPlayer()==opPlayer)counter++;
            if(j!=x && map[j][y].getPlayer()==player && counter!=0)return true;
        }
        counter=0;
        for(int j=x;j<8;j++){
            if(j!=x &&map[j][y].getPlayer()==0)break;
            if(map[j][y].getPlayer()==opPlayer)counter++;
            if(j!=x && map[j][y].getPlayer()==player && counter!=0)return true;
        }
        //"\" like path
        counter=0;
        int j=x;
        for(int i=y;i>=0;i--){
            if(j!=x && i!=y &&map[j][i].getPlayer()==0)break;
            if(map[j][i].getPlayer()==opPlayer)counter++;
            if(j!=x && i!=y &&map[j][i].getPlayer()==player &&counter!=0)return true;
            if(j-1>=0)
                j--;
            else
                break;
        }
        counter=0;
        j=x;
        for(int i=y;i<8;i++){
            if(j!=x && i!=y &&map[j][i].getPlayer()==0)break;
            if(map[j][i].getPlayer()==opPlayer)counter++;
            if(j!=x && i!=y &&map[j][i].getPlayer()==player &&counter!=0)return true;
            if(j+1<8)
                j++;
            else
                break;
        }
        // the '/' paths
        j=x;
        counter=0;
        for(int i=y;i>=0;i--){
            if(j!=x && i!=y &&map[j][i].getPlayer()==0)break;
            if(map[j][i].getPlayer()==opPlayer)counter++;
            if(j!=x && i!=y &&map[j][i].getPlayer()==player &&counter!=0)return true;
            if(j+1<8)
                j++;
            else
                break;
        }
        counter=0;
        j=x;
        for(int i=y;i<8;i++){
            if(j!=x && i!=y &&map[j][i].getPlayer()==0)break;
            if(map[j][i].getPlayer()==opPlayer)counter++;
            if(j!=x && i!=y &&map[j][i].getPlayer()==player &&counter!=0)return true;
            if(j-1>=0)
                j--;
            else
                break;
        }
        return false;
    }
    private void placeDisc(int playerId,int x,int y){
           map[x][y].setPlayer(playerId);
           this.flipDiscs(playerId,x,y);
    }
    private void flipDiscs(int player,int x,int y){
        /*
        sth about using i and j
        x in multiDimensional array represents j
        and y in multidimensional array represents i
         */
        int counter=0;
        int opPlayer= player==1? 2:1;
        //handling horizontal line
        for(int i=y;i<8;i++){
            if (map[x][i].getPlayer()==opPlayer)
                counter++;
            else{
                if(map[x][i].getPlayer()==player){
                    while(counter>0){
                        map[x][y+counter].setPlayer(player);
                        counter--;
                    }
                    if(i!=y)
                        break;
                }else
                    break;
            }
        }
        counter=0;
        for(int i=y;i>=0;--i){
            if (map[x][i].getPlayer()==opPlayer)
                counter++;
            else{
                if(map[x][i].getPlayer()==player){
                    while(counter>0){
                        map[x][y-counter].setPlayer(player);
                        counter--;
                    }
                    if(i!=y)
                      break;
                }
                else
                    break;
            }
        }
        counter=0;
        //handling vertical line
        for(int j=x;j>=0;j--){
            if(map[j][y].getPlayer()==opPlayer)
                counter++;
            else{
                if(map[j][y].getPlayer()==player){
                    while(counter>0){
                        map[x-counter][y].setPlayer(player);
                        counter--;
                    }
                    if(j!=x)
                        break;
                }else
                    break;
            }
        }
        counter=0;
        for(int j=x;j<8;j++){
            if(map[j][y].getPlayer()==opPlayer)
                counter++;
            else{
                if(map[j][y].getPlayer()==player){
                    while(counter>0){
                        map[x+counter][y].setPlayer(player);
                        counter--;
                    }
                    if(j!=x)
                        break;
                }else
                     break;
            }
        }
        counter=0;
        //this part handles cross like paths
        // "\" like path
        int j=x;
        for(int i=y;i>=0;i--){
            if(map[j][i].getPlayer()==opPlayer)
                counter++;
            else{
                if(map[j][i].getPlayer()==player){
                    while(counter>0){
                        map[x-counter][y-counter].setPlayer(player);
                        counter--;
                    }
                    if(i!=y && j!=x)
                        break;
                }else
                 break;
            }
            if(j-1>=0)
                j--;
            else
                break;
        }
        counter=0;
        j=x;
        for(int i=y;i<8;i++){
            if(map[j][i].getPlayer()==opPlayer) {
                counter++;
            }
            else{
                if(map[j][i].getPlayer()==player){
                    //System.out.println(counter);
                    while(counter>0){
                        map[x+counter][y+counter].setPlayer(player);
                        counter--;
                    }
                    if(i!=y && j!=x)
                        break;
                }else
                  break;
            }
            if(j+1<8)
                j++;
            else
                break;
        }
        // again cross like part
        // "/" like path
        j=x;
        counter=0;
        for(int i=y;i>=0;i--){
            if(map[j][i].getPlayer()==opPlayer)
                counter++;
            else{
                if(map[j][i].getPlayer()==player){
                    while(counter>0){
                        map[x+counter][y-counter].setPlayer(player);
                        counter--;
                    }
                    if(i!=y && j!=x)
                        break;
                }else
                    break;

            }
            if(j+1<8)
                j++;
            else
                break;
        }
        counter=0;
        j=x;
        for(int i=y;i<8;i++){
            if(map[j][i].getPlayer()==opPlayer)
                counter++;
            else {
                if (map[j][i].getPlayer() == player) {
                    while (counter > 0) {
                        map[x - counter][y + counter].setPlayer(player);
                        counter--;
                    }
                    if (i != y && j != x)
                        break;
                    } else
                        break;

            }
            if(j-1>=0)
                j--;
            else
                break;
        }
    }
    public void printMap(int playerId) {
        /*
        player 1 has white discs
        player 2 has black discs
         */
        System.out.print("   |");
        for(char c='A';c<='H';c++){
            System.out.printf("  %c  ",c);
        }
        System.out.println("|");
        System.out.println("---  ---  ---  ---  ---  ---  ---  ---  ---");
        for(int j=0;j<8;j++){
            System.out.printf(" %d |",j+1);
            for(int i=0;i<8;i++){
                if(map[j][i].getPlayer()==1)
                    System.out.printf("  %c  ",'\u25cf');
                else if(map[j][i].getPlayer()==2)
                    System.out.printf("  %c  ",'\u25cb');
                else if(this.checkPlace(playerId,j,i)){
                    if(playerId==1)
                        System.out.printf(" p%c  ",'W');
                    else
                        System.out.printf(" p%c  ",'B');
                }
                else
                    System.out.printf("  .  ");

            }
            System.out.println("\n     ---  ---  ---  ---  ---  ---  ---  --- |");
        }
    }
    /**
     *
     * @param player the id of the player
     * @return how many places avaliable to choose in map for that player
     */
    private int placesToChoice(int player){
        int counter=0;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(checkPlace(player,i,j))counter++;
            }
        }
        return counter;
    }
    public boolean continueCondition(){

        return placesToChoice(1)!=0 && placesToChoice(2)!=0;
    }
    /**
     *
     * @param mapSide the character of map position
     * @return returns the equal in Map second dimension
     */
    private int convertChar(char mapSide){
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
