package ch.supsi.tictactoe.player;

public class HumanPlayer extends Player {

    private final PlayerTurnListener listener;

    public HumanPlayer(PlayerTurnListener listener) {
        this.listener = listener;
    }

    @Override
    public void onMyTurn() {
        listener.onMyTurn();
    }
}