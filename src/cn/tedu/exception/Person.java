package cn.tedu.exception;

public class Person {
    private int age;

    public int getAge() {
        return age;
    }

    /*
     * 如果要对外抛出异常,我们会使用throw关键字进行异常的抛出,
     * 如果抛出的是运行时异常,那么只能在运行过程中发现该异常
     * 而如果抛出的时编译时异常,那么必须要在方法的后面添加throws关键字
     * 告知调用者,在编译期时,需要检验该异常,所以在写代码时,就必须要处理该异常
     */
    public void setAge(int age) throws IllegalAgeException {
        //判断录入的年龄是否符合需求
        if (age < 0 || age > 100) {
            //此处主动抛异常(表示抛出异常的动作)
            throw new IllegalAgeException("您录入的年龄不合法!!");
        }
        this.age = age;
    }
}
