package cn.tedu.io;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 此案例学习缓冲流写出数据的缓冲区问题
 */
public class BOS_flushDemo {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("./demo/flush.txt");
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        String line = "唧唧复唧唧,木兰开飞机,开的什么机,波音747!";
        /*
         * 缓冲输出流,内部声明了一个8k的缓冲区,
         * 该缓冲区的特点,是如果读取不满8k的数据,不会将数据写出
         */
        bos.write(line.getBytes(StandardCharsets.UTF_8));
        System.out.println("写出完毕!");
        //故意不写close
        /*
         * 会将缓冲区的数据强制写出
         * close方法的内部会调用flush方法
         */
        bos.flush();
        //bos.close();
    }
}
