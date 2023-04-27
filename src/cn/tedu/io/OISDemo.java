package cn.tedu.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * 通过此案例学习使用对象输入流将指定文件中的对象还原为对象
 */
public class OISDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("./demo/person.txt");
        //创建对象输入流,绑定指定的文件字节输入流,用于将该文件中读取的字节还原为对象
        ObjectInputStream ois = new ObjectInputStream(fis);
        /*
         * Object readObject()
         * 将文件中的字节数据还原为对象,该对象由于程序不知道是什么类型,所以返回的是Object
         *
         */
        Object p = ois.readObject();
        System.out.println(p);
        ois.close();
    }
}
