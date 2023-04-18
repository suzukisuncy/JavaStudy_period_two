package cn.tedu.file;

import java.io.File;

/**
 * 此案例学习使用File获取指定目录下的一层子项
 */
public class ListFilesDemo {
    public static void main(String[] args) {
        File dir = new File("./demo");
        /*
         * boolean isDirectory()
         * 判断当前调用的File是否表示的是一个目录,是则返回true,不是返回false
         */
        if (dir.isDirectory()) {
            /*
             * File[] listFiles()
             * 将当前调用的File表示的目录中的一层子项,各自实例化为File,并存储到File数组中
             */
            File[] subs = dir.listFiles();
            System.out.println("demo目录下,有" + subs.length + "个子项!");
            //subs.fori 自动生成根据下标遍历数组的代码结构
            for (int i = 0; i < subs.length; i++) {
                System.out.println(subs[i].getName());
            }
        } else {
            System.out.println("是文件!!!!");
        }
    }
}
