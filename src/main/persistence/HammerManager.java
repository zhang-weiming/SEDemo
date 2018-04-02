package main.persistence;

import main.entities.Hammer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class HammerManager extends AbstractManager {
//    private Properties config;

    private List<Hammer> hammers;

    public HammerManager(File objectFile) throws IOException {
        super(objectFile);
//        this.config = new Properties();
        this.hammers = new ArrayList<>();

//        this.init();
    }

//    public void init() throws IOException {
//        this.config.load(new FileReader("src/main/configuration/sys-config.properties"));
//        super.setObjectFile(
//                new File(this.config.getProperty("rootPath") + this.config.getProperty("dbFile")));
//    }

    public void setHammers(List<Hammer> hammers) {
        this.hammers = hammers;
    }

    public List<Hammer> getHammers() {
        return this.hammers;
    }

    public void addHammer(Hammer hammer) {
        this.hammers.add(hammer);
    }

    public void addAllHammer(List<Hammer> hammers) {
        this.hammers.addAll(hammers);
    }

    /**
     * 保存对象到持久化文件中
     * @throws IOException
     */
    @Override
    public void save() throws IOException {
        if (super.getObjectFile() == null) {
            throw new FileNotFoundException("持久化文件对象未初始化");
        }

        BufferedWriter bufw = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(super.getObjectFile()), "UTF-8"));

        for (Hammer hammer : this.hammers) {
            bufw.write(hammer.toCsvString());
            bufw.flush();
            bufw.newLine();
        }

        bufw.close();
    }

    /**
     * 从持久化文件中读取第i个对象
     * @param i 对象在持久化文件中的次序，从1算起。注意：<b>不是下标</b>
     * @return
     * @throws IOException
     */
    @Override
    public Hammer read(int i) throws IOException {
        if (super.getObjectFile() == null) {
            throw new FileNotFoundException("持久化文件对象未初始化");
        }

        BufferedReader bufr = new BufferedReader(
                new InputStreamReader(new FileInputStream(super.getObjectFile()), "UTF-8"));

        String str = null;
        while ((str = bufr.readLine()) != null) {
            if (--i == 0)
                return new Hammer(str);
        }

        return null;
    }

    @Override
    public Hammer[] read() throws IOException {
        return null;
    }

    @Override
    public void remove(Object object) throws IOException {

    }
}
