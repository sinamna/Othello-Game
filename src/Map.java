import java.util.Scanner;

public class Map implements Cloneable{

    private Disc[][]map;
    private int whiteDiscCount;
    private int blackDiscCount;

    /**
     * default constructor creates 2 dimensional array of discs
     * and give them default value of playerId 0 which means its empty spot;
     * at the beginning
     * number of white discs and black discs are 0 at the begining
     */
    public Map(){
        map=new Disc[8][8];
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                map[i][j]=new Disc();
            }
        }
        whiteDiscCount=0;
        blackDiscCount=0;
    }

    /**
     * this constructor is used to create a deep copy of map
     *  it creates new map which a copy of arrays of discs and if we alter the
     *  array it wont effect the original map
     * @param mapToClone is the map which is going to be cloned
     */
    public Map(Map mapToClone){
        Disc[][] preMap=mapToClone.getMap();
        map=new Disc[8][8];
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                map[i][j]=new Disc(preMap[i][j].getPlayer());
            }
        }
        whiteDiscCount=mapToClone.getWhiteDiscCount();
        blackDiscCount=mapToClone.getBlackDiscCount();
    }
    /**
     * this method takes a coordinates and checks the surrounding in all 8 direction to check
     *  the disc with taken playerId can be placed there or not
     *  note that y is the char taken from user and converted to equal place in map
     * @param player is the id of player.1 for white disc and 2 for black discs
     * @param x place in the map
     * @param y place in the map
     * @return whether disc can be placed in (x,y) or not
     */
    public boolean checkPlace(int player,int x,int y){
        /*
        the main logic is that it starts from x and y place and checks is there any
        opposite discs between taken coordinates and same-player discs it finds
         */
        int opPlayer=player==1?2:1;
        if (map[x][y].getPlayer()!=0) return false;
        //horizontal line
        int counter=0;
        for(int i=y;i<8;i++){
            if(i!=y && map[x][i].getPlayer()==0)break;
            if(map[x][i].getPlayer()==opPlayer) counter++;
            if(i!=y &&map[x][i].getPlayer()==player &&counter==0)break;
            if(i!=y && map[x][i].getPlayer()==player && counter!=0)return true;
        }
        counter=0;
        for(int i=y;i>=0;i--){
            if(i!=y && map[x][i].getPlayer()==0)break;
            if(map[x][i].getPlayer()==opPlayer)counter++;
            if(i!=y &&map[x][i].getPlayer()==player &&counter==0)break;
            if(i!=y && map[x][i].getPlayer()==player && counter!=0)return true;
        }
        //vertical line
        counter=0;
        for(int j=x;j>=0;j--){
            if(j!=x &&map[j][y].getPlayer()==0)break;
            if(map[j][y].getPlayer()==opPlayer)counter++;
            if(j!=x &&map[j][y].getPlayer()==player &&counter==0)break;
            if(j!=x && map[j][y].getPlayer()==player && counter!=0)return true;
        }
        counter=0;
        for(int j=x;j<8;j++){
            if(j!=x &&map[j][y].getPlayer()==0)break;
            if(map[j][y].getPlayer()==opPlayer)counter++;
            if(j!=x &&map[j][y].getPlayer()==player &&counter==0)break;
            if(j!=x && map[j][y].getPlayer()==player && counter!=0)return true;
        }
        //"\" like path
        counter=0;
        int j=x;
        for(int i=y;i>=0;i--){
            if(j!=x && i!=y &&map[j][i].getPlayer()==0)break;
            if(map[j][i].getPlayer()==opPlayer)counter++;
            if(j!=x && i!=y &&map[j][i].getPlayer()==player &&counter==0)break;
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
            if(j!=x && i!=y &&map[j][i].getPlayer()==player &&counter==0)break;
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
            if(j!=x && i!=y &&map[j][i].getPlayer()==player &&counter==0)break;
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
            if(j!=x && i!=y &&map[j][i].getPlayer()==player &&counter==0)break;
            if(j!=x && i!=y &&map[j][i].getPlayer()==player &&counter!=0)return true;
            if(j-1>=0)
                j--;
            else
                break;
        }
        return false;
    }

    /**
     * this method place disc in the given coordinates and adds to counter number and
     * flips the discs can be flipped
     * @param playerId the playerId of the disc that is going to be placed
     * @param x place in the map
     * @param y place in the map
     */
    public void placeDisc(int playerId,int x,int y){
           map[x][y].setPlayer(playerId);
           if(playerId==1)
               whiteDiscCount++;
           else
                blackDiscCount++;
           this.flipDiscs(playerId,x,y);
    }

    /**
     * it recounts the discs its flipped
     * @param playerId id of the disc placed
     */
    private void flippingRecount(int playerId){
        if(playerId==1){
            whiteDiscCount++;
            blackDiscCount--;
        }else{

            blackDiscCount++;
            whiteDiscCount--;
        }
    }

    /**
     * method will flip other discs if its possible when placing a disc in given coordinates
     * @param player the playerId of the disc placed
     * @param x place on the map
     * @param y place on the map
     * @return returns number of dics flipped when the dics placed in the map
     */
    public int flipDiscs(int player,int x,int y){
        /*
        sth about using i and j
        x in multiDimensional array represents j
        and y in multidimensional array represents i
         */
        int numOfFlips=0;
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
                        numOfFlips++;
                        flippingRecount(player);
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
                        numOfFlips++;
                        flippingRecount(player);
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
                        numOfFlips++;
                        flippingRecount(player);
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
                        numOfFlips++;
                        flippingRecount(player);
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
                        numOfFlips++;
                        flippingRecount(player);
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
                        numOfFlips++;
                        flippingRecount(player);
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
                        numOfFlips++;
                        flippingRecount(player);
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
                        numOfFlips++;
                        flippingRecount(player);
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
        return numOfFlips;
    }

    /**
     *
     * @param playerId it takes players id and prints map in that turn
     */
    public void printMap(int playerId) {
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
                    System.out.printf("  %c  ",'\u25a0');
                }
                else
                    System.out.printf("  .  ");
            }
            System.out.println("\n     ---  ---  ---  ---  ---  ---  ---  --- |");
        }
        System.out.printf("white discs : %d | black discs : %d\n",whiteDiscCount,blackDiscCount);
    }
    /**
     *
     * @param player the id of the player
     * @return how many places available to choose in map for that player
     */
    public int placesToChoice(int player){
        int counter=0;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(checkPlace(player,i,j))counter++;
            }
        }
        return counter;
    }

    /**
     * game is finished when non of the player could choose a place in the map to
     * place their dics
     * @return returns true if there is still places to choose , and false when game is finished
     */
    public boolean continueCondition(){
        return placesToChoice(1)!=0 && placesToChoice(2)!=0;
    }

    /**
     *
     * @return returns black disc count
     */
    public int getBlackDiscCount() {
        return blackDiscCount;
    }

    /**
     *
     * @return returns white disc count
     */
    public int getWhiteDiscCount() {
        return whiteDiscCount;
    }

    /**
     *
     * @return returns 2 dimenstional array of discs
     */
    public Disc[][] getMap() {
        return map;
    }
}
