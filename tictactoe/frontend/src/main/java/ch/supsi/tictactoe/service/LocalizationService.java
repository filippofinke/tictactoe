package ch.supsi.tictactoe.service;

import ch.supsi.tictactoe.model.LocalizationHandler;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;

import java.util.Locale;
import java.util.List;


public class LocalizationService {

    private static LocalizationHandler localizationHandler;

    public static void init(LocalizationHandler localizationHandler) {
        LocalizationService.localizationHandler = localizationHandler;
    }

    public static List<Locale> getLocales() {
        if (localizationHandler != null && localizationHandler.isInitialized()) {
            return localizationHandler.getLocales();
        }

        return null;
    }

    public static void setLocale(Locale locale) {
        if (localizationHandler != null && localizationHandler.isInitialized()) {
            localizationHandler.setLocale(locale);
        }
    }

    public static StringBinding createStringBinding(String key, Object... args) {
        if (localizationHandler != null && localizationHandler.isInitialized()) {
            return localizationHandler.createStringBinding(key, args);
        }

        // Return a StringBinding that always returns the key
        return new StringBinding() {
            @Override
            protected String computeValue() {
                return key;
            }
        };
    }

}
