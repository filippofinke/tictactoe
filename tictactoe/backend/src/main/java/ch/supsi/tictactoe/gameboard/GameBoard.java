package ch.supsi.tictactoe.gameboard;

import ch.supsi.tictactoe.player.Player;

public interface GameBoard {
    void setCell(int row, int col, Player value);
    Player getCell(int row, int col);
    Player[][] getCells();

    boolean isFull();
}
