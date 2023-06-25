package ch.supsi.tictactoe.player;


import ch.supsi.tictactoe.gamelogic.Game;
import ch.supsi.tictactoe.gamelogic.Move;

public abstract class Player implements PlayerTurnListener {
    private Game game;

    public Player() {}

    public void setGame(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }


    public void play(int row, int col) {
        Move move = new Move(row, col, this);
        game.play(move, false);
    }

    @Override
    public String toString() {
        return "Player{game=" + game + '}';
    }
}
