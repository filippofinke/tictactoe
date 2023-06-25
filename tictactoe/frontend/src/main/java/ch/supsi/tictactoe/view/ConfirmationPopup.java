package ch.supsi.tictactoe.view;

import javafx.beans.binding.StringBinding;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class ConfirmationPopup {

    private final StringBinding title;
    private final StringBinding header;
    private final StringBinding content;

    public ConfirmationPopup(StringBinding title, StringBinding header, StringBinding content) {
        this.title = title;
        this.header = header;
        this.content = content;
    }

    public boolean show() {
        // ask for confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        // set focus on cancel button
        Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
        Button cancelButton = (Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL);
        okButton.setDefaultButton(false);
        cancelButton.setDefaultButton(true);
        alert.titleProperty().bind(title);
        alert.headerTextProperty().bind(header);
        alert.contentTextProperty().bind(content);
        alert.showAndWait();

        return alert.getResult() == ButtonType.OK;
    }
}
