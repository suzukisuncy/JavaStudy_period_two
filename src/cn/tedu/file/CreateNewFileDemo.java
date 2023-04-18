package cn.tedu.file;

import java.io.File;
import java.io.IOException;

/**
 * 这个案例是学习使用File创建文件
 */
public class CreateNewFileDemo {
    public static void main(String[] args) throws IOException {
        //idea中,相对路径中"./"可以不写,会默认识别
        //File file = new File("demo/new.txt");
        File file = new File("./demo/new.txt");
        /*
         * boolean exists()
         * 判断调用的File实例是否存在,存在返回true,不存在返回false
         * File可能是目录也可能是文件
         */
        if (file.exists()) {
            System.out.println("文件已存在!");
        } else {
            //方法报红线错误,按alt+enter(回车),然后直接再按回车
            file.createNewFile();//create 创建 new 新的 file 文件
            System.out.println("该文件创建完毕!!!");
        }
    }
}
