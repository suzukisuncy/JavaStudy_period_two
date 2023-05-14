# 線程

## 1 概念

- 線程是進程中的單個順序執行流，是一條執行路徑。一個進程如果只有一條執行路徑，則成爲單線程程序；而如果有多條執行路徑，則成爲多線程程序。 

### 1.1 CPU分時調度

<img src="https://gitee.com/paida-spitting-star/image/raw/master/image-20230507142542146.png" alt="image-20230507142542146" style="zoom:50%;" />

- 時間片，即CPU分配給各個程序的時間，每個進程被分配一個時間段，稱作它的**時間片**。即該進程允許運行的時間，使各個程序從表面上看是同時進行的。如果在時間片結束時進程還在運行，則CPU將被剝奪並分配給另一個進程，將當前進程掛起。如果進程在時間片結束前阻塞或結束，則CPU當即進行切換，而不會造成CPU資源浪費。當又切換到之前執行的進程，把現場恢復，繼續執行。
- 在宏觀上：我們可以同時打開多個應用程序，每個程序並行，同時運行。
- 在微觀上：由於只有一個CPU，一次只能處理程序要求的一部分，如何處理公平，一種方法就是引入時間片，每個程序輪流執行。多核提高了併發能力。

###  1.2 單核CPU和多核CPU(瞭解): 

- 單核CPU同時只能幹一件事情，當啓動多個程序時，CPU快速切換輪流處理每個程序，但如果CPU不夠強勁，同時排隊等待處理的任務太多，就會感覺系統會有延時、反應慢、卡頓等，甚至某一個程序在處理時出現錯誤，無法響應了，會造成後面排隊的其他任務只能等待。

- 多核CPU是在基板上集成多個單核CPU+任務分配系統，兩個核心同時處理兩份任務，相比單核執行速度更快，有利於同時運行多個程序，不容易造成卡頓，更流暢！

### 1.3 Java程序的運行過程:

- 通過idea(java命令)運行一個Java程序，java命令會啓動JVM(java虛擬機)，等於啓動了一個應用程序，也就是啓動了一個進程。

- 該進程會自動啓動一個“主線程”，然後主線程去調用某個類的 main 方法，所以 main 方法運行在主線程中。在此之前的所有程序代碼都是按照順序依次調用的，都是單線程的。

- 如果希望程序中實現多段程序代碼交替執行的效果，則需要創建多線程程序。

## 2 爲什麼要使用多線程?

- 單線程程序執行時都只有一條順序執行流，如果程序執行某行代碼時遇到了阻塞(比如:拋異常)，那麼程序的運行將會停滯在這一行，其他代碼將會無法執行！
- 這就像去銀行辦理業務，只有1個業務窗口(單線程)，所有的客戶都需要在一個窗口排隊辦理業務，如果業務員在爲某一個客戶辦理業務時，花費了很長時間，那麼將會導致後面的客戶等待很長時間，這樣處理業務的效率也是非常低的。
- 但如果銀行爲了提高效率，同時開放了5個窗口(多線程)，客戶可以分佈在這5個窗口分別辦理業務，即使某一個窗口在爲個別客戶辦理業務時花費了很長時間，但不影響其他窗口辦理業務的進度。
- 多線程理解起來其實非常簡單: 
  - 單線程的程序只有一個順序執行流。
  - 多線程的程序則可以包括多個順序執行流，多個順序流之間互不干擾。

### 2.1 [並行]和[併發]

- 並行執行指在同一時刻，有多條指令在多個處理器上同時執行；
- 併發執行指在同一時刻只能有一條指令執行，但多個進程指令被快速輪換執行，使得在宏觀上具有多個進程同時執行的效果,在微觀角度而言,多個進程被分成多個片段依次隨機執行。

## 3 多線程的特性

### 3.1 隨機性

- 多線程的程序在執行時，在某一時間點具體執行哪個線程是不確定的，可以確定的是某一個時間點只有一個線程在執行(單核CPU)。

