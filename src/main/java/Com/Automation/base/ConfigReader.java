package Com.Automation.base;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties prop = new Properties();
    private static String configPath = "src\\main\\resources\\config.prop";

  
    static {
        try (FileInputStream fis = new FileInputStream(configPath)) {
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String getProperty(String key) {
        return prop.getProperty(key);
    }

  
    public static void setProperty(String key, String value) {
        try (FileOutputStream fos = new FileOutputStream(configPath)) {
            prop.setProperty(key, value);
            prop.store(fos, "Updated config");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}