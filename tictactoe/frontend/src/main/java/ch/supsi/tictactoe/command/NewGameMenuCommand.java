package ch.supsi.tictactoe.command;

import ch.supsi.tictactoe.gamelogic.GameStatus;
import ch.supsi.tictactoe.model.GameModel;
import ch.supsi.tictactoe.service.LocalizationService;
import ch.supsi.tictactoe.view.ConfirmationPopup;

public class NewGameMenuCommand implements MenuCommand {

    private final GameModel gameModel;

    public NewGameMenuCommand(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    @Override
    public void execute() {

        boolean confirmed = gameModel.getGameStatus() == GameStatus.NOT_STARTED;

        if(gameModel.getGameStatus() != GameStatus.NOT_STARTED) {
            ConfirmationPopup popup = new ConfirmationPopup(
                    LocalizationService.createStringBinding("ui.alert.newgame.title"),
                    LocalizationService.createStringBinding("ui.alert.newgame.header"),
                    LocalizationService.createStringBinding("ui.alert.newgame.content")
            );

            confirmed = popup.show();
        }

        if (confirmed) {
            gameModel.startNewGame();
        }

    }
}
