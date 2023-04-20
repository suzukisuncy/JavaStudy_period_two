# 反射

## 获取Class对象

获取Class对象的三种方式：（例如获取Student类的Class对象）

1. 通过Student类的任意对象调用 getClass 方法：

   Class clazz = new Student().getClass();

2. 通过Student类调用class属性：

   Class clazz = Student.class;

3. 通过Class类的forName("类路径")方法：

   Class clazz = Class.forName( "cn.tedu.Student" );

## Class对象常用方法

###  获取Class对象所代表类(或接口)的所在包路径、类名、全限定名称

```
String getPackageName() -- 返回所反射 类(或接口) 的包名
String getPackage().getName() -- 返回所反射 类(或接口) 的包名
String getSimpleName() -- 返回所反射 类 的类名(或所反射接口的接口名)
String getName()--返回 所反射 类(或接口) 的全限定类名(包名+类名/接口名)
```

### 获取所反射 类(或接口) 的成员变量定义信息

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

###  获取所反射 类(或接口) 的构造方法定义信息

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

###  获取所反射 类(或接口) 的成员方法定义信息

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

## 反射的应用

### 准备工作

#### 定义一个Person类（对该类进行反射）

```java
package reflect;

/**
 * 使用当前类来测试反射机制
 */
public class Person {
    private String name = "张三";
    private int age = 22;
    //无参构造 全参构造
    public Person(){}
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public void sayHello(){
        System.out.println(name+":hello!!");
    }
    public void sayHi(){
        System.out.println(name+":hi!!");
    }
    public void sayGoodBye(){
        System.out.println(name+":bye!!");
    }
    public void dosome(String thing){
        System.out.println(name+"正在"+thing);
    }
    public void dosome(String thing,int sum){
        for(int i=0;i<sum;i++){
            System.out.println(name+"正在"+thing);
        }
    }
    private void secret(){
        System.out.println(name+":这是我的私有方法!");
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

#### 定义一个Student类

```java
package reflect;

public class Student {
    public void study(){
        System.out.println("学生:好好学习,天天向上!");
    }
    public void playGame(){
        System.out.println("学生:好好游戏,才好学习!");
    }
}
```

### 测试开始

#### ReflectDemo1

```java

```

####  ReflectDemo2 

```java

```

#### ReflectDemo3

```java

```

####  ReflectDemo4

```java

```

#### ReflectDemo5

```java

```

#### ReflectDemo6

```java

```

#### ReflectDemo7

```java

```

#### ReflectDemo8

```java

```

#### ArgsDemo

```java

```

#### Test1

```java

```

#### Test2

```java

```

#### Test3

```java

```
