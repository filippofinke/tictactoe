package ch.supsi.tictactoe.exceptions;

public class FailedToSaveGameException extends Exception {
    public FailedToSaveGameException(String message) {
        super(message);
    }
}
