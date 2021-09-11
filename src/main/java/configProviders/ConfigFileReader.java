package configProviders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static java.lang.String.format;

public class ConfigFileReader {

    private static Properties properties;
    private static String propertyFilePath;
    public static String environment;
    public static String baseUrl;

    public static void ReadTestConfigurationProperties(){
        environment = isNotNullOrEmpty(System.getProperty("environment")) ? System.getProperty("environment") : "DEV";

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("Target environment: " + environment);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++");

        BufferedReader reader;
        try {
            if(environment.toUpperCase().equals("DEV")) {
                propertyFilePath = "configs//devConfig.properties";
            } else if(environment.toUpperCase().equals("UAT")) {
                propertyFilePath = "configs//uatConfig.properties";
            } else
                throw new RuntimeException(format("The specified target environment %s doesn't exist", environment));

            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Environment config.properties not found at %s" + propertyFilePath);
        }
        baseUrl = properties.getProperty("baseUrl");
    }

    private static boolean isNotNullOrEmpty(String value) {
        return (value != null && !value.trim().isEmpty()) ? true : false;
    }
}
