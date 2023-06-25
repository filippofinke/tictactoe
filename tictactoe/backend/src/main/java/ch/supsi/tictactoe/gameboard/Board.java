package ch.supsi.tictactoe.gameboard;

import ch.supsi.tictactoe.player.Player;

public class Board implements GameBoard {
    private static final int BOARD_SIZE = 3;
    private final Player[][] cells = new Player[BOARD_SIZE][BOARD_SIZE];

    private void validateCoordinates(int row, int col) {
        if (row < 0 || row > cells.length - 1 || col < 0 || col > cells.length - 1)
            throw new IllegalArgumentException("Invalid row or column");
    }

    @Override
    public void setCell(int row, int col, Player value) {
        validateCoordinates(row, col);

        if (cells[row][col] != null)
            throw new IllegalArgumentException("Cell already occupied");

        cells[row][col] = value;
    }

    @Override
    public Player getCell(int row, int col) {
        validateCoordinates(row, col);
        return cells[row][col];
    }

    @Override
    public Player[][] getCells() {
        return cells;
    }

    @Override
    public boolean isFull() {
        for (int i = 0; i < cells.length; i++)
            for (int j = 0; j < cells.length; j++)
                if (cells[i][j] == null)
                    return false;
        return true;
    }
}
