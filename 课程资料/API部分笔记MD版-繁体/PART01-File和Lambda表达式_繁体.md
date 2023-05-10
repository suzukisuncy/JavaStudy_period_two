# 一、File

## 1 File類概述

- File類的每一個實例可以表示硬盤(文件系統)中的一個文件或目錄(實際上表示的是一個抽象路徑)

- 使用File可以做到:
  1. 訪問其表示的文件或目錄的屬性信息,例如:名字,大小,修改時間等等
  2. 創建和刪除文件或目錄
  3. 訪問一個目錄中的子項

## 2 獲取文件及其屬性

```
length(): 返回文件的長度, 單位是字節數(如果File是目錄, 則返回0)
exists(): 判斷當前文件或目錄是否存在，存在則返回true
isFile(): 判斷當前file是否爲文件，是文件則返回true
isDirectory(): 判斷當前file是否爲目錄，是目錄返回true
getName(): 獲取當前File文件或目錄的名字
getParent(): 獲取當前File父目錄的路徑
getAbsolutePath(): 獲取當前File文件或目錄的完整路徑
```

- **代碼案例**

```java
package cn.tedu.file;

import java.io.File;

/**
 * 這個案例是學習File綁定指定目標文件
 */
public class FileDemo {
    public static void main(String[] args) {
        //訪問當前項目下的demo目錄中的demo.txt文件
        /*
         * 路徑一般分爲兩種,絕對路徑和相對路徑
         * ①絕對路徑: 從根目錄一直定位到目標文件的路徑 絕對路徑一般不用
         * 選中指定文件,右鍵→Copy Path→Absolute Path,複製絕對路徑
         * D:\Dates\IDEASPACE\JSDVN2302SE-TEACHER\demo\demo.txt
         * 在java中"\"(反斜槓)是特殊字符,是轉義字符
         * ②相對路徑 相對於特殊節點所在位置的路徑
         * 在idea中使用"./"表示當前項目的目錄
         * 在本項目中,"./"就表示"D:\Dates\IDEASPACE\JSDVN2302SE-TEACHER\"
         */
        File file = new File("./demo/demo.txt");
        //獲取文件名字(綁定的字符串的名字)
        //變量名.soutv 快速生成輸出語句
        String name = file.getName();
        System.out.println("name = " + name);
        //獲取文件的長度(字節) 一個因爲字母是1個字節 一個中文是3個字節
        long length = file.length();
        System.out.println("length = " + length+"個字節");
    }
}
```

## 3 創建文件

```
createNewFile(): 創建指定路徑和名稱的文件, 如果文件不存在, 則創建並返回true, 否則就不創建並返回false
```

- **代碼案例**

```java
package cn.tedu.file;

import java.io.File;
import java.io.IOException;

/**
 * 這個案例是學習使用File創建文件
 */
public class CreateNewFileDemo {
    public static void main(String[] args) throws IOException {
        //idea中,相對路徑中"./"可以不寫,會默認識別
        //File file = new File("demo/new.txt");
        File file = new File("./demo/new.txt");
        /*
         * boolean exists()
         * 判斷調用的File實例是否存在,存在返回true,不存在返回false
         * File可能是目錄也可能是文件
         */
        if (file.exists()) {
            System.out.println("文件已存在!");
        } else {
            //方法報紅線錯誤,按alt+enter(回車),然後直接再按回車
            file.createNewFile();//create 創建 new 新的 file 文件
            System.out.println("該文件創建完畢!!!");
        }
    }
}
```

## 4 刪除文件

```
delete(): 刪除文件或刪除空目錄, 刪除成功返回true(非空目錄刪除會失敗)
```

- **代碼案例**

```java
package cn.tedu.file;

import java.io.File;
import java.io.IOException;

/**
 * 這個案例是學習使用File刪除文件
 */
public class DeleteFileDemo {
    public static void main(String[] args) throws IOException {
        //idea中,相對路徑中"./"可以不寫,會默認識別
        File file = new File("./demo/new.txt");
        if (file.exists()) {
            file.delete(); //delete刪除
            System.out.println("文件刪除成功!!!");
        } else {
            System.out.println("文件不存在!!!不可刪除!!!");
        }
    }
}
```

