package cn.tedu.file;

import java.io.File;

/**
 * 此案例学习使用File创建目录
 */
public class MKDirDemo {
    public static void main(String[] args) {
        //目录是没有后缀名
        File dir = new File("./demo/a");
        if (dir.exists()) {
            System.out.println("该目录已存在!");
        } else {
            dir.mkdir(); //make 制作 dir目录
            System.out.println("目录制作完毕!!");
        }
    }
}
