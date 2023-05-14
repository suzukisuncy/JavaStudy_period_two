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

### 3.8 服務器向客戶端回信

![image-20230513205029819](https://gitee.com/paida-spitting-star/image/raw/master/image-20230513205029819.png)

1. 在Server類中的run方法裏,定義輸出流向客戶端發送信息

```java
@Override
public void run() {
    try {
        InputStream in = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        //通過socket獲取輸出流,用於給客戶端發送信息
        OutputStream out = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(out, StandardCharsets.UTF_8);
        BufferedWriter bw = new BufferedWriter(osw);
        PrintWriter pw = new PrintWriter(bw, true);
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
            //將客戶端發送的信息回覆給客戶端
            pw.println(line);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

2. 在Client中的start方法中創建輸入流,讀取服務器發送的信息

```java
public void start() {
    try {
        OutputStream out = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(out, StandardCharsets.UTF_8);
        BufferedWriter bw = new BufferedWriter(osw);
        PrintWriter pw = new PrintWriter(bw, true);
        //通過socket獲取輸入流讀取服務器發送的信息
        InputStream in = socket.getInputStream();
        //連接輸入轉換字符流,並指定編碼
        InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8);
        //連接緩衝輸入字符流
        BufferedReader br = new BufferedReader(isr);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine();
            if ("exit".equals(line)) {
                break;
            }
            pw.println(name + "說: " + line);
            //讀取服務器回覆的一句話
            line = br.readLine();
            System.out.println(line);
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

### 3.9 實現客戶端羣聊

![image-20230513211950314](https://gitee.com/paida-spitting-star/image/raw/master/image-20230513211950314.png)

1. 在Server中添加一個**全局屬性**:allOut的空數組,用於存放所有對客戶端的輸出流

```java
private PrintWriter[] allOut = {};
```

2. 在ClientHandler中的run方法中,將對客戶端的輸出流,存儲到allOut中,並且在收到客戶端發送信息後,將信息羣發給所有客戶端

```java
public void run() {
    try {
        InputStream in = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        OutputStream out = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(out, StandardCharsets.UTF_8);
        BufferedWriter bw = new BufferedWriter(osw);
        PrintWriter pw = new PrintWriter(bw, true);
        //將該客戶端的輸出流存入到共享數組allOut中
        //①對allOut擴容
        allOut = Arrays.copyOf(allOut, allOut.length + 1);
        //②將pw存儲到allOut中(存儲到allOut中的最後一個位置)
        allOut[allOut.length - 1] = pw;
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
            //將客戶端發送的信息回覆給所有客戶端
            for (int i = 0; i < allOut.length; i++) {
                allOut[i].println(line);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

3. 啓動服務器,運行多個客戶端,測試時,發現,多個客戶端在發送信息時,出現發送一行信息,才能收到一行信息的情況,原因主要是出現在Client類中如下位置:

```java
while (true) {
    String line = scanner.nextLine();
    if ("exit".equals(line)) {
        break;
    }
    pw.println(name + "說: " + line);
    //讀取服務器回覆的一句話
    line = br.readLine();
    System.out.println(line);
}
```

其中`scanner.nextLine()`這句代碼是阻塞方法,只有在控制敲擊回車鍵,程序才會繼續向下執行,所以此處導致我們不在控制檯敲擊回車鍵,就不能執行`br.readLine();`代碼,也就是不能讀取服務器轉發給各個客戶端的信息,如果要解決該問題,需要使用線程,消除此處代碼片段之間的互相影響

### 3.10 客戶端引入多線程

1. 在Client中,創建ServerHandler負責讀取服務器發送的信息,並且將start方法中讀取服務器信息的內容移動到ServerHandler中的run方法內部

①ServerHandler

```java
/**
 * 該線程負責讀取服務器發送的信息
 */
private class ServerHandler implements Runnable {
    @Override
    public void run() {
        try {
            //通過socket獲取輸入流讀取服務器發送的信息
            InputStream in = socket.getInputStream();
            //連接輸入轉換字符流,並指定編碼
            InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8);
            //連接緩衝輸入字符流
            BufferedReader br = new BufferedReader(isr);
            //創建流之後,開始循環讀取服務器發送的信息
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

②start方法

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

2. 在start方法中,啓動線程,並執行讀取服務器信息的任務

```java
//啓動一個線程來讀取服務器端發送的信息
ServerHandler handler = new ServerHandler();
Thread t = new Thread(handler);
t.start();
```

3. 由於服務器可能存在異常斷開,會導致我們客戶端報錯,但是這種斷開是不能控制的,所以索性就忽略就可以了,所以我們可以在ServerHandler中的run方法中捕獲異常但是不需要打印異常

```java
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
        //此處只有當鏈接異常斷開時,才會觸發異常,但是控制不了,所以索性就忽視該異常
        //e.printStackTrace();
    }
}
```

4. 同樣在Server類中,斷開連接時,也同時關閉流並且向客戶端揮手示意,所以在Server中的run方法中添加finally,關閉資源

```java
public void run() {
    try {
        InputStream in = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        OutputStream out = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(out, StandardCharsets.UTF_8);
        BufferedWriter bw = new BufferedWriter(osw);
        PrintWriter pw = new PrintWriter(bw, true);
        allOut = Arrays.copyOf(allOut, allOut.length + 1);
        allOut[allOut.length - 1] = pw;
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
            for (int i = 0; i < allOut.length; i++) {
                allOut[i].println(line);
            }
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

5. 啓動測試,測試效果沒有問題,但是目前有一點要說明,客戶端啓動後,會存在兩個線程,一個是主線程,一個是ServerHandler的子線程,當客戶端輸入exit時,意味着主線程結束,但是子線程會結束嗎?很明顯不會,因爲子線程是普通用戶線程,所以我們爲了實現主線程結束後,子線程也會被同步殺死,可以將子線程設置爲**守護線程**,所以在啓動ServerHandler線程前,將其設置爲守護線程

```java
ServerHandler handler = new ServerHandler();
Thread t = new Thread(handler);
//設置爲守護線程
t.setDaemon(true);
t.start();
```

### 3.11 客戶端下線

- 客戶端下線包含兩種情況,一種是客戶端輸入exit時,正常下線,另一種是直接關閉程序,總之不論是哪種方式,都需要從allOut數組中,取出對下線的客戶端的輸出流,所以這部分代碼適合寫在finally中

1. 由於我們在下線時,需要比較輸出流內存地址,所以爲了能夠在finally中使用pw實例,所以需要將pw的作用域提高到全局

```java
public void run() {
    PrintWriter pw = null;
    try {
        //此處省略
        pw = new PrintWriter(bw, true);
    }
```

2. 在finally中進行取出客戶端輸出流操作

> 取出思路:
>
> ​	①遍歷allOut數組
> ​	②將要刪除的元素和數組中的每一個元素進行內存地址的比較,相同的即是要刪除的元素
> ​	③將數組的最後一個元素替換到刪除元素的位置
> ​	④將數組的最後一個元素縮容

```java
public void run() {
    PrintWriter pw = null;
    try {
        //此處省略
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        //將當前客戶端的輸出流從allOut數組中取出
        //遍歷allOut
        for (int i = 0; i < allOut.length; i++) {
            //找到要刪除的元素
            if (allOut[i] == pw) {
                //將最後一個元素替換到目標元素
                allOut[i] = allOut[allOut.length - 1];
                //進行數組的縮容(將數組的最後一個元素刪除)
                allOut = Arrays.copyOf(allOut, allOut.length-1);
                //由於數組中只會存儲一個目標元素,所以找到目標元素取出後,就可以停止遍歷了
                break;
            }
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

3.12 廣播在線人數

- 廣播通知的操作在當前案例會出現多次
  - 當用戶上線時,需要廣播所有客戶端
  - 當用戶下線時,需要廣播所有客戶端
  - 當一個客戶端發送信息時,需要廣播所有客戶端

1. 在Server中的ClientHandler類中,添加一個廣播通知的方法

```java
/**
 * 廣播通知所有客戶端
 *
 * @param message 廣播的信息
 */
private void sendMessage(String message) {
    for (int i = 0; i < allOut.length; i++) {
        allOut[i].println(message);
    }
}
```

2. 調用該方法

```java
public void run() {
    PrintWriter pw = null;
    try {
        InputStream in = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        OutputStream out = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(out, StandardCharsets.UTF_8);
        BufferedWriter bw = new BufferedWriter(osw);
        pw = new PrintWriter(bw, true);
        allOut = Arrays.copyOf(allOut, allOut.length + 1);一個位
        allOut[allOut.length - 1] = pw;
        //廣播通知所有客戶端該用戶上線了
        sendMessage("一個用戶上線了!當前在線人數:" + allOut.length);
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
            //將客戶端發送的信息回覆給所有客戶端
            sendMessage(line);
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        for (int i = 0; i < allOut.length; i++) {
            if (allOut[i] == pw) {
                allOut[i] = allOut[allOut.length - 1];)
                allOut = Arrays.copyOf(allOut, allOut.length - 1);
                break;
            }
        }
        //廣播通知所有客戶端用戶下線了
        sendMessage("一個用戶下線了,當前在線人數:" + allOut.length);
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
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
