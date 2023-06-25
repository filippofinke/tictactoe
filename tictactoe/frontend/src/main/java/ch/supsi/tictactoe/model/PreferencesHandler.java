package ch.supsi.tictactoe.model;

import java.nio.file.Path;
import java.util.Locale;

public interface PreferencesHandler extends Handler {
    void save();
    Locale getLocale();
    void setLocale(Locale locale);
    Symbol[] getSymbols();
    void setSymbols(Symbol[] symbols);
    void setDefaultDirectoryPath(Path path);
    Path getDefaultDirectoryPath();
}
