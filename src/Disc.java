public class Disc {
    /*
    if player is 0 it means there is a empty spot
    if player is 1 it means it belongs to player one and its white
    if player is 2 it means it belongs to player two and its black
     */
    private int player;

    /**
     * this constructor used when cloning a map
     * @param playerId the player Id of the disc
     */
    public Disc(int playerId){
        player=playerId;
    }

    /**
     * when making empty spot this constructor is used
     */
    public Disc(){
        player=0;
    }

    /**
     *
     * @return returns the player ID
     */
    public int getPlayer() {
        return player;
    }

    /**
     * sets the player id of the disc
     * @param player id to be set
     */
    public void setPlayer(int player) {
        this.player=player;
    }
}
