package ch.supsi.tictactoe.model;

import ch.supsi.tictactoe.gamelogic.EventListener;
import ch.supsi.tictactoe.gamelogic.GameEventType;
import ch.supsi.tictactoe.gamelogic.GameStatus;
import ch.supsi.tictactoe.player.Player;

import java.nio.file.Path;

public interface GameHandler extends Handler {

    void start();

    void newGame();

    Path getCurrentFilePath();

    void addPlayer(Player player);


    int currentPlayerIndex();

     <T> void subscribe(GameEventType eventType, EventListener<T> listener);

    GameStatus getGameStatus();
}
