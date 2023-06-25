package ch.supsi.tictactoe.model;

import javafx.scene.paint.Color;

public class ThemeModel implements ThemeHandler {

    private static PreferencesHandler preferencesHandler;
    private static GameModel gameModel;
    private static ThemeModel instance;

    private final Symbol[] symbols;

    private ThemeModel() {
        preferencesHandler = PreferencesModel.getInstance();
        gameModel = GameModel.getInstance();

        // Default symbols
        symbols = preferencesHandler.getSymbols();
    }

    public static ThemeModel getInstance() {
        if (instance == null)
            instance = new ThemeModel();
        return instance;
    }

    public Symbol[] getSymbols() {
        return symbols;
    }

    public void setColor(int index, Color color) {
        if(index < 0 || index > symbols.length)
            throw new IllegalArgumentException("Index out of bounds");

        symbols[index].setColor(color);

        preferencesHandler.setSymbols(symbols);
    }

    public void setSymbol(int index, char symbol) {
        if(index < 0 || index > symbols.length)
            throw new IllegalArgumentException("Index out of bounds");

        symbols[index].setSymbol(symbol);

        preferencesHandler.setSymbols(symbols);
    }

    public Symbol getSymbol(int index) {
        if(index < 0 || index > symbols.length)
            throw new IllegalArgumentException("Index out of bounds");

        return symbols[index];
    }

    @Override
    public Symbol getCurrentSymbol() {
        return getSymbol(gameModel.currentPlayerIndex());
    }


    @Override
    public boolean isInitialized() {
        return instance != null;
    }
}
