package cn.tedu.file;

import java.io.File;

/**
 * 此案例学习使用File删除目录
 */
public class DeleteDirDemo {
    public static void main(String[] args) {
        //目录是没有后缀名
        File dir = new File("./demo/h/e/l/l/o");
        if (dir.exists()) {
            /*
             * delete方法,只能删除一个文件或者目录
             * 原因是delete不能删除非空目录
             */
            dir.delete();
            System.out.println("删除目录成功!");
        } else {
            System.out.println("目录不存在!!删除失败!!!");
        }
    }
}
