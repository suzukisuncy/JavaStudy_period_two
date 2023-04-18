package cn.tedu.file;

import java.io.File;

/**
 * 此案例学习使用File创建目录
 */
public class MKDirDemo {
    public static void main(String[] args) {
        //目录是没有后缀名
        File dir = new File("./demo/h/e/l/l/o");
        if (dir.exists()) {
            System.out.println("该目录已存在!");
        } else {
            /*
             * mkdir() 创建目录时要求该目录所在的目录必须存在,否则无法创建
             * mkdirs() 创建目录时,会将不存在的目录以一同创建出来(推荐)
             */
            dir.mkdirs(); //make 制作 dir目录
            System.out.println("目录制作完毕!!");
        }
    }
}
