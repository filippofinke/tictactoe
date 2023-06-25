package ch.supsi.tictactoe.model;

import java.util.List;
import java.io.IOException;
import java.util.Properties;

public class PropertiesModel implements PropertiesHandler {

    private static PropertiesModel instance = null;
    private String version;
    private List<Character> symbols;
    private PropertiesModel() {
        final Properties properties = new Properties();

        try{
            properties.load(this.getClass().getClassLoader().getResourceAsStream("project.properties"));
            version = properties.getProperty("version");
            symbols = properties.getProperty("symbols").chars().mapToObj(c -> (char) c).toList();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public static PropertiesModel getInstance() {
        if (instance == null)
            instance = new PropertiesModel();
        return instance;
    }

    public String getVersion() {
        return version;
    }

    public List<Character> getSymbols() {
        return symbols;
    }

    @Override
    public boolean isInitialized() {
        return instance != null;
    }
}
