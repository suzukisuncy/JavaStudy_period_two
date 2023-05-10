### 1 在IDEA中創建類或者文件

①創建類時,可以通過**`包名.類名`**的形式連同包和類一起創建

```java
cn.tedu.file.FileDemo
```

②創建文件時,可以通過**`文件夾名/文件名`**的形式連同文件夾和文件一起創建

```java
demo/demo.txt
```

### 2 IDEA切換項目

①點擊**`File→Open Recent`**,打開最近打開的項目

②點擊**`File→Open`**,在彈出的面板中選中要打開的項目即可打開電腦中的項目

### 3 如果更新老師的項目報錯的解決方案

①刪除本地的老師的項目

②然後重新根據git連接下載

### 4 IDEA快速打開文件所在目錄

選中要打開的文件,**`右鍵→選擇Open In→Explorer`**

<img src="https://gitee.com/paida-spitting-star/image/raw/master/image-20230418200710212.png" alt="image-20230418200710212" style="zoom: 80%; border: solid;" />

### 5 IDEA生成方法

①生成無參無返回值的方法

- 調用方法處

```java
recursionDir();
```

- 方法會報紅色提示,按alt+enter,直接回車,會自動生成方法體

```java
private static void recursionDir() {
}
```

②生成含參無返回值的方法

- 調用方法處

```java
recursionDir(dir);
```

- 方法會報紅色提示,按alt+enter,直接回車,會自動生成方法體

```java
private static void recursionDir(File dir) {
}
```

③生成含參含返回值的方法

- 調用方法處

```java
boolean result = recursionDir(dir);
```

- 方法會報紅色提示,按alt+enter,直接回車,會自動生成方法體

```java
private static boolean recursionDir(File dir) {
}
```

### 6 修改類名

①方式一:

直接在類中將類名修改,然後類名會報錯,直接按`alt+enter`,選擇rename file即可

<img src="https://gitee.com/paida-spitting-star/image/raw/master/image-20230425201041809.png" alt="image-20230425201041809" style="zoom: 80%; border: solid;"/>

②方式二:

選中類名,按`shift+F6`,直接修改類名即可

### 7 設置編碼

- 在使用idea時,一般都會去往**`File → Settings → Editor → File Encodings`**先統一設置全局項目和文件編碼

![image-20230427210039769](https://gitee.com/paida-spitting-star/image/raw/master/image-20230427210039769.png)

### 8 生成try.catch

- 針對編譯時異常出現時,可以使用**`alt+enter`**,選擇下述選項,生成捕獲語句

![image-20230504212228684](https://gitee.com/paida-spitting-star/image/raw/master/image-20230504212228684.png)

- 如果出現多個異常,也可以有多種選擇(根據實際情況選擇)

![image-20230504212617196](https://gitee.com/paida-spitting-star/image/raw/master/image-20230504212617196.png)

