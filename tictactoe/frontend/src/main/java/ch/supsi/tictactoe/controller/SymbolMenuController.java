package ch.supsi.tictactoe.controller;

import ch.supsi.tictactoe.model.*;
import ch.supsi.tictactoe.service.LocalizationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class SymbolMenuController implements Initializable {
    private final ThemeHandler themeHandler;

    @FXML private Label labelPlayer0;
    @FXML private Label labelPlayer1;
    @FXML private ComboBox<String> symbolPlayer0;
    @FXML private ComboBox<String> symbolPlayer1;

    @FXML private ColorPicker colorPlayer0;
    @FXML private ColorPicker colorPlayer1;

    public SymbolMenuController() {
        this.themeHandler = ThemeModel.getInstance();
    }

    private void loadSymbolsList() {
        Symbol[] selectedSymbols = themeHandler.getSymbols();

        // remove action listeners to avoid triggering them

        symbolPlayer0.setOnAction(null);
        symbolPlayer1.setOnAction(null);

        symbolPlayer0.getItems().clear();
        symbolPlayer1.getItems().clear();

        symbolPlayer0.getItems().addAll(
                Symbol.SYMBOLS.stream().filter(
                        symbol -> symbol != selectedSymbols[1].getSymbol()
                ).map(Object::toString).toList());

        symbolPlayer1.getItems().addAll(
                Symbol.SYMBOLS.stream().filter(
                        symbol -> symbol != selectedSymbols[0].getSymbol()
                ).map(Object::toString).toList());

        symbolPlayer0.setValue(selectedSymbols[0].toString());
        symbolPlayer1.setValue(selectedSymbols[1].toString());

        // add action listeners back

        symbolPlayer0.setOnAction(this::editSymbol);
        symbolPlayer1.setOnAction(this::editSymbol);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadSymbolsList();

        // set color pickers
        Symbol[] selectedSymbols = themeHandler.getSymbols();
        colorPlayer0.setValue(selectedSymbols[0].getColor());
        colorPlayer1.setValue(selectedSymbols[1].getColor());

        labelPlayer0.textProperty().bind(LocalizationService.createStringBinding("symbols.player1"));
        labelPlayer1.textProperty().bind(LocalizationService.createStringBinding("symbols.player2"));
    }

    @FXML
    public void editSymbol(ActionEvent actionEvent) {

        @SuppressWarnings("unchecked")
        ComboBox<String> source = (ComboBox<String>) actionEvent.getSource();
        int playerToModify = Integer.parseInt(source.getId().replace("symbolPlayer", ""));

        themeHandler.setSymbol(playerToModify, source.getValue().charAt(0));

        loadSymbolsList();
    }


    @FXML
    public void editColor(ActionEvent actionEvent) {
        ColorPicker source = (ColorPicker) actionEvent.getSource();
        int playerToModify = Integer.parseInt(source.getId().replace("colorPlayer", ""));

        themeHandler.setColor(playerToModify, source.getValue());
    }
}
