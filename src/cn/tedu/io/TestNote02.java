package cn.tedu.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 利用PrintWriter实现建议的记事本,并且可以按行写入
 */
public class TestNote02 {
    public static void main(String[] args) throws FileNotFoundException {
        //创建低级的文件字节输出流 ①绑定文件 ②开启追加模式
        FileOutputStream fos = new FileOutputStream("./demo/note_pw.txt", true);
        //创建高级的转换输出字符流 ①衔接字节流和字符流 ②将写出的字符自动按照编码集转换为字节数据
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        //创建高级的缓冲字符输出流 ①提高块写文本数据的效率
        BufferedWriter bw = new BufferedWriter(osw);
        //创建高级的按行刷新字符流 ①按行插入字符串 ②开启自动行刷新(写一行字符串,回车之后,会自动调用flush方法)
        PrintWriter pw = new PrintWriter(bw, true);
        //完成简易记事本的录入
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine();
            if ("exit".equals(line)) {
                break;
            }
            //将控制台录入的字符串写入到文件中
            pw.println(line);
        }
        //释放流资源
        pw.close();
    }
}
