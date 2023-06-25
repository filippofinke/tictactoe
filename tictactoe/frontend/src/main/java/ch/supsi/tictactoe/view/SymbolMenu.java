package ch.supsi.tictactoe.view;

import ch.supsi.tictactoe.controller.SymbolMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import static javafx.stage.Modality.APPLICATION_MODAL;

public class SymbolMenu {

    private final Parent root;

    public SymbolMenu() {
        URL symbolMenuFxmlUrl = getClass().getResource("/symbolmenu.fxml");
        if (symbolMenuFxmlUrl == null) {
            throw new RuntimeException("symbolmenu.fxml not found");
        }

        FXMLLoader symbolMenuLoader = new FXMLLoader(symbolMenuFxmlUrl);
        symbolMenuLoader.setControllerFactory(c -> new SymbolMenuController());

        try {
            root = symbolMenuLoader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void show() {
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