- 雖然我們感覺這些線程像是在同時運行，實際上是因爲CPU在快速切換輪流執行這些線程，由於切換速度是納秒級別的，所以我們感覺不到。

## 4 如何實現多線程

- 由於線程是依賴進程存在的，因此首先需要創建一個進程，但進程是操作系統創建的，而Java程序是不能直接調用系統功能的。但Java可以去調用C或C++寫好的程序去創建進程從而實現多線程程序。

- 在Java中要想實現多線程操作有兩種方式，一種是繼承Thread類，另一種是實現Runnable接口。接下來針對這兩種創建多線程的方式分別進行講解。

### 4.1 繼承Thread類

#### 4.1.1 Thread類概述

- Thread類是在java.lang包中定義的類

- JavaSE規範中規定，一個類只要繼承了Thread類，此類就是多線程的子類。

- 在Thread子類中，必須重寫該類的run()方法，此方法爲線程的主體。

#### 4.1.2 通過繼承Thread類創建線程

- **代碼實現**

```java
package cn.tedu.thread;

/**
 * 多線程
 * 多線程可以併發執行多個任務
 * 線程的第一種創建方式:
 * 繼承Thread類,並重寫run方法,在run方法中定義需要併發執行的任務代碼
 */
public class ThreadDemo01 {
    public static void main(String[] args) {
        //④創建要使用的線程實例
        MyThread01 t1 = new MyThread01();
        MyThread02 t2 = new MyThread02();
        //⑤將要執行的線程啓動
        /*
         * Thread中提供的start方法,作用是將線程啓動,並且納入到線程調度器中,被統一管理,
         * 當線程被分配到CPU的時間片時,就會開始自動去執行各自線程中run方法定義的內容
         */
        t1.start();
        t2.start();
    }
}

/**
 * Thread是線程的父類
 * ①可以通過繼承Thread的方式,創建線程實例
 */
class MyThread01 extends Thread {
    //②重寫父類中的run方法 線程啓動後,會自動執行run方法
    @Override
    public void run() {
        //③run方法中則定義線程要執行的任務
        for (int i = 0; i < 1000; i++) {
            System.out.println("我是金剛葫蘆娃!");
        }
    }
}

class MyThread02 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("我是查水錶的!!");
        }
    }
}
```

- 從上面的運行結果可以看出，main方法(主線程)和run方法(子線程)中的兩個for循環中的輸出語句交替執行了，說明通過集成Thread類實現了多線程。(如果沒有測試出主線程和子線程交替執行的效果，可以多測試幾次!)

### 4.2 實現Runnable接口

#### 4.2.1 Runnable接口概述

- 通過繼承Thread類實現了多線程，但是這種方式有一定的局限性。因爲Java中只支持單繼承，一個類一旦繼承了某個父類就無法再繼承Thread類，例如貓類Cat繼承了動物類Animal，就無法通過繼承Thread類實現多線程。

- 爲了克服這種弊端，在Thread類中提供了兩個構造方法: 
  - public Thread（Runnable target）
  - public Thread（Runnable target，String name）

- 這兩個構造方法都可以接收Runnable的子類實例對象，這樣創建的線程將調用實現了Runnable接口類中的run()方法作爲運行代碼，而不需要調用Thread類的run()方法，所以就可以依靠Runnable接口的實現類啓動多線程。

#### 4.2.2 通過實現Runnable接口實現多線程

- **代碼實現**

```java
package cn.tedu.thread;

/**
 * 第二種創建線程的方式:
 * 實現Runnable接口單獨定義線程任務
 */
public class ThreadDemo02 {
    public static void main(String[] args) {
        //④先將要執行的線程任務實例化
        MyRunnable01 r1 = new MyRunnable01();
        MyRunnable02 r2 = new MyRunnable02();
        //⑤將線程的任務分配給線程實例
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        //⑥將線程啓動,會自動執行分配給其的任務
        t1.start();
        t2.start();
    }
}

/**
 * Runnable是線程任務類接口,不是線程本身
 * //①實現Runnable,創建線程任務子類
 */
class MyRunnable01 implements Runnable {
    //②強制要求必須重寫run方法,線程啓動後,會自動執行run方法的內容
    @Override
    public void run() {
        //③定義線程任務
        for (int i = 0; i < 1000; i++) {
            System.out.println("我是迪迦奧特曼!!!");
        }
    }
}

class MyRunnable02 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("我是小怪獸!!");
        }
    }
}
```

