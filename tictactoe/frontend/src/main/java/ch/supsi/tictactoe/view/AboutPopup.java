package ch.supsi.tictactoe.view;

import ch.supsi.tictactoe.model.PropertiesHandler;
import ch.supsi.tictactoe.service.LocalizationService;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AboutPopup {

    private final PropertiesHandler propertiesHandler;

    public AboutPopup(PropertiesHandler propertiesModel) {
        this.propertiesHandler = propertiesModel;
    }

    public void show() {
        Alert alert = new Alert(Alert.AlertType.NONE, "Filippo Finke - Matteo Forni\nv." + propertiesHandler.getVersion(), ButtonType.CLOSE);
        alert.titleProperty().bind(LocalizationService.createStringBinding("ui.menuitem.about"));
        alert.headerTextProperty().bind(LocalizationService.createStringBinding("user.message.welcome"));
        alert.showAndWait();
        if (alert.getResult() == ButtonType.CANCEL) {
            alert.close();
        }
    }
}
