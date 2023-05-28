package cn.tedu.reflect;


import java.lang.reflect.Method;

/**
 * 通过反射调用私有方法
 * 有违私有的实际意义
 */
public class ReflectDemo06 {
    public static void main(String[] args) throws Exception {
        Class cls = Class.forName("cn.tedu.reflect.pojo.Person");
        //创建实例对象,方便后面调用方法
        Object o = cls.newInstance();
        //getMethod 获取公开方法
        //getDeclaredMethod 获取本类中的方法(包含私有)
        Method secret = cls.getDeclaredMethod("secret");
        //暴力反射,强制打开当前对象的访问权限
        secret.setAccessible(true);
        secret.invoke(o);
    }
}