## 5 創建目錄

```
mkdir(): 創建指定路徑和名稱的目錄, 如果目錄不存在, 則創建並返回true, 否則就不創建並返回false
mkdirs(): 創建指定路徑和名稱的多級目錄, 如果目錄不存在, 則創建並返回true, 否則就不創建並返回false
```

- **代碼案例**

```java
package cn.tedu.file;

import java.io.File;

/**
 * 此案例學習使用File創建目錄
 */
public class MKDirDemo {
    public static void main(String[] args) {
        //目錄是沒有後綴名
        File dir = new File("./demo/h/e/l/l/o");
        if (dir.exists()) {
            System.out.println("該目錄已存在!");
        } else {
            /*
             * mkdir() 創建目錄時要求該目錄所在的目錄必須存在,否則無法創建
             * mkdirs() 創建目錄時,會將不存在的目錄以一同創建出來(推薦)
             */
            dir.mkdirs(); //make 製作 dir目錄
            System.out.println("目錄製作完畢!!");
        }
    }
}
```

## 6 刪除目錄

```
delete(): 刪除文件或刪除空目錄, 刪除成功返回true(非空目錄刪除會失敗)
```

- **代碼案例**

```java
package cn.tedu.file;

import java.io.File;

/**
 * 此案例學習使用File刪除目錄
 */
public class DeleteDirDemo {
    public static void main(String[] args) {
        //目錄是沒有後綴名
        File dir = new File("./demo/h/e/l/l/o");
        if (dir.exists()) {
            /*
             * delete方法,只能刪除一個文件或者目錄
             * 原因是delete不能刪除非空目錄
             */
            dir.delete();
            System.out.println("刪除目錄成功!");
        } else {
            System.out.println("目錄不存在!!刪除失敗!!!");
        }
    }
}
```

## 7 獲取目錄中的子項

- **代碼案例**

```java
package cn.tedu.file;

import java.io.File;

/**
 * 此案例學習使用File獲取指定目錄下的一層子項
 */
public class ListFilesDemo {
    public static void main(String[] args) {
        File dir = new File("./demo");
        /*
         * boolean isDirectory()
         * 判斷當前調用的File是否表示的是一個目錄,是則返回true,不是返回false
         */
        if (dir.isDirectory()) {
            /*
             * File[] listFiles()
             * 將當前調用的File表示的目錄中的一層子項,各自實例化爲File,並存儲到File數組中
             */
            File[] subs = dir.listFiles();
            System.out.println("demo目錄下,有" + subs.length + "個子項!");
            //subs.fori 自動生成根據下標遍歷數組的代碼結構
            for (int i = 0; i < subs.length; i++) {
                System.out.println(subs[i].getName());
            }
        } else {
            System.out.println("是文件!!!!");
        }
    }
}
```

## 8 獲取目錄中的符合過濾條件子項

<img src="https://gitee.com/paida-spitting-star/image/raw/master/image-20230420201307142.png" alt="image-20230420201307142" style="zoom: 33%;" />

- **代碼案例**

```java
package cn.tedu.file;

import java.io.File;
import java.io.FileFilter;

/**
 * 通過該案例學習通過File的listFiles方法獲取符合過濾條件的目錄中的所有子項
 * 獲取一個目錄中的符合過濾條件的子項
 * 重載的lsitFiles方法
 * File[] listFiles(FileFilter filter)
 * 該方法要求傳入一個文件過濾器(FileFilter),然後該方法會自動根據文件過濾器的要求,將符合條件的子項返回
 */
public class ListFilesDemo2 {
    public static void main(String[] args) {
        //指定當前項目的目錄,只需要寫一個"."就可以
        File dir = new File(".");
        //創建文件過濾器實例,定義過濾規則(一般會使用匿名內部類創建,並且重寫accept方法)
        FileFilter fileFilter = new FileFilter() {
            /**
             * accept方法就是用於定義過濾規則
             * @param file 默認的,表示要過濾的一個文件
             * @return 布爾值, 如果返回true, 表示當前過濾的文件符合條件, 反之則不符合
             */
            @Override
            public boolean accept(File file) {
                //定義過濾規則(要求獲取子項名字中包含"o")
                //獲取過濾的文件的名字
                String fileName = file.getName();
                //contains判斷是否包含,包含就返回true,不包含返回false
                return fileName.contains("o");
            }
        };
        //將文件過濾器傳入到listFiles方法中
        File[] subs = dir.listFiles(fileFilter);
        for (int i = 0; i < subs.length; i++) {
            System.out.println(subs[i].getName());
        }
    }
}
```

