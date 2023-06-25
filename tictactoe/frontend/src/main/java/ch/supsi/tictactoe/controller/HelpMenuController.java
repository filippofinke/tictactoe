package ch.supsi.tictactoe.controller;

import ch.supsi.tictactoe.command.ShowAboutMenuCommand;
import ch.supsi.tictactoe.model.PropertiesHandler;
import ch.supsi.tictactoe.service.LocalizationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCodeCombination;

import java.net.URL;
import java.util.ResourceBundle;

public class HelpMenuController implements Initializable {
    private final ShowAboutMenuCommand showAboutMenuCommand;

    @FXML
    private MenuItem miAbout;

    public HelpMenuController(PropertiesHandler propertiesHandler) {
        this.showAboutMenuCommand = new ShowAboutMenuCommand(propertiesHandler);
    }

    @FXML
    public void showAbout(ActionEvent e) {
        showAboutMenuCommand.execute();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        miAbout.textProperty().bind(LocalizationService.createStringBinding("ui.menuitem.about"));

        // F1 on Windows, F1 on macOS
        miAbout.setAccelerator(new KeyCodeCombination(javafx.scene.input.KeyCode.F1));
    }

}
