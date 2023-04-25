package cn.tedu.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * 此案例学习对象的序列化流
 */
public class OOSDemo {
    public static void main(String[] args) throws IOException {
        String name = "薛宏举";
        int age = 18;
        String gender = "男";
        String[] otherInfo = {"是一个帅锅", "来自于天津", "爱好学习", "是广大男性之友"};
        Person person = new Person(name, age, gender, otherInfo);
        System.out.println(person);
        //创建文件字节输出流,绑定要输出的文件
        FileOutputStream fos = new FileOutputStream("./demo/person.txt");
        //创建对象字节输出流,绑定文件字节输出流,将对象转换为字节数据,再将字节数据写入到文件中
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        //将person对象交给对象字节输出流
        //此处注意,写出的对象类必须要实现Serializable接口,否则不能序列化
        oos.writeObject(person);
        System.out.println("写出完毕!");
        //释放资源
        oos.close();
    }
}
