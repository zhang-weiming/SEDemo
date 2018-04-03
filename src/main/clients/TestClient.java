package main.clients;

import main.entities.Hammer;
import main.interfaces.HammerEncoder;
import main.persistence.HammerManager;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class TestClient {
    public static Properties config;
    public static HammerManager hammerManager;

    public static void main(String[] args) throws Exception {
        init();

        HammerEncoder.toHTML(hammerManager.read(),
                new File(config.getProperty("rootPath")+config.getProperty("htmlFile")));
    }

    public static void init() throws Exception {
        config = new Properties();
        config.load(new FileReader("src/main/configuration/sys-config.properties"));
        hammerManager = new HammerManager(
                new File(config.getProperty("rootPath") + config.getProperty("dbFile")));
    }


    /**
     * 测试对象持久化存储功能
     * @throws Exception
     */
    public static void testSave() throws Exception {
        hammerManager.getHammers().add(
                new Hammer("1", "Sam Hammer", "class1", 20.0));
        hammerManager.getHammers().add(
                new Hammer("2", "Harry Hammer", "class1", 25.0));

        hammerManager.save();
        System.out.println("存储完毕");
    }

    /**
     * 测试对象持久化读取功能
     * @throws Exception
     */
    public static void testRead() throws Exception {
        System.out.println(hammerManager.read(2));
    }

    /**
     * 测试对象持久化读取所有对象功能
     * @throws Exception
     */
    public static void testReadAll() throws Exception {
        Hammer[] hammers = hammerManager.read();

        for (Hammer hammer : hammers)
            System.out.println(hammer);
    }

    /**
     * 测试对象持久化删除指定对象功能
     * @throws Exception
     */
    public static void testRemove() throws Exception {
        hammerManager.remove(new Hammer("2,Harry Hammer,class1,25.0"));

        testReadAll();
    }
}