## 9 遞歸遍歷目錄

遞歸（recursion）是一種常見的解決問題的方法，即把問題逐漸簡單化。

遞歸的基本思想就是“自己調用自己”，一個使用遞歸技術的方法將會直接或者間接的調用自己。

```java
 m();
 ...
 public void m(){
   ...
   m();
   ...
 }
```

需要注意的是: 遞歸方法一定要有出口, 否則將會一直自己調用自己, 變成死循環, 嚴重時將會導致內存溢出!

### 9.1 需求:

- 遍歷指定File(目錄)下的所有子目錄和子文件, 輸出該目錄下的所有目錄和文件名

```
思路: 聲明一個diGui目錄的方法, 接收一個File類型的對象, 方法內部實現如下: 
1.判斷當前File是否爲文件(防止第一次傳入的是文件)
  1.1.如果file是文件, 輸出: "文件不支持遞歸!"
  1.2.如果file是目錄, 則繼續執行第2步
2.獲取當前目錄下的所有子目錄及子文件對象組成的File數組
3.遍歷當前目錄下的所有子目錄及子文件對象
4.判斷當前遍歷的是目錄還是文件
  4.1.如果當前遍歷的是文件, 輸出該文件的路徑+名稱
  4.2.如果當前遍歷的是目錄, 輸出當前目錄的路徑+名稱
  並以此目錄作爲根, 接着遍歷該目錄下的所有子目錄和子文件, 輸出該目錄下的所有目錄和文件名
```

### 9.2 統計一個目錄下所有文件的大小之和

- **代碼案例**

```java
package cn.tedu.file;

import java.io.File;

/**
 * 此案例使用遞歸(recursion),藉助listFiles實現獲取指定目錄中的所有子項
 */
public class ListDirDemo {
    public static void main(String[] args) {
        File dir = new File("./demo");
        //調用recursionDir,遞歸遍歷上面的目錄
        recursionDir(dir);
    }

    /**
     * 遞歸遍歷指定的目錄
     *
     * @param dir 要遍歷的目錄
     */
    private static void recursionDir(File dir) {
        //1.判斷當前File是否爲文件(防止第一次傳入的是文件)
        /*
         * boolean isFile()
         * 判斷當前調用者是否是一個文件,是則返回true,不是返回false
         */
        if (dir.isFile()) {
            //1.1.如果file是文件, 輸出: "文件不支持遞歸!"
            System.out.println("文件是不支持遞歸的!!!");
            //return在此處直接結束當前方法
            return;
        } else {
            //1.2.如果file是目錄, 則繼續執行第2步
            //2.獲取當前目錄下的所有子目錄及子文件對象組成的File數組
            File[] subs = dir.listFiles();
            //3.遍歷當前目錄下的所有子目錄及子文件對象
            for (int i = 0; i < subs.length; i++) {
                //4.判斷當前遍歷的是目錄還是文件
                if (subs[i].isFile()) {
                    //4.1.如果當前遍歷的是文件, 輸出該文件的路徑+名稱
                    System.out.println("文件:" + subs[i]);
                } else {
                    //4.2.如果當前遍歷的是目錄, 輸出當前目錄的路徑+名稱
                    System.out.println("目錄:" + subs[i]);
                    //並以此目錄爲根,再次執行recursionDir方法的邏輯,遍歷該目錄的子目錄和子文件
                    recursionDir(subs[i]);
                }
            }
        }
    }
}
```

