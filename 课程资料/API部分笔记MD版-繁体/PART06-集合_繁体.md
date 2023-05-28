#  集合

## 1 Collection接口

### 1.1 概述

- 數組和集合都是Java中提供的容器

- 集合: 英文譯爲 Collection，用來存放對象的容器，集合中可以存放不同類型的對象，並且集合的長度可變。在編程時，常常需要集中存放多個數據，可以使用數組來保存多個對象，但數組長度不可變化，一旦在初始化數組時指定了數組長度，這個數組長度就是不可變的，如果需要保存數量變化的數據，數組就有點無能爲力了；爲了保存數量不確定的數據，以及保存具有映射關係的數據，Java提供了集合類。集合類主要負責保存、盛裝其他數據，因此集合類也被稱爲容器類。
- 集合和數組的對比:
  - 數組中的元素可以基本類型的值，也可以是對象; 而集合中只能保存對象
  - 數組一旦指定了長度，長度就不能再改變; 而集合的長度是可以隨時改變的
  - 往數組中插入元素非常麻煩,需要將插入位置後面的元素往後移動; 或者刪除數組中間位置的某一個元素, 需要將刪除位置後的元素往前移動; 而如果往集合中插入元素或者刪除集合中的某一個元素,直接使用現成的方法操作即可

### 1.2 集合的繼承結構

- 由於需求不同，Java就提供了不同的集合類。這多個集合類的數據結構不同，但是它們都是要提供存儲和遍歷功能的，我們把它們的共性不斷的向上提取，最終就形成了集合的繼承體系結構圖。

- Collection接口
  - List接口
    - ArrayList類
    - LinkedList類
  - Set接口
    - HashSet類
    - TreeSet類

- 解釋說明:
  - Collection集合是所有單值集合的頂層接口, 其中定義了常用的用於操作集合以及集合中元素的方法例如: 添加元素、刪除元素、獲取元素、獲取集合長度、判斷功能、將集合轉成數組、迭代器遍歷元素等功能
  - List是Collection的子接口，特點是其中的元素是有序的(即:元素存入集合時的順序和取出的順序一致)可以通過下標訪問List中的元素，另,List集合中的元素是可以重複的(包括null)
  - Set也是Collection的子接口，特點是其中的元素是無序(即:元素存入集合時的順序和取出的順序不一定一致)無法通過下標訪問Set中的元素，另外,Set集合中的元素是不可以重複的

- 學習集合的建議:
  - 學習接口中提供的共性方法
  - 通過實現類創建對象, 調用這些共性方法

### 1.3 常用方法

```java
boolean add(E e) //往集合中添加指定元素e
boolean addAll(Collection c) //將小集合添加到大集合中
boolean isEmpty() //如果集合中沒有任何元素(空集合), 返回true
boolean contains(Object o) //如果此集合中包含指定元素o, 則返回true
boolean containsAll(Collection c) //如果此集合中包含指定 集合c 中的所有元素，則返回 true。 
int size() 返回集合的大小(元素個數)
boolean remove(Object o) //從集合中刪除指定的元素o, 刪除成功則返回true
boolean removeAll(Collection c) //刪除此集合中那些也包含在指定集合c中的所有元素
boolean retainAll(Collection c) //僅保留此集合中那些也包含在指定集合c中的元素 
c1.retainAll(c2) //只保留c1中兩個共同的元素 "a","b", 對c2沒有影響
void clear() //刪除此集合中的所有元素
Object[] toArray() //將此集合轉成對象數組
boolean equals(Object o) //比較此 collection 與指定對象是否相等。 
Iterator<E> iterator() //返回此集合中所有元素組成的迭代器。
```

### 1.4 CollectionDemo1

