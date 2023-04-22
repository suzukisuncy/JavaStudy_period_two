package cn.tedu.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 此案例学习FileInputStream文件字节输入流向指定文件读取字节数据
 */
public class FISDemo {
    public static void main(String[] args) throws IOException {
        //创建文件字节输入流并绑定文件
        FileInputStream fis = new FileInputStream("./demo/fos.txt");
        //读取一个字节
        /*
         * int read()
         * 读取1个字节,并将该字节转换为整数返回,如果读取到了文件的末尾,则返回-1
         * 连续调用read方法时,会连续读取指定文件中的字节数据
         */
//        int d = fis.read();
//        System.out.println((char) d);
//        System.out.println((char) fis.read());
//        System.out.println((char) fis.read());
//        System.out.println((char) fis.read());
//        System.out.println(fis.read());
//        System.out.println(fis.read());
        //使用循环,连续读取文件中的字节数据
        int data;//封装本次读取的结果
        while ((data = fis.read()) != -1) {//说明不是结尾
            System.out.print((char) data);
        }
        //关闭流
        fis.close();
    }
}
