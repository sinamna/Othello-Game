public class Disc {
    /*
    if player be 1 then its white
    player=2 its black
    if player be -1 it can be placed by white
    player=-2 can be place by black
    if player is set to -3 it means in can be placed by both white
    and black dics
     */
    private int player;
    public Disc(int playerId){
        player=playerId;
    }
    public Disc(){
        player=0;
    }
    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player=player;
    }
}
