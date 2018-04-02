package main.persistence;

import java.io.File;
import java.io.IOException;

/**
 * 抽象类
 */
public abstract class AbstractManager {
    private File objectFile = null;

    /**
     * 有参构造方法
     * @param objectFile
     */
    public AbstractManager(File objectFile){
        this.objectFile = objectFile;
    }

    /**
     * 设置持久化文件对象
     * @param objectFile
     */
    public void setObjectFile(File objectFile){
        this.objectFile = objectFile;
    }

    public File getObjectFile() {
        return this.objectFile;
    }

    /**
     * 写出对象到持久化文件中
     * @throws IOException
     */
    public abstract void save() throws IOException;

    /**
     * 从持久化文件中读入第i个对象.
     * @param i
     * @Return
     * @throws IOException
     */
    public abstract Object read(int i) throws IOException;

    /**
     * 读入全部对象到数组中.
     * @return
     * @throws IOException
     */
    public abstract Object[] read() throws IOException;

    /**
     * //移除持久化文件中的对象
     * @param object
     * @throws IOException
     */
    public abstract void remove(Object object) throws IOException;
}
