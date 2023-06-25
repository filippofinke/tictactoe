package ch.supsi.tictactoe.command;

import ch.supsi.tictactoe.model.PropertiesHandler;
import ch.supsi.tictactoe.view.AboutPopup;

public class ShowAboutMenuCommand implements MenuCommand {
    private final PropertiesHandler propertiesHandler;

    public ShowAboutMenuCommand(PropertiesHandler propertiesHandler) {
        this.propertiesHandler = propertiesHandler;
    }
    @Override
    public void execute() {
        new AboutPopup(propertiesHandler).show();
    }
}
