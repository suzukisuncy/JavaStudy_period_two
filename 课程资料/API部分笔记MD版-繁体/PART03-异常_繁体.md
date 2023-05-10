# 異常處理

## 1 概述

- **異常:** 就是指程序出現了不正常的情況
- 用來封裝錯誤信息的對象。
- 組成結構：**類型**，**提示**，**行號**。

## 2 異常的繼承結構

## ![異常的繼承結構](https://gitee.com/paida-spitting-star/image/raw/master/%E5%BC%82%E5%B8%B8%E7%9A%84%E7%BB%A7%E6%89%BF%E7%BB%93%E6%9E%84.png)    

- Java中所有錯誤和異常的頂級父類是Throwable類

- Throwable類下有兩個子類, 分別是Error和Exception, 兩者的區別是:

  - Error: 是指不需要捕獲的嚴重問題, 通常是java程序以外的問題, 比如硬件問題或者內存不足導致的問題等.因此, 如果java程序中出現了Error, 我們無需處理。

  - Exception: 稱爲異常類, 它表示程序本身可以處理的問題

- Exception下有很多異常子類, 其中有一個異常子類是RuntimeException類, 這裏還可以將異常分爲兩大類:

  - 編譯時異常: 

    其他異常類以及不是RuntimeException子類的異常類都是檢查異常(也叫編譯時異常),在編寫完程序後,Java編譯器會對其進行檢查, 如果檢查出此類異常, 就必須要顯式處理, 否則程序將無法進行編譯。例如:ClassNotFoundException、FileNotFoundException、SQLException等都是編譯時異常。

  - 運行時異常: 
  
    RuntimeException以及子類被稱爲未經檢查的異常(也叫運行時異常),這類異常通常在編寫完程序後沒有問題, 但是運行程序才出現異常, 需要我們回來修改代碼進行解決的異常,這類異常無需顯式處理, 當然也可以像編譯時異常一樣處理,例如:IndexOutOfBoundsException、ArithmeticException、NullPointerException、ClassCastException 等都是運行時異常。判斷一個異常是不是運行時異常, 可以通過檢查這個異常類是不是RuntimeException的子類, 或者檢查這個異常是否只有在程序運行時才會出現!

## 3 虛擬機的默認處理方式

- 如果程序在運行時出現了問題，而我們又沒有處理該問題，最終虛擬機會做默認的處理，而這種默認處理方式爲:
  - 將異常的名稱(類型)、異常的原因以及異常出現的位置等信息輸出在了控制檯（Console窗口）
  - 將程序停止運行（這意味着，出現異常的代碼後面的代碼將不會再執行）

### 3.1 異常示例1

```java
package exception;

/**
 * 異常示例
 */
public class ExceptionDemo {
    public static void main(String[] args) {
        //沒有報錯,表示沒有編譯期異常
        //執行代碼,報錯了,說明有運行時異常
        int i = 1/0;
        System.out.println("hahaha");
    }
}
```

**運行結果:**

```java
Exception in thread "main" java.lang.ArithmeticException: / by zero
	at exception.ExceptionDemo.main(ExceptionDemo.java:10)
```

### 3.2 異常示例2

```java
package exception;

/**
 * 異常示例
 */
public class ExceptionDemo2 {
    public static void main(String[] args) {
        //定義一個數組
        int[] arr = {1,2,3,4,5,6};
        System.out.println(arr[0]);
        System.out.println(arr[1]);
        System.out.println(arr[2]);
        System.out.println(arr[3]);
        System.out.println(arr[4]);
        System.out.println(arr[5]);
        System.out.println(arr[6]);

    }
}
```

**運行結果:**

```java
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 6
	at exception.ExceptionDemo2.main(ExceptionDemo2.java:16)
```

- 如果程序出現了異常, 需要我們自己處理, 有兩種方案: 
  - 使用try...catch...進行處理(捕獲異常)
  - 使用throws進行處理(拋出異常)

## 4 異常處理之try...catch...處理

### 4.1 語法格式

```
try{
  可能出現異常的代碼;
}catch(異常類型 變量名){
  異常處理代碼; 
  //當try中的代碼出現了異常並且這個異常能和catch中的異常類型匹配上, 才會執行catch
  //反之, 如果出現的異常和catch中的異常類型不匹配, 就不會執行catch
}
```

**執行流程**

1. 程序執行到try{}中的代碼時, 如果出現了異常, 將會自動產生一個異常對象, 該異常對象將會被提交給java運行時系統;

2. 當Java運行時系統接收到異常對象時, 回到catch()中尋找匹配的異常類型, 找到後就進入catch{}中進行異常的處理;

3. 執行完畢後, 程序還可以繼續執行try...catch之後的代碼

### 4.2 代碼案例1

