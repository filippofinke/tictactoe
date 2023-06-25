package ch.supsi.tictactoe.player;


public class ComputerPlayer extends Player {

    private final AI ai;

    public ComputerPlayer() {
        ai = new AI(this);
    }

    @Override
    public void onMyTurn() {
        ai.play();
    }
}
