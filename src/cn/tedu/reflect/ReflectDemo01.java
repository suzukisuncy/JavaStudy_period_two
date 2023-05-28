package cn.tedu.reflect;

/**
 * java 反射机制
 * 反射是java中的一种动态机制,它允许我们在程序运行期间决定代码的执行走向,使得程序的灵活度大大提高
 * 但是同时会带来更多的性能损耗,所以反射一般都只使用在画龙点睛的地方,不能过度的依赖
 */
public class ReflectDemo01 {
    public static void main(String[] args) throws ClassNotFoundException {
        //获取String类的Class实例
        /**
         * 获取Class实例的三种方式的优缺点:
         * 方式一: 类名.class
         *  优点: 简单明了,不需要额外操作,并且是唯一可以获取基础类型的Class实例的方式
         *  缺点: 需要知道类名,必须写死类名,不能动态获取
         * 方式二: 实例对象.getClass()
         *  优点: 简单明了,不需要额外操作
         *  缺点: 只适用于已经实例化的对象
         * 方式三: Class.forName()
         *  优点: 因为可以通过传参的方式来创建Class实例,所以适合动态获取指定类的Class实例
         *  缺点: 写法稍微复杂一些
         */
        Class cls = String.class;//方式一
        System.out.println("cls1 = " + cls);
        cls = new String().getClass();//方式二
        System.out.println("cls2 = " + cls);
        cls = Class.forName("java.lang.String");//方式三
        System.out.println("cls3 = " + cls);
        System.out.println("类名:" + cls.getSimpleName());
        System.out.println("全类名:" + cls.getName());
        //类 -- Class实例 包 -- Package实例 方法 -- Method实例 属性 -- Filed实例
        System.out.println("包名:" + cls.getPackage().getName());
    }
}
