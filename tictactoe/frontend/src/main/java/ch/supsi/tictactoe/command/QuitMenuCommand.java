package ch.supsi.tictactoe.command;

import ch.supsi.tictactoe.gamelogic.GameStatus;
import ch.supsi.tictactoe.model.GameModel;
import ch.supsi.tictactoe.service.LocalizationService;
import ch.supsi.tictactoe.view.ConfirmationPopup;
import javafx.application.Platform;

public class QuitMenuCommand implements MenuCommand {
    private final GameModel gameModel;

    public QuitMenuCommand(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    @Override
    public void execute() {
        if (gameModel.getGameStatus() == GameStatus.NOT_STARTED) {
            Platform.exit();
        } else {

            ConfirmationPopup popup = new ConfirmationPopup(
                    LocalizationService.createStringBinding("ui.alert.quit.title"),
                    LocalizationService.createStringBinding("ui.alert.quit.header"),
                    LocalizationService.createStringBinding("ui.alert.quit.content")
            );

            boolean confirmed = popup.show();

            if (confirmed) {
                Platform.exit();
            }

        }
    }

}