```java
package cn.tedu.collection;

import java.util.ArrayList;
import java.util.Collection;

/**
 * JAVA集合類
 * 集合和數組一樣,都是用於保存一組數據,但是集合將元素的操作都封裝成了方法,操作更加簡便
 * 並且集合提供了很多不同的實現類供我們使用
 * <p>
 * java.util.Collection是所有集合的頂級接口,裏面定義了所有集合都必須具備的功能方法
 * 集合的兩類常用子類:
 * ①java.util.List: 可以重複的集合,且有序,通常我們稱爲叫做線性表
 * ②java.util.Set: 不可以重複的集合
 * 上述兩個集合都是接口,而元素是否重複取決於equals方法
 */
public class CollectionDemo01 {
    public static void main(String[] args) {
        Collection c = new ArrayList();
        /*
         * boolean add(E e);
         * 添加指定元素到集合中,添加成功返回true,否則返回false
         * 此處的參數E其實是泛型,先理解爲是Object類型
         * 集合存儲的元素必須要是引用類型
         * 並且一般使用時,集合中存儲的元素的類型都是一致的
         */
        c.add("one");
        c.add("two");
        c.add("three");
        c.add("four");
        c.add("five");
        System.out.println(c);
        c.add("six");
        c.add("seven");
        //1是基本類型,此處觸發自動裝箱特性 int→Integer
        c.add(1);
        c.add(true);
        c.add(new Object());
        System.out.println(c);
        /*
         * int size();
         * 返回當前集合中的元素個數
         * size要區別與數組的length
         * length表示的數組能存儲多少個元素(如果數組中的元素不夠長度那麼長,但是長度是不會變化的)
         * size表示集合中現在有幾個元素
         */
        System.out.println(c.size());
        /*
         * boolean isEmpty();
         * 判斷集合是否爲一個空集合,空集合等價於集合的size方法返回0
         */
        System.out.println("集合是否爲空集:" + c.isEmpty());
        /*
         * 清空集合,將集合中存儲的所有元素刪除
         */
        System.out.println("開始清空集合!");
        c.clear();
        System.out.println("集合是否爲空集:" + c.isEmpty());
        System.out.println(c.size());
    }
}
```

### 1.5 CollectionDemo2

```java
package cn.tedu.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class CollectionDemo02 {
    public static void main(String[] args) {
        //Collection c = new ArrayList();
        Collection c = new HashSet();//不可重複集合,底層也是通過equals方法判斷元素是否重複
        c.add(new Point(1, 2));
        c.add(new Point(3, 4));
        //ArrayList是可以存儲重複元素的
        c.add(new Point(3, 4));
        c.add(new Point(5, 6));
        c.add(new Point(7, 8));
        c.add(new Point(9, 0));
        /*
         * 集合重寫了toString方法
         * 格式:
         * [元素1.toString(),元素2.toString(),...]
         */
        System.out.println(c);
        Point p = new Point(3, 4);
        /*
         * boolean contains(Object o);
         * 判斷當前集合中是否包含給定的元素
         * 集合是否包含給定的元素取決於給定的元素和集合中的元素通過equals比較的結果是否爲true
         * Object中提供的equals方法,該方法作用是比較內存地址
         * 如果希望equals比較的是內容是否相同時,需要重寫equals方法
         * alt+insert→equals and hashcode→Next→Next→Finish
         */
        boolean contains = c.contains(p);
        System.out.println("集合c中是否包含給定的(3,4)點:" + contains);
        /*
         * boolean remove(Object o);
         * 如果集合中存在給定的元素,則刪除
         * 底層也是通過equals的比較結果來判斷是否存在
         * 會刪除最早出現的那一個
         */
        c.remove(p);
        System.out.println(c);
    }
}
```

#### 1.6 Point

```java
package cn.tedu.collection;

import java.util.Objects;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    //alt+insert→equals and hashcode→Next→Next→Finish
    @Override
    public boolean equals(Object o) {
        /*
         * p2.equals(p);
         * this: 指的調用當前方法的實例 就是p2
         * o: 指的是傳遞的參數 就是p
         */
        if (this == o) return true;
        /*
         * ①如果傳入的對象爲空,則沒有可比性,直接返回false
         * ②如果p2和p不是同一個類的實例,則直接返回false
         */
        if (o == null || getClass() != o.getClass()) return false;
        //由於需要比較熟悉,而參數是Object,需要向下轉換爲原類型
        Point point = (Point) o;
        //開始進行兩個對象的屬性值的比較 p2的x和p的x是否相同,p2的y和p的y是否相同
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
```

### 1.7 CollectionDemo3

