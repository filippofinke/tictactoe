package ch.supsi.tictactoe.exceptions;

public class NotYourTurnException extends IllegalStateException {
    public NotYourTurnException() {
        super("It's not your turn!");
    }
}
