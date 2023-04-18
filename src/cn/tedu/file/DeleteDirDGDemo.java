package cn.tedu.file;

import java.io.File;

/**
 * 此案例使用递归(recursion),删除指定目录及其中所有子项
 */
public class DeleteDirDGDemo {
    public static void main(String[] args) {
        File dir = new File("./demo");
        recursionDeleteDir(dir);
    }

    /**
     * 递归遍历删除指定的目录
     *
     * @param dir 要遍历删除的目录
     */
    private static void recursionDeleteDir(File dir) {
        //1.判断当前File是否为文件(防止第一次传入的是文件)
        /*
         * boolean isFile()
         * 判断当前调用者是否是一个文件,是则返回true,不是返回false
         */
        if (dir.isFile()) {
            //1.1.如果file是文件, 输出: "文件不支持递归!"
            System.out.println("文件是不支持递归的!!!");
            //return在此处直接结束当前方法
            return;
        } else {
            //1.2.如果file是目录, 则继续执行第2步
            //2.获取当前目录下的所有子目录及子文件对象组成的File数组
            File[] subs = dir.listFiles();
            //3.遍历当前目录下的所有子目录及子文件对象
            for (int i = 0; i < subs.length; i++) {
                //4.判断当前遍历的是目录还是文件
                if (subs[i].isFile()) {
                    //4.1.如果当前遍历的是文件, 则删除并输出该文件的路径+名称
                    System.out.println("文件:" + subs[i]);
                    subs[i].delete();
                } else {
                    //4.2.并以此目录为根,再次执行recursionDir方法的逻辑,遍历该目录的子目录和子文件
                    recursionDeleteDir(subs[i]);
                }
            }
            //5.删除dir目录
            dir.delete();
            System.out.println("成功删除" + dir.getName() + "目录!");
        }
    }
}
