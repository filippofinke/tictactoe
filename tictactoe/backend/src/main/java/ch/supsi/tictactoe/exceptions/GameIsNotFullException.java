package ch.supsi.tictactoe.exceptions;

public class GameIsNotFullException extends IllegalStateException {
    public GameIsNotFullException() {
        super("Game is not full");
    }
}
