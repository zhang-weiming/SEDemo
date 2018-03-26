package main.clients;

import main.entities.Hammer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TestDemo {
    /**
     * 项目根目录的绝对路径
     */
    public static final String projectRootUrl = "C:/Users/zwm/Workplace/IdeaProjects/SEDemo/";

    /**
     * 配置文件
     */
    public Properties config;

    public List<Hammer> hammers;

    public TestDemo() {
        config = new Properties();
        hammers = new ArrayList<>();
    }

    public static void main(String[] args) throws Exception {
        TestDemo testDemo = new TestDemo();

        testDemo.config.load(new FileReader(
                projectRootUrl + "src/main/configuration/sys-config.properties"));

        FileInputStream fileInputStream = new FileInputStream(testDemo.config.get("inputFile").toString());
        BufferedReader bufr = new BufferedReader(
                new InputStreamReader(fileInputStream, "UTF-8"));

        String str = null;
        while ((str = bufr.readLine()) != null) {
            String[] items = str.trim().split(",");
//            testDemo.hammers.add(
//                    new Hammer(items));
            Hammer hammer = new Hammer(items);
            System.out.println(hammer);
        }

        bufr.close();
        fileInputStream.close();
    }

}
