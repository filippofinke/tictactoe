package ch.supsi.tictactoe.exceptions;

public class GameAlreadyStartedException extends IllegalStateException {
    public GameAlreadyStartedException() {
        super("Game already started");
    }
}
