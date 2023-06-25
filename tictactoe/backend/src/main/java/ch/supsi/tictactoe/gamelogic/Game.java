package ch.supsi.tictactoe.gamelogic;

import ch.supsi.tictactoe.exceptions.*;
import ch.supsi.tictactoe.gameboard.GameBoard;
import ch.supsi.tictactoe.persistence.GamePersistenceManager;
import ch.supsi.tictactoe.player.Player;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private static final int PLAYER_COUNT = 2;
    private final GameBoard board;
    private final GamePersistenceManager gamePersistenceManager;
    public final EventManager events;
    private final PlayerTurnManager playerTurnManager;
    private GameStatus status;
    private final List<Move> moves;

    public Game(GameBoard board, GamePersistenceManager gamePersistenceManager) {
        this.board = board;
        this.gamePersistenceManager = gamePersistenceManager;
        this.events = new EventManager();
        this.playerTurnManager = new PlayerTurnManager(PLAYER_COUNT);
        this.status = GameStatus.NOT_STARTED;
        this.moves = new ArrayList<>();
    }

    public Player[][] getBoard() {
        return board.getCells();
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public int getCurrentPlayerIndex() {
        return playerTurnManager.getCurrentPlayerIndex();
    }

    private boolean isWinningMove(Move move) {
        Player[][] cells = board.getCells();
        Player p = move.getPlayer();

        // check row
        for (int i = 0; true; i++) {
            if (cells[move.getX()][i] == null || cells[move.getX()][i] != p) {
                break;
            }
            if (i == 2) {
                return true;
            }
        }

        // check column
        for (int i = 0; true; i++) {
            if (cells[i][move.getY()] == null || cells[i][move.getY()] != p) {
                break;
            }
            if (i == 2) {
                return true;
            }
        }

        for (int i = 0; true; i++) {
            if (cells[i][i] == null || cells[i][i] != p) {
                break;
            }
            if (i == 2) {
                return true;
            }
        }


        for (int i = 0; true; i++) {
            if (cells[i][(2 - i)] == null || cells[i][(2 - i)] != p) {
                break;
            }
            if (i == 2) {
                return true;
            }

        }


        return false;
    }

    public GameStatus getGameStatus() {
        return status;
    }

    public void addPlayer(Player player) {
        playerTurnManager.addPlayer(player);
        player.setGame(this);
    }

    public void play(Move move, boolean load) {
        if (status != GameStatus.IN_PROGRESS) {
            throw new GameNotInProgressException();
        }

        if (move.getPlayer() != playerTurnManager.getCurrentPlayer()) {
            throw new NotYourTurnException();
        }

        if (board.getCell(move.getX(), move.getY()) != null) {
            throw new CellNotEmptyException();
        }

        moves.add(move);
        board.setCell(move.getX(), move.getY(), move.getPlayer());

        events.notify(GameEventType.PLAYER_MOVE, move);

        if (isWinningMove(move)) {
            status = GameStatus.WINNER;
            events.notify(GameEventType.GAME_END, move.getPlayer());
        } else if (board.isFull()) {
            status = GameStatus.DRAW;
            events.notify(GameEventType.GAME_END, null);
        } else {
            playerTurnManager.switchTurn(!load);
        }
    }

    public void start() {
        if (playerTurnManager.getPlayersCount() < PLAYER_COUNT) {
            throw new GameIsNotFullException();
        }
        status = GameStatus.IN_PROGRESS;
        events.notify(GameEventType.GAME_START, null);

        playerTurnManager.getCurrentPlayer().onMyTurn();
    }

    public void saveGame(Path destination){
        try {
            gamePersistenceManager.save(destination, moves);
            events.notify(GameEventType.FILE_SAVED, true);
        } catch (FailedToSaveGameException e) {
            events.notify(GameEventType.FILE_SAVED, false);
        }
    }

    public void load(Path path){
        try {
            List<Move> loadedMoves = gamePersistenceManager.load(path);
            events.notify(GameEventType.GAME_START, null);
            playerTurnManager.reset();
            for(int i = 0; i < loadedMoves.size(); i++){
                Move m = loadedMoves.get(i);
                m.setPlayer(playerTurnManager.getPlayer(i%2));
                play(m, true);
            }
        } catch (FailedToLoadGameException e) {
            e.printStackTrace();
        }
    }

    public void startNewGame(){
        events.notify(GameEventType.GAME_START, null);
        playerTurnManager.reset();
    }
}
