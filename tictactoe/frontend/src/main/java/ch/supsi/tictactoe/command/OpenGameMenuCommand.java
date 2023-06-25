package ch.supsi.tictactoe.command;

import ch.supsi.tictactoe.gamelogic.GameStatus;
import ch.supsi.tictactoe.model.GameModel;
import ch.supsi.tictactoe.model.PreferencesModel;
import ch.supsi.tictactoe.service.LocalizationService;
import ch.supsi.tictactoe.view.ConfirmationPopup;
import javafx.stage.FileChooser;

import java.io.File;

public class OpenGameMenuCommand implements MenuCommand {

    private final GameModel gameModel;

    public OpenGameMenuCommand(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    @Override
    public void execute() {
        boolean confirmed = gameModel.getGameStatus() == GameStatus.NOT_STARTED;

        if(gameModel.getGameStatus() != GameStatus.NOT_STARTED) {
            ConfirmationPopup popup = new ConfirmationPopup(
                    LocalizationService.createStringBinding("ui.alert.opengame.title"),
                    LocalizationService.createStringBinding("ui.alert.opengame.header"),
                    LocalizationService.createStringBinding("ui.alert.opengame.content")
            );
            confirmed = popup.show();
        }

        if(confirmed) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(PreferencesModel.getInstance().getDefaultDirectoryPath().toFile());
            File file = fileChooser.showOpenDialog(null);

            if(file != null){
                gameModel.openGame(file.toPath());
            }
        }
    }
}
