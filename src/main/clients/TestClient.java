package main.clients;

import main.entities.Hammer;
import main.interfaces.HammerEncoder;
import main.persistence.HammerManager;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class TestClient {
    public Properties config;
    public HammerManager hammerManager;

    /**
     * build a TestClient
     */
    public TestClient() {
        try {
            this.config = new Properties();
            this.config.load(new FileReader("src/main/configuration/sys-config.properties"));
            this.hammerManager = new HammerManager(
                    new File(config.getProperty("rootPath") + config.getProperty("dbFile")));
        } catch (Exception e) {
            System.out.println("x_x error's comming!");
            e.printStackTrace();
        }
    }

    /**
     * 测试对象持久化存储功能
     * @throws Exception
     */
    @Test
    public void testSave() throws Exception {
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
    @Test
    public void testRead() throws Exception {
        System.out.println(hammerManager.read(2));
    }

    /**
     * 测试对象持久化读取所有对象功能
     * @throws Exception
     */
    @Test
    public void testReadAll() throws Exception {
        Hammer[] hammers = hammerManager.read();

        for (Hammer hammer : hammers)
            System.out.println(hammer);
    }

    /**
     * 测试对象持久化删除指定对象功能
     * @throws Exception
     */
    @Test
    public void testRemove() throws Exception {
        hammerManager.remove(new Hammer("2,Harry Hammer,class1,25.0"));

        testReadAll();
    }

    @Test
    public void test() throws Exception {
        HammerEncoder.toHTML(this.hammerManager.read(),
                new File(this.config.getProperty("rootPath")+this.config.getProperty("htmlFile")));
    }
}
