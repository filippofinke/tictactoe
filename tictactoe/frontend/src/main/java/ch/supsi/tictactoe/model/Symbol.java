package ch.supsi.tictactoe.model;

import ch.supsi.tictactoe.exceptions.InvalidSymbolException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;

import java.util.List;
public class Symbol {
    public static final List<Character> SYMBOLS = PropertiesModel.getInstance().getSymbols();
    private final ObjectProperty<Character> symbol;
    private final ObjectProperty<Color> color;

    public Symbol(char symbol) {
        if (!SYMBOLS.contains(symbol))
            throw new InvalidSymbolException(symbol);

        this.symbol = new SimpleObjectProperty<>(symbol);
        this.color = new SimpleObjectProperty<>(Color.BLACK);
    }

    public char getSymbol() {
        return symbol.get();
    }

    public Color getColor() {
        return color.get();
    }

    public ObjectProperty<Character> symbolProperty() {
        return symbol;
    }

    public ObjectProperty<Color> colorProperty() {
        return color;
    }

    public void setSymbol(char symbol) {
        this.symbol.set(symbol);
    }

    public void setColor(Color color) {
        this.color.set(color);
    }

    @Override
    public String toString() {
        return String.valueOf(symbol.get());
    }
}