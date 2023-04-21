# 反射

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

```

- **定义一个Student类**

```java

```

### 3.2 测试开始

#### 3.2.1 ReflectDemo1

```java

```

####  3.2.2 ReflectDemo2 

```java

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
