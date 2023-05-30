package cn.tedu.reflect;

import java.io.File;

/**
 * 自动调用pojo包下的所有类中的方法名含有"s"的无参且公开的方法
 */
public class Test02 {
    public static void main(String[] args) throws Exception {
        //定位Test02这个类所在的包
        File dir1 = new File(
                //①定位到Test02.class所在的字节码文件的目录
                //②定位到字节码文件所在顶级包的上一级目录
                Test02.class.getClassLoader().getResource(
                        "."
                ).toURI()
        );
        System.out.println("定位指定字节码文件顶级包的上一层目录: " + dir1);
        File dir2 = new File(
                //直接定位Test02.class文件所在的目录
                Test02.class.getResource("./pojo").toURI()
        );
        System.out.println("定位指定字节码文件所在目录: " + dir2);
    }
}
