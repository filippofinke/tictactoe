package ch.supsi.tictactoe.exceptions;

public class InvalidSymbolException extends IllegalArgumentException {

    public InvalidSymbolException(char symbol) {
        super("Symbol not allowed: " + symbol);
    }
}
