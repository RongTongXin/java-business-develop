package util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import annotation.ApiModelProperty;
import annotation.ObjectField;
import pojo.HistoryDesc;

/**
 * <p>Title: ObjectCompareUtil</p>
 * <p>Describtion:对象对比工具类,可以对比两个相同的对象的变化值.以便实现历史变更详情等业务</p>
 * 
 * @author MuZiYu
 * @version 1.0
 * @created 2019年5月15日 下午3:00:00
 */
public class ObjectCompareUtil {

	private ObjectCompareUtil() {
	}

	/**
	 * @Title: compareTwoObject
	 * @Description: 业务对象对比-记录变更详情业务需求的简易实现.(反射获取属性的值应当使用ReflectUtils等工具类执行get方法获取,这里使用暴力反射简易实现)
	 * @param oldObject 旧的业务对象
	 * @param newObject 新的更新对象-其属性需要使用@ApiModelProperty、@ObjectField等注解控制是否需要对比,是否需要对象类型属性的递归对比.
	 * @param oldClazz  旧的业务对象的class对象
	 * @param newClazz  新的更新对象的class对象
	 * @param historys  空的容器对象,最终返回对比结果列表
	 * @return
	 * @throws Exception 参数
	 * @return List<HistoryDesc> 返回类型
	 * @throws @author RongTongXin
	 * @date 2019年5月15日 下午3:56:13
	 */
	@SuppressWarnings("unused")
	public static <T> List<HistoryDesc> compareTwoObject(T oldObject, T newObject, Class<? extends T> oldClazz,
			Class<? extends T> newClazz, List<HistoryDesc> historys) throws Exception {
		if (historys == null) {
			historys = new ArrayList<>();
		}
		Field[] oldDeclaredFields = oldClazz.getDeclaredFields();
		Field[] newDeclaredFields = newClazz.getDeclaredFields();
		for (int i = 0; i < oldDeclaredFields.length; i++) {
			Field field = oldDeclaredFields[i];
			field.setAccessible(true);
			// 1.筛选要对比的属性
			// -如果没有@ApiModelProperty注解则无需对比。
			ApiModelProperty annotation = field.getAnnotation(ApiModelProperty.class);
			if (annotation == null) {
				continue;
			}
			// -如果有@ApiModelProperty注解,再判断是否是对象属性,如果对象属性是不比较,则跳过
			ObjectField objectVariable = field.getAnnotation(ObjectField.class);
			if (objectVariable != null && objectVariable.compare() == false) {
				continue;
			}
			if (objectVariable != null && objectVariable.compare()) {
				// 递归比较对象属性
				compareTwoObject(field.get(oldObject), field.get(newObject), field.get(oldObject).getClass(),
						field.get(newObject).getClass(), historys);
				continue;
			}
			// 普通属性正常对比
			// 判断值是否相等
			Field newField = newDeclaredFields[i];
			newField.setAccessible(true);
			Object newValue = newField.get(newObject);
			Object oldValue = field.get(oldObject);
			if (newValue.equals(oldValue)) {
				continue;
			}
			HistoryDesc hDesc = new HistoryDesc(field.getName(), annotation.value(), oldValue == null ? "" : oldValue,
					newValue);
			historys.add(hDesc);
		}
		if (newClazz.getSuperclass() != Object.class) {
			compareTwoObject(oldObject, newObject, oldClazz.getSuperclass(), newClazz.getSuperclass(), historys);
		}
		return historys;
	}
}
