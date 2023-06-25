package ch.supsi.tictactoe.model;

import javafx.scene.paint.Color;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Properties;

public class PreferencesModel implements PreferencesHandler {
    private static PreferencesModel instance;
    private final static String preferencesFileName = "user.prefs";
    private final static Path preferencesPath =
            Paths.get(System.getProperty("user.home"), ".tictactoe", preferencesFileName);
    private final Properties properties;

    private void load() {
        createPreferencesFolder();
        try {
            properties.load(Files.newInputStream(preferencesPath));
        } catch (IOException io) {
            System.out.printf("Preferences file not found: %s%n", preferencesPath);
        }
    }

    public Locale getLocale() {
        String language = properties.getProperty("language");

        Locale fallBack = Locale.US;

        return language == null ? fallBack : Locale.forLanguageTag(language);
    }

    public void setLocale(Locale locale) {
        properties.setProperty("language", locale.toLanguageTag());
    }

    public Symbol[] getSymbols() {
        String symbols = properties.getProperty("symbols");
        if (symbols == null) {
            return new Symbol[]{
                    new Symbol('X'),
                    new Symbol('O')
            };
        }
        String[] symbolsArray = symbols.split(",");
        Color[] colors = getColors();

        Symbol[] result = new Symbol[symbolsArray.length];
        for (int i = 0; i < symbolsArray.length; i++) {
            result[i] = new Symbol(symbolsArray[i].charAt(0));
            result[i].setColor(colors[i]);
        }

        return result;
    }

    public void setSymbols(Symbol[] symbols) {
        properties.setProperty("symbols", String.format("%s,%s", symbols[0].getSymbol(), symbols[1].getSymbol()));

        Color[] colors = new Color[symbols.length];
        for (int i = 0; i < symbols.length; i++) {
            colors[i] = symbols[i].getColor();
        }
        setColors(colors);
    }

    private Color[] getColors() {
        String colors = properties.getProperty("colors");
        if (colors == null) {
            return new Color[]{Color.RED, Color.BLUE};
        }
        String[] colorsArray = colors.split(",");
        return new Color[]{Color.valueOf(colorsArray[0]), Color.valueOf(colorsArray[1])};
    }

    private void setColors(Color[] colors) {
        properties.setProperty("colors", String.format("%s,%s", colors[0].toString(), colors[1].toString()));
    }

    @Override
    public void setDefaultDirectoryPath(Path path){
        properties.setProperty("defaultSaveDirectory", path.toString());
    }

    @Override
    public Path getDefaultDirectoryPath(){
        String path = properties.getProperty("defaultSaveDirectory");
        if(path == null) {
            path = preferencesPath.getParent().toString();
        }

        File file = new File(path);

        // check if path does not exist
        if(!file.exists()){
            file.mkdirs();
        }

        return file.toPath();
    }

    public static PreferencesModel getInstance() {
        if (instance == null) {
            instance = new PreferencesModel();
        }
        return instance;
    }

    private PreferencesModel() {
        properties = new Properties();
        load();
    }

    private void createPreferencesFolder() {
        if (!Files.exists(preferencesPath)) {
            try {
                Files.createDirectories(preferencesPath.getParent());
                Files.createFile(preferencesPath);
            } catch (IOException io) {
                System.out.printf("Failed to create preferences file: %s%n", preferencesPath);
            }
        }
    }

    public void save() {
        createPreferencesFolder();
        try {
            properties.store(Files.newOutputStream(preferencesPath), null);
        } catch (IOException io) {
            System.out.printf("Failed to save preferences file: %s%n", preferencesPath);
        }
    }

    @Override
    public boolean isInitialized() {
        return properties != null;
    }
}