```java
package cn.tedu.exception;

/**
 * 通過此案例學習異常捕獲,瞭解基礎語法結構
 * try{
 * 可能出現異常的代碼;
 * }catch(異常類型 變量名){
 * 異常處理代碼;
 * //當try中的代碼出現了異常並且這個異常能和catch中的異常類型匹配上, 才會執行catch
 * //反之, 如果出現的異常和catch中的異常類型不匹配, 就不會執行catch
 * }
 */
public class TryCatchDemo01 {
    public static void main(String[] args) {
        System.out.println("程序開始了!");
        //①將可能發生異常的代碼放在try中
        try {
            String str = null;
            //在java中,null表示什麼也沒有,是不能調用方法的,所以虛擬機會提示空指針異常
            System.out.println(str.length());
        } catch (NullPointerException e) {//②通過catch關鍵字捕獲try中代碼出現的異常類型
            //③如果try中出現空指針異常,會捕獲,並執行該catch中的內容
            System.out.println("服務器忙,請稍後重試!!!");
            //④將捕獲的異常信息打印出來,一般是給程序員觀看解決問題的
            e.printStackTrace();
        }
        System.out.println("程序結束了!");
    }
}
```

### 4.3 代碼案例2

```java
package cn.tedu.exception;

/**
 * 通過此案例學習異常捕獲,異常可以捕獲多個
 */
public class TryCatchDemo02 {
    public static void main(String[] args) {
        System.out.println("程序開始了!");
        try {
            String str = "";
            System.out.println(str.length());
            //char charAt(int index) 獲取字符串中指定下標的字符
            System.out.println(str.charAt(0));
            //try中代碼一旦發生異常,異常發生位置之後的代碼不會被執行
            System.out.println("try中代碼執行結束了!");
        } catch (NullPointerException e) {
            System.out.println("出現空指針異常!!!");
        } catch (StringIndexOutOfBoundsException e) {//catch可以定義多個
            System.out.println("出現了字符串下標越界異常!!!");
        }
        System.out.println("程序結束了!");
    }
}
```

### 4.4 代碼案例3

```java
package cn.tedu.exception;

/**
 * 通過此案例學習異常捕獲,異常可以合併捕獲
 */
public class TryCatchDemo03 {
    public static void main(String[] args) {
        System.out.println("程序開始了!");
        try {
            String str = null;
            System.out.println(str.length());
            System.out.println(str.charAt(0));
            System.out.println("try中代碼執行結束了!");
        } catch (NullPointerException|StringIndexOutOfBoundsException e) {//合併捕獲
            System.out.println("出現空指針異常或者字符串下標越界異常!!!");
        }
        System.out.println("程序結束了!");
    }
}
```

### 4.5 代碼案例4

```java
package cn.tedu.exception;

/**
 * 通過此案例學習異常捕獲,異常可以進行捕獲父類異常進行兜底
 */
public class TryCatchDemo04 {
    public static void main(String[] args) {
        System.out.println("程序開始了!");
        try {
            String str = "a";
            System.out.println(str.length());
            System.out.println(str.charAt(0));
            //將字符串轉換爲整數,但是前提是字符串必須由數字組成
            System.out.println(Integer.parseInt(str));
            System.out.println("try中代碼執行結束了!");
        } catch (NullPointerException | StringIndexOutOfBoundsException e) {//合併捕獲
            System.out.println("出現空指針異常或者字符串下標越界異常!!!");
        } catch (Exception e) {//捕獲父類異常 工作中,一般情況下,依舊是出現一個異常寫一個catch,不建議直接捕獲Exception
            System.out.println("我也不知道出現什麼異常,但是就是出現了異常!!!");
        }
        System.out.println("程序結束了!");
    }
}
```

## 5 finally塊

- 作用就是確保一定要執行某些代碼

### 5.1 代碼案例

```java
package cn.tedu.exception;

/**
 * finally塊
 * finally是異常處理機制中的最後一塊,可以直接跟在try語句塊最後一個catch之後
 */
public class FinallyDemo {
    public static void main(String[] args) {
        System.out.println("程序開始了!");
        try {
            String str = "";
            System.out.println(str.length());
            //程序遇到return,直接跳出當前方法
            return;
        } catch (Exception e) {
            System.out.println("出現了一個錯誤");
        } finally {//一定會被執行
            System.out.println("必須要執行的內容~~");
        }
        System.out.println("程序結束了!");
    }
}
```

### 5.2 代碼案例2

```java
package cn.tedu.exception;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 通過Finally代碼塊釋放IO資源
 */
public class IOCloseDemo01 {
    public static void main(String[] args) {
        FileOutputStream fos = null;
        try {
            int a = 1 / 0;//模擬出問題
            fos = new FileOutputStream("demo/fos.txt");
            fos.write(1);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

### 5.3 代碼案例3

```java
package cn.tedu.exception;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 釋放IO流資源時,可以使用JDK7時推出的自動關閉特性
 */
