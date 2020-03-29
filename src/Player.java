import java.util.*;
public class Player {
    //the list is used to determine the number of discs each player has
    private int discCount;
    private int playerId;
    public Player(int playerId){
        this.playerId=playerId;
        discCount=0;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getDiscCount() {
        return discCount;
    }

    public void setDiscCount(int discCount) {
        this.discCount = discCount;
    }
}
