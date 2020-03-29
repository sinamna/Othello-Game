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
        //x-1 is the position of first dimension of array
        this.prepareSpace(1,4-1,convertChar('D'));
        this.prepareSpace(1,5-1,convertChar('E'));
        this.prepareSpace(2,4-1,convertChar('E'));
        this.prepareSpace(2,5-1,convertChar('D'));
    }
    public void placeDisc(Player player){
        //takes mokhtasat -> checks it can be placed -> new a disc and adds to players disc
        //changes map discs player Id
        Scanner input=new Scanner(System.in);
        // continue taking inputs until user enters correct input.
        while(true) {
            int x=Integer.parseInt(input.next());
            char yChar=input.next().charAt(0);
            if (canPlace(player, x - 1, convertChar(yChar))) {
                this.placeDisc(player.getPlayerId(), x - 1, convertChar(yChar));
                player.setDiscCount(player.getDiscCount() + 1);
                break;
            } else {
                System.out.println("You can not place disc at this place");
            }
        }
    }
    private void placeDisc(int playerId,int x,int y){
           map[x][y].setPlayer(playerId);
           this.flipDiscs(playerId,x,y);
           this.prepareSpace(playerId,x,y);
          // player.setDiscCount(player.getPlayerId()+1);
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
                        //this.prepareSpace(player,x,y+counter);
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
                        //this.prepareSpace(player,x,y-counter);
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
                        //this.prepareSpace(player,x-counter,y);
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
                        //this.prepareSpace(player,x+counter,y);
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
                        //this.prepareSpace(player,x-counter,y-counter);
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

                        //this.prepareSpace(player,x+counter,y+counter);
                        counter--;
                    }
                    if(i!=y && j!=x)
                        break;
                }else
                    //this part has passed same color disc or it didn't find any
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
                        //this.prepareSpace(player,x+counter,y-counter);
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
                        //this.prepareSpace(player,x-counter,y+counter);
                        counter--;
                    }
                    if (i != y && j != x)
                        break;
                } else{
                   // if(counter!=0 && )
                }

            }
            if(j-1>=0)
                j--;
            else
                break;
        }
    }
    /**
     * ok ...this method is weird.it checks around specific disc . if it encounter
     * disc of opposite player mark the opposite situation in a manner which means the other
     * player can place their disc there
     * @param player is the Id of player
     * @param x is the position in first dimension of array
     * @param y is the position in the second dimension of array
     */
    private void prepareSpace(int player,int x,int y){
        int opPlayer= player==1? 2:1;
        for(int i=y-1;i<=y+1;i++){
            for(int j=x-1;j<=x+1;j++){
                try{
                    if(map[j][i].getPlayer()!=player && map[j][i].getPlayer()==opPlayer){
                        if(map[x-(j-x)][y-(i-y)].getPlayer()<=0)
                            map[x-(j-x)][y-(i-y)].setPlayer(-opPlayer);
                    }
                } catch (Exception e){
                }
            }
        }
    }
    private boolean canPlace(Player player,int x,int y){
        return map[x][y].getPlayer()==(-player.getPlayerId());
    }
    public void printMap() {
        /*
        player 1 has white discs
        player 2 has black discs
         */
        for (int i = 0; i < 8; i++) {
            for (Disc disc : map[i]) {
                if (disc.getPlayer() == 1) {
                    System.out.printf("%c  ", 'w');
                } else if (disc.getPlayer() == 2) {
                    System.out.printf("%c  ", 'b');
                }else if(disc.getPlayer()==-1){
                    System.out.printf("%c  ",'3');
                }else if(disc.getPlayer()==-2){
                    System.out.printf("%c  ",'4');
                }
                else {
                    System.out.printf("0  ");
                }
            }
            System.out.println();
        }
    }

    public boolean continueCondition(){
        return true;
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
