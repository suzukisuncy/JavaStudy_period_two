package cn.tedu.reflect;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 自动调用pojo包下的所有类中的方法名含有"s"的无参且公开的方法
 */
public class Test03 {
    public static void main(String[] args) throws Exception {
        //定位Test02这个类所在的包
        File dir = new File(
                Test03.class.getResource("./pojo").toURI()
        );
        //获取包下的所有字节码文件
        File[] subs = dir.listFiles(f -> f.getName().endsWith(".class"));
        for (File sub : subs) {
            String fileName = sub.getName(); //获取文件名 Person.class
            //substring(a,b) 截取字符串中[a,b)范围的内容
            //indexOf("") 获取指定字符串的下标
            String className = fileName.substring(0, fileName.indexOf(".")); //获取类名
            String allName = Test03.class.getPackage().getName() +
                    ".pojo." + className; //获取全路径名
            Class cls = Class.forName(allName); //声明对应类的Class实例,方便后面的反射操作
            Object o = cls.newInstance(); //创建两个类的实例对象
            Method[] methods = cls.getDeclaredMethods();//获取类中定义的所有方法
            for (Method method : methods) {
                if (method.getName().contains("s") && //名字含有s的
                        method.getParameterCount() == 0 && //无参的
                        method.getModifiers() == Modifier.PUBLIC) { //公开的
                    System.out.println("自动调用" + className + "类中的方法:" + method.getName());
                    method.invoke(o);//调用方法
                }
            }
        }
    }
}
