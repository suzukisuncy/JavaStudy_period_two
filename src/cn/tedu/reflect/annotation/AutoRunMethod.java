package cn.tedu.reflect.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记自动调用的方法
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD) //修饰方法的注解
public @interface AutoRunMethod {

}
