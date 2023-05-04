### 1 在IDEA中创建类或者文件

①创建类时,可以通过**`包名.类名`**的形式连同包和类一起创建

```java
cn.tedu.file.FileDemo
```

②创建文件时,可以通过**`文件夹名/文件名`**的形式连同文件夹和文件一起创建

```java
demo/demo.txt
```

### 2 IDEA切换项目

①点击**`File→Open Recent`**,打开最近打开的项目

②点击**`File→Open`**,在弹出的面板中选中要打开的项目即可打开电脑中的项目

### 3 如果更新老师的项目报错的解决方案

①删除本地的老师的项目

②然后重新根据git连接下载

### 4 IDEA快速打开文件所在目录

选中要打开的文件,**`右键→选择Open In→Explorer`**

<img src="https://gitee.com/paida-spitting-star/image/raw/master/image-20230418200710212.png" alt="image-20230418200710212" style="zoom: 80%; border: solid;" />

### 5 IDEA生成方法

①生成无参无返回值的方法

- 调用方法处

```java
recursionDir();
```

- 方法会报红色提示,按alt+enter,直接回车,会自动生成方法体

```java
private static void recursionDir() {
}
```

②生成含参无返回值的方法

- 调用方法处

```java
recursionDir(dir);
```

- 方法会报红色提示,按alt+enter,直接回车,会自动生成方法体

```java
private static void recursionDir(File dir) {
}
```

③生成含参含返回值的方法

- 调用方法处

```java
boolean result = recursionDir(dir);
```

- 方法会报红色提示,按alt+enter,直接回车,会自动生成方法体

```java
private static boolean recursionDir(File dir) {
}
```

### 6 修改类名

①方式一:

直接在类中将类名修改,然后类名会报错,直接按`alt+enter`,选择rename file即可

<img src="https://gitee.com/paida-spitting-star/image/raw/master/image-20230425201041809.png" alt="image-20230425201041809" style="zoom: 80%; border: solid;"/>

②方式二:

选中类名,按`shift+F6`,直接修改类名即可

