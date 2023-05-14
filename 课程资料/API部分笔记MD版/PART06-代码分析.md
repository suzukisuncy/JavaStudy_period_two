# 代码分析

## 遵循的原则

- 局部变量存储在栈中
- 局部变量是基本类型,存储的值就是值本身
- 局部变量是引用类型,存储的值就是对象在堆中的地址
- 对象存储在堆中
- 对象的属性存储在对象中,也就是在堆中

## 案例1

### 代码

```java
package collection;

import java.util.ArrayList;
import java.util.Collection;

public class Test2 {
    public static void main(String[] args) {
        String s = "hello";
        int a = 1;
        Point p = new Point(1, 2);
        Collection c = new ArrayList();
        c.add(p);
        test(s, a, p, c);
        System.out.println("s:" + s);//?
        System.out.println("a:" + a);//?
        System.out.println("p:" + p);//?
        System.out.println("c:" + c);//?
    }

    public static void test(String s, int a, Point p, Collection c) {
        a++;
        s = s + "world";
        p.setX(3);
        p = new Point(4, 5);
        c.clear();
        c.add(p);
        c = new ArrayList();
        p.setX(7);
        c.add(p);
    }
}
```

### 分析图

#### String s = "hello";

![image-20221025205705958](https://gitee.com/paida-spitting-star/image/raw/master/image-20221025205705958.png)

#### int a = 1;

![image-20221025205803574](https://gitee.com/paida-spitting-star/image/raw/master/image-20221025205803574.png)

#### Point p = new Point(1, 2);

![image-20221025205913255](https://gitee.com/paida-spitting-star/image/raw/master/image-20221025205913255.png)

#### Collection c = new ArrayList();

![image-20221025210051255](https://gitee.com/paida-spitting-star/image/raw/master/image-20221025210051255.png)

#### c.add(p);

![image-20221025210133786](https://gitee.com/paida-spitting-star/image/raw/master/image-20221025210133786.png)

#### test(s, a, p, c);

- 首先需要给test方法做标记
- 调用方法时,需要将参数传递给方法的形参,所以test方法中的形参的值和main中声明的局部变量的值相同,所以也会和堆中的对象形成引用

![image-20221025210621043](https://gitee.com/paida-spitting-star/image/raw/master/image-20221025210621043.png)

#### a++;

![image-20221025210730592](https://gitee.com/paida-spitting-star/image/raw/master/image-20221025210730592.png)

#### s = s + "world";

- 字符串的拼接,会生成一个新的字符串,所以此处会生成一个新的内存地址

![image-20221025210943042](https://gitee.com/paida-spitting-star/image/raw/master/image-20221025210943042.png)

#### p.setX(3);

![image-20221025211007437](https://gitee.com/paida-spitting-star/image/raw/master/image-20221025211007437.png)

#### p = new Point(4, 5);

![image-20221025211052829](https://gitee.com/paida-spitting-star/image/raw/master/image-20221025211052829.png)

#### c.clear();

- 清空集合

![image-20221025211124345](https://gitee.com/paida-spitting-star/image/raw/master/image-20221025211124345.png)

#### c.add(p);

![image-20221025211246390](https://gitee.com/paida-spitting-star/image/raw/master/image-20221025211246390.png)

#### c = new ArrayList();

![image-20221025211333287](https://gitee.com/paida-spitting-star/image/raw/master/image-20221025211333287.png)

#### p.setX(7);

![image-20221025211359200](https://gitee.com/paida-spitting-star/image/raw/master/image-20221025211359200.png)

#### c.add(p);

![image-20221025211441421](https://gitee.com/paida-spitting-star/image/raw/master/image-20221025211441421.png)

#### test执行完毕

- test方法执行完毕后,会将test标记以上的栈中局部变量全部弹出

![image-20221025211608505](https://gitee.com/paida-spitting-star/image/raw/master/image-20221025211608505.png)

- 由于栈中的局部变量消失,所以对堆中的引用也会消失

![image-20221025211643719](https://gitee.com/paida-spitting-star/image/raw/master/image-20221025211643719.png)

- 此时,GC会自动将没有引用的堆中的对象进行清理

![image-20221025211757626](https://gitee.com/paida-spitting-star/image/raw/master/image-20221025211757626.png)

### 输出结果

- 根据图中实际的引用,输出结果

![image-20221025211912896](https://gitee.com/paida-spitting-star/image/raw/master/image-20221025211912896.png)

## 案例2

### 代码

```java
package collection;

import java.util.Arrays;

public class Test3 {
    public static void main(String[] args) {
        int a = 1;
        String str = "hello";
        Point p = new Point(1, 2);
        String[] arr = {"one", "two", "three", str};
        int[] arr1 = {1, 2, 3, 4, a};
        Point[] arr2 = {new Point(1, 2), new Point(3, 4), new Point(5, 6), p};
        dosome(a, str, p, arr, arr1, arr2);
        System.out.println("a:" + a);//1
        System.out.println("str:" + str);//hello
        System.out.println("p:" + p);//(4,2)
        System.out.println("arr:" + Arrays.toString(arr));//[one,str,hello,hello]
        System.out.println("arr1:" + Arrays.toString(arr1));//[1,3,3,4,1]
        System.out.println("arr2:" + Arrays.toString(arr2));//[(1,2), (3,4),(12,6), (4,2)]
    }

    public static void dosome(int a, String str, Point p, String[] arr, int[]
            arr1, Point[] arr2) {
        arr1[a] = 3;
        arr[a] = "str";
        arr[a + 1] = str;
        str = "world";
        str = arr[2];
        str += "!!";
        p.setX(4);
        p = arr2[2];
        p.setX(6);
        arr2 = Arrays.copyOf(arr2, arr2.length - 1);
        arr2[2].setX(8);
        arr2 = new Point[]{new Point(2, 2), p};
        arr2[arr2.length - 1].setX(12);
    }
}
```

### 分析图

#### int a = 1;

![image-20221030095225471](https://gitee.com/paida-spitting-star/image/raw/master/image-20221030095114268.png)

#### String str = "hello";

![image-20221030095225471](https://gitee.com/paida-spitting-star/image/raw/master/image-20221030095225471.png)

#### Point p = new Point(1, 2);

![image-20221030095334477](https://gitee.com/paida-spitting-star/image/raw/master/image-20221030095334477.png)

#### String[] arr = {"one", "two", "three", str};

![image-20221030095801040](https://gitee.com/paida-spitting-star/image/raw/master/image-20221030095801040.png)

#### int[] arr1 = {1, 2, 3, 4, a};

![image-20221030095932376](https://gitee.com/paida-spitting-star/image/raw/master/image-20221030095932376.png)

#### Point[] arr2 = {new Point(1, 2), new Point(3, 4), new Point(5, 6), p};

![image-20221030100122981](https://gitee.com/paida-spitting-star/image/raw/master/image-20221030100122981.png)

#### dosome(a, str, p, arr, arr1, arr2);

![image-20221030100414202](https://gitee.com/paida-spitting-star/image/raw/master/image-20221030100414202.png)

#### arr1[a] = 3;

![image-20221030100540792](https://gitee.com/paida-spitting-star/image/raw/master/image-20221030100540792.png)

#### arr[a] = "str";

![image-20221030100743269](https://gitee.com/paida-spitting-star/image/raw/master/image-20221030100743269.png)

#### arr[a + 1] = str;

![image-20221030100910075](https://gitee.com/paida-spitting-star/image/raw/master/image-20221030100910075.png)

#### str = "world";

![image-20221030101001876](https://gitee.com/paida-spitting-star/image/raw/master/image-20221030101001876.png)

#### str = arr[2];

![image-20221030101040427](https://gitee.com/paida-spitting-star/image/raw/master/image-20221030101040427.png)

#### str += "!!";

![image-20221030101157165](https://gitee.com/paida-spitting-star/image/raw/master/image-20221030101157165.png)

#### p.setX(4);

![image-20221030101227906](https://gitee.com/paida-spitting-star/image/raw/master/image-20221030101227906.png)

#### p = arr2[2];

![image-20221030101317414](https://gitee.com/paida-spitting-star/image/raw/master/image-20221030101317414.png)

#### p.setX(6);

![image-20221030101336119](https://gitee.com/paida-spitting-star/image/raw/master/image-20221030101336119.png)

#### arr2 = Arrays.copyOf(arr2, arr2.length - 1);

![image-20221030101605729](https://gitee.com/paida-spitting-star/image/raw/master/image-20221030101605729.png)

#### arr2[2].setX(8);

![image-20221030101639999](https://gitee.com/paida-spitting-star/image/raw/master/image-20221030101639999.png)

#### arr2 = new Point[]{new Point(2, 2), p};

![image-20221030101859222](https://gitee.com/paida-spitting-star/image/raw/master/image-20221030101859222.png)

#### arr2[arr2.length - 1].setX(12);

![image-20221030101946084](https://gitee.com/paida-spitting-star/image/raw/master/image-20221030101946084.png)

#### dosome执行完毕

- 将dosome方法中的变量弹出栈

![image-20221030102053451](https://gitee.com/paida-spitting-star/image/raw/master/image-20221030102053451.png)

- 清除无引用的实例

![image-20221030102204573](https://gitee.com/paida-spitting-star/image/raw/master/image-20221030102204573.png)

### 输出结果

![image-20221030102307508](https://gitee.com/paida-spitting-star/image/raw/master/image-20221030102307508.png)