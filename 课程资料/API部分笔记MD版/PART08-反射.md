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
package cn.tedu.reflect;

import cn.tedu.reflect.pojo.Person;

import java.util.Scanner;

/**
 * 通过反射创建实例对象
 * cn.tedu.reflect.pojo.Student
 * cn.tedu.reflect.pojo.Person
 * java.util.Date
 */
public class ReflectDemo03 {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        System.out.println("请输入全路径名:");
        String line = new Scanner(System.in).nextLine();
        Class cls = Class.forName(line);
        //newInstance() 调用类中的无参且公开构造,创建对象实例
        //InstantiationException 实例化异常,如果没有无参构造,就抛出该异常
        //IllegalAccessException 非法访问异常,如果无参构造是非公开的,就抛出该异常
        Object o = cls.newInstance();
        System.out.println(o);
    }
}
```

####  3.2.4 ReflectDemo4

```java
package cn.tedu.reflect;

import java.lang.reflect.Constructor;
import java.util.Scanner;

/**
 * 通过反射创建指定参数的实例对象
 * cn.tedu.reflect.pojo.Student
 * cn.tedu.reflect.pojo.Person
 * java.util.Date
 */
public class ReflectDemo04 {
    public static void main(String[] args) throws Exception {
        Class cls = Class.forName("cn.tedu.reflect.pojo.Person");
        //getConstructor 获取指定的构造器,传入对应的构造器的参数的Class实例
        Constructor c = cls.getConstructor(String.class, int.class);
        //newInstance 执行当前构造器,并且接收传入的参数,参数顺序需要和构造器保持一致
        Object o = c.newInstance("张飞", 34);
        System.out.println(o);
    }
}
```

#### 3.2.5 ReflectDemo5

```java
package cn.tedu.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * 通过反射调用方法
 */
public class ReflectDemo05 {
    public static void main(String[] args) throws Exception {
        Class cls = Class.forName("cn.tedu.reflect.pojo.Person");
        //创建实例对象,方便后面调用方法
        Object o = cls.newInstance();
        //getMethod 获取指定的方法
        Method say = cls.getMethod("say");
        //o.say() invoke执行当前方法,并将方法对象作为参数传入
        say.invoke(o);
        Method doSome = cls.getMethod("doSome", String.class);//获取doSome(String thing)
        //invoke执行当前方法,并将方法对象作为参数传入,且将方法所需参数传入
        doSome.invoke(o, "打豆豆");
        doSome = cls.getMethod("doSome", String.class, int.class);
        doSome.invoke(o, "喝水", 5);
    }
}
```

#### 3.2.6 Test01

```java
package cn.tedu.reflect;

import com.sun.org.apache.xpath.internal.operations.Mod;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 自动调用Person类中所有名字含有"s"的无参的公开的方法
 */
public class Test01 {
    public static void main(String[] args) throws Exception {
        Class cls = Class.forName("cn.tedu.reflect.pojo.Person");
        Object o = cls.newInstance();
        //获取Person类中的所有方法
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            //筛选出名字含有"s"的方法
            if (method.getName().contains("s")) {
                //筛选出公开的方法 getModifiers 获取方法的权限
                if (method.getModifiers() == Modifier.PUBLIC) {
                    //筛选出无参的方法 getParameterCount 获取方法的参数个数
                    if (method.getParameterCount() == 0) {
                        System.out.println("自动执行" +
                                cls.getSimpleName() +
                                "类中方法:" +
                                method.getName());
                        method.invoke(o);
                    }
                }
            }
        }
    }
}
```

#### 3.2.7 Test02

```java
package cn.tedu.reflect;

import java.io.File;

/**
 * 自动调用pojo包下的所有类中的方法名含有"s"的无参且公开的方法
 */
public class Test02 {
    public static void main(String[] args) throws Exception {
        //定位Test02这个类所在的包
        File dir1 = new File(
                //①定位到Test02.class所在的字节码文件的目录
                //②定位到字节码文件所在顶级包的上一级目录
                Test02.class.getClassLoader().getResource(
                        "."
                ).toURI()
        );
        System.out.println("定位指定字节码文件顶级包的上一层目录: " + dir1);
        File dir2 = new File(
                //直接定位Test02.class文件所在的目录
                Test02.class.getResource("./pojo").toURI()
        );
        System.out.println("定位指定字节码文件所在目录: " + dir2);
    }
}
```

#### 3.2.8 Test03

```java
package cn.tedu.reflect;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 自动调用pojo包下的所有类中的方法名含有"s"的无参且公开的方法
 */
