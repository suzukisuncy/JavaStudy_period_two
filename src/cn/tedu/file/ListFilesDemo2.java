package cn.tedu.file;

import java.io.File;
import java.io.FileFilter;

/**
 * 通过该案例学习通过File的listFiles方法获取符合过滤条件的目录中的所有子项
 * 获取一个目录中的符合过滤条件的子项
 * 重载的lsitFiles方法
 * File[] listFiles(FileFilter filter)
 * 该方法要求传入一个文件过滤器(FileFilter),然后该方法会自动根据文件过滤器的要求,将符合条件的子项返回
 */
public class ListFilesDemo2 {
    public static void main(String[] args) {
        //指定当前项目的目录,只需要写一个"."就可以
        File dir = new File(".");
        //创建文件过滤器实例,定义过滤规则(一般会使用匿名内部类创建,并且重写accept方法)
        FileFilter fileFilter = new FileFilter() {
            /**
             * accept方法就是用于定义过滤规则
             * @param file 默认的,表示要过滤的一个文件
             * @return 布尔值, 如果返回true, 表示当前过滤的文件符合条件, 反之则不符合
             */
            @Override
            public boolean accept(File file) {
                //定义过滤规则(要求获取子项名字中包含"o")
                //获取过滤的文件的名字
                String fileName = file.getName();
                //contains判断是否包含,包含就返回true,不包含返回false
                return fileName.contains("o");
            }
        };
        //将文件过滤器传入到listFiles方法中
        File[] subs = dir.listFiles(fileFilter);
        for (int i = 0; i < subs.length; i++) {
            System.out.println(subs[i].getName());
        }
    }
}
