package ch.supsi.tictactoe.player;

import java.io.Serializable;

public interface PlayerTurnListener extends Serializable {
    void onMyTurn();
}
