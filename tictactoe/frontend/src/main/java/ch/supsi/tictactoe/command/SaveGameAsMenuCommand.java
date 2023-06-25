package ch.supsi.tictactoe.command;

import ch.supsi.tictactoe.model.GameModel;
import ch.supsi.tictactoe.model.PreferencesModel;
import ch.supsi.tictactoe.service.LocalizationService;
import javafx.stage.FileChooser;

import java.io.File;

public class SaveGameAsMenuCommand implements MenuCommand {

    private final GameModel gameModel;

    public SaveGameAsMenuCommand(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    @Override
    public void execute() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(PreferencesModel.getInstance().getDefaultDirectoryPath().toFile());
        fileChooser.setInitialFileName(LocalizationService.createStringBinding("game.filename.default").getValue());

        File file = fileChooser.showSaveDialog(null);
        if(file != null) {
            gameModel.saveGame(file.toPath());
        }

    }
}
