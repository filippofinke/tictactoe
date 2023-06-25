package ch.supsi.tictactoe.exceptions;

public class GameNotInProgressException extends IllegalStateException {
    public GameNotInProgressException() {
        super("Game is not in progress");
    }
}
