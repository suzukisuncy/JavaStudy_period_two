package cn.tedu.io;

import java.io.*;
import java.util.Scanner;

/**
 * 利用PrintWriter实现建议的记事本,并且可以按行写入
 */
public class TestNote02 {
    public static void main(String[] args) throws FileNotFoundException {
        //创建低级的文件字节输出流 ①绑定文件
        FileOutputStream fos = new FileOutputStream("./demo/note_pw.txt");
        //创建高级的转换输出字符流 ①衔接字节流和字符流 ②将写出的字符自动转换为字节数据
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        //创建高级的缓冲字符输出流 ①提高块写文本数据的效率
        BufferedWriter bw = new BufferedWriter(osw);
        //创建高级的按行刷新字符流 ①按行插入字符串
        PrintWriter pw = new PrintWriter(bw);
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
