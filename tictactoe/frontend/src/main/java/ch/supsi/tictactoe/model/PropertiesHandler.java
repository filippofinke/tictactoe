package ch.supsi.tictactoe.model;

import java.util.List;

public interface PropertiesHandler extends Handler {

    String getVersion();
    List<Character> getSymbols();
}
