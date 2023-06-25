package ch.supsi.tictactoe.controller;

import ch.supsi.tictactoe.gamelogic.GameEventType;
import ch.supsi.tictactoe.gamelogic.Move;
import ch.supsi.tictactoe.model.GameModel;
import ch.supsi.tictactoe.model.Symbol;
import ch.supsi.tictactoe.model.ThemeHandler;
import ch.supsi.tictactoe.model.ThemeModel;
import ch.supsi.tictactoe.player.Player;
import ch.supsi.tictactoe.service.LocalizationService;
import javafx.beans.binding.StringBinding;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class InfoBarController {

    private final ThemeHandler themeHandler = ThemeModel.getInstance();
    @FXML
    private Text infoBar;

    public InfoBarController() {
        GameModel gameModel = GameModel.getInstance();
        gameModel.subscribe(GameEventType.GAME_START, this::onGameStart);
        gameModel.subscribe(GameEventType.GAME_END, this::onGameEnd);
        gameModel.subscribe(GameEventType.PLAYER_MOVE, this::onPlayerMove);
        gameModel.subscribe(GameEventType.FILE_SAVED, this::onFileSaved);
    }

    public void showMessage(StringBinding message) {
        infoBar.textProperty().bind(message);
    }

    private void onGameEnd(Player winner) {
        if (winner == null) {
            showMessage(LocalizationService.createStringBinding("user.message.game.end.draw"));
        } else {
            showMessage(LocalizationService.createStringBinding("user.message.game.end.winner", themeHandler.getCurrentSymbol()));
        }
    }

    private void onGameStart(Void n) {
        showMessage(LocalizationService.createStringBinding("user.message.game.start"));
    }


    private void onPlayerMove(Move move) {
        Symbol symbol = themeHandler.getCurrentSymbol();
        showMessage(LocalizationService.createStringBinding("user.message.player.move", symbol.getSymbol(), move.getX(), move.getY()));
    }

    private void onFileSaved(boolean saved) {
        showMessage(LocalizationService.createStringBinding(saved ? "user.message.save.success" : "user.message.save.error"));
    }
}
