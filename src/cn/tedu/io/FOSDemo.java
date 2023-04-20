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
        //一、创建流对象
        //①第一种方式
        // 创建File实例,绑定目标文件
        //File file = new File("./demo/fos.txt");
        // 创建文件字节输出流,并将file传入到该实例中
        //FileOutputStream fos = new FileOutputStream(file);
        //②第二种方式
        // 创建文件字节流的同时,将目标文件路径传入该实例中
        //fos = new FileOutputStream("./demo/fos.txt");
        //③第三种方式
        // 创建文件字节流时,可以开启追加模式(只需要添加第二个参数为true,即可开启追加模式)
        FileOutputStream fos = new FileOutputStream("./demo/fos.txt", true);
        //二、通过字节输出流向指定的文件中输出字节数据
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
        fos.write(13);//回车符(将光标回归到行首)
        fos.write(10);//换行符(将光标移到下一行)
        //②一次性输出多个字节数据(依赖字节数组)
        /*
         * byte[] getBytes()
         * 将字符串转换为字节数据,存储到字节数组中
         * \r\n 表示回车符和换行符
         */
        fos.write("Hello JSDVN2302!\r\n".getBytes());
        //③输出字节数组中的一部分字节数据
        //ABCDEFGH\r\n → FGH\r\n
        /*
         * void write(byte b[], int off, int len)
         * off: 表示要输出的头字节的下标,下标5的位置就是F
         * len: 表示从要输出的头字节之后的几个字节数据
         */
        fos.write("ABCDEFGH\r\n".getBytes(), 5, 5);
        System.out.println("写出完毕!!");
        //三、关闭流资源,否则会占用资源
        fos.close();
    }
}
