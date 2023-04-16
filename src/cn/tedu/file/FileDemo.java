package cn.tedu.file;

import java.io.File;

/**
 * 这个案例是学习File绑定指定目标文件
 */
public class FileDemo {
    public static void main(String[] args) {
        //访问当前项目下的demo目录中的demo.txt文件
        /*
         * 路径一般分为两种,绝对路径和相对路径
         * ①绝对路径: 从根目录一直定位到目标文件的路径 绝对路径一般不用
         * 选中指定文件,右键→Copy Path→Absolute Path,复制绝对路径
         * D:\Dates\IDEASPACE\JSDVN2302SE-TEACHER\demo\demo.txt
         * 在java中"\"(反斜杠)是特殊字符,是转义字符
         * ②相对路径 相对于特殊节点所在位置的路径
         * 在idea中使用"./"表示当前项目的目录
         * 在本项目中,"./"就表示"D:\Dates\IDEASPACE\JSDVN2302SE-TEACHER\"
         */
        File file = new File("./demo/demo.txt");
        //获取文件名字(绑定的字符串的名字)
        //变量名.soutv 快速生成输出语句
        String name = file.getName();
        System.out.println("name = " + name);
        //获取文件的长度(字节) 一个因为字母是1个字节 一个中文是3个字节
        long length = file.length();
        System.out.println("length = " + length+"个字节");
    }
}
