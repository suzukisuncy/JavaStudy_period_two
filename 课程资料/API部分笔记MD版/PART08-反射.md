# 反射

![image-20230528151146248](https://gitee.com/paida-spitting-star/image/raw/master/image-20230528151146248.png)

## 1 获取Class对象

获取Class对象的三种方式：（例如获取Student类的Class对象）

1. 通过Student类的任意对象调用 getClass 方法：

   Class clazz = new Student().getClass();

2. 通过Student类调用class属性：

   Class clazz = Student.class;

3. 通过Class类的forName("类路径")方法：

   Class clazz = Class.forName( "cn.tedu.Student" );

## 2 Class对象常用方法

- **获取Class对象所代表类(或接口)的所在包路径、类名、全限定名称**

```
String getPackageName() -- 返回所反射 类(或接口) 的包名
String getPackage().getName() -- 返回所反射 类(或接口) 的包名
String getSimpleName() -- 返回所反射 类 的类名(或所反射接口的接口名)
String getName()--返回 所反射 类(或接口) 的全限定类名(包名+类名/接口名)
```

- **获取所反射 类(或接口) 的成员变量定义信息**

```
Field getField(String name) 
  -- 返回一个Field对象，表示所反射 类(或接口) 中指定名称的成员变量
  -- 也就是说，可以通过 name 获取所反射 类(或接口) 中的某一个成员
  -- 只能获取公开的成员变量（包括从父类中继承的）
Field[] getFields() 
  -- 返回一个Field对象数组，该数组中包含所反射 类(或接口) 的所有成员变量
  -- 只能获取公开的成员变量（包括从父类中继承的）
Field getDeclaredField(String name) -- 返回一个 Field对象
  -- 返回一个Field对象，表示所反射 类(或接口) 中指定名称的成员变量
  -- 只能获取本类中的成员变量（包括私有成员变量），但不包括从父类中继承的
Field[] getDeclaredFields() 
  -- 返回一个Field对象数组，该数组中包含所反射 类(或接口) 中的所有成员变量
  -- 只能获取本类中的成员变量（包括私有成员变量），但不包括从父类中继承的
```

- **获取所反射 类(或接口) 的构造方法定义信息**

```
Constructor getConstructor(类... parameterTypes)
  -- 返回一个Constructor对象，表示 所反射 类 中指定参数的构造方法
  -- 只能获取公开的构造方法
Constructor[] getConstructors() 
  -- 返回一个Constructor对象数组，该数组中包含 所反射 类 中的所有构造方法
  -- 只能获取公开的构造方法
Constructor getDeclaredConstructor(类... parameterTypes) 
  -- 返回一个Constructor对象，表示 所反射 类 中指定参数的构造方法
  -- 获取的构造方法可以是公开的，也可以是私有的
Constructor[] getDeclaredConstructors() 
  -- 返回一个Constructor对象数组，表示 所反射 类 中的所有构造方法
  -- 获取的构造方法可以是公开的，也可以是私有的
```

- **获取所反射 类(或接口) 的成员方法定义信息**

```
Method getMethod(String name, 类... parameterTypes) 
  -- 返回一个Method对象，表示 所反射 类 中指定参数的成员方法
  -- 只能获取公开的成员方法
Method[] getMethods() 
  -- 返回一个Method对象数组，该数组中包含 所反射 类 中的所有成员方法
  -- 只能获取公开的成员方法
Method getDeclaredMethod(String name, 类... parameterTypes) 
  -- 返回一个Method对象，表示 所反射 类 中指定参数的成员方法
  -- 获取的成员方法可以是公开的，也可以是私有的
Method[] getDeclaredMethods()
  -- 返回一个Method对象数组，该数组中包含 所反射 类 中的所有成员方法
  -- 获取的成员方法可以是公开的，也可以是私有的
```

## 3 反射的应用

### 3.1 准备工作

- **定义一个Person类（对该类进行反射）**

```java
package cn.tedu.reflect.pojo;

public class Person {
    private String name = "张三";
    private int age = 18;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void eat() {
        System.out.println(name + ": 在吃饭!");
    }

    public void sleep() {
        System.out.println(name + ": 在睡觉!");
    }

    public void say() {
        System.out.println(name + ": 在说话!");
    }

    public void sing() {
        System.out.println(name + ": 在唱歌!");
    }

    private void secret() {
        System.out.println(name + "的小秘密,不能被人知道");
    }

    public void doSome(String thing) {
        System.out.println(name + "正在做" + thing);
    }

    public void doSome(String thing, int num) {
        for (int i = 1; i <= num; i++) {
            System.out.println(name + "正在第" + num + "次做" + thing);
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```

- **定义一个Student类**

```java
package cn.tedu.reflect.pojo;

public class Student {
    private String name = "李四";
    private int age = 18;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void study() {
        System.out.println(name + ": 在学习!");
    }

    public void course() {
        System.out.println(name + ": 在上课!");
    }

    public void homework() {
        System.out.println(name + ": 在做作业!");
    }

    public void java() {
        System.out.println(name + ": 在敲代码!");
    }

    private void secret() {
        System.out.println(name + "的小秘密,不能被人知道");
    }

    public void doSome(String thing) {
        System.out.println(name + "正在做" + thing);
    }

    public void doSome(String thing, int num) {
        for (int i = 1; i <= num; i++) {
            System.out.println(name + "正在第" + num + "次做" + thing);
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```

### 3.2 测试开始

#### 3.2.1 ReflectDemo1

```java
package cn.tedu.reflect;

/**
 * java 反射机制
 * 反射是java中的一种动态机制,它允许我们在程序运行期间决定代码的执行走向,使得程序的灵活度大大提高
 * 但是同时会带来更多的性能损耗,所以反射一般都只使用在画龙点睛的地方,不能过度的依赖
 */
public class ReflectDemo01 {
    public static void main(String[] args) throws ClassNotFoundException {
        //获取String类的Class实例
        /**
         * 获取Class实例的三种方式的优缺点:
         * 方式一: 类名.class
         *  优点: 简单明了,不需要额外操作,并且是唯一可以获取基础类型的Class实例的方式
         *  缺点: 需要知道类名,必须写死类名,不能动态获取
         * 方式二: 实例对象.getClass()
         *  优点: 简单明了,不需要额外操作
         *  缺点: 只适用于已经实例化的对象
         * 方式三: Class.forName()
         *  优点: 因为可以通过传参的方式来创建Class实例,所以适合动态获取指定类的Class实例
         *  缺点: 写法稍微复杂一些
         */
        Class cls = String.class;//方式一
        System.out.println("cls1 = " + cls);
        cls = new String().getClass();//方式二
        System.out.println("cls2 = " + cls);
        cls = Class.forName("java.lang.String");//方式三
        System.out.println("cls3 = " + cls);
        System.out.println("类名:" + cls.getSimpleName());
        System.out.println("全类名:" + cls.getName());
        //类 -- Class实例 包 -- Package实例 方法 -- Method实例 属性 -- Filed实例
        System.out.println("包名:" + cls.getPackage().getName());
    }
}
```

####  3.2.2 ReflectDemo2 

```java
package cn.tedu.reflect;

import cn.tedu.reflect.pojo.Person;
import cn.tedu.reflect.pojo.Student;

import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * 通过反射获取类中方法
 * cn.tedu.reflect.pojo.Person
 * cn.tedu.reflect.pojo.Student
 * java.lang.String
 */
public class ReflectDemo02 {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("请输入想获取的类的全路径名:");
        String line = new Scanner(System.in).nextLine();
        Class cls = Class.forName(line);
        //getDeclaredMethods() 获取当前类中的自身定义的所有的方法(包含私有的方法)
        Method[] methods = cls.getDeclaredMethods();
        System.out.println(cls.getSimpleName() + "类中共有" + methods.length + "个自定义的方法");
        for (Method method : methods) {
            System.out.println(method.getName());
        }
        System.out.println("※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※");
        //getMethods() 获取类中的所有的公开方法(包含父类中继承的)
        methods = cls.getMethods();
        System.out.println(cls.getSimpleName() + "类中共有" + methods.length + "个公开的方法");
        for (Method method : methods) {
            System.out.println(method.getName());
        }
    }
}
```

#### 3.2.3 ReflectDemo3

```java

```

####  3.2.4 ReflectDemo4

```java

```

#### 3.2.5 ReflectDemo5

```java

```

#### 3.2.6 ReflectDemo6

```java

```

#### 3.2.7 ReflectDemo7

```java

```

#### 3.2.8 ReflectDemo8

```java

```

#### 3.2.9 ReflectDemo9
