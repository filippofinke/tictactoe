package ch.supsi.tictactoe.model;

import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;

import java.util.Locale;
import java.util.List;

public interface LocalizationHandler extends Handler {

    void init(String bundleName, Locale locale);

    List<Locale> getLocales();

    void setLocale(Locale locale);

    String localize(String key, Object... args);

    StringBinding createStringBinding(String key, Object... args);

}
