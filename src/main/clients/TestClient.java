package main.clients;

import main.entities.Hammer;
import main.persistence.HammerManager;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class TestClient {
    public static Properties config;
    public static HammerManager hammerManager;

    public static void main(String[] args) throws Exception {
        init();

//        testSave();

        testRead();
    }

    public static void init() throws Exception {
        config = new Properties();
        config.load(new FileReader("src/main/configuration/sys-config.properties"));
        hammerManager = new HammerManager(
                new File(config.getProperty("rootPath") + config.getProperty("dbFile")));
    }

    public static void testSave() throws Exception {
        hammerManager.getHammers().add(
                new Hammer("1", "Sam Hammer", "class1", 20.0));
        hammerManager.addHammer(
                new Hammer("2", "Harry Hammer", "class1", 25.0));

        hammerManager.save();

        System.out.println("存储完毕");
    }

    public static void testRead() throws Exception {
        System.out.println(hammerManager.read(2));
    }
}
