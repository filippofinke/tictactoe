package ch.supsi.tictactoe.controller;

import ch.supsi.tictactoe.command.EditDefaultDirectoryMenuCommand;
import ch.supsi.tictactoe.command.EditSymbolsMenuCommand;
import ch.supsi.tictactoe.model.PreferencesHandler;
import ch.supsi.tictactoe.service.LocalizationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class EditMenuController implements Initializable {
    private final PreferencesHandler preferencesHandler;
    private final EditSymbolsMenuCommand editSymbolsMenuCommand;
    private final EditDefaultDirectoryMenuCommand editDefaultDirectoryMenuCommand;
    @FXML
    private MenuItem miSymbols;
    @FXML
    private Menu miLanguage;
    @FXML
    private MenuItem miDefaultDirectory;

    public EditMenuController(PreferencesHandler preferencesHandler) {
        this.preferencesHandler = preferencesHandler;
        this.editSymbolsMenuCommand = new EditSymbolsMenuCommand();
        this.editDefaultDirectoryMenuCommand = new EditDefaultDirectoryMenuCommand(preferencesHandler);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        miSymbols.textProperty().bind(LocalizationService.createStringBinding("ui.menuitem.symbols"));
        miLanguage.textProperty().bind(LocalizationService.createStringBinding("ui.menuitem.language"));
        miDefaultDirectory.textProperty().bind(LocalizationService.createStringBinding("ui.menuitem.defaultdirectory"));

        buildLanguageMenu();
    }

    private void buildLanguageMenu() {
        Objects.requireNonNull(LocalizationService.getLocales()).forEach((locale) -> {
            MenuItem mi = new MenuItem(locale.getDisplayLanguage(locale));
            mi.setOnAction((event) -> {
                LocalizationService.setLocale(locale);
                preferencesHandler.setLocale(locale);
            });
            miLanguage.getItems().add(mi);
        });
    }

    @FXML
    public void editSymbols(ActionEvent e) {
        editSymbolsMenuCommand.execute();
    }

    @FXML
    public void editDefaultDirectory(ActionEvent e) {
        editDefaultDirectoryMenuCommand.execute();
    }
}
