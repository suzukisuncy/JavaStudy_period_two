package cn.tedu.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 此案例实现一个简易的记事本
 */
public class TestNotes01 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请开始输入内容,单独输入exit退出!");
        FileOutputStream fos = new FileOutputStream("./demo/note.txt");
        while (true) {
            //获取在控制台输入的字符串
            String line = scanner.nextLine();
            //判断控制台输入的字符串是否是exit
            if ("exit".equals(line)) {
                //跳出当前循环,结束程序
                break;
            }
            //将控制台输入的内容写入到文件中
            fos.write(line.getBytes(StandardCharsets.UTF_8));
        }
    }
}
