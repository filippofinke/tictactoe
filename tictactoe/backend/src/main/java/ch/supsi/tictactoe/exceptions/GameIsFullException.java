package ch.supsi.tictactoe.exceptions;

public class GameIsFullException extends IllegalStateException {
    public GameIsFullException() {
        super("Game is full");
    }
}
