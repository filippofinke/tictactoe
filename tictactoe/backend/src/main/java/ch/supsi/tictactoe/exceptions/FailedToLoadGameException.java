package ch.supsi.tictactoe.exceptions;

public class FailedToLoadGameException extends Exception {
    public FailedToLoadGameException(String message) {
        super(message);
    }
}
