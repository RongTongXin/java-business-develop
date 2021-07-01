package main;

import com.alibaba.fastjson.JSON;
import pojo.CardInfo;
import pojo.HistoryDesc;
import pojo.Linkman;
import pojo.User;
import util.ObjectCompareUtil;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * MainTest
 *
 * @author RongTongXin
 * @date 2021/7/1 下午2:09
 */
public class Main {
    public static void main(String[] args) throws Exception {
        // 模拟更新的数据-from web
        Linkman linkman = new Linkman(1, "李四", "", "13585749632");
        CardInfo cardInfo = new CardInfo(1, "123456", 5000.00, new Date());
        User user = new User(1, 23, linkman, cardInfo);
        user.setBirthday(new Date());
        user.setName("MuZiYu");
        user.setRoot(true);
        update(user);
    }

    /**
     * 测试更新User后对比
     *
     * @param newUser:
     * @date 2021/7/1 下午2:11
     * @author RongTongXin
     */
    public static void update(User newUser) throws Exception {
        // 模拟从数据库查询原始User
        Linkman oldLinkman = new Linkman(1, "李四", "", "11111111111");
        Linkman newLinkman = new Linkman(1, "张三", "zhangsan@163.com", "22222222222");
        CardInfo cardInfo = new CardInfo(1, "789410", 1000.00, null);
        User oldUser = new User(1, 22, newLinkman, cardInfo);
        oldUser.setName("SilenceMu");
        oldUser.setRoot(false);
        oldUser.setBirthday(null);

        // test:对象中组合另一个对象的比较
        List<HistoryDesc> result = ObjectCompareUtil.compareTwoObject(oldUser, newUser, oldUser.getClass(), newUser.getClass(),
                new LinkedList<>());
        String compareResult4Json = JSON.toJSONString(result, true);
        System.out.println("=================嵌套对象对比 compare nested object ================");
        System.out.println(compareResult4Json);

        // test:对象属性中除基本和包装类型外,无其他自定义Object的比较
        List<HistoryDesc> result2 = ObjectCompareUtil.compareTwoObject(oldLinkman, newLinkman, oldLinkman.getClass(), newLinkman.getClass(),
                new LinkedList<>());
        String compareResult4Json2 = JSON.toJSONString(result2, true);
        System.out.println("=================普通对象对比 compare basic object ================");
        System.out.println(compareResult4Json2);
    }
}
