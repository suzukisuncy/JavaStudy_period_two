# 网络编程

## 1 Socket

### 1.1 IP

#### 1.1.1 什么是IP

IP是根据TCP/IP协议划定,由32位二进制数组成,而且在因特网上是唯一的数值

例如:某台计算机,连上网的IP是:

11010101 01001001 11110000 11001100

为了便于记忆,会将这32位二进制数,每8位一组,每段之间用小数点分割

11010101.01001001.11110000.11001100

再将每八位转化为十进制

213.73.240.204

#### 1.1.2 如何查看自己电脑的ip

1. 按win+R, 输入cmd,打开dos窗口

2. 在dos窗口输入ipconfig

<img src="https://gitee.com/paida-spitting-star/image/raw/master/image-20230506212751768.png" alt="image-20230506212751768" style="border:solid"/>

### 1.2 什么是端口号

- 端口号(port number)就是计算机为了给每个网络程序分配一个独一无二的区别符,有了这些端口号,就可以准确定位到具体的程序。
- 端口号是个整数,范围0-65535,分为周知端口和动态端口
  - 周知端口就是众所周知的端口,是端口号中的明星,本身的存在就是有自身用途,这些端口我们一般不使用,范围是0-1023。
  - 动态端口,剩下的端口号都是动态端口,动态端口的意思就是将这些端口动态的分配给每个需要端口号的程序,当开启一个程序时,就分配给它一个端口

## 2 Socket概述

在计算机领域中,Socket也被称为套接字编程,它是计算机之间进行通信的一种约定或者说是一种方式。

应用程序可以通过它发送或者接收数据,可以对其发送过来的内容像处理文件一样,打开、关闭或者读写等操作,套接字允许应用程序将I/O插入到互联网上,并与网络中的其他程序进行通信。

### 2.1 Socket常用方法

#### 2.1.1 服务器端ServerSocket

在服务器端选择一个端口号,然后在指定的端口号上等待客户端发起的连接

构造方法:

```
ServerSocket(int port) 创建一个绑定特定端口号的服务器套接字
accept() 侦听并接受到发送到此套接字的连接
close() 关闭此套接字
```

#### 2.1.2 客户端Socket

构造方法:

```
Socket(String host,int port) 创建一个套接字,并且连接到host,并且绑定端口号
close() 关闭此套接字
getInputStream() 返回此套接字的输入流
getOutputStram() 返回此套接字的输出流
```

## 3 聊天室

### 3.1 初始Client 

1. 创建socket包,并在包下创建Client类和Server类
2. 在Client类中定义如下内容

- **构造器**的作用主要是用于初始化客户端的内容,主要是用于做前期准备
- **start方法**的作用主要是用于执行客户端的工作逻辑
- **main方法**中只用于书写流程调用内容

```java
package cn.tedu.socket;

/**
 * 聊天室的客户端
 */
public class Client {
    /**
     * 初始化客户端
     */
    public Client() {
    }

    /**
     * 客户端开始工作的方法
     */
    public void start() {

    }

    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }
}
```

