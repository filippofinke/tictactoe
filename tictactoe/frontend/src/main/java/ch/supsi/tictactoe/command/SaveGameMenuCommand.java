package ch.supsi.tictactoe.command;

import ch.supsi.tictactoe.model.GameModel;

public class SaveGameMenuCommand implements MenuCommand {

    private final GameModel gameModel;
    private final SaveGameAsMenuCommand saveGameAs;

    public SaveGameMenuCommand(GameModel gameModel, SaveGameAsMenuCommand saveGameAs) {
        this.gameModel = gameModel;
        this.saveGameAs = saveGameAs;
    }

    @Override
    public void execute() {
        if(gameModel.getCurrentFilePath() == null) {
            saveGameAs.execute();
        } else {
            gameModel.saveGame();
        }
    }
}
