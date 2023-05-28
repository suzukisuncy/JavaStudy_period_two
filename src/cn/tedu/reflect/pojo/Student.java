package cn.tedu.reflect.pojo;

public class Student {
    private String name = "李四";
    private int age = 18;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void study() {
        System.out.println(name + ": 在学习!");
    }

    public void course() {
        System.out.println(name + ": 在上课!");
    }

    public void homework() {
        System.out.println(name + ": 在做作业!");
    }

    public void java() {
        System.out.println(name + ": 在敲代码!");
    }

    private void secret() {
        System.out.println(name + "的小秘密,不能被人知道");
    }

    public void doSome(String thing) {
        System.out.println(name + "正在做" + thing);
    }

    public void doSome(String thing, int num) {
        for (int i = 1; i <= num; i++) {
            System.out.println(name + "正在第" + num + "次做" + thing);
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