3. 在Client中声明[Socket实例](#2 Socket概述),用于搭建TCP连接,并在构造器中实例化该socket,并指定IP和端口号

- 其中的IP可以参考[1.1 IP](###1.1 IP)
- 端口号可以参考[1.2 什么是端口号](#1.2 什么是端口号)

![image-20230506213621300](https://gitee.com/paida-spitting-star/image/raw/master/image-20230506213621300.png)

```java
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
        socket = new Socket("localhost",8088);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

### 3.2 初始化Server

1. 仿照Client类,在Server中也定义构造器、start方法和main方法

```java
package cn.tedu.socket;

/**
 * 聊天室服务器端
 */
public class Server {
    public Server() {
    }

    public void start() {

    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}
```

2. 在Server中声明ServerSocket,并且在构造器中实例化ServerSocket,并指定占用端口号

![image-20230506214901662](https://gitee.com/paida-spitting-star/image/raw/master/image-20230506214901662.png)

```java
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
```

3. 在start方法中调用accept方法,监听端口

```java
public void start() {
    while (true) {
        try {
            System.out.println("等待客户端的连接...");
            /*
             * 监听当前占用的端口号,并且该方法是一个阻塞方法
             * 当端口号没有被客户端访问时,程序不会向下执行,一直阻塞在此处,
             * 当端口号被客户端访问时,会理解返回一个Socket实例,并且程序继续向下运行
             */
            Socket socket = server.accept();
            System.out.println("一个客户端连接了!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### 3.3 客户端发一条信息

![image-20230507100013197](https://gitee.com/paida-spitting-star/image/raw/master/image-20230507100013197.png)

1. 在Client的start方法中建立流链接

![image-20230427213447577](https://gitee.com/paida-spitting-star/image/raw/master/image-20230427213447577.png)

```java
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
        pw.println("你好啊,客户端!");
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

2. 在Server类中的start方法中,实现接受客户端发送的字符串功能

![image-20230507100907051](https://gitee.com/paida-spitting-star/image/raw/master/image-20230507100907051.png)

```java
public void start() {
    while (true) {
        try {
            System.out.println("等待客户端的连接...");
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
            //读取客户端发送的一行字符串
            String line = br.readLine();
            System.out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
```

### 3.4 客户端循环发送

1. 在Client类中的start方法中,仿照记事本案例,实现客户端在控制台写一行字符串,可以向服务器发送一行字符串

```java
public void start() {
    try {
        OutputStream out = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(out, StandardCharsets.UTF_8);
        BufferedWriter bw = new BufferedWriter(osw);
        PrintWriter pw = new PrintWriter(bw, true);
        //仿照记事本实现写一行发一行
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
    }
}
```

2. 在Server的start方法中,循环读取客户端发送的字符串

```java
public void start() {
    while (true) {
        try {
            System.out.println("等待客户端的连接...");
            Socket socket = server.accept();
            System.out.println("一个客户端连接了!!!");
            InputStream in = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8);
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
```

### 3.5 客户端多开

## 4 常见问题

### 4.1 测试问题

![image-20230507092040868](https://gitee.com/paida-spitting-star/image/raw/master/image-20230507092040868.png)

- 如果服务器不启动,客户端会连接失败

<img src="https://gitee.com/paida-spitting-star/image/raw/master/image-20230507104133171.png" alt="image-20230507104133171" style="border:solid"/>

### 4.2 端口号被占用问题

#### 4.2.1 问题描述

- 当程序启动时,控制台报如下错误,一定是发送了端口号占用的问题

<img src="https://gitee.com/paida-spitting-star/image/raw/master/image-20230507102923841.png" alt="image-20230507102923841" style="border:solid"/>

#### 4.2.2 解决方式

①方式一: 更换端口号

注意: 如果修改了服务器的端口号,客户端连接的端口号也要一同发生改变

②方式二: 杀死现占用端口号的程序

1. 按**`WIN+R`**,敲击cmd,打开DOS窗口
2. 然后敲击如下命令,该命令会查询出占用端口号的程序

```shell
netstat -ano|findstr "端口号"
```

![image-20230507103408733](https://gitee.com/paida-spitting-star/image/raw/master/image-20230507103408733.png)

**注意:** 其中11240是当前这个程序的PID

3. 根据PID值,获取对应的程序名,来防止杀死不该杀死的程序

```shell
tasklist|findstr "PID值"
```

![image-20230507103731362](https://gitee.com/paida-spitting-star/image/raw/master/image-20230507103731362.png)

4. 经过检查,该程序是可以关闭的,所以关闭该程序

```shell
taskkill /f /pid "PID值"
```

![image-20230507103907216](https://gitee.com/paida-spitting-star/image/raw/master/image-20230507103907216.png)
