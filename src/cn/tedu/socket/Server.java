package cn.tedu.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 聊天室服务器端
 */
public class Server {
    /**
     * java.net.ServerSocket
     * 是运行在服务器端的,它具有以下两个作用:
     * ①打开服务器端口(占用一个端口,客户端访问服务器的端口,就是它占用)
     * ②监听该端口,一旦一个客户端访问服务器,则会立即返回一个Socket实例,并通过这个Socket实例和客户端进行交互
     */
    private ServerSocket server;

    public Server() {
        try {
            System.out.println("正在启动服务器...");
            /*
             * 实例化ServerSocket时,需要指定要占用的端口,而该端口就是我们Client类要连接的端口号
             */
            server = new ServerSocket(8088);
            System.out.println("服务器启动完毕!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        while (true) {
            try {
                System.out.println("等待客户端的连接...");
                /*
                 * ServerSocket提供的accept方法:
                 * 监听当前占用的端口号,并且该方法是一个阻塞方法
                 * 当端口号没有被客户端访问时,程序不会向下执行,一直阻塞在此处,
                 * 当端口号被客户端访问时,会理解返回一个Socket实例,并且程序继续向下运行
                 * 强调: 可以利用该Socket实例和当前来访的客户端进行交互
                 */
                Socket socket = server.accept();
                System.out.println("一个客户端连接了!!!");
                /*
                 * Socket的getInputStream方法
                 * 可以获取一个低级的字节输入流,可以读取来自远端计算机发送过来的字节数据
                 */
                InputStream in = socket.getInputStream();
                //连接输入转换字符流,并指定编码
                InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8);
                //连接缓冲输入字符流
                BufferedReader br = new BufferedReader(isr);
                //循环读取客户端发送的一行字符串
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}