- 從上面的運行結果可以看出，main方法(主線程)和run方法(子線程)中的兩個for循環中的輸出語句交替執行了，說明實現Runnable接口同樣也實現了多線程。

### 4.3 簡化寫法

```java
package cn.tedu.thread;

/**
 * 使用匿名內部類簡化兩種線程的創建方式
 */
public class ThreadDemo03 {
    public static void main(String[] args) {
        //簡化直接繼承Thread重寫run方法的創建方式
        Thread t1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    System.out.println("我是毛利小五郎!");
                }
            }
        };
        //簡化實現Runnable重寫run方法的創建方式
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    System.out.println("我是齊天大聖!!");
                }
            }
        };
        Thread t2 = new Thread(r1);
        //通過Lambda表達式簡化實現Runnable重寫run方法的創建方式
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("我是武大郎!!");
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }
}
```

## 5 Thread的常用方法和總結

### 5.1 CurrentThreadDemo

```java
package cn.tedu.thread;

/**
 * Thread中提供了一個靜態的方法currentThread方法
 * 該方法可以返回運行這個方法的線程實例
 * java中所有的代碼都是依靠線程運行的,main方法也不例外,JVM啓動後,會自動創建一個線程,
 * 執行main方法,所以我們會將這條線程稱爲主線程,而這個線程的名字也叫"main"
 */
public class CurrentThreadDemo {
    public static void main(String[] args) {
        Thread t = Thread.currentThread();
        /*
         * Thread中提供了getName方法,用於獲取線程名
         */
        System.out.println("主線程:" + t.getName());
        Thread t2 = new Thread("無敵風火輪");
        System.out.println("t2線程:" + t2.getName());
        Thread t3 = new Thread(() -> {
            Thread tt = Thread.currentThread();
            System.out.println("t3線程:" + tt.getName());
        });
        t3.setName("瘋狂霸王雞");
        t3.start();
        Thread t4 = new Thread("美麗金針菇") {
            @Override
            public void run() {
                Thread tt = currentThread();
                System.out.println("t4線程:" + tt.getName());
            }
        };
        t4.start();
    }
}
```

### 5.2 PriorityDemo

```java
package cn.tedu.thread;

/**
 * 該案例學習線程的優先級
 * 線程的優先級分爲10級,分別對應整數1-10,其中1是最低優先級,10是最高優先級,所有線程如果不設置
 * 優先級,則默認優先級爲5
 */
public class PriorityDemo {
    public static void main(String[] args) {
        Thread min = new Thread() {
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    System.out.println("我是min");
                }
            }
        };
        Thread norm = new Thread() {
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    System.out.println("我是norm");
                }
            }
        };
        Thread max = new Thread() {
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    System.out.println("我是max");
                }
            }
        };
        min.setPriority(Thread.MIN_PRIORITY);//設置最低的優先級,就是1
        norm.setPriority(Thread.NORM_PRIORITY);//設置中度的優先級(不設置也是5)
        max.setPriority(Thread.MAX_PRIORITY);//設置最高的優先級,就是10
        min.start();
        norm.start();
        max.start();
    }
}
```

## 6 進程

### 6.1 什麼是進程

