package commons.helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesConfig {
    private final static ThreadLocal<Properties> prop = new ThreadLocal<>();

    public static Properties loadPropertiesData(String serverName) {
        if (prop.get() == null) {
            Properties evnProp = new Properties();
            String filePath;
            switch (serverName.toLowerCase()) {
                case "dev":
                    filePath = "src/test/resources/env_dev.properties";
                    break;
                case "staging":
                    filePath = "src/test/resources/env_staging.properties";
                    break;
                default:
                    throw new RuntimeException("serverName is not valid: " + serverName);
            }

            try {
                evnProp.load(new FileInputStream(filePath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            prop.set(evnProp);
        }
        return prop.get();
    }



    public static String getProperties(String key, String serverName){
        String value = loadPropertiesData(serverName).getProperty(key);
        if (value==null){
            throw new RuntimeException("Key is not defined in properties file: "+key);
        }
        return value;
    }

}
