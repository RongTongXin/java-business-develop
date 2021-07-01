package util;

import annotation.ApiModelProperty;
import annotation.ComparableObjectField;
import pojo.HistoryDesc;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * <p>Title: ObjectCompareUtil</p>
 * <p>Describtion:对象对比工具类,可以对比两个相同的对象的变化值.以便实现历史变更详情等业务</p>
 *
 * @author RongTongxin
 * @version 1.0
 */
public class ObjectCompareUtil {

    private ObjectCompareUtil() {
    }

    /**
     * 业务对象对比-记录变更详情业务需求的简易实现.(反射获取属性的值应可使用ReflectUtils等工具类执行get方法获取,这里使用暴力反射简易实现)
     *
     * @param oldObject 旧的业务对象
     * @param newObject 新的更新对象
     * @param oldClazz  旧的业务对象的class对象
     * @param newClazz  新的更新对象的class对象
     * @param histories 容器对象,最终返回对比结果列表
     * @return List<HistoryDesc> 返回类型
     * @throws Exception 参数
     * @date 2019年5月15日 下午3:56:13
     */
    public static <T> List<HistoryDesc> compareTwoObject(T oldObject,
                                                         T newObject,
                                                         Class<? extends T> oldClazz,
                                                         Class<? extends T> newClazz,
                                                         List<HistoryDesc> histories) throws Exception {
        if (histories == null) {
            histories = new LinkedList<>();
        }
        Field[] oldDeclaredFields = oldClazz.getDeclaredFields();
        Field[] newDeclaredFields = newClazz.getDeclaredFields();
        for (int i = 0; i < oldDeclaredFields.length; i++) {
            Field field = oldDeclaredFields[i];
            field.setAccessible(true);
            // 1.筛选要对比的属性
            // -如果没有@ApiModelProperty注解则不对比。
            ApiModelProperty annotation = field.getAnnotation(ApiModelProperty.class);
            if (annotation == null) {
                continue;
            }
            // -如果有@ApiModelProperty注解,再判断是否是对象属性,如果对象属性是不比较,则跳过
            ComparableObjectField comparableObjectField = field.getAnnotation(ComparableObjectField.class);
            if (Objects.nonNull(comparableObjectField)) {
                if (comparableObjectField.value()) {
                    // 递归比较对象属性
                    compareTwoObject(field.get(oldObject), field.get(newObject), field.get(oldObject).getClass(),
                            field.get(newObject).getClass(), histories);
                }
                continue;
            }
            // 普通属性正常对比,判断值是否相等
            Field newField = newDeclaredFields[i];
            newField.setAccessible(true);
            Object newValue = newField.get(newObject);
            Object oldValue = field.get(oldObject);
            if (Objects.equals(newValue, oldValue)) {
                continue;
            }
            HistoryDesc hDesc = new HistoryDesc(field.getName(), annotation.value(), oldValue == null ? "" : oldValue,
                    newValue);
            histories.add(hDesc);
        }
        if (newClazz.getSuperclass() != Object.class) {
            compareTwoObject(oldObject, newObject, oldClazz.getSuperclass(), newClazz.getSuperclass(), histories);
        }
        return histories;
    }
}
