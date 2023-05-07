package cn.tedu.socket;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 聊天室的客户端
 */
public class Client {
    /**
     * java.net.Socket 套接字,原意是插座
     * Socket封装了TCP协议的通信细节,我们使用它就可以与远端计算机建立TCP连接,并基于一堆流的IO操作完成
     * 与远端计算机的数据交互
     */
    private Socket socket;

    /**
     * 初始化客户端
     */
    public Client() {
        try {
            System.out.println("正在连接服务器...");
            /*
             * 实例化Socket时,需要传入两个参数:
             * ①要链接的远端计算机的IP地址
             * 连接的计算机IP分为两种情况:
             *  1)如果连接的是非本机,则需要查询对方的IP地址,进行连接
             *  2)如果连接的是本机,则可以选取如下的值:
             *      i. 真实IP(通过ipconfig查询出来的)
             *      ii. 127.0.0.1(会自动映射本机的真实IP)
             *      iii. localhost(是127.0.0.1的域名)
             * ②要链接的远端计算机的端口
             */
            socket = new Socket("localhost", 8088);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 客户端开始工作的方法
     */
    public void start() {
        try {
            /*
             * 通过Socket的getOutputStream方法
             * 获取字节输出流写出的字节会通过网络发送给远端计算机
             * 此处获取的是一个低级的字节输出流
             */
            OutputStream out = socket.getOutputStream();
            //和转换输出字符流连接,并且指定编码
            OutputStreamWriter osw = new OutputStreamWriter(out, StandardCharsets.UTF_8);
            //和缓冲字符流连接
            BufferedWriter bw = new BufferedWriter(osw);
            //和按行写出字符流连接,并且开启自动行刷新
            PrintWriter pw = new PrintWriter(bw, true);
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String line = scanner.nextLine();
                if ("exit".equals(line)) {
                    break;
                }
                pw.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                /*
                 * Socket提供的close方法
                 * ①可以断开和远端计算机的连接,并挥手示意
                 * ②可以将socket所连接的流进行关闭
                 */
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }
}