public class IOCloseDemo02 {
    public static void main(String[] args) {
        //①在try關鍵字後,添加"()",將IO流對象的聲明寫入到這部分
        try (
                FileOutputStream fos = new FileOutputStream("./demo/fos.txt");
        ) {
            //②關於流的使用代碼則放在"{}"中
            fos.write(1);
            //③當try代碼塊結束,在"()"聲明的流會自動釋放資源
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

## 6 throw關鍵字

- 當程序發生錯誤而無法處理的時候,會**拋出**對應的異常對象
- 除此之外,在某些時刻,您可能會想要自行拋出異常,例如字異常處理結束後,再將異常拋出,讓下一層異常處理塊來捕捉,若想要自行拋出異常,您可以使用"throw"關鍵字,並生成執行的異常對象後拋出.
- 例如:throw new ArithmeticException();

### 6.1 Person

```java
package cn.tedu.exception;

public class Person {
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        //判斷錄入的年齡是否符合需求
        if (age < 0 || age > 100) {
            //此處可以拋異常
            throw new NullPointerException("您錄入的年齡不合法!!");
        }
        this.age = age;
    }
}
```

### 6.2 ThrowDemo

```java
package cn.tedu.exception;

/**
 * 模擬異常的拋出
 */
public class ThrowDemo {
    public static void main(String[] args) {
        Person p = new Person();
        //滿足語法,但是不滿業務需求
        p.setAge(10000);
        System.out.println("此人年齡:" + p.getAge() + "歲");
    }
}
```

## 7 異常處理之throws處理

- 程序中會聲明許多的方法,這些方法中可能會因某些錯誤而引發異常,但是不希望直接在這個方法中處理這些異常,而希望調用這個它的方法來統一處理,這時候可以使用throws關鍵字來聲明這個方法將會拋出的異常

### 7.1 語法格式

```
...方法名() throws 異常類名 {
  方法體
}
```

### 7.2 Person

```java
package cn.tedu.exception;

public class Person {
    private int age;

    public int getAge() {
        return age;
    }

    /*
     * 如果要對外拋出異常,我們會使用throw關鍵字進行異常的拋出,
     * 如果拋出的是運行時異常,那麼只能在運行過程中發現該異常
     * 而如果拋出的時編譯時異常,那麼必須要在方法的後面添加throws關鍵字
     * 告知調用者,在編譯期時,需要檢驗該異常,所以在寫代碼時,就必須要處理該異常
     */
    public void setAge(int age) throws Exception {
        //判斷錄入的年齡是否符合需求
        if (age < 0 || age > 100) {
            //此處主動拋異常(表示拋出異常的動作)
            throw new Exception("您錄入的年齡不合法!!");
        }
        this.age = age;
    }
}
```

### 7.3 ThrowDemo

```java
package cn.tedu.exception;

/**
 * 模擬異常的拋出
 */
public class ThrowDemo {
    public static void main(String[] args) {
        Person p = new Person();
        //滿足語法,但是不滿業務需求
        try {
            p.setAge(230);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("此人年齡:" + p.getAge() + "歲");
    }
}
```

## 8 自定義異常

### 8.1 IllegalAgeException

```java
package cn.tedu.exception;

/**
 * 自定義異常:非法的年齡異常
 * 自定義異常步驟:
 * ①類名要見名知意(一般是XxxException的格式,要求可以直觀的表明異常的類型)
 * ②需要該類直接或者間接繼承Exception類(表明當前類是異常子類)
 * ③提供父類中的所有的構造器
 */
public class IllegalAgeException extends Exception {
    public IllegalAgeException() {
    }

    public IllegalAgeException(String message) {
        super(message);
    }

    public IllegalAgeException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalAgeException(Throwable cause) {
        super(cause);
    }

    public IllegalAgeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
```

### 8.2 Person

```java
package cn.tedu.exception;

public class Person {
    private int age;

    public int getAge() {
        return age;
    }

    /*
     * 如果要對外拋出異常,我們會使用throw關鍵字進行異常的拋出,
     * 如果拋出的是運行時異常,那麼只能在運行過程中發現該異常
     * 而如果拋出的時編譯時異常,那麼必須要在方法的後面添加throws關鍵字
     * 告知調用者,在編譯期時,需要檢驗該異常,所以在寫代碼時,就必須要處理該異常
     */
    public void setAge(int age) throws IllegalAgeException {
        //判斷錄入的年齡是否符合需求
        if (age < 0 || age > 100) {
            //此處主動拋異常(表示拋出異常的動作)
            throw new IllegalAgeException("您錄入的年齡不合法!!");
        }
        this.age = age;
    }
}
```

### 8.3 ThrowDemo

```java
package cn.tedu.exception;

/**
 * 模擬異常的拋出
 */
public class ThrowDemo {
    public static void main(String[] args) {
        Person p = new Person();
        //滿足語法,但是不滿業務需求
        try {
            p.setAge(230);
        } catch (IllegalAgeException e) {
            e.printStackTrace();
        }
        System.out.println("此人年齡:" + p.getAge() + "歲");
    }
}
```

## 9 總結

**什麼時候需要try...catch異常, 什麼時候需要throws異常?**

1. 如果這個異常是方法內部的代碼造成的異常, 而不是因爲調用者的傳參導致的異常(也就是說這個異常和調用者沒有關係), 通常需要我們try...catch異常

2. 如果這個異常是調用者的傳參導致的異常, 則將異常throws拋出(就是將異常拋給調用者)

3. 不要在main方法上throws拋出異常, 因爲這樣會將異常拋給虛擬機, 而虛擬機是不會幫我們處理異常的!(虛擬機會按照默認方式處理:輸出異常信息以及終止程序執行!)