![image-20230509201709125](https://gitee.com/paida-spitting-star/image/raw/master/image-20230509201709125.png)

- 進程是操作系統中運行得到一個任務(一個應用程序運行在一個進程中).

- 進程(process)是一塊包含了某些資源的內存區域.操作同利用進程把它的工作劃分爲一些功能單元.

- 進程中所包含的一個或者多個執行單元稱爲線程(thread).進程擁有一個私有的虛擬地址空間,該空間僅能被他所包含的線程訪問

- 線程只能歸屬於一個進程並且他只能訪問該進程所擁有的資源.當操作系統創建一個進程後,該進程會自動申請一個主線程或者首要線程的線程

## 7 線程狀態

### 7.1 概述

![image-20230509192300788](https://gitee.com/paida-spitting-star/image/raw/master/image-20230509192300788.png)

<font color=green>①新建狀態(New)：當一個線程對象被創建後，線程就處於新建狀態。在新建狀態中的線程對象從嚴格意義上看還只是一個普通的對象，還不是一個獨立的線程，不會被線程調度程序調度。新建狀態是線程生命週期的第一個狀態。</font>

<font color=green>	例如: Thread t = new MyThread();</font>

<font color=#15aabf>②就緒狀態(Runnable)：處於新建狀態中的線程被調用start()方法就會進入就緒狀態。處於就緒狀態的線程，只是說明此線程已經做好了準備，隨時等待CPU調度執行，但並不是說執行了start()方法線程就會立即執行;另外，在等待/阻塞狀態中的線程，被解除等待和阻塞後將不直接進入運行狀態，而是首先進入就緒狀態。</font>

<font color=#228be6>③運行狀態(Running)：處於就緒狀態中的線程一旦被系統選中，使線程獲取了 CPU 時間，就會進入運行狀態。線程在運行狀態下隨時都可能被調度程序調度回就緒狀態。在運行狀態下還可以讓線程進入到等待/阻塞狀態。在通常的單核CPU中，在同一時刻只有一個線程處於運行狀態。在多核的CPU中，就可能兩個線程或更多的線程同時處於運行狀態，這也是多核CPU運行速度快的原因。注：就緒狀態是進入到運行狀態的唯一入口，也就是說，線程要想進入運行狀態執行，必須先處於就緒狀態中。</font>

<font color=#F28C28>④阻塞狀態(Blocked)：根據阻塞產生的原因不同，阻塞狀態又可以分爲三種:</font>

<font color=#F28C28>	1)等待阻塞：運行狀態中的線程執行wait()方法，使當前線程進入到等待阻塞狀態；</font>

<font color=#F28C28>	2)鎖阻塞：線程在獲取synchronized同步鎖失敗(因爲鎖被其它線程所佔用)，線程會進入同步阻塞狀態；</font>

<font color=#F28C28>	3)其他阻塞：通過調用線程的sleep(),suspend(), join(), 或發出了I/O請求時等，線程會進入到阻塞狀態。當sleep()睡眠結束、調用resume(),?join()等待的線程終止或者超時、或I/O處理完畢時，線程重新轉入就緒狀態。</font>

<font color=red>⑤死亡狀態(Dead)：當線程中的run方法執行結束後，或者程序發生異常終止運行後，線程會進入死亡狀態。處於死亡狀態的線程不能再使用 start 方法啓動線程。</font>

### 7.2 SleepDemo01

![image-20230509205105459](https://gitee.com/paida-spitting-star/image/raw/master/image-20230509205105459.png)

```java
package cn.tedu.thread;

/**
 * Thread中提供了一個靜態的sleep方法
 * 當線程調用sleep方法後,會進入阻塞狀態指定的毫秒,超過這個時間後,會自動進入到就緒狀態,當CPU分配時間片後,會繼續執行
 */
public class SleepDemo01 {
    public static void main(String[] args) {
        System.out.println("程序開始了!");
        try {
            //1秒=1000毫秒 讓線程進入睡眠阻塞5秒時間
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("程序結束了!");
    }
}
```

### 7.3 SleepDemo02

```java
package cn.tedu.thread;

import java.util.Scanner;

/**
 * 利用sleep寫一個倒計時的程序
 */
public class SleepDemo02 {
    public static void main(String[] args) {
        System.out.println("程序開始了!");
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("請輸入要倒計時的時間!");
            int time = scanner.nextInt();
            //變量.forr 生成逆向for循環
            for (int i = time; i > 0; i--) {
                System.out.println(i);
                //沒循環一次,睡眠阻塞1秒
                Thread.sleep(1000);
            }
            System.out.println("時間到!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("程序結束了!");
    }
}
```

### 7.4 SleepDemo03

![image-20230509210942241](https://gitee.com/paida-spitting-star/image/raw/master/image-20230509210942241.png)

```java
package cn.tedu.thread;

/**
 * sleep方法調用時,必須要處理中斷異常
 * 當一個線程調用sleep方法處於睡眠阻塞狀態的過程中,如果該線程的interrupt()方法被調用時,
 * 會立即中斷該睡眠阻塞,並拋出中斷異常
 */
public class SleepDemo03 {
    public static void main(String[] args) {
        Thread lin = new Thread() {
            public void run() {
                System.out.println("林:剛打掃完衛生,小憩一會~");
                try {
                    Thread.sleep(10000);
                    System.out.println("林:睡的真舒服啊~~");
                } catch (InterruptedException e) {
                    System.out.println("林:幹嘛呢!幹嘛呢!都破了相!!!");
                }
            }
        };
        Thread huang = new Thread() {
            public void run() {
                System.out.println("黃:大錘80!小錘40!開始砸牆!");
                //5.fori
                for (int i = 0; i < 5; i++) {
                    System.out.println("黃(((;꒪ꈊ꒪;))):80!");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("哐啷啷!");
                System.out.println("黃:大哥!搞定!");
                //強制喚醒lin線程
                lin.interrupt();
            }
        };
        lin.start();
        huang.start();
    }
}
```

### 7.5 DaemonThreadDemo

![image-20230509213717430](https://gitee.com/paida-spitting-star/image/raw/master/image-20230509213717430.png)

```java
package cn.tedu.thread;

/**
 * 守護線程
 * java將線程分爲兩類,用戶線程和守護線程,也稱爲前臺線程和後臺線程
 * 守護線程和用戶線程區別不大,守護線程就是用戶線程通過調用setDaemon(true)方法轉變而來,
 * 而用戶線程就是普通線程
 * 而兩者最主要的區別在於當一個java進程中所有的用戶線程都結束時,進程就會結束,此時會將所有的守護線程殺死
 */
public class DaemonThreadDemo {
    public static void main(String[] args) {
        Thread rose = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println("rose:Let me die!");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("rose:Ah, ah, ah, ah, ah, ah, ah....");
                System.out.println("噗通!咕嚕嚕嚕嚕");
            }
        };
        Thread jack = new Thread() {
            public void run() {
                while (true) {
                    System.out.println("jack:My darling,you jump!i jump!!");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        rose.start();
        //在jack線程啓動之前,先將其設置爲守護線程
        jack.setDaemon(true);
        jack.start();
        while (true);//程序會一直卡在此處,導致main永遠不會結束
    }
}
```

## 8 線程的同步和異步

![image-20230511195612768](https://gitee.com/paida-spitting-star/image/raw/master/tb.png)

### 8.1 JoinDemo

![image-20230511200611145](https://gitee.com/paida-spitting-star/image/raw/master/image-20230511200611145.png)

```java
package cn.tedu.thread;

/**
 * 線程提供的join方法可以協調線程進入同步運行狀態
 * 多線程本身就是併發運行的,所以本就是一種異步的狀態
 * 而異步運行就表示: 多條線程各自執行各自的
 * 而同步運行則表示: 多條線程在運行時存在了先後的順序
 */
public class JoinDemo {
    static boolean isFinish = false;//表示圖片默認是未下載完

    public static void main(String[] args) {
        Thread download = new Thread() {
            public void run() {
                System.out.println("down: 開始下載圖片...");
                for (int i = 1; i <= 100; i++) {
                    System.out.println("已下載" + i + "%");
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("down: 圖片下載完畢!!!");
                isFinish = true;//表示圖片此時已下載完畢
            }
        };
        Thread show = new Thread() {
            @Override
            public void run() {
                System.out.println("show: 開始顯示文字...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("show: 顯示文字完畢!!!");
                System.out.println("show: 開始顯示圖片...");
                //先等待下載線程運行結束後,再繼續執行
                try {
                    /*
                     * 是show線程進入到阻塞狀態,直到download執行完畢時,阻塞狀態結束
                     * 理解爲插隊,show線程讓download線程插隊
                     * join方法和sleep方法的區別:
                     * ①sleep方法,可以讓線程阻塞指定的時間
                     * ②join方法,可以讓線程阻塞,但是時間不確定,具體得看插隊的線程執行的時間
                     */
                    System.out.println("圖片此時沒下載完,等待下載ing...");
                    download.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //開始顯示圖片之前,判斷圖片下載狀態
                if (isFinish == false) {
                    throw new RuntimeException("圖片加載失敗!!");
                }
                System.out.println("show: 圖片顯示完畢!!!");
            }
        };
        download.start();
        show.start();
    }
}
```

## 9 同步鎖

![image-20230511204857970](https://gitee.com/paida-spitting-star/image/raw/master/image-20230511204857970.png)

### 9.1 同步方法

####  9.1.1 同步方法概述

- 除了可以將需要的代碼設置成同步代碼塊以外，也可以使用synchronized關鍵字將一個方法修飾成同步方法，它能實現和同步代碼塊同樣的功能。

- **語法格式**

```
權限修飾符 synchronized 返回值類型/void 方法名([參數1,...]){
  需要同步的代碼;
}
```

- 被synchronized修飾的方法在某一時刻只允許一個線程訪問，訪問該方法的其他線程都會發生阻塞，直到當前線程訪問完畢後，其他線程才有機會執行該方法。

- 需要注意的是，同步方法的鎖是當前調用該方法的對象，也就是this指向的對象。

#### 9.1.2 同步方法的使用

- 需要注意的是: 
  - 將有可能發生線程安全問題的方法使用synchronized修飾，同一時間只允許一個線程進入同步方法中
  - synchronized方法的鎖對象是當前調用該方法的對象，也就是this指向的對象。

- 如果當前方法是非靜態方法，this表示的是調用當前方法的對象

- 如果當前方法的靜態方法，this表示的是當前類。

- **示例1: SyncDemo1**

![image-20230511213109280](https://gitee.com/paida-spitting-star/image/raw/master/image-20230511213109280.png)

- 出現併發安全問題

![image-20230511213458052](https://gitee.com/paida-spitting-star/image/raw/master/image-20230511213458052.png)

- 解決方法,使用同步方法

![image-20230511214842686](https://gitee.com/paida-spitting-star/image/raw/master/image-20230511214842686.png)

```java
package cn.tedu.thread;

/**
 * 多線程併發安全問題
 * 當多個線程併發操作同一臨界資源時,由於線程的切換存在不可確定性,這就會導致線程的切換順序出現混亂,而產生各種的邏輯錯誤
 * 而臨界資源就是指操作資源的完整過程應該同一時刻只能由單線程執行
 */
public class SyncDemo01 {
    public static void main(String[] args) {
        Table table = new Table();
        Thread t1 = new Thread("白露") {
            @Override
            public void run() {
                while (true) {
                    int bean = table.getBean();
                    System.out.println(getName() + "搶一顆豆子,此時豆子數量爲:" + (bean - 1));
                }
            }
        };
        Thread t2 = new Thread("青雀") {
            @Override
            public void run() {
                while (true) {
                    int bean = table.getBean();
                    System.out.println(getName() + "搶一顆豆子,此時豆子數量爲:" + (bean - 1));
                }
            }
        };
        t1.start();
        t2.start();
    }
}

class Table {
    private int beans = 20;//桌子上有20顆豆子

    /*
     * 當一個方法使用關鍵字synchronized時,該方法稱爲"同步方法"
     * 同步: 指多個線程之間存在先後順序執行
     * 同步方法: 指多個線程調用該方法需要有先後順序
     * 多線程的併發安全問題通過讓線程排隊執行,可以有效解決該問題
     */
    public synchronized int getBean() {
        if (beans == 0) {
            throw new RuntimeException("桌子上已經沒有豆子了!!!");
        }
        //禮讓線程,主動讓出CPU分配給他的時間片
        Thread.yield();
        return beans--;
    }
}
```

### 9.2 同步代碼塊

#### 9.2.1 同步代碼塊概述

- 同步是指多個操作在同一個時間段內只能有一個線程進行，其他線程要等待此線程完成之後才可以繼續執行。
- Java爲線程的同步操作提供了synchronized關鍵字，使用該關鍵字修飾的代碼塊被稱爲同步代碼塊。

**語法格式**

```java
synchronized( 同步對象 ){
  需要同步的代碼;
}
```

- 注意: 在使用同步代碼塊時必須指定一個需要同步的對象，也稱爲鎖對象，這裏的鎖對象可以是任意對象。但多個線程必須使用同一個鎖對象。

#### 9.2.2 同步代碼塊的使用

- 需要注意的是: 
  - 將有可能發生線程安全問題的代碼包含在同步代碼塊中，同一時間只允許一個線程進入同步代碼塊
  - synchronized代碼塊中的鎖對象可以是任意對象，但必須只能是一個鎖。
  - 若使用this作爲鎖對象，需保證多個線程執行時，this指向的是同一個對象

- **代碼案例**

```java
package cn.tedu.thread;

/**
 * 同步塊的應用
 * 有效的縮小同步範圍,並可以在保證併發安全的情況下,儘可能的提高併發效率
 */
public class SyncDemo02 {
    public static void main(String[] args) {
        Shop shop = new Shop();
        Thread t1 = new Thread("繆鋮航") {
            @Override
            public void run() {
                shop.buy();
            }
        };
        Thread t2 = new Thread("薛宏舉") {
            @Override
            public void run() {
                shop.buy();
            }
        };
        t1.start();
        t2.start();
    }
}

class Shop {
    public void buy() {
        try {
            Thread t = Thread.currentThread();
            System.out.println(t.getName() + ": 正在挑衣服...");
            Thread.sleep(5000);
            /*
             * 同步塊在指定同步監視器對象時,可以是任何引用類型實例,
             * 只要保證多個執行該代碼片段的線程看到的這個對象是"同一個"即可
             * 此處使用this this代表當前實例化對象的引用,也就是調用buy方法的實例對象
             * t1線程中調用buy方法時,this指向的是shop實例,而t2線程也是shop實例,所以此處可以使用this
             */
            synchronized (this) {
                System.out.println(t.getName() + ": 正在試衣服...");
                Thread.sleep(5000);
            }
            System.out.println(t.getName() + ": 結賬離開!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

### 9.3 互斥鎖

![image-20230513201754584](https://gitee.com/paida-spitting-star/image/raw/master/image-20230513201754584.png)

```java
package cn.tedu.thread;

/**
 * 互斥鎖
 * 當使用多個synchronized關鍵字鎖定多個代碼片段,並且指定的鎖對象都是相同的,那麼這些代碼片段之間就是互斥的
 */
public class SyncDemo03 {
    public static void main(String[] args) {
        Person person = new Person();
        Thread t1 = new Thread() {
            @Override
            public void run() {
                person.eat();
            }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                person.breath();
            }
        };
        t1.start();
        t2.start();
    }
}

class Person {
    public synchronized void eat() {
        try {
            Thread t = Thread.currentThread();
            System.out.println(t.getName() + ": 正在吃飯...");
            Thread.sleep(5000);
            System.out.println(t.getName() + ": 吃飯完畢!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void breath() {
        try {
            Thread t = Thread.currentThread();
            System.out.println(t.getName() + ": 正在呼吸...");
            Thread.sleep(5000);
            System.out.println(t.getName() + ": 呼吸完畢!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

## 10 線程池

```java

```
