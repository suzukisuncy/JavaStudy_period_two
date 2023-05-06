package cn.tedu.exception;

/**
 * 自定义异常:非法的年龄异常
 * 自定义异常步骤:
 * ①类名要见名知意(一般是XxxException的格式,要求可以直观的表明异常的类型)
 * ②需要该类直接或者间接继承Exception类(表明当前类是异常子类)
 * ③提供父类中的所有的构造器
 * alt+insert→选择Constructor→ctrl+a全选,回车即可
 */
public class IllegalAgeException extends Exception {
    public IllegalAgeException() {
    }

    public IllegalAgeException(String message) {
        super(message);
    }

    public IllegalAgeException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalAgeException(Throwable cause) {
        super(cause);
    }

    public IllegalAgeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
