package ch.supsi.tictactoe.controller;

import ch.supsi.tictactoe.gamelogic.GameEventType;
import ch.supsi.tictactoe.gamelogic.GameStatus;
import ch.supsi.tictactoe.gamelogic.Move;
import ch.supsi.tictactoe.model.GameModel;
import ch.supsi.tictactoe.model.Symbol;
import ch.supsi.tictactoe.model.ThemeHandler;
import ch.supsi.tictactoe.model.ThemeModel;
import ch.supsi.tictactoe.player.HumanPlayer;
import ch.supsi.tictactoe.player.Player;
import ch.supsi.tictactoe.player.PlayerTurnListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class GameController implements PlayerTurnListener {

    @FXML private AnchorPane anchorPane;

    private HumanPlayer player;
    private final GameModel game = GameModel.getInstance();
    private final ThemeHandler themeHandler = ThemeModel.getInstance();

    public GameController() {
        game.subscribe(GameEventType.GAME_START, this::onGameStart);
        game.subscribe(GameEventType.GAME_END, this::onGameEnd);
        game.subscribe(GameEventType.PLAYER_MOVE, this::onPlayerMove);
    }

    public void setPlayer(HumanPlayer player) {
        this.player = player;
    }

    private void disableAllButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = (Button) anchorPane.lookup("#b" + i + j);
                button.setDisable(true);
            }
        }
    }


    @FXML
    public void buttonAction(ActionEvent e) {
        if(game.getGameStatus() != GameStatus.IN_PROGRESS)
            game.start();

        Button button = (Button) e.getSource();
        String[] coordinates = button.getId().split("");
        int x = Integer.parseInt(coordinates[1]);
        int y = Integer.parseInt(coordinates[2]);

        try {
            player.play(x, y);
        } catch(IllegalStateException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onMyTurn() {}

    private void onGameStart(Void n) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = (Button) anchorPane.lookup("#b" + i + j);
                button.setDisable(false);
                button.textProperty().unbind();
                button.setText("");
            }
        }
    }

    private void onGameEnd(Player winner) {
        disableAllButtons();
    }

    private void onPlayerMove(Move move) {
        Symbol symbol = themeHandler.getCurrentSymbol();
        Button button = (Button) anchorPane.lookup("#b" + move.getX() + move.getY());
        button.textProperty().bind(symbol.symbolProperty().asString());
        button.textFillProperty().bind(symbol.colorProperty());
        button.setDisable(true);
    }
}
