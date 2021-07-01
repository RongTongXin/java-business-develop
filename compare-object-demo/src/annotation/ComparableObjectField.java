package annotation;

import java.lang.annotation.*;

/**
 * 一个类中如果有自定义对象属性,加上此注解控制是否对比该对象属性
 *
 * @author RongTongXin
 * @date 2021/7/1 下午2:06
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ComparableObjectField {
    /**
     * 默认对比. 如果不需要对比的话设置为false
     */
    boolean value() default true;
}
