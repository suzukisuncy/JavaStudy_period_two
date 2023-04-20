#  IO

## IO简介

- In/Out: 相对于程序而言的输入(读取)和输出(写出)的过程。

  即: 通过java程序到磁盘读取数据的过程, 我们称为In的过程, 也就是读取(输入)

- 将java程序中的数据写入磁盘的过程, 我们称为Out的过程, 也就是写出(输出)

- JDK核心类库中提供了IO流相关的类, 这些类都在<java.io>包下

## 流的概念

- 程序中数据的读取和写入, 可以想象成水流在管道中流动。
  - 流只能单方向流动
  - 输入流用来读取in
  - 输出流用来写出Out
  - 数据只能从头到尾顺序的读取一次或写出一次

![image-20230420210605045](https://gitee.com/paida-spitting-star/image/raw/master/image-20230420210605045.png)

## 节点流和处理流

- 按照流是否直接与特定的地方(如磁盘,内存,设备等)相连,分为节点流和处理流两类

### 节点流

- 可以从或向一个特定的地方(节点)读写数据

### 处理流

- 是对一个已存在的流的连接和封装,通过所封装的流的功能调用实现数据读写

### 处理流特点

- 处理流的构造方法总是要带一个其他的流对象做参数,一个流对象经过其他流的多次包装,成为流的链接.

- 通常节点流也被称之为低级流.处理流也被称之为高级流或者过滤流

## 节点流

### OutputStream 

- 此抽象类是表示**`输出字节流`**的所有类的超类。输出流接受输出字节并将这些字节发送到某个接收器。

### FileOutputStream

- 直接插在文件上，直接写出(输出)文件数据

**创建对象：**

```java
FileOutputStream(String name) //创建一个向具有指定名称的文件中写入数据的输出文件流。
FileOutputStream(File file) //创建一个向指定 File 对象表示的文件中写入数据的文件输出流。
FileOutputStream(File file, boolean append) //追加 创建一个向指定 File 对象表示的文件中写入数据的文件输出流。
```

<font color=red>注意: 以上构造方法中, 如果参数指向的文件以及父目录都不存在, 将会抛出FileNotFoundException异常!如果参数指向的文件不存在, 但文件的所在目录存在, 将不会抛异常, 程序会自动创建该文件!</font>

#### 代码案例

```java

```

### InputStream

- 此抽象类是表示字节输入流的所有类的超类/抽象类。

### FileInputStream

- 直接插在文件上，直接读取文件数据。

**创建对象**

```
FileInputStream(File file) 
通过打开一个到实际文件的连接来创建一个 FileInputStream，该文件通过文件系统中的 File 对象 file 指定。 
FileInputStream(String pathname) 
通过打开一个到实际文件的连接来创建一个 FileInputStream，该文件通过文件系统中的路径名 name 指定。
```

#### 代码案例

```java

```

### 复制文件

#### 代码案例

```java

```

#### 块读写案例

```java

```

#### 写入字符串

```java

```

#### 简易笔记本

```java

```

#### 文件追加模式案例

```java

```

## 处理流

### 缓冲流

- BufferedOutputStream缓冲输出流
- BufferedInputStream 缓冲输入流

#### 复制文件代码案例

```java

```

#### flush案例

```java

```

### 对象流

#### Person代码 

```java

```

#### OOSDemo案例

```java

```

#### OISDemo案例

```java

```

##  字节流和字符流

- 在Java中，根据处理的数据单位不同，分为字节流和字符流。
  - 字节流: 一个字节(byte)一个字节的去读取, 或者写出
  - 字符流: 一个字符一个字符的去读取, 或者写出

### 字节流

- **字节流(stream)：**针对二进制文件(文本,图片,音频,视频...等)     

- InputStream(包含input都是输入流)
  - FileInputStream
  - BufferedInputStream
  - ObjectInputStream

- OutputStream(包含output都是输出流)
  - FileOutputStream
  - BufferedOutputStream
  - ObjectOutputStream

### 字符流

- **字符流(Reader,Writer)：**针对文本文件，读写容易发生乱码现象，在读写时最好指定编码集为utf-8
- Reader(Reader结尾的都是字符输入流)
  - BufferedReader
  - InputStreamReader

- Writer(Writer结尾的都是字符输出流)
  - BufferedWriter
  - OutputStreamWriter
  - PrintWriter

## 转换流

### OutputStreamWriter

#### 代码案例

```java

```

### InputStreamReader

#### 代码案例

```java

```

## 缓冲字符流

### PrintWriter

#### 代码案例

```java

```

#### 代码案例2

```java

```

