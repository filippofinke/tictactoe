package ch.supsi.tictactoe.gamelogic;

import ch.supsi.tictactoe.player.Player;

import java.io.Serial;
import java.io.Serializable;

public final class Move implements Serializable {
    @Serial
    private static final long serialVersionUID = 1234567L;
    private final int x;
    private final int y;
    private transient Player player;
    public Move(final int x, final int y, Player player) {
        this.x = x;
        this.y = y;
        this.player = player;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return "Move{" +
                "x=" + x +
                ", y=" + y +
                ", player=" + player +
                '}';
    }
}
