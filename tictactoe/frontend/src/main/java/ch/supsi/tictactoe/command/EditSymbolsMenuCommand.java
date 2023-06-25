package ch.supsi.tictactoe.command;

import ch.supsi.tictactoe.view.SymbolMenu;

public class EditSymbolsMenuCommand implements MenuCommand {
    @Override
    public void execute() {
        new SymbolMenu().show();
    }
}
