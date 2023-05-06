package cn.tedu.exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 通过Finally代码块释放IO资源
 */
public class IOCloseDemo01 {
    public static void main(String[] args) {
        FileOutputStream fos = null;
        try {
            int a = 1 / 0;//模拟出问题
            fos = new FileOutputStream("demo/fos.txt");
            fos.write(1);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
