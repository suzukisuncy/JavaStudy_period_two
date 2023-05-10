#  IO

## 1 IO簡介

- In/Out: 相對於程序而言的輸入(讀取)和輸出(寫出)的過程。

  即: 通過java程序到磁盤讀取數據的過程, 我們稱爲In的過程, 也就是讀取(輸入)

- 將java程序中的數據寫入磁盤的過程, 我們稱爲Out的過程, 也就是寫出(輸出)

- JDK核心類庫中提供了IO流相關的類, 這些類都在<java.io>包下

## 2 流的概念

- 程序中數據的讀取和寫入, 可以想象成水流在管道中流動。
  - 流只能單方向流動
  - 輸入流用來讀取in
  - 輸出流用來寫出Out
  - 數據只能從頭到尾順序的讀取一次或寫出一次

![image-20230420210605045](https://gitee.com/paida-spitting-star/image/raw/master/image-20230420210605045.png)

## 3 節點流和處理流

- 按照流是否直接與特定的地方(如磁盤,內存,設備等)相連,分爲節點流和處理流兩類

### 3.1 節點流

- 可以從或向一個特定的地方(節點)讀寫數據

### 3.2 處理流

- 是對一個**已存在的流**的連接和封裝,通過所封裝的流的功能調用實現數據讀寫

### 3.3 處理流特點

- 處理流的構造方法總是要帶一個其他的流對象做參數,一個流對象經過其他流的多次包裝,成爲流的鏈接.

- 通常節點流也被稱之爲低級流.處理流也被稱之爲高級流或者過濾流

![無標題-2023-04-25-1707](https://gitee.com/paida-spitting-star/image/raw/master/%E6%97%A0%E6%A0%87%E9%A2%98-2023-04-25-1707.png)

## 4 節點流

### 4.1 OutputStream 

- 此抽象類是表示**`輸出字節流`**的所有類的超類。輸出流接受輸出字節並將這些字節發送到某個接收器。

### 4.2 FileOutputStream

- 直接插在文件上，直接寫出(輸出)文件數據

**創建對象：**

```java
FileOutputStream(String name) //創建一個向具有指定名稱的文件中寫入數據的輸出文件流。
FileOutputStream(File file) //創建一個向指定 File 對象表示的文件中寫入數據的文件輸出流。
FileOutputStream(File file, boolean append) //追加 創建一個向指定 File 對象表示的文件中寫入數據的文件輸出流。
```

<font color=red>注意: 以上構造方法中, 如果參數指向的文件以及父目錄都不存在, 將會拋出FileNotFoundException異常!如果參數指向的文件不存在, 但文件的所在目錄存在, 將不會拋異常, 程序會自動創建該文件!</font>

- **代碼案例**

```java
package cn.tedu.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 此案例學習FileOutputStream文件字節輸出流向指定文件寫出字節數據
 */
public class FOSDemo {
    public static void main(String[] args) throws IOException {
        //一、創建流對象
        //①第一種方式
        // 創建File實例,綁定目標文件
        //File file = new File("./demo/fos.txt");
        // 創建文件字節輸出流,並將file傳入到該實例中
        //FileOutputStream fos = new FileOutputStream(file);
        //②第二種方式
        // 創建文件字節流的同時,將目標文件路徑傳入該實例中
        //fos = new FileOutputStream("./demo/fos.txt");
        //③第三種方式
        // 創建文件字節流時,可以開啓追加模式(只需要添加第二個參數爲true,即可開啓追加模式)
        FileOutputStream fos = new FileOutputStream("./demo/fos.txt", true);
        //二、通過字節輸出流向指定的文件中輸出字節數據
        //①輸出單字節數據
        /*
         * void write(int b)
         * 參數類型是整數,表示一個字節
         * 該方法,每次調用,都會向綁定的文件中,寫出一個字節數據
         */
        fos.write(97);//a
        fos.write(98);//b
        fos.write(99);//c
        fos.write(100);//d
        fos.write(13);//回車符(將光標迴歸到行首)
        fos.write(10);//換行符(將光標移到下一行)
        //②一次性輸出多個字節數據(依賴字節數組)
        /*
         * byte[] getBytes()
         * 將字符串轉換爲字節數據,存儲到字節數組中
         * \r\n 表示回車符和換行符
         */
        fos.write("Hello JSDVN2302!\r\n".getBytes());
        //③輸出字節數組中的一部分字節數據
        //ABCDEFGH\r\n → FGH\r\n
        /*
         * void write(byte b[], int off, int len)
         * off: 表示要輸出的頭字節的下標,下標5的位置就是F
         * len: 表示從要輸出的頭字節之後的幾個字節數據
         */
        fos.write("ABCDEFGH\r\n".getBytes(), 5, 5);
        System.out.println("寫出完畢!!");
        //三、關閉流資源,否則會佔用資源
        fos.close();
    }
}
```

### 4.3 InputStream

- 此抽象類是表示字節輸入流的所有類的超類/抽象類。

### 4.4 FileInputStream

- 直接插在文件上，直接讀取文件數據。

- **創建對象**

```
FileInputStream(File file) 
通過打開一個到實際文件的連接來創建一個 FileInputStream，該文件通過文件系統中的 File 對象 file 指定。 
FileInputStream(String pathname) 
通過打開一個到實際文件的連接來創建一個 FileInputStream，該文件通過文件系統中的路徑名 name 指定。
```

- **代碼案例**

```java
package cn.tedu.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 此案例學習FileInputStream文件字節輸入流向指定文件讀取字節數據
 */
public class FISDemo {
    public static void main(String[] args) throws IOException {
        //創建文件字節輸入流並綁定文件
        FileInputStream fis = new FileInputStream("./demo/fos.txt");
        //讀取一個字節
        /*
         * int read()
         * 讀取1個字節,並將該字節轉換爲整數返回,如果讀取到了文件的末尾,則返回-1
         * 連續調用read方法時,會連續讀取指定文件中的字節數據
         */
//        int d = fis.read();
//        System.out.println((char) d);
//        System.out.println((char) fis.read());
//        System.out.println((char) fis.read());
//        System.out.println((char) fis.read());
//        System.out.println(fis.read());
//        System.out.println(fis.read());
        //使用循環,連續讀取文件中的字節數據
        int data;//封裝本次讀取的結果
        while ((data = fis.read()) != -1) {//說明不是結尾
            System.out.print((char) data);
        }
        //關閉流
        fis.close();
    }
}
```

### 4.5 複製文件

![image-20230422203046994](https://gitee.com/paida-spitting-star/image/raw/master/image-20230422203046994.png)

```java
package cn.tedu.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 通過此案例學習單字節複製文件
 */
public class CopyDemo01 {
    public static void main(String[] args) throws IOException {
        //創建文件字節輸入流,用於讀取目標文件字節數據
        FileInputStream fis = new FileInputStream("./demo/ZhenDe.jpg");
        //創建文件字節輸出流,用於寫出數據到複製文件
        FileOutputStream fos = new FileOutputStream("./demo/ZhenDe_01.jpg");
        int data;
        long start = System.currentTimeMillis();
        //循環讀取目標文件中的字節數據
        while ((data = fis.read()) != -1) {
            //將本次讀取的字節數據,寫出到複製文件中
            fos.write(data);
        }
        long end = System.currentTimeMillis();
        System.out.println("複製該圖片共耗時:" + (end - start) + "毫秒!");
        //釋放資源
        fis.close();
        fos.close();
    }
}
```

### 4.6 塊讀寫案例

![image-20230422213149799](https://gitee.com/paida-spitting-star/image/raw/master/image-20230422213149799.png)

```java
package cn.tedu.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 通過此案例學習多字節複製文件
 * 單字節複製文件,由於需要頻繁的內存和硬盤進行交互,導致整體效率極低,
 * 所以可以提高每次交互時讀寫的字節數量,減少實際交互的次數,進而提高讀寫效率,
 * 而一組字節一組字節的讀寫稱爲: 塊讀寫形式
 */
public class CopyDemo02 {
    public static void main(String[] args) throws IOException {
        //創建文件字節輸入流,用於讀取目標文件字節數據
        FileInputStream fis = new FileInputStream("./demo/ZhenDe.jpg");
        //創建文件字節輸出流,用於寫出數據到複製文件
        FileOutputStream fos = new FileOutputStream("./demo/ZhenDe_02.jpg");
        long start = System.currentTimeMillis();
        //使用塊讀的方式複製圖片
        //1kb=1024byte
        byte[] data = new byte[10 * 1024];//10kb的字節數組
        //每次讀取目標文件10kb的字節數據
        while (fis.read(data) != -1) {
            //每次讀取的字節數據,都會存儲到data中,所以直接將data寫出即可
            fos.write(data);
        }
        long end = System.currentTimeMillis();
        System.out.println("複製該圖片共耗時:" + (end - start) + "毫秒!");
        //釋放資源
        fis.close();
        fos.close();
    }
}
```

**塊讀複製文件偏大的問題解決**

![image-20230422214801109](https://gitee.com/paida-spitting-star/image/raw/master/image-20230422214801109.png)

```java
package cn.tedu.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 通過此案例學習多字節複製文件時,文件大小保持一致
 * 由於塊讀時,最後一次如果不滿足data數組的長度,則會導致最後一次寫入數據時,
 * 會將多餘的上次讀取的殘餘數據也一同寫入,導致複製的文件偏大
 */
public class CopyDemo03 {
    public static void main(String[] args) throws IOException {
        //創建文件字節輸入流,用於讀取目標文件字節數據
        FileInputStream fis = new FileInputStream("./demo/ZhenDe.jpg");
        FileOutputStream fos = new FileOutputStream("./demo/ZhenDe_03.jpg");
        long start = System.currentTimeMillis();
        byte[] data = new byte[10 * 1024];
        int len;//定義len記錄本次讀取的字節量
        while ((len = fis.read(data)) != -1) {
            //根據len,保證本次寫入的字節的個數爲實際讀取的個數
            fos.write(data, 0, len);
        }
        long end = System.currentTimeMillis();
        System.out.println("複製該圖片共耗時:" + (end - start) + "毫秒!");
        fis.close();
        fos.close();
    }
}
```

### 4.7 寫入字符串

```java
package cn.tedu.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 通過此案例向文件中寫入字符串
 */
public class WriteStringDemo {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("./demo/string.txt");
        String line = "鵝鵝鵝,曲頸向刀割,拔毛燒開水,鐵鍋燉大鵝!";
        /*
         * 在使用IO寫入中文時,最好指定字符集,防止亂碼
         * ASCII: 最早的編碼表之一,它包含了128個字符,包括英文,數字,標點符號和一些特殊字符,不包含中文
         * Unicode: 支持超過130000個字符,包括世界各地的語言和符號
         * UTF-8: 是Unicode的傳輸格式
         */
        fos.write(line.getBytes(StandardCharsets.UTF_8));
        fos.close();
    }
}
```

### 4.8 簡易筆記本

```java
package cn.tedu.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 此案例實現一個簡易的記事本
 */
public class TestNotes01 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("請開始輸入內容,單獨輸入exit退出!");
        FileOutputStream fos = new FileOutputStream("./demo/note.txt");
        while (true) {
            //獲取在控制檯輸入的字符串
            String line = scanner.nextLine();
            //判斷控制檯輸入的字符串是否是exit
            if ("exit".equals(line)) {
                //跳出當前循環,結束程序
                break;
            }
            //將控制檯輸入的內容寫入到文件中
            fos.write(line.getBytes(StandardCharsets.UTF_8));
        }
        fos.close();
    }
}
```

### 4.9 讀取字符串

```java
package cn.tedu.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * 此案例來學習從文件中讀取字符串
 */
public class ReadStringDemo {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("./demo/string.txt");
        /*
         * int available()
         * 預估本次使用輸入流讀取該文件的字節量,可以利用它表示文件長度
         */
        byte[] data = new byte[fis.available()];
        //一次性讀取data大小的內容(文件中所有的數據)
        fis.read(data);
        //利用String的構造方法將data數組按照指定的編碼表還原爲字符串
        String str = new String(data, StandardCharsets.UTF_8);
        System.out.println(str);
        fis.close();
    }
}
```

## 5 處理流

### 5.1 緩衝流

- BufferedOutputStream緩衝輸出流
- BufferedInputStream 緩衝輸入流

#### 5.1.1 複製文件

![無標題-2023-04-25-1707(2)](https://gitee.com/paida-spitting-star/image/raw/master/%E6%97%A0%E6%A0%87%E9%A2%98-2023-04-25-1707(2).png)

```java
package cn.tedu.io;

import java.io.*;

/**
 * 此案例使用緩衝流高效複製文件
 */
public class CopyDemo04 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("./demo/ZhenDe.jpg");
        //創建高級流,緩衝字節輸入流,綁定低級流
        BufferedInputStream bis = new BufferedInputStream(fis);
        FileOutputStream fos = new FileOutputStream("./demo/ZhenDe_04.jpg");
        //創建高級流,緩衝字節輸出流,綁定低級流
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        long start = System.currentTimeMillis();
        int data;
        //循環通過高級流單字節讀取數據
        while ((data = bis.read()) != -1) {
            //通過高級流寫出該字節數據
            bos.write(data);
        }
        long end = System.currentTimeMillis();
        System.out.println("複製該圖片共耗時:" + (end - start) + "毫秒!");
        //關閉資源(關閉高級流,會將所連的低級流也一同關閉)
        bis.close();
        bos.close();
    }
}
```

#### 5.1.2 flush

```java
package cn.tedu.io;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 此案例學習緩衝流寫出數據的緩衝區問題
 */
public class BOS_flushDemo {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("./demo/flush.txt");
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        String line = "唧唧復唧唧,木蘭開飛機,開的什麼機,波音747!";
        /*
         * 緩衝輸出流,內部聲明了一個8k的緩衝區,
         * 該緩衝區的特點,是如果讀取不滿8k的數據,不會將數據寫出
         */
        bos.write(line.getBytes(StandardCharsets.UTF_8));
        System.out.println("寫出完畢!");
        //故意不寫close
        /*
         * 會將緩衝區的數據強制寫出
         * close方法的內部會調用flush方法
         */
        bos.flush();
        //bos.close();
    }
}
```

### 5.2 對象流

#### 5.2.1 Person代碼 

```java
package cn.tedu.io;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 使用該類的實例,測試對象流的內容
 */
public class Person implements Serializable {
    //固定當前類的版本號爲42
    static final long serialVersionUID = 42L;
    private String name;
    private int age;
    private String gender;
    /*
     * transient可以將修飾的屬性在進行序列化時,忽略該屬性的值,
     * 當我們反序列化時,改屬性的值將不會被讀取,以此達到一個對象瘦身的目的,
     * 從而提高程序的響應速度,減少資源開銷
     */
    private transient String[] otherInfo;
    private double salary;
    //生成全參構造
    public Person(String name, int age, String gender, String[] otherInfo) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.otherInfo = otherInfo;
    }

    //生成get和set方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String[] getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String[] otherInfo) {
        this.otherInfo = otherInfo;
    }

    //生成toString方法
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", otherInfo=" + Arrays.toString(otherInfo) +
                '}';
    }
}
```

#### 5.2.2 OOSDemo案例

![image-20230425214429960](https://gitee.com/paida-spitting-star/image/raw/master/image-20230425214429960.png)

```java
package cn.tedu.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * 此案例學習對象的序列化流
 */
public class OOSDemo {
    public static void main(String[] args) throws IOException {
        String name = "薛宏舉";
        int age = 18;
        String gender = "男";
        String[] otherInfo = {"是一個帥鍋", "來自於天津", "愛好學習", "是廣大男性之友"};
        Person person = new Person(name, age, gender, otherInfo);
        System.out.println(person);
        //創建文件字節輸出流,綁定要輸出的文件
        FileOutputStream fos = new FileOutputStream("./demo/person.txt");
        //創建對象字節輸出流,綁定文件字節輸出流,將對象轉換爲字節數據,再將字節數據寫入到文件中
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        //將person對象交給對象字節輸出流
        //此處注意,寫出的對象類必須要實現Serializable接口,否則不能序列化
        oos.writeObject(person);
        System.out.println("寫出完畢!");
        //釋放資源
        oos.close();
    }
}
```

#### 5.2.3 OISDemo案例

```java
package cn.tedu.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * 通過此案例學習使用對象輸入流將指定文件中的對象還原爲對象
 */
public class OISDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("./demo/person.txt");
        //創建對象輸入流,綁定指定的文件字節輸入流,用於將該文件中讀取的字節還原爲對象
        ObjectInputStream ois = new ObjectInputStream(fis);
        /*
         * Object readObject()
         * 將文件中的字節數據還原爲對象,該對象由於程序不知道是什麼類型,所以返回的是Object
         *
         */
        Object p = ois.readObject();
        System.out.println(p);
        ois.close();
    }
}
```

### 5.2.4 版本號衝突

<img src="https://gitee.com/paida-spitting-star/image/raw/master/uid.png" alt="image-20230427200427921" />

##  6字節流和字符流

- 在Java中，根據處理的數據單位不同，分爲字節流和字符流。
  - 字節流: 一個字節(byte)一個字節的去讀取, 或者寫出
  - 字符流: 一個字符一個字符的去讀取, 或者寫出

### 6.1 字節流

- **字節流(stream)：**針對二進制文件(文本,圖片,音頻,視頻...等)     

- InputStream(包含input都是輸入流)
  - FileInputStream
  - BufferedInputStream
  - ObjectInputStream

- OutputStream(包含output都是輸出流)
  - FileOutputStream
  - BufferedOutputStream
  - ObjectOutputStream

### 6.2 字符流

- **字符流(Reader,Writer)：**針對文本文件，讀寫容易發生亂碼現象，在讀寫時最好指定編碼集爲utf-8
- Reader(Reader結尾的都是字符輸入流)
  - BufferedReader
  - InputStreamReader

- Writer(Writer結尾的都是字符輸出流)
  - BufferedWriter
  - OutputStreamWriter
  - PrintWriter

## 7 轉換流

![image-20230427203358355](https://gitee.com/paida-spitting-star/image/raw/master/image-20230427203358355.png)

### 7.1 OutputStreamWriter

![image-20230427204645876](https://gitee.com/paida-spitting-star/image/raw/master/image-20230427204645876.png)

```java
package cn.tedu.io;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 學習字符流時,必須要掌握轉換流的內容,原因:
 * 字節流和字符流不能直接相連,需要轉換流做協調,
 * 並且轉換流具備以下功能:
 * ①在流鏈接中,銜接其他的高級字符流和下面的字節流
 * ②負責將字符與對應的字節按照指定的字符集進行自動轉換方便讀寫
 */
public class OSWDemo {
    public static void main(String[] args) throws Exception {
        //低級的文件字節輸出流
        FileOutputStream fos = new FileOutputStream("./demo/osw.txt");
        //高級的輸出字符轉換流,指定編碼
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        //利用輸出字符流,自動將寫出的字符串按照編碼寫出
        osw.write("鵝鵝鵝");
        osw.write("曲頸向刀割");
        osw.write("拔毛燒開水");
        osw.write("鐵鍋燉大鵝");
        System.out.println("寫出完畢!");
        osw.close();
    }
}
```

### 7.2 InputStreamReader

![image-20230427210457988](https://gitee.com/paida-spitting-star/image/raw/master/image-20230427210457988.png)

```java
package cn.tedu.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * 使用輸入字符轉換流讀取文本內容
 */
public class ISRDemo {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("./demo/osw.txt");
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        int d;
        //使用字符流讀取內容時,是一個字符一個字符讀取,比如一次性可以將"鵝"這個字讀進來
        //而使用字節流讀取內容,是一個字節一個字節讀取,在UTF-8中,一個中文由三個字節組成,所以讀取三次才可以將中文"鵝讀取出來
        while ((d = isr.read()) != -1) {
            System.out.print((char) d);
        }
        isr.close();
    }
}
```

## 8 緩衝字符流

### 8.1 PrintWriter

- **代碼案例**: 連接文件時

![image-20230427212909951](https://gitee.com/paida-spitting-star/image/raw/master/image-20230427212909951.png)

```java
package cn.tedu.io;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * 緩衝字符流
 * PrintWriter是實際開發中使用的緩衝字符輸出流
 * 功能:
 * ①可以提高寫出字符的效率
 * ②可以按行寫出字符串
 * ③可以自動行刷新
 */
public class PWDemo {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("./demo/pw.txt");
        //按行寫出字符串
        pw.println("無竹令人俗,");
        pw.println("無肉使人瘦.");
        pw.println("不俗又不瘦,");
        pw.println("竹筍燜豬肉.");
        pw.println("出自--<蘇軾的竹筍燜豬肉>");
        pw.close();
    }
}
```

- **代碼案例**: 模擬連接的不是文件時

![image-20230427213447577](https://gitee.com/paida-spitting-star/image/raw/master/image-20230427213447577.png)

```java
package cn.tedu.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 利用PrintWriter實現建議的記事本,並且可以按行寫入
 */
public class TestNote02 {
    public static void main(String[] args) throws FileNotFoundException {
        //創建低級的文件字節輸出流 ①綁定文件 ②開啓追加模式
        FileOutputStream fos = new FileOutputStream("./demo/note_pw.txt", true);
        //創建高級的轉換輸出字符流 ①銜接字節流和字符流 ②將寫出的字符自動按照編碼集轉換爲字節數據
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        //創建高級的緩衝字符輸出流 ①提高塊寫文本數據的效率
        BufferedWriter bw = new BufferedWriter(osw);
        //創建高級的按行刷新字符流 ①按行插入字符串 ②開啓自動行刷新(寫一行字符串,回車之後,會自動調用flush方法)
        PrintWriter pw = new PrintWriter(bw, true);
        //完成簡易記事本的錄入
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine();
            if ("exit".equals(line)) {
                break;
            }
            //將控制檯錄入的字符串寫入到文件中
            pw.println(line);
        }
        //釋放流資源
        pw.close();
    }
}
```

## 9 總結

![17-二階段IO流總結](https://gitee.com/paida-spitting-star/image/raw/master/666.png)
