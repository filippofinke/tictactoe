package ch.supsi.tictactoe.model;

import javafx.scene.paint.Color;

public interface ThemeHandler extends Handler {
    Symbol[] getSymbols();

    void setColor(int index, Color color);

    void setSymbol(int index, char symbol);

    Symbol getSymbol(int index);

    Symbol getCurrentSymbol();
}