```java
package cn.tedu.collection;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 集合間的操作
 */
public class CollectionDemo03 {
    public static void main(String[] args) {
        Collection c1 = new ArrayList();
        c1.add("大娃");
        c1.add("二娃");
        c1.add("三娃");
        System.out.println("c1 = " + c1);
        Collection c2 = new ArrayList();
        c2.add("四娃");
        c2.add("五娃");
        //c2.add("大娃");
        System.out.println("c2 = " + c2);
        /*
         * boolean addAll(Collection c);
         * 將給定集合中的所有元素都添加到當前集合中(取並集)
         * 操作後,如果當前集合發生了改變,則返回true
         */
        c1.addAll(c2);
        System.out.println("c1 = " + c1);
        System.out.println("c2 = " + c2);
        Collection c3 = new ArrayList();
        c3.add("二娃");
        c3.add("大娃");
        c3.add("七娃");
        System.out.println("c3 = " + c3);
        /*
         * boolean containsAll(Collection c);
         * 判斷當前集合中是否包含給定集合中的所有元素
         */
        boolean b = c1.containsAll(c3);
        System.out.println("c1集合是否包含c3集合:" + b);
        /*
         * boolean retainAll(Collection c);
         * 將當前集合中的元素保留和給定集合中的元素相同的部分
         */
        c1.retainAll(c3);
        System.out.println("c1 = " + c1);
        System.out.println("c3 = " + c3);
        c1.add("小明");
        /*
         * boolean removeAll(Collection c);
         * 將當前集合中和給定集合中共有的元素刪除
         */
        c1.removeAll(c3);
        System.out.println("c1 = " + c1);
        System.out.println("c3 = " + c3);
    }
}
```

## 2 集合的遍歷

