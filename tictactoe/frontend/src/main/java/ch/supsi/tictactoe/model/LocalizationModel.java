package ch.supsi.tictactoe.model;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.Locale;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class LocalizationModel implements LocalizationHandler {

    private ObjectProperty<Locale> locale;

    private static LocalizationModel model;

    private boolean initialized;

    private String bundleName;

    protected LocalizationModel() {
        this.initialized = false;
    }

    public static LocalizationModel getInstance() {
        if (model == null) {
            model = new LocalizationModel();
        }
        return model;
    }

    @Override
    public boolean isInitialized() {
        return initialized;
    }

    @Override
    public void init(String bundleName, Locale locale) {
        if (initialized)
            throw new IllegalStateException("LocalizationModel already initialized");

        Locale.setDefault(locale);
        this.bundleName = bundleName;
        this.locale = new SimpleObjectProperty<>(locale);
        this.locale.addListener((observable, oldValue, newValue) -> Locale.setDefault(newValue));
        this.initialized = true;
    }

    @Override
    public List<Locale> getLocales() {
        return List.of(
                Locale.forLanguageTag("it-CH"),
                Locale.forLanguageTag("en-US"),
                Locale.forLanguageTag("de-DE"),
                Locale.forLanguageTag("fr-FR")
        );
    }

    @Override
    public void setLocale(Locale locale) {
        this.locale.set(locale);
    }

    @Override
    public String localize(String key, Object... args) {
        String translation;
        try {
            translation = String.format(ResourceBundle.getBundle(bundleName, locale.get()).getString(key), args);
        } catch (MissingResourceException e) {
            translation = key;
        }
        return translation;
    }

    @Override
    public StringBinding createStringBinding(String key, Object... args) {
        return Bindings.createStringBinding(() -> localize(key, args), locale);
    }


}
