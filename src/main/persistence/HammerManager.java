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

//    public void addHammer(Hammer hammer) {
//        this.hammers.add(hammer);
//    }
//
//    public void addAllHammer(List<Hammer> hammers) {
//        this.hammers.addAll(hammers);
//    }

    /**
     * 保存对象到持久化文件中
     * @throws IOException
     */
    @Override
    public void save() throws IOException {
        if (super.getObjectFile() == null) {
            throw new FileNotFoundException("持久化文件对象未初始化");
        }

        ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(super.getObjectFile()));

        for (Hammer hammer : this.hammers) {
            oos.writeObject(hammer);
        }

        oos.flush();
        oos.close();
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

        ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(super.getObjectFile())
        );

        try {
            Hammer hammer = null;
            while ((hammer = (Hammer) ois.readObject()) != null) {
                if (--i == 0)
                    return hammer;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (EOFException e) {
            // pass
        }

        ois.close();
        return null;
    }

    /**
     * 从持久化文件中读取所有对象
     * @return
     * @throws IOException
     */
    @Override
    public Hammer[] read() throws IOException {
        if (super.getObjectFile() == null) {
            throw new FileNotFoundException("持久化文件对象未初始化");
        }

        ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(super.getObjectFile()));
        List<Hammer> hammers = new ArrayList<>();

        try {
            Hammer hammer = null;
            while ((hammer = (Hammer) ois.readObject()) != null) {
                hammers.add(hammer);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (EOFException e) {
            // pass
        }

        ois.close();

        Hammer[] array = new Hammer[hammers.size()];
        return hammers.toArray(array);
    }

    /**
     * 从持久化文件中删除指定对象
     * @param object
     * @throws IOException
     */
    @Override
    public void remove(Object object) throws IOException {
        if (super.getObjectFile() == null) {
            throw new FileNotFoundException("持久化文件对象未初始化");
        }

        String objectCsvString = ((Hammer) object).toCsvString();
        ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(super.getObjectFile()));
        List<Hammer> hammers = new ArrayList<>();

        try {
            Hammer hammer = null;
            while ((hammer = (Hammer) ois.readObject()) != null)
                if (!hammer.toCsvString().equals(objectCsvString))
                    hammers.add(hammer);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (EOFException e) {
            // pass
        }

        this.hammers = hammers;
        this.save();
    }
}