> ![image-20230514163332841](https://gitee.com/paida-spitting-star/image/raw/master/image-20230514163332841.png)
>
> ①獲取該集合的迭代器
> ②迭代器在創建初始,默認位置在要遍歷的集合的第一個元素之前
> ③調用hasNext(),判斷當前迭代器所處位置是否有下一個元素
> ④如果有下一個元素,則調用next()來獲取當前迭代器所處位置的下一個元素,並且會將迭代器向後移動一個位置

```java
package cn.tedu.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 集合支持隨機訪問(根據指定的位置獲取對應的元素)
 * 但是Collection層面不支持隨機訪問 原因是Collection層面沒有下標概念
 * 所以Collection選擇採取其他的方式,就是迭代器遍歷
 * 而迭代器遍歷是父類中定義的,所以所有集合本身都是支持迭代器的
 */
public class IteratorDemo {
    public static void main(String[] args) {
        Collection c = new ArrayList();
        c.add("A");
        c.add("B");
        c.add("C");
        c.add("D");
        c.add("E");
        c.add("F");
        System.out.println("c = " + c);
        //①獲取迭代器
        Iterator it = c.iterator();
        //②判斷當前迭代器所處位置是否有下一個元素
        while (it.hasNext()) {
            //③如果有,則取出下一個元素,並且將迭代器的位置向後移動一個
            Object e = it.next();
            System.out.println(e);
            //④判斷遍歷的元素是否是D,如果是,則刪除
            if ("D".equals(e)) {
                //⑤迭代器中提供的刪除方法 remove()
                it.remove();
            }
        }
        System.out.println("c = " + c);
    }
}
```

## 3 增強型for循環

```java
package cn.tedu.collection;

import java.util.ArrayList;
import java.util.Collection;

/**
 * JDK5推出時,推出了一個新的特性: 增強型for循環
 * 通常也稱爲新循環
 * 新循環不能取代傳統for循環的工作,它的出現僅爲了使用相同的格式遍歷集合和數組
 * 語法:
 * for(元素類型 e : 集合或數組){
 * <p>
 * }
 */
public class NewForDemo {
    public static void main(String[] args) {
        String[] arr = {"A", "B", "C", "D", "E"};
        System.out.println("==========傳統for循環遍歷數組==========");
        //arr.fori
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println("\r\n==========增強for循環遍歷數組==========");
        //arr.for
        for (String s : arr) {
            System.out.print(s + "\t");
        }
        Collection<String> c = new ArrayList();
        c.add("1");
        c.add("2");
        c.add("3");
        c.add("4");
        c.add("5");
        System.out.println("\r\n==========增強for循環遍歷集合==========");
        //c.for
        for (String s : c) {
            System.out.println(s + "\t");
        }
    }
}
```

### 3.1 泛型

```java
package cn.tedu.collection;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 泛型是JDK1.5增加的一個新特性,泛型本質是參數化類型,也就是說,操作的數據類型可以被指定爲一個參數,
 * 增加泛型這個概念,主要是爲了讓集合能記住其元素的數據類型
 */
public class GenericsDemo {
    public static void main(String[] args) {
        //聲明一個集合,但是沒有指定泛型
        Collection c = new ArrayList();
        //集合在沒有泛型的約束下,可以裝任意的類型
        c.add("任意類型的元素");
        //聲明了一個集合,泛型是String類型
        Collection<String> c1 = new ArrayList<>();
        c1.add("hello");
        // c1.add(1);//編譯錯誤,add方法不能添加int類型
        Collection<Integer> c2 = new ArrayList();
        c2.add(1);
        // c2.add("hello"); //編譯錯誤,add方法不能添加String類型
        //在不指定泛型時,泛型其實就是Object類型
        Test test = new Test();
        test.setObj("你好");
        Object obj = test.getObj();
        //聲明泛型時,Test類中,所有T的部門都是String類型
        Test<String> test1 = new Test<>();
        test1.setObj("我不好");
        String obj1 = test1.getObj();
    }
}
//①在類名後使用泛型約束 T → Type E → Element K → key V → Value
class Test<T> {
    //②將類中需要參數控制的地方改成泛型
    private T obj;

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}
```

## 4 List集合

### 4.1 概述

- List是一個有序的Collection(List是Collection的子接口)，使用此接口能夠精確的控制每個元素插入的位置，能夠通過索引(類似於數組的下標)來訪問List中的元素，第一個元素的索引爲 0，而且允許有相同的元素。

- List 接口存儲一組可重複、有序（插入順序）的對象。

### 4.2 特點

- 元素有下標,可以通過下標訪問元素

- 元素是有序的(存入集合的順序和取出的順序一定相同)

- 元素可以重複(包括null)

### 4.3 List方法測試

#### 4.3.1 ListDemo

```java
package cn.tedu.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 學習List中定義的方法
 */
public class ListDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        System.out.println("list = " + list);
        //E get(int index) 取出指定下標位置的元素(下標起始是0)
        String str = list.get(2);
        System.out.println("str = " + str);
        //通過下標遍歷集合
        for (int i = 0; i < list.size(); i++) {
            str = list.get(i);
            System.out.println("集合中的第" + (i + 1) + "個元素: " + str);
        }
        /*
         * E set(int index, E element);
         * 將給定的元素element設置到指定的index位置上,並將替換的元素返回
         */
        str = list.set(2, "★");
        System.out.println("被替換的元素 = " + str);
        System.out.println("list = " + list);
        //翻轉集合 java.util.Collections 集合的工具類
        Collections.reverse(list);
        System.out.println("翻轉後的集合: " + list);
        ArrayList<String> list1 = new ArrayList<>();
        Collections.addAll(list1, "1","2","3","4","5");
        System.out.println("list1 = " + list1);
    }
}
```

#### 4.3.2 ListDemo2

```java
package cn.tedu.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * 給指定的下標位置添加元素或者刪除元素
 */
public class ListDemo02 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        System.out.println("list = " + list);
        //將元素添加到集合的最後面
        list.add("F");
        //將元素添加到指定的位置
        /*
         * void add(int index, E element);
         * 將給定元素element添加到指定的index位置,然後將原位置的元素整體後移
         */
        list.add(3, "★");
        System.out.println("list = " + list);
        String str = list.remove(3);
        System.out.println("刪除了: " + str);
        System.out.println("list = " + list);
    }
}
```

### 4.4 集合和數組的轉換

#### 4.4.1 集合轉換數組

```java
package cn.tedu.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * 集合轉換爲數組
 */
public class CollectionToArrayDemo {
    public static void main(String[] args) {
        Collection<String> c = new ArrayList<>();
        c.add("A");
        c.add("B");
        c.add("C");
        c.add("D");
        c.add("E");
        System.out.println("集合c = " + c);
        /*
         * Object[] toArray();
         * 將當前集合轉換爲一個Object類型的數組
         * 由於返回的是一個Object類型的數組,所以用的很少
         */
        Object[] array = c.toArray();
        /*
         * T[] toArray(T[] a);
         * 將當前集合轉換爲給定數組類型的數組
         * 其中參數聲明的數組有一些要注意的事項:
         * ①定義的數組類型,要和待轉換的集合的泛型最好一致
         * ②定義的數組長度,最好和待轉換的集合的存儲的元素個數一致
         */
        String[] array2 = c.toArray(new String[c.size()]);
        System.out.println("數組的長度:" + array2.length);
        System.out.println("數組array2 = " + Arrays.toString(array2));
    }
}
```

#### 4.4.2 數組轉換集合

```java
package cn.tedu.collection;

import java.util.Arrays;
import java.util.List;

/**
 * 數組轉換爲集合
 */
public class ArrayToListDemo {
    public static void main(String[] args) {
        String[] array = {"A", "B", "C", "D", "E"};
        System.out.println("數組array = " + Arrays.toString(array));
        List<String> list = Arrays.asList(array);
        System.out.println("集合list = " + list);
    }
}
```

### 4.5 集合的排序

#### 4.5.1 SortListDemo

```java
package cn.tedu.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 集合的排序
 * 集合的工具類Collections中提供了一個靜態的sort方法,可以對集合中的元素進行自然排序
 */
public class SortDemo01 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            list.add(random.nextInt(100));
        }
        System.out.println("亂序list = " + list);
        Collections.sort(list);//自然排序,從小到大
        System.out.println("自然排序list = " + list);
    }
}
```

#### 4.5.2 SortListDemo2

```java
package cn.tedu.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 集合的自定義排序
 */
public class SortDemo02 {
    public static void main(String[] args) {
        List<Point> list = new ArrayList<>();
        list.add(new Point(1, 4));
        list.add(new Point(3, 12));
        list.add(new Point(2, 5));
        list.add(new Point(0, 8));
        list.add(new Point(3, 3));
        list.add(new Point(9, 2));
        System.out.println("亂序: " + list);
        /*
         * Collections提供的sort方法,如果對集合進行排序,必要要求集合中的元素要實現Comparable接口,
         * 該接口中的compareTo方法定義排序規則
         * sort方法會自動將集合中的兩個元素進行比較,而比較時,會通過重寫的compareTo方法比較,
         * 格式
         * A.compareTo(B) A就是調用該方法的集合的參數 B就是和A比較的集合的參數
         * A 大於 B 返回正數
         * A 等於 B 返回0
         * A 小於 B 返回負數
         */
        Collections.sort(list);
        System.out.println("正序:" + list);
    }
}
```

#### 4.5.3 SortListDemo3

```java
package cn.tedu.collection;

import java.util.*;

/**
 * 集合的自定義排序
 */
public class SortDemo02 {
    public static void main(String[] args) {
        List<Point> list = new ArrayList<>();
        list.add(new Point(1, 4));
        list.add(new Point(3, 12));
        list.add(new Point(2, 5));
        list.add(new Point(0, 8));
        list.add(new Point(3, 3));
        list.add(new Point(9, 2));
        System.out.println("亂序: " + list);
        /*
         * 內部比較器
         * Collections提供的sort方法,如果對集合進行排序,必要要求集合中的元素要實現Comparable接口,
         * 該接口中的compareTo方法定義排序規則
         * sort方法會自動將集合中的兩個元素進行比較,而比較時,會通過重寫的compareTo方法比較,
         * 格式
         * A.compareTo(B) A就是調用該方法的集合的參數 B就是和A比較的集合的參數
         * A 大於 B 返回正數
         * A 等於 B 返回0
         * A 小於 B 返回負數
         * 但是上述的方式具有侵入性,爲了調用該API,反而要求我們去修改其他的代碼,導致代碼結構出現混亂,
         * 就造成了侵入性,侵入性不利於後期維護,儘可能的避免
         */
        //Collections.sort(list);
        /*
         * 外部比較器
         * 創建Comparator比較器實例,然後重寫compare方法,定義比較規則
         * 然後將比較器實例傳入到sort方法的第二個參數中,sort方法就會自動根據該比較器定義的規則,進行排序
         * 格式
         * int compare(Point o1, Point o2)
         * o1 大於 o2 返回正數
         * o1 等於 o2 返回0
         * o1 小於 o2 返回負數
         */
        Comparator<Point> com = new Comparator<Point>() {
            /**
             * 定義比較規則
             */
            @Override
            public int compare(Point o1, Point o2) {
                //比較y座標的大小,y座標越大,該點就越大
                int y1 = o1.getY();
                int y2 = o2.getY();
                return y1 - y2;
            }
        };
        Collections.sort(list, com);
        System.out.println("正序:" + list);
    }
}
```

#### 4.5.4 Point

```java
package cn.tedu.collection;

import java.util.Objects;

public class Point implements Comparable<Point> {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    //alt+insert→equals and hashcode→Next→Next→Finish
    @Override
    public boolean equals(Object o) {
        /*
         * p2.equals(p);
         * this: 指的調用當前方法的實例 就是p2
         * o: 指的是傳遞的參數 就是p
         */
        if (this == o) return true;
        /*
         * ①如果傳入的對象爲空,則沒有可比性,直接返回false
         * ②如果p2和p不是同一個類的實例,則直接返回false
         */
        if (o == null || getClass() != o.getClass()) return false;
        //由於需要比較熟悉,而參數是Object,需要向下轉換爲原類型
        Point point = (Point) o;
        //開始進行兩個對象的屬性值的比較 p2的x和p的x是否相同,p2的y和p的y是否相同
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * 定義排序規則
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Point o) {
        //定義比教兩個點的x座標
        int x1 = this.getX();
        int x2 = o.getX();
        // //判斷x1大於x2說明x1大
        // if (x1 - x2 > 0) {
        //     return 1;
        // } else if (x1 - x2 < 0) {//說明x2大
        //     return -1;
        // } else {//x1和x2相等
        //     return 0;
        // }
        return x1 - x2;
    }
}
```

## 5 Map接口

### 5.1 概述

- Map用於保存具有映射關係的數據，因此Map集合裏保存着兩組值，一組值用於保存Map裏的鍵(key)另外一組值用於保存Map裏的值(value)，鍵和值是一一對應的關係，稱爲映射。根據鍵就能找到對應的值，類似於生活中一張身份證對應一個人一樣。

- Map的key和value可以是任何引用類型的數據，其中key不允許重複，同一個Map對象的任何兩個key通過equals方法比較總是返回false。

### 5.2 特點

- Map集合中每個元素都有兩個值, 分別是key(鍵) 和 value(值)
- Map集合中的key(鍵)不允許重複, 在第二次添加已有的key時, value會被會覆蓋
- Map集合中的元素是無序的(即元素存入集合的順序和取出時的順序很可能不一致)
- Map集合中的key和value具有映射關係, 可以通過key(鍵)來獲取對應的value(值)
  - key和value之間存在單向一對一關係，即通過指定的key，總能找到唯一的、確定的value。從Map中取出數據時，只要給出指定的key，就可以取出對應的value。
  - 如果把Map裏的所有key放在一起來看，它們就組成了一個Set集合（所有的key沒有順序，key與key之間不能重複），實際上Map確實包含了一個keySet()方法，用於返回Map裏所有key組成的Set集合。
  - 如果把Map裏的所有value放在一起來看，它們又非常類似於一個List：元素與元素之間可以重複，每個元素可以根據索引來查找，只是Map中的索引(也就是key)不是從0開始的整數值，而是任意類型的對象；
  - 如果需要從List集合中取出元素，則需要提供該元素的數字索引；如果需要從Map中取出元素，則需要提供該元素的key。因此，Map有時也被稱爲字典，或關聯數組。

### 5.3 繼承結構

- Map接口
  - HashMap類
  - TreeMap類
  - Hashtable類

- 解釋說明:
  - Map集合是採用鍵-值對(key-value)的存儲方式, 鍵(key)、值(value)可以是引用類型的數據, key不允許重複, vaue可以重複, key和value是一對一的關係, 通過指定的key總能找到唯一的、確定的value值
  - HashMap 和 Hashtable 都是Map接口的實現類，它們之間的關係完全類似於ArrayList和Vector的關係 
    - HashMap是線程不安全的, 所以HashMap的性能要比HashTable高一些
    - HashMap可以使用null作爲key或value, Hashtable不允許使用null作爲key和value; 
    - Hashtable是一個古老的Map實現類，JDK 1.0出現，出現時，Java還沒有提供Map接口，命名沒有遵守Java的命名規範，與Vector類似的是，盡量少用Hashtable實現類，即使需要創建線程安全的Map實現類，也無須使用Hashtable實現類，可以通過別的方式來解決線程安全問題。
  - TreeMap是Map的子接口SortedMap的的實現類, 是可以支持對內部元素進行排序的類, 也正因爲如此, TreeMap的執行效率通常要比HashMap和HashTable慢。

### 5.4 MapDemo

```java
package cn.tedu.map;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * java.util.Map接口 查找表
 * Map體現的結構就是多行兩列的表格,左列稱爲"key",右列稱爲"value"
 * Map總是以key-value的形式保存一組數據,並且可以根據key獲取對應的value
 */
public class MapDemo01 {
    public static void main(String[] args) {
        //LinkedHashMap 記錄存儲的順序,更佔用內存
        //HashMap不會記錄存儲的順序
        Map<String, Integer> map = new HashMap<>();
        /*
         * V put(K key, V value);
         * 向當前map存儲一組鍵值對,
         * 如果存儲的鍵值對在map不存在,則返回null,
         * 如果存儲的鍵值對在map中已存在,則會覆蓋原先的鍵值對,並且將被覆蓋的value作爲返回值返回
         */
        map.put("語文", 90);
        map.put("數學", 99);
        map.put("物理", 80);
        map.put("化學", 85);
        System.out.println(map);
        //Map中的value是允許重複的
        Integer score = map.put("英語", 80);
        System.out.println(map);
        System.out.println(score);
        //Map中的key是不允許重複的
        score = map.put("數學", 100);
        System.out.println(score);
        System.out.println(map);
        /*
         * V get(Object key);
         * 根據給定的key,獲取對應的value
         * 如果給定的key不存在,則返回null
         */
        score = map.get("數學");//獲取數學成績,將數學的value返回
        System.out.println(score);
        score = map.get("體育");//獲取體育成績,體育不存在,返回null
        System.out.println(score);
        int size = map.size();
        System.out.println("map中包含" + size + "個元素!");
        /*
         * V remove(Object key);
         * 刪除給定的key的這組鍵值對,如果刪除成功,會將刪除的value返回,
         * 如果刪除的key不存在,則直接返回null
         */
        score = map.remove("數學");
        System.out.println(score);
        score = map.remove("體育");
        System.out.println(score);
        boolean k = map.containsKey("物理");
        if (k) {
            System.out.println("包含'物理'這個key");
        } else {
            System.out.println("不包含'物理'這個key");
        }
        boolean v = map.containsValue(105);
        if (v) {
            System.out.println("包含'105'這個value");
        } else {
            System.out.println("不包含'105'這個value");
        }
    }
}
```

### 5.5 MapDemo2

```java
package cn.tedu.map;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Map的遍歷
 * Map的三種遍歷方式
 * ①單獨遍歷key
 * ②單獨遍歷value(基本不用)
 * ③遍歷每一組鍵值對
 * <p>
 * JDK8之後,集合和Map都支持了基於Lambda表達式的遍歷
 */
public class MapDemo02 {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("語文", 90);
        map.put("數學", 99);
        map.put("物理", 80);
        map.put("化學", 85);
        System.out.println(map);
        System.out.println("------------------單獨遍歷key-------------------");
        //將map中所有的key值都存在Set集合中
        Set<String> keySet = map.keySet();
        for (String k : keySet) {
            System.out.println("k = " + k);
        }
        System.out.println("------------------單獨遍歷value-------------------");
        //將map中的所有value都存在Collection集合中
        Collection<Integer> values = map.values();
        for (Integer value : values) {
            System.out.println("value = " + value);
        }
        System.out.println("------------------遍歷每一組鍵值對-------------------");
        //將map中的所有的entry存儲在Set集合中 entry就是鍵值對實例對象
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + " = " + value);
        }
        System.out.println("------------------Lambda表達式遍歷Map-------------------");
        map.forEach(
                (k, v) -> System.out.println(k + " = " + v)
        );
        System.out.println("------------------Lambda表達式遍歷集合-------------------");
        Collection<String> c = new ArrayList<>();
        c.add("A");
        c.add("B");
        c.add("C");
        c.add("D");
        c.forEach(
                (e) -> System.out.println(e)
        );
        //參數只有一個時,可以省略小括號
        c.forEach(
                e -> System.out.println(e)
        );
        //如果輸出的參數和傳入的參數是同一個時,就可以省略參數,替換::
        c.forEach(
                System.out::println
        );
    }
}
```
