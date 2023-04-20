package cn.tedu.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 此案例学习FileOutputStream文件字节输出流向指定文件写出字节数据
 */
public class FOSDemo {
    public static void main(String[] args) throws IOException {
        //①第一种方式
        // 创建File实例,绑定目标文件
        File file = new File("./demo/fos.txt");
        // 创建文件字节输出流,并将file传入到该实例中
        FileOutputStream fos = new FileOutputStream(file);
        //②第二种方式
        // 创建文件字节流的同时,将目标文件路径传入该实例中
        fos = new FileOutputStream("./demo/fos.txt");
        //通过字节输出流向指定的文件中输出字节数据
        //①输出单字节数据
        /*
         * void write(int b)
         * 参数类型是整数,表示一个字节
         * 该方法,每次调用,都会向绑定的文件中,写出一个字节数据
         */
        fos.write(97);//a
        fos.write(98);//b
        fos.write(99);//c
        fos.write(100);//d
        System.out.println("写出完毕!!");
    }
}
