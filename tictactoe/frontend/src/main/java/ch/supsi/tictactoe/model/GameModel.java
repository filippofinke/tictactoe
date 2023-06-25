package ch.supsi.tictactoe.model;

import ch.supsi.tictactoe.exceptions.GameAlreadyStartedException;
import ch.supsi.tictactoe.gameboard.Board;
import ch.supsi.tictactoe.gamelogic.Game;
import ch.supsi.tictactoe.gamelogic.EventListener;
import ch.supsi.tictactoe.gamelogic.GameEventType;
import ch.supsi.tictactoe.gamelogic.GameStatus;
import ch.supsi.tictactoe.persistence.GameFilePersistenceManager;
import ch.supsi.tictactoe.player.Player;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameModel implements GameHandler {

    private static GameModel model;
    private Game game;
    private final List<Player> players = new ArrayList<>();
    private final Map<GameEventType, List<EventListener<?>>> eventListeners = new HashMap<>();

    private Path currentFilePath;

    @Override
    public Path getCurrentFilePath() {
        return currentFilePath;
    }


    protected GameModel() {
        this.newGame();
    }

    public static GameModel getInstance() {
        if (model == null) {
            model = new GameModel();
        }
        return model;
    }

    @Override
    public boolean isInitialized() {
        return game != null;
    }

    @Override
    public void start() {
        if (game.getGameStatus() != GameStatus.NOT_STARTED)
            throw new GameAlreadyStartedException();
        game.start();
    }

    @Override
    public void newGame() {
        game = new Game(new Board(), new GameFilePersistenceManager());
    }

    @Override
    public void addPlayer(Player player) {
        if (game.getGameStatus() != GameStatus.NOT_STARTED)
            throw new GameAlreadyStartedException();

        game.addPlayer(player);
        players.add(player);
    }

    @Override
    public int currentPlayerIndex() {
        return game.getCurrentPlayerIndex();
    }


    @Override
    public <T> void subscribe(GameEventType eventType, EventListener<T> listener) {
        game.events.subscribe(eventType, listener);
        eventListeners.computeIfAbsent(eventType, k -> new ArrayList<>()).add(listener);
    }

    @Override
    public GameStatus getGameStatus() {
        return game.getGameStatus();
    }

    public void saveGame() {
        if (currentFilePath == null) {
            throw new RuntimeException("No file path set");
        }
        saveGame(currentFilePath);
    }

    public void saveGame(Path destination) {
        game.saveGame(destination);
        currentFilePath = destination;
    }

    public void openGame(Path path) {
        newGameStarter();

        game.load(path);

        currentFilePath = path;
    }

    public void newGameStarter() {
        newGame();
        eventListeners.forEach((eventType, listeners) -> listeners.forEach(l ->
                game.events.subscribe(
                        eventType, l
                )));

        for (Player player : players) {
            game.addPlayer(player);
            player.setGame(game);
        }
        game.setStatus(GameStatus.IN_PROGRESS);
    }

    public void startNewGame() {
        currentFilePath = null;
        newGameStarter();
        game.startNewGame();
    }
}