public class Test03 {
    public static void main(String[] args) throws Exception {
        //定位Test02这个类所在的包
        File dir = new File(
                Test03.class.getResource("./pojo").toURI()
        );
        //获取包下的所有字节码文件
        File[] subs = dir.listFiles(f -> f.getName().endsWith(".class"));
        for (File sub : subs) {
            String fileName = sub.getName(); //获取文件名 Person.class
            //substring(a,b) 截取字符串中[a,b)范围的内容
            //indexOf("") 获取指定字符串的下标
            String className = fileName.substring(0, fileName.indexOf(".")); //获取类名
            String allName = Test03.class.getPackage().getName() +
                    ".pojo." + className; //获取全路径名
            Class cls = Class.forName(allName); //声明对应类的Class实例,方便后面的反射操作
            Object o = cls.newInstance(); //创建两个类的实例对象
            Method[] methods = cls.getDeclaredMethods();//获取类中定义的所有方法
            for (Method method : methods) {
                if (method.getName().contains("s") && //名字含有s的
                        method.getParameterCount() == 0 && //无参的
                        method.getModifiers() == Modifier.PUBLIC) { //公开的
                    System.out.println("自动调用" + className + "类中的方法:" + method.getName());
                    method.invoke(o);//调用方法
                }
            }
        }
    }
}
```

#### 3.2.9 ReflectDemo07

```java
package cn.tedu.reflect;

import cn.tedu.reflect.annotation.AutoRunClass;

/**
 * 通过反射判断类上是否被指定注解修饰
 */
public class ReflectDemo07 {
    public static void main(String[] args) throws Exception {
        Class<?> cls = Class.forName("cn.tedu.reflect.pojo.Person");
        if (cls.isAnnotationPresent(AutoRunClass.class)) {
            System.out.println(cls.getSimpleName() + "被@AutoRunClass注解修饰了");
        } else {
            System.out.println(cls.getSimpleName() + "没有被@AutoRunClass注解修饰了");
        }
    }
}
```

#### 3.2.10 Test04



# 注解

## 什么是注解

注解(Annotation)是java语言提供的一种注释机制,但是这种注释机制不是给我们看的,而是给虚拟机看的,我们可以将注解应用于类、方法、字段等元素上,可以表示更多的信息和意图,方便开发者在后续的开发中利用这些注解使程序更加的灵活多变

## 三种注解

①预定义注解

JAVASE中定义了一些注解,开发人员可以使用这些注解实现一些辅助功能,比如

`@Override`、`@Deprecated`等等.

②元注解

元注解是用于修饰注解的注解,当我们定义注解时,会使用元注解来配置一些内容

- `@Retention`: 用来指定当前注解的保留级别,有三个可选值,对应:
  - `RetentionPolicy.SOURCE` 表示当前注解仅保留在源码中
  - `RetentionPolicy.CLASS` 默认值,表示注解会保留在字节码文件中,但是不能被反射使用
  - **`RetentionPolicy.RUNTIME`** 表示注解会保留在字节码文件中,但是能被反射使用
- `@Target`: 用来指定当前注解使用的位置,如果不设置该元注解,则表示当前注解使用的位置任意,但是不建议这样去做,可选值都在ElementType中,常见的有:
  - `ElementType.TYPE` 在类上使用
  - `ElementType.FIELD` 在属性上使用
  - `ElementType.METHOD` 在方法上使用
  - `.....``
- `如果需要同时设置多个值,可以这样写:@Target({ElementType.METHOD,ElementType.TYPE})`

③自定义注解

用户自己定义的注解

## 自定义注解流程

①定义注解

使用`@interface`关键字来定义注解,例如我们声明一个名为`@AutoRunMethod`的注解

```java
public @interface AutoRunMethods {
}
```

②为注解声明元注解,需要根据实际情况,比如我们的注解需要被反射调用,并且需要声明在方法上

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.Method)
public @interface AutoRunMethod {

}
```

③为注解添加参数













