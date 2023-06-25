package ch.supsi.tictactoe.gamelogic;

import ch.supsi.tictactoe.exceptions.GameIsFullException;
import ch.supsi.tictactoe.player.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerTurnManager {
    private final List<Player> players;
    private final int maxPlayers;
    private int currentPlayerIndex;
    public PlayerTurnManager(int maxPlayers) {
        this.players = new ArrayList<>();
        this.maxPlayers = maxPlayers;
        this.currentPlayerIndex = 0;
    }
    public void addPlayer(Player player) {
        if (players.size() >= maxPlayers)
            throw new GameIsFullException();
        players.add(player);
    }
    public int getPlayersCount() {
        return players.size();
    }

    public void reset() {
        currentPlayerIndex = 0;
    }
    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }
    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public Player getPlayer(int index) {
        return players.get(index);
    }
    public void switchTurn(boolean notifyTurnChange) {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        if (notifyTurnChange)
            players.get(currentPlayerIndex).onMyTurn();
    }
}
