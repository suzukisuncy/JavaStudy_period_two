# 網絡編程

## 1 Socket

### 1.1 IP

#### 1.1.1 什麼是IP

IP是根據TCP/IP協議劃定,由32位二進制數組成,而且在因特網上是唯一的數值

例如:某臺計算機,連上網的IP是:

11010101 01001001 11110000 11001100

爲了便於記憶,會將這32位二進制數,每8位一組,每段之間用小數點分割

11010101.01001001.11110000.11001100

再將每八位轉化爲十進制

213.73.240.204

#### 1.1.2 如何查看自己電腦的ip

1. 按win+R, 輸入cmd,打開dos窗口

2. 在dos窗口輸入ipconfig

<img src="https://gitee.com/paida-spitting-star/image/raw/master/image-20230506212751768.png" alt="image-20230506212751768" style="border:solid"/>

### 1.2 什麼是端口號

- 端口號(port number)就是計算機爲了給每個網絡程序分配一個獨一無二的區別符,有了這些端口號,就可以準確定位到具體的程序。
- 端口號是個整數,範圍0-65535,分爲周知端口和動態端口
  - 周知端口就是衆所周知的端口,是端口號中的明星,本身的存在就是有自身用途,這些端口我們一般不使用,範圍是0-1023。
  - 動態端口,剩下的端口號都是動態端口,動態端口的意思就是將這些端口動態的分配給每個需要端口號的程序,當開啓一個程序時,就分配給它一個端口

## 2 Socket概述

在計算機領域中,Socket也被稱爲套接字編程,它是計算機之間進行通信的一種約定或者說是一種方式。

應用程序可以通過它發送或者接收數據,可以對其發送過來的內容像處理文件一樣,打開、關閉或者讀寫等操作,套接字允許應用程序將I/O插入到互聯網上,並與網絡中的其他程序進行通信。

### 2.1 Socket常用方法

#### 2.1.1 服務器端ServerSocket

在服務器端選擇一個端口號,然後在指定的端口號上等待客戶端發起的連接

構造方法:

```
ServerSocket(int port) 創建一個綁定特定端口號的服務器套接字
accept() 偵聽並接受到發送到此套接字的連接
close() 關閉此套接字
```

#### 2.1.2 客戶端Socket

構造方法:

```
Socket(String host,int port) 創建一個套接字,並且連接到host,並且綁定端口號
close() 關閉此套接字
getInputStream() 返回此套接字的輸入流
getOutputStram() 返回此套接字的輸出流
```

## 3 聊天室

