package ch.supsi.tictactoe.command;

import ch.supsi.tictactoe.model.PreferencesHandler;
import javafx.stage.DirectoryChooser;

import java.io.File;

public class EditDefaultDirectoryMenuCommand implements MenuCommand {

    private final PreferencesHandler preferencesHandler;

    public EditDefaultDirectoryMenuCommand(PreferencesHandler preferencesHandler) {
        this.preferencesHandler = preferencesHandler;
    }

    @Override
    public void execute() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(preferencesHandler.getDefaultDirectoryPath().toFile());
        File selectedDirectory = directoryChooser.showDialog(null);

        if (selectedDirectory != null) {
            preferencesHandler.setDefaultDirectoryPath(selectedDirectory.toPath());
        }
    }
}
