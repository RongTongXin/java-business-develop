package main;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.alibaba.fastjson.JSON;

import pojo.CardInfo;
import pojo.HistoryDesc;
import pojo.Linkman;
import pojo.User;
import util.ObjectCompareUtil;

/**
 * <p>Title: Main</p>
 * <p>Describtion:主测试类</p>
 * 
 * @author MuZiYu
 * @version 1.0
 * @created 2019年5月14日 下午3:17:22
 */
public class Main {
	public static void main(String[] args) throws Exception {
		// 模拟要更新的数据
		Linkman linkman = new Linkman(1, "李四", "", "13585749632");
		CardInfo cardInfo = new CardInfo(1, "123456", 5000.00, new Date());
		User user = new User(1, 23, linkman, cardInfo);
		user.setBirthday(new Date());
		user.setName("MuZiYu");
		user.setRoot(true);
		update(user);
	}

	// 更新方法
	public static void update(User newUser) throws Exception {
		// 模拟从数据库查询一个User;
		Linkman oldLinkman = new Linkman(1, "李四", "", "11111111111");
		Linkman newLinkman = new Linkman(1, "张三", "zhangsan@163.com", "22222222222");
		CardInfo cardInfo = new CardInfo(1, "789410", 1000.00, null);
		User oldUser = new User(1, 22, newLinkman, cardInfo);
		oldUser.setName("SilenceMu");
		oldUser.setRoot(false);
		oldUser.setBirthday(null);

		// 对象中组合另一个对象的比较Test
		List<HistoryDesc> result = ObjectCompareUtil.compareTwoObject(oldUser, newUser, oldUser.getClass(), newUser.getClass(),
				new LinkedList<>());
		String compareResult4Json = JSON.toJSONString(result);
		System.out.println(compareResult4Json);
		
		// 属性中除基本和包装类型外,无其他自定义Object的比较Test
		List<HistoryDesc> result2 = ObjectCompareUtil.compareTwoObject(oldLinkman, newLinkman, oldLinkman.getClass(), newLinkman.getClass(),
				new LinkedList<>());
		String compareResult4Json2 = JSON.toJSONString(result2);
		System.out.println(compareResult4Json2);
	}
}