![01-CS模型](https://gitee.com/paida-spitting-star/image/raw/master/01-CS%E6%A8%A1%E5%9E%8B.png)

### 3.1 初始Client 

1. 創建socket包,並在包下創建Client類和Server類
2. 在Client類中定義如下內容

- **構造器**的作用主要是用於初始化客戶端的內容,主要是用於做前期準備
- **start方法**的作用主要是用於執行客戶端的工作邏輯
- **main方法**中只用於書寫流程調用內容

```java
package cn.tedu.socket;

/**
 * 聊天室的客戶端
 */
public class Client {
    /**
     * 初始化客戶端
     */
    public Client() {
    }

    /**
     * 客戶端開始工作的方法
     */
    public void start() {

    }

    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }
}
```

3. 在Client中聲明[Socket實例](#2 Socket概述),用於搭建TCP連接,並在構造器中實例化該socket,並指定IP和端口號

- 其中的IP可以參考[1.1 IP](###1.1 IP)
- 端口號可以參考[1.2 什麼是端口號](#1.2 什麼是端口號)

![image-20230506213621300](https://gitee.com/paida-spitting-star/image/raw/master/image-20230506213621300.png)

```java
/**
 * java.net.Socket 套接字,原意是插座
 * Socket封裝了TCP協議的通信細節,我們使用它就可以與遠端計算機建立TCP連接,並基於一堆流的IO操作完成
 * 與遠端計算機的數據交互
 */
private Socket socket;

/**
 * 初始化客戶端
 */
public Client() {
    try {
        System.out.println("正在連接服務器...");
        /*
         * 實例化Socket時,需要傳入兩個參數:
         * ①要鏈接的遠端計算機的IP地址
         * 連接的計算機IP分爲兩種情況:
         *  1)如果連接的是非本機,則需要查詢對方的IP地址,進行連接
         *  2)如果連接的是本機,則可以選取如下的值:
         *      i. 真實IP(通過ipconfig查詢出來的)
         *      ii. 127.0.0.1(會自動映射本機的真實IP)
         *      iii. localhost(是127.0.0.1的域名)
         * ②要鏈接的遠端計算機的端口
         */
        socket = new Socket("localhost",8088);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

### 3.2 初始化Server

1. 仿照Client類,在Server中也定義構造器、start方法和main方法

```java
package cn.tedu.socket;

/**
 * 聊天室服務器端
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

2. 在Server中聲明ServerSocket,並且在構造器中實例化ServerSocket,並指定佔用端口號

![image-20230506214901662](https://gitee.com/paida-spitting-star/image/raw/master/image-20230506214901662.png)

```java
/**
 * java.net.ServerSocket
 * 是運行在服務器端的,它具有以下兩個作用:
 * ①打開服務器端口(佔用一個端口,客戶端訪問服務器的端口,就是它佔用)
 * ②監聽該端口,一旦一個客戶端訪問服務器,則會立即返回一個Socket實例,並通過這個Socket實例和客戶端進行交互
 */
private ServerSocket server;

public Server() {
    try {
        System.out.println("正在啓動服務器...");
        /*
         * 實例化ServerSocket時,需要指定要佔用的端口,而該端口就是我們Client類要連接的端口號
         */
        server = new ServerSocket(8088);
        System.out.println("服務器啓動完畢!!!");
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

3. 在start方法中調用accept方法,監聽端口

```java
public void start() {
    while (true) {
        try {
            System.out.println("等待客戶端的連接...");
            /*
             * 監聽當前佔用的端口號,並且該方法是一個阻塞方法
             * 當端口號沒有被客戶端訪問時,程序不會向下執行,一直阻塞在此處,
             * 當端口號被客戶端訪問時,會理解返回一個Socket實例,並且程序繼續向下運行
             */
            Socket socket = server.accept();
            System.out.println("一個客戶端連接了!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### 3.3 客戶端發一條信息

![image-20230507100013197](https://gitee.com/paida-spitting-star/image/raw/master/image-20230507100013197.png)

1. 在Client的start方法中建立流鏈接

![image-20230427213447577](https://gitee.com/paida-spitting-star/image/raw/master/image-20230427213447577.png)

```java
public void start() {
    try {
        /*
         * 通過Socket的getOutputStream方法
         * 獲取字節輸出流寫出的字節會通過網絡發送給遠端計算機
         * 此處獲取的是一個低級的字節輸出流
         */
        OutputStream out = socket.getOutputStream();
        //和轉換輸出字符流連接,並且指定編碼
        OutputStreamWriter osw = new OutputStreamWriter(out, StandardCharsets.UTF_8);
        //和緩衝字符流連接
        BufferedWriter bw = new BufferedWriter(osw);
        //和按行寫出字符流連接,並且開啓自動行刷新
        PrintWriter pw = new PrintWriter(bw, true);
        pw.println("你好啊,客戶端!");
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

2. 在Server類中的start方法中,實現接受客戶端發送的字符串功能

![image-20230507100907051](https://gitee.com/paida-spitting-star/image/raw/master/image-20230507100907051.png)

```java
public void start() {
    while (true) {
        try {
            System.out.println("等待客戶端的連接...");
            Socket socket = server.accept();
            System.out.println("一個客戶端連接了!!!");
            /*
             * Socket的getInputStream方法
             * 可以獲取一個低級的字節輸入流,可以讀取來自遠端計算機發送過來的字節數據
             */
            InputStream in = socket.getInputStream();
            //連接輸入轉換字符流,並指定編碼
            InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8);
            //連接緩衝輸入字符流
            BufferedReader br = new BufferedReader(isr);
            //讀取客戶端發送的一行字符串
            String line = br.readLine();
            System.out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
```

### 3.4 客戶端循環發送

1. 在Client類中的start方法中,仿照記事本案例,實現客戶端在控制檯寫一行字符串,可以向服務器發送一行字符串

```java
public void start() {
    try {
        OutputStream out = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(out, StandardCharsets.UTF_8);
        BufferedWriter bw = new BufferedWriter(osw);
        PrintWriter pw = new PrintWriter(bw, true);
        //仿照記事本實現寫一行發一行
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

2. 在Server的start方法中,循環讀取客戶端發送的字符串

```java
public void start() {
    while (true) {
        try {
            System.out.println("等待客戶端的連接...");
            Socket socket = server.accept();
            System.out.println("一個客戶端連接了!!!");
            InputStream in = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            //循環讀取客戶端發送的一行字符串
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

3. 測試程序,當客戶端輸入"exit"時,會退出程序,但是服務器處會報如下錯誤:

<img src="https://gitee.com/paida-spitting-star/image/raw/master/image-20230507111002043.png" alt="image-20230507111002043" style="border:solid"/>

- **原因:** 是因爲基於TCP的底層協議,要求客戶端斷開連接時,需要和服務器揮手示意,告知服務器客戶端斷開了,否則就會報錯

4. 無論是客戶端的流資源釋放,還是客戶端下線了,去揮手示意,都是必須要實現的,所以適合寫在start方法中的try代碼塊的finally中

```java
public void start() {
    try {
        OutputStream out = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(out, StandardCharsets.UTF_8);
        BufferedWriter bw = new BufferedWriter(osw);
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
             * ①可以斷開和遠端計算機的連接,並揮手示意
             * ②可以將socket所連接的流進行關閉
             */
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

5. 此時再進行測試,可以發現輸入exit時,服務器端不會發生異常,但是強制關閉客戶端依舊還是報錯,這是不可避免的,因爲我們不能徹底約束客戶端的行爲!!!

### 3.5 客戶端起名

1. 在Client類中聲明name的全局變量

```java
public class Client {
    private Socket socket;
    private static String name;
    ...
}
```

2. 然後在main方法中,爲name屬性賦值

```java
public static void main(String[] args) {
    System.out.println("請輸入您的用戶名:");
    name = new Scanner(System.in).nextLine();
    Client client = new Client();
    client.start();
}
```

3. 然後在發送字符串時,將name一同發送給服務器

```java
public void start() {
    try {
        OutputStream out = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(out, StandardCharsets.UTF_8);
        BufferedWriter bw = new BufferedWriter(osw);
        PrintWriter pw = new PrintWriter(bw, true);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine();
            if ("exit".equals(line)) {
                break;
            }
            pw.println(name + "說: " + line);
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### 3.6 客戶端多開

1. 點擊如下內容

<img src="https://gitee.com/paida-spitting-star/image/raw/master/image-20230507113113183.png" alt="image-20230507113113183" style="border:solid"/>

2. 在左側欄中找到要多開的程序,然後點擊右側欄中的**`Modify options`**

<img src="https://gitee.com/paida-spitting-star/image/raw/master/image-20230507113337437.png" alt="image-20230507113337437" style="border: solid; zoom: 33%;"/>

注意: 不同的idea,該設置會略有不同,比如如果是下圖樣式,可以參考如下配置

<img src="https://gitee.com/paida-spitting-star/image/raw/master/image-20230507113523821.png" alt="image-20230507113523821" style="zoom: 33%;" />

3. 勾選如下的選項,即可多開

<img src="https://gitee.com/paida-spitting-star/image/raw/master/image-20230507113655762.png" alt="image-20230507113655762" style="zoom:50%;border: solid;" />

### 3.7 引入多線程

- 此時測試,會發現多個客戶端不能同時向服務器發送內容,服務器只能同一時間和一個客戶端進行交互,當多個客戶端訪問服務器時,需要排隊
- 原因是我們目前的程序都是單線程程序(main線程),是沒辦法解決當前問題的

<img src="https://gitee.com/paida-spitting-star/image/raw/master/image-20230507115134595.png" alt="image-20230507115134595" style="border: solid"/>

- 所以需要引入**多線程**,讓主線程負責接受客戶端,當客戶端來訪問時,再創建一條線程負責和客戶端進行交互

<img src="https://gitee.com/paida-spitting-star/image/raw/master/image-20230507163813021.png" alt="image-20230507163813021" style="border:solid"/>

1. 在Server類中,將start方法中和客戶端進行交互的內容,移動到線程任務類的run方法中

- **注意事項**
  - ClientHandler是內部類
  - start方法中在接收一個客戶端訪問時,一定要創建線程,並且將對客戶端生成的socket實例傳遞給線程任務類,此處使用的是構造器傳輸

①start方法

```java
public void start() {
    while (true) {
        try {
            System.out.println("等待客戶端的連接...");
            Socket socket = server.accept();
            System.out.println("一個客戶端連接了!!!");
            //當有客戶端訪問時,可以創建一個線程負責和當前客戶端進行交互
            ClientHandler handler = new ClientHandler(socket);
            Thread t = new Thread(handler);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

②ClientHandler類

```java
/**
 * 定義線程的任務類,負責和客戶端進行交互
 */
private class ClientHandler implements Runnable {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream in = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
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

## 4 常見問題

### 4.1 測試問題

![image-20230507092040868](https://gitee.com/paida-spitting-star/image/raw/master/image-20230507092040868.png)

- 如果服務器不啓動,客戶端會連接失敗

<img src="https://gitee.com/paida-spitting-star/image/raw/master/image-20230507104133171.png" alt="image-20230507104133171" style="border:solid"/>

### 4.2 端口號被佔用問題

#### 4.2.1 問題描述

- 當程序啓動時,控制檯報如下錯誤,一定是發送了端口號佔用的問題

<img src="https://gitee.com/paida-spitting-star/image/raw/master/image-20230507102923841.png" alt="image-20230507102923841" style="border:solid"/>

#### 4.2.2 解決方式

①方式一: 更換端口號

注意: 如果修改了服務器的端口號,客戶端連接的端口號也要一同發生改變

②方式二: 殺死現佔用端口號的程序

1. 按**`WIN+R`**,敲擊cmd,打開DOS窗口
2. 然後敲擊如下命令,該命令會查詢出佔用端口號的程序

```shell
netstat -ano|findstr "端口號"
```

![image-20230507103408733](https://gitee.com/paida-spitting-star/image/raw/master/image-20230507103408733.png)

**注意:** 其中11240是當前這個程序的PID

3. 根據PID值,獲取對應的程序名,來防止殺死不該殺死的程序

```shell
tasklist|findstr "PID值"
```

![image-20230507103731362](https://gitee.com/paida-spitting-star/image/raw/master/image-20230507103731362.png)

4. 經過檢查,該程序是可以關閉的,所以關閉該程序

```shell
taskkill /f /pid "PID值"
```

![image-20230507103907216](https://gitee.com/paida-spitting-star/image/raw/master/image-20230507103907216.png)