### 9.3 刪除一個目錄及其中所有子項

- **代碼案例**

```java
package cn.tedu.file;

import java.io.File;

/**
 * 此案例使用遞歸(recursion),刪除指定目錄及其中所有子項
 */
public class DeleteDirDGDemo {
    public static void main(String[] args) {
        File dir = new File("./demo");
        recursionDeleteDir(dir);
    }

    /**
     * 遞歸遍歷刪除指定的目錄
     *
     * @param dir 要遍歷刪除的目錄
     */
    private static void recursionDeleteDir(File dir) {
        //1.判斷當前File是否爲文件(防止第一次傳入的是文件)
        /*
         * boolean isFile()
         * 判斷當前調用者是否是一個文件,是則返回true,不是返回false
         */
        if (dir.isFile()) {
            //1.1.如果file是文件, 輸出: "文件不支持遞歸!"
            System.out.println("文件是不支持遞歸的!!!");
            //return在此處直接結束當前方法
            return;
        } else {
            //1.2.如果file是目錄, 則繼續執行第2步
            //2.獲取當前目錄下的所有子目錄及子文件對象組成的File數組
            File[] subs = dir.listFiles();
            //3.遍歷當前目錄下的所有子目錄及子文件對象
            for (int i = 0; i < subs.length; i++) {
                //4.判斷當前遍歷的是目錄還是文件
                if (subs[i].isFile()) {
                    //4.1.如果當前遍歷的是文件, 則刪除並輸出該文件的路徑+名稱
                    System.out.println("文件:" + subs[i]);
                    subs[i].delete();
                } else {
                    //4.2.並以此目錄爲根,再次執行recursionDir方法的邏輯,遍歷該目錄的子目錄和子文件
                    recursionDeleteDir(subs[i]);
                }
            }
            //5.刪除dir目錄
            dir.delete();
            System.out.println("成功刪除" + dir.getName() + "目錄!");
        }
    }
}
```

#  二、Lambda表達式

- JDK8之後,java支持了lambda表達式這個特性.

- lambda可以用更精簡的代碼創建匿名內部類.但是該匿名內部類實現的接口只能有一個抽象方法,否則無法使用!

- lambda表達式是編譯器認可的,最終會將其改爲內部類編譯到class文件中

- **代碼案例**

```java
package cn.tedu.lambda;

import java.io.File;
import java.io.FileFilter;

/**
 * 通過此案例學習Lambda表達式的使用
 * JDK8之後,java支持了lambda表達式這個特性.
 * lambda可以用更精簡的代碼創建匿名內部類.但是該匿名內部類實現的接口只能有一個抽象方法,否則無法使用!
 * 語法:
 * (參數列表) ->{
 * 方法體
 * }
 */
public class LambdaDemo {
    public static void main(String[] args) {
        //①不使用λ表達式的匿名內部類寫法
        FileFilter f1 = new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.getName().contains("o");
            }
        };
        //②使用λ表達式忽略接口名和方法名
        /*
         * 1) 將new FileFilter() {}刪除
         * 2) public boolean accept
         * 3) 在方法參數和方法體之間添加'->'
         */
        FileFilter f2 = (File file) -> {
            return file.getName().contains("o");
        };
        //③使用λ表達式忽略參數類型
        FileFilter f3 = (file) -> {
            return file.getName().contains("o");
        };
        //④如果要重寫的方法中只有一個形參時,那麼參數的'()'也可以省略
        FileFilter f4 = file -> {
            return file.getName().contains("o");
        };
        //⑤如果方法體中只有一句代碼,那麼可以將方法體的'{}'省略,如果代碼包含return,return也需要一同省略
        FileFilter f5 = file -> file.getName().contains("o");
        
        File dir = new File(".");
        File[] subs = dir.listFiles(file -> file.getName().contains("o"));
        for (int i = 0; i < subs.length; i++) {
            System.out.println(subs[i].getName());
        }
    }
}
```
