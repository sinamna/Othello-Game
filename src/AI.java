import java.util.ArrayList;

public class AI extends Player{
    class Coordinates{
        int x;
        int y;
        public Coordinates(int x,int y){
            this.x=x;
            this.y=y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
    //ArrayList<Coordinates> availablePlaces;
    public AI (int id,Map map){
        super(id,map);
       //availablePlaces=new ArrayList<>();
    }
    @Override
    public void placeDisc(){
        Coordinates bestPlace=findBest();
        map.placeDisc(playerId,bestPlace.getX(),bestPlace.getY());
        System.out.printf("%d %c",bestPlace.getX(),convertIntToChar(bestPlace.getY()));

    }
    private Coordinates findBest(){
        ArrayList <Coordinates> possiblePlaces=new ArrayList<>();
        for(int j=0;j<8;j++){
            for(int i=0;i<8;i++){
                if(map.checkPlace(playerId,j,i))
                    possiblePlaces.add(new Coordinates(j,i));
            }
        }
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
    private int numOfFlip(int x,int y){
        Map testMap = null;
        try{
            testMap=map.clone();
        }catch (CloneNotSupportedException e){
            System.out.println("can not be cloned");
        }
        return testMap.flipDiscs(playerId,x,y);
    }
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
