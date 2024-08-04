package Settings;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Settings {
    private static Settings instance;
    private final String Settings;
    private final String masinaFile;
    private final String inchiriereFile;
    private final String App;
    public Settings(String Settings, String masinaFile, String inchiriereFile, String App) {
        this.Settings = Settings;
        this.masinaFile = masinaFile;
        this.inchiriereFile = inchiriereFile;
        this.App = App;
    }

    private static Properties loadSettings(){
        try (FileReader fr = new FileReader("C:\\Users\\Frosty Lumberjack\\Documents\\GitHub\\a4-MaximTGMX\\settings.properties")) {
            Properties settings = new Properties();
            settings.load(fr);
            return settings;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized Settings getInstance() {
        Properties properties = loadSettings();

        instance = new Settings(properties.getProperty("Repository"), properties.getProperty("Masina"),properties.getProperty("Inchiriere"),properties.getProperty("App"));
        return instance;
    }

    public String getSettings(){
        return this.Settings;
    }
    public String getMasinaFile(){
        return this.masinaFile;
    }
    public String getInchiriereFile(){
        return this.inchiriereFile;
    }
    public String getApp(){
        return this.App;
    }
}
