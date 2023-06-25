package ch.supsi.tictactoe.controller;

import ch.supsi.tictactoe.command.*;
import ch.supsi.tictactoe.model.GameModel;
import ch.supsi.tictactoe.service.LocalizationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

import java.net.URL;
import java.util.ResourceBundle;

public class FileMenuController implements Initializable {

    private final QuitMenuCommand quitMenuCommand;
    private final NewGameMenuCommand newGameMenuCommand;
    private final OpenGameMenuCommand openGameMenuCommand;
    private final SaveGameMenuCommand saveGameMenuCommand;
    private final SaveGameAsMenuCommand saveGameAsMenuCommand;

    private @FXML MenuItem miNew;
    private @FXML MenuItem miOpen;
    private @FXML MenuItem miSave;
    private @FXML MenuItem miSaveAs;
    private @FXML MenuItem miQuit;

    public FileMenuController(GameModel gameModel) {
        this.quitMenuCommand = new QuitMenuCommand(gameModel);
        this.newGameMenuCommand = new NewGameMenuCommand(gameModel);
        this.openGameMenuCommand = new OpenGameMenuCommand(gameModel);
        this.saveGameAsMenuCommand = new SaveGameAsMenuCommand(gameModel);
        this.saveGameMenuCommand = new SaveGameMenuCommand(gameModel, saveGameAsMenuCommand);
    }

    @FXML
    public void newGame(ActionEvent e) {
        newGameMenuCommand.execute();
    }

    @FXML
    public void openGame(ActionEvent e) {
        openGameMenuCommand.execute();
    }

    @FXML
    public void saveGame(ActionEvent e) {
        saveGameMenuCommand.execute();
    }

    @FXML
    public void saveGameAs(ActionEvent e) {
        saveGameAsMenuCommand.execute();
    }

    @FXML
    public void quit(ActionEvent e) {
        quitMenuCommand.execute();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        miNew.textProperty().bind(LocalizationService.createStringBinding("ui.menuitem.new"));
        miOpen.textProperty().bind(LocalizationService.createStringBinding("ui.menuitem.open"));
        miSave.textProperty().bind(LocalizationService.createStringBinding("ui.menuitem.save"));
        miSaveAs.textProperty().bind(LocalizationService.createStringBinding("ui.menuitem.saveas"));
        miQuit.textProperty().bind(LocalizationService.createStringBinding("ui.menuitem.quit"));


        // CTRL + N on Windows, CMD + N on macOS
        miNew.setAccelerator(new KeyCodeCombination(
                javafx.scene.input.KeyCode.N,
                KeyCombination.SHORTCUT_DOWN
        ));

        // CTRL + O on Windows, CMD + O on MacOS
        miOpen.setAccelerator(new KeyCodeCombination(
                javafx.scene.input.KeyCode.O,
                KeyCombination.SHORTCUT_DOWN
        ));

        // CTRL + S on Windows, CMD + S on macOS
        miSave.setAccelerator(new KeyCodeCombination(
                javafx.scene.input.KeyCode.S,
                KeyCombination.SHORTCUT_DOWN
        ));

        // CTRL + SHIFT + S on Windows, CMD + SHIFT + S on macOS
        miSaveAs.setAccelerator(new KeyCodeCombination(
                javafx.scene.input.KeyCode.S,
                KeyCombination.SHORTCUT_DOWN,
                KeyCombination.SHIFT_DOWN
        ));
    }
}