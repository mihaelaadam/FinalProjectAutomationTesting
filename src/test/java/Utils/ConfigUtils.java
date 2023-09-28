package Utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigUtils {

    public static String getGenericElement(String configFile, String propertyName, String defaultValue) {
        String results = defaultValue;
        try {
            Properties appProp = new Properties();  //  Properties - o clasa care stie sa citeasca fifiere de tip properties
            appProp.load(Files.newInputStream(Paths.get(configFile)));  //  Properties stie sa funcioneze doar cu InputStream
            results = appProp.getProperty(propertyName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return results;
    }
    public static String getGenericElement(String configFile, String propertyName) {    //  de ce aici? Nu trebuia adaugata citirea in prima metoda?
        return getGenericElement(configFile, propertyName, "");

    }


}
