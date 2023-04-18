package cn.tedu.file;

import java.io.File;
import java.io.IOException;

/**
 * 这个案例是学习使用File删除文件
 */
public class DeleteFileDemo {
    public static void main(String[] args) throws IOException {
        //idea中,相对路径中"./"可以不写,会默认识别
        File file = new File("./demo/new.txt");
        if (file.exists()) {
            file.delete(); //delete删除
            System.out.println("文件删除成功!!!");
        } else {
            System.out.println("文件不存在!!!不可删除!!!");
        }
    }
}
