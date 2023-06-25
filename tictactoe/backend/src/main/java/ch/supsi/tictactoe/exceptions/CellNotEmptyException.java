package ch.supsi.tictactoe.exceptions;

public class CellNotEmptyException extends IllegalStateException {
    public CellNotEmptyException() {
        super("Cell is not empty");
    }
}
