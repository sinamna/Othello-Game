import java.util.Scanner;

public class Map implements Cloneable{

    private Disc[][]map;
    private int whiteDiscCount;
    private int blackDiscCount;
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
    public Disc[][] getMap() {
        return map;
    }

    /**
     * method goes throw all 8 direction and checks if it can be placed based on opposite compontent discs or not
     * @param player is the id of player.1 for white disc and 2 for black discs
     * @param x place in the map
     * @param y place in the map
     * @return whether can be placed in (x,y) or not
     */

    public boolean checkPlace(int player,int x,int y){
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
    public void placeDisc(int playerId,int x,int y){
           map[x][y].setPlayer(playerId);
           if(playerId==1)
               whiteDiscCount++;
           else
                  blackDiscCount++;
           this.flipDiscs(playerId,x,y);
    }
    private void flippingRecount(int playerId){
        if(playerId==1){
            whiteDiscCount++;
            blackDiscCount--;
        }else{

            blackDiscCount++;
            whiteDiscCount--;
        }
    }
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
     * @return how many places avaliable to choose in map for that player
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
    public boolean continueCondition(){
            boolean flag=placesToChoice(1)!=0 && placesToChoice(2)!=0;

        return flag;
    }


    public int getBlackDiscCount() {
        return blackDiscCount;
    }

    public int getWhiteDiscCount() {
        return whiteDiscCount;
    }

    @Override
    protected Map clone() throws CloneNotSupportedException {
        Map returnable=(Map) super.clone();
        return returnable;
    }
}
