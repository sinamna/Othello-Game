import java.util.ArrayList;

public class Ai extends Player{
    /**
     * coordinates is actually (x,y) object which holds both x and y
     */
    class Coordinates{
        int x;
        int y;
        public Coordinates(int x,int y){
            this.x=x;
            this.y=y;
        }

        /**
         *
         * @return returns x
         */
        public int getX() {
            return x;
        }

        /**
         *
         * @return returns y
         */
        public int getY() {
            return y;
        }
    }

    /**
     *
     * @param id is the id of AI
     * @param map  is the map which AI will be playing based on it
     *             using super constructor it places starting discs
     */
    public Ai (int id,Map map){
        super(id,map);
    }

    /**
     * AI choose a place which has maximum flip of other players discs
     * first it finds possible places and then compare their number of flipping they cause
     * and then choose the one which cause maximum flippings
     */
    @Override
    public void placeDisc(){
        Coordinates bestPlace=findBest();
        map.placeDisc(playerId,bestPlace.getX(),bestPlace.getY());
        System.out.printf("%d %c\n",bestPlace.getX()+1,convertIntToChar(bestPlace.getY()));
    }

    /**
     * it finds best coordinate for disc to be placed
     * @return returns the best spot for placing the disc
     */
    private Coordinates findBest(){
        ArrayList <Coordinates> possiblePlaces=new ArrayList<>();
        //this part stores all of possible places for AI in the list
        for(int j=0;j<8;j++){
            for(int i=0;i<8;i++){
                if(map.checkPlace(playerId,j,i))
                    possiblePlaces.add(new Coordinates(j,i));
            }
        }
        //this part gets the coordinate which make maximum flip using numOfFlip method and
        //comparing results
        Coordinates bestPlace=possiblePlaces.get(0);
        int maxflips=this.numOfFlip(bestPlace.getX(),bestPlace.getY());
        for(int i=1;i<possiblePlaces.size();i++){
            int x=possiblePlaces.get(i).getX();
            int y=possiblePlaces.get(i).getY();
            if(numOfFlip(x,y)>maxflips){
                bestPlace=possiblePlaces.get(i);
                maxflips=numOfFlip(x,y);
            }
        }
        return bestPlace;
    }

    /**
     * this method make a clone of current state of the map and calculate number of
     * flips when placing disc in (x,y)
     * @param x place in the map
     * @param y place in the map
     * @return number of flips if we put a disc in (x,y)
     */
    private int numOfFlip(int x,int y){
        Map tempMap=new Map(this.map);
        tempMap.getMap()[x][y].setPlayer(playerId);
        return tempMap.flipDiscs(playerId,x,y);
    }

    /**
     * converts the y coordinate in the map to char
     * used for printing chosen place
     * @param mapSide
     * @return
     */
    private char convertIntToChar(int mapSide){
        switch(mapSide){
            case 0:
                return 'A';
            case 1:
                return 'B';
            case 2:
                return 'C';
            case 3:
                return 'D';
            case 4:
                return 'E';
            case 5:
                return 'F';
            case 6:
                return 'G';
            case 7:
                return 'H';
        }
        //just for skipping the errors
        return '.';
    }
}
