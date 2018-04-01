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
//    public static final String projectRootUrl = "C:/Users/zwm/Workplace/IdeaProjects/SEDemo/";
//    public static final String projectRootUrl = "E:/Workplace/idea-workplace/SEDemo/";

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
        testDemo.config.load(
                new FileReader("src/main/configuration/sys-config.properties"));
//        testDemo.config.load(
//              new FileReader(projectRootUrl + "src/main/configuration/sys-config.properties"));

        BufferedReader bufr = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(testDemo.config.getProperty("rootPath") + testDemo.config.getProperty("inputFile")), "UTF-8"));

        String str = null;
        while ((str = bufr.readLine()) != null) {
            String[] items = str.trim().split(",");
            Hammer hammer = new Hammer(items);

            System.out.println(hammer);
        }

        bufr.close();
    }

}
