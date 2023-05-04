# 异常处理

## 1 概述

- **异常:** 就是指程序出现了不正常的情况
- 用来封装错误信息的对象。
- 组成结构：**类型**，**提示**，**行号**。

## 2 异常的继承结构

## ![异常的继承结构](https://gitee.com/paida-spitting-star/image/raw/master/%E5%BC%82%E5%B8%B8%E7%9A%84%E7%BB%A7%E6%89%BF%E7%BB%93%E6%9E%84.png)    

- Java中所有错误和异常的顶级父类是Throwable类

- Throwable类下有两个子类, 分别是Error和Exception, 两者的区别是:

  - Error: 是指不需要捕获的严重问题, 通常是java程序以外的问题, 比如硬件问题或者内存不足导致的问题等.因此, 如果java程序中出现了Error, 我们无需处理。

  - Exception: 称为异常类, 它表示程序本身可以处理的问题

- Exception下有很多异常子类, 其中有一个异常子类是RuntimeException类, 这里还可以将异常分为两大类:

  - 编译时异常: 

    其他异常类以及不是RuntimeException子类的异常类都是检查异常(也叫编译时异常),在编写完程序后,Java编译器会对其进行检查, 如果检查出此类异常, 就必须要显式处理, 否则程序将无法进行编译。例如:ClassNotFoundException、FileNotFoundException、SQLException等都是编译时异常。

  - 运行时异常: 
  
    RuntimeException以及子类被称为未经检查的异常(也叫运行时异常),这类异常通常在编写完程序后没有问题, 但是运行程序才出现异常, 需要我们回来修改代码进行解决的异常,这类异常无需显式处理, 当然也可以像编译时异常一样处理,例如:IndexOutOfBoundsException、ArithmeticException、NullPointerException、ClassCastException 等都是运行时异常。判断一个异常是不是运行时异常, 可以通过检查这个异常类是不是RuntimeException的子类, 或者检查这个异常是否只有在程序运行时才会出现!

## 3 虚拟机的默认处理方式

- 如果程序在运行时出现了问题，而我们又没有处理该问题，最终虚拟机会做默认的处理，而这种默认处理方式为:
  - 将异常的名称(类型)、异常的原因以及异常出现的位置等信息输出在了控制台（Console窗口）
  - 将程序停止运行（这意味着，出现异常的代码后面的代码将不会再执行）

### 3.1 异常示例1

```java
package exception;

/**
 * 异常示例
 */
public class ExceptionDemo {
    public static void main(String[] args) {
        //没有报错,表示没有编译期异常
        //执行代码,报错了,说明有运行时异常
        int i = 1/0;
        System.out.println("hahaha");
    }
}
```

**运行结果:**

```java
Exception in thread "main" java.lang.ArithmeticException: / by zero
	at exception.ExceptionDemo.main(ExceptionDemo.java:10)
```

### 3.2 异常示例2

```java
package exception;

/**
 * 异常示例
 */
public class ExceptionDemo2 {
    public static void main(String[] args) {
        //定义一个数组
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

**运行结果:**

```java
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 6
	at exception.ExceptionDemo2.main(ExceptionDemo2.java:16)
```

- 如果程序出现了异常, 需要我们自己处理, 有两种方案: 
  - 使用try...catch...进行处理(捕获异常)
  - 使用throws进行处理(抛出异常)

## 4 异常处理之try...catch...处理

### 4.1 语法格式

```
try{
  可能出现异常的代码;
}catch(异常类型 变量名){
  异常处理代码; 
  //当try中的代码出现了异常并且这个异常能和catch中的异常类型匹配上, 才会执行catch
  //反之, 如果出现的异常和catch中的异常类型不匹配, 就不会执行catch
}
```

**执行流程**

1. 程序执行到try{}中的代码时, 如果出现了异常, 将会自动产生一个异常对象, 该异常对象将会被提交给java运行时系统;

2. 当Java运行时系统接收到异常对象时, 回到catch()中寻找匹配的异常类型, 找到后就进入catch{}中进行异常的处理;

3. 执行完毕后, 程序还可以继续执行try...catch之后的代码

### 4.2 代码案例1

```java
package cn.tedu.exception;

/**
 * 通过此案例学习异常捕获,了解基础语法结构
 * try{
 * 可能出现异常的代码;
 * }catch(异常类型 变量名){
 * 异常处理代码;
 * //当try中的代码出现了异常并且这个异常能和catch中的异常类型匹配上, 才会执行catch
 * //反之, 如果出现的异常和catch中的异常类型不匹配, 就不会执行catch
 * }
 */
public class TryCatchDemo01 {
    public static void main(String[] args) {
        System.out.println("程序开始了!");
        //①将可能发生异常的代码放在try中
        try {
            String str = null;
            //在java中,null表示什么也没有,是不能调用方法的,所以虚拟机会提示空指针异常
            System.out.println(str.length());
        } catch (NullPointerException e) {//②通过catch关键字捕获try中代码出现的异常类型
            //③如果try中出现空指针异常,会捕获,并执行该catch中的内容
            System.out.println("服务器忙,请稍后重试!!!");
            //④将捕获的异常信息打印出来,一般是给程序员观看解决问题的
            e.printStackTrace();
        }
        System.out.println("程序结束了!");
    }
}
```

### 4.3 代码案例2

```java

```

### 4.4 代码案例3

```java

```

## 5 finally块

- 作用就是确保一定要执行某些代码

### 5.1 代码案例

```java

```

### 5.2 代码案例2

```java

```

### 5.3 代码案例3

```java

```

## 6 throw关键字

- 当程序发生错误而无法处理的时候,会抛出对应的异常对象
- 除此之外,在某些时刻,您可能会想要自行抛出异常,例如字异常处理结束后,再将异常抛出,让下一层异常处理块来捕捉,若想要自行抛出异常,您可以使用"throw"关键字,并生成执行的异常对象后抛出.例如:throw new ArithmeticException();

### 6.1 Person

```java

```

### 6.2 ThrowDemo

```java

```

## 7 异常处理之throws处理

- 程序中会声明许多的方法,这些方法中可能会因某些错误而引发异常,但是不希望直接在这个方法中处理这些异常,而希望调用这个它的方法来统一处理,这时候可以使用throws关键字来声明这个方法将会抛出的异常

### 7.1 语法格式

```
...方法名() throws 异常类名 {
  方法体
}
```

### 7.2 Person

```java

```

### 7.3 ThrowDemo

```java

```

## 8 自定义异常

### 8.1 IllegalAgeException

```java

```

### 8.2 Person

```java

```

### 8.3 ThrowDemo

```java

```

## 9 总结

**什么时候需要try...catch异常, 什么时候需要throws异常?**

1. 如果这个异常是方法内部的代码造成的异常, 而不是因为调用者的传参导致的异常(也就是说这个异常和调用者没有关系), 通常需要我们try...catch异常

2. 如果这个异常是调用者的传参导致的异常, 则将异常throws抛出(就是将异常抛给调用者)

3. 不要在main方法上throws抛出异常, 因为这样会将异常抛给虚拟机, 而虚拟机是不会帮我们处理异常的!(虚拟机会按照默认方式处理:输出异常信息以及终止程序执行!)