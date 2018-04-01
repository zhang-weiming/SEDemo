package main.clients;

import java.io.FileReader;
import java.util.Properties;

public class Test {

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileReader(
                "src/main/configuration/sys-config.properties"));



        System.out.println(properties.getProperty("inputFile"));
    }
}
