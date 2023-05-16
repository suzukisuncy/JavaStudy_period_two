package cn.tedu.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

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
    // private PrintWriter[] allOut = {};
    private Collection<PrintWriter> allOut = new ArrayList<>();

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
                //当有客户端访问时,可以创建一个线程负责和当前客户端进行交互
                ClientHandler handler = new ClientHandler(socket);
                Thread t = new Thread(handler);
                t.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    /**
     * 定义线程的任务类,负责和客户端进行交互
     */
    private class ClientHandler implements Runnable {
        private Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            PrintWriter pw = null;
            try {
                /*
                 * Socket的getInputStream方法
                 * 可以获取一个低级的字节输入流,可以读取来自远端计算机发送过来的字节数据
                 */
                InputStream in = socket.getInputStream();
                //连接输入转换字符流,并指定编码
                InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8);
                //连接缓冲输入字符流
                BufferedReader br = new BufferedReader(isr);
                //通过socket获取输出流,用于给客户端发送信息
                OutputStream out = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                BufferedWriter bw = new BufferedWriter(osw);
                pw = new PrintWriter(bw, true);
                //将该客户端的输出流存入到共享集合allOut中
                // allOut = Arrays.copyOf(allOut, allOut.length + 1);
                // allOut[allOut.length - 1] = pw;
                allOut.add(pw);
                //广播通知所有客户端该用户上线了
                // sendMessage("一个用户上线了!当前在线人数:" + allOut.length);
                sendMessage("一个用户上线了!当前在线人数:" + allOut.size());
                //循环读取客户端发送的一行字符串
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                    //将客户端发送的信息回复给所有客户端
                    sendMessage(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //将当前客户端的输出流从allOut集合中取出
                // for (int i = 0; i < allOut.length; i++) {
                //     if (allOut[i] == pw) {
                //         allOut[i] = allOut[allOut.length - 1];
                //         allOut = Arrays.copyOf(allOut, allOut.length - 1);
                //         break;
                //     }
                // }
                allOut.remove(pw);
                //广播通知所有客户端用户下线了
                // sendMessage("一个用户下线了,当前在线人数:" + allOut.length);
                sendMessage("一个用户下线了,当前在线人数:" + allOut.size());
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * 广播通知所有客户端
         *
         * @param message 广播的信息
         */
        private void sendMessage(String message) {
            // for (int i = 0; i < allOut.length; i++) {
            //     allOut[i].println(message);
            // }
            for (PrintWriter pw : allOut) {
                pw.println(message);
            }
        }
    }
}
