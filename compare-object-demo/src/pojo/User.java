package pojo;
/**
 * <p>Title: User</p>
 * <p>Describtion: </p>
 * <p>Company: www.git.com.cn</p>
 * @author  RongTongXin    
 * @version 1.0        
 * @created 2019年5月14日 下午3:20:24   
*/

import annotation.ApiModelProperty;
import annotation.ObjectField;

/**
 * 使用继承方便测试父类属性的比较
 */
public class User extends BaseUser{
	private Integer id;

	@ApiModelProperty("年龄")
	private Integer age;

	@ApiModelProperty("联系人信息")
	@ObjectField(compare = true)
	private Linkman linkman;

	@ApiModelProperty("银行卡信息")
	@ObjectField(compare = false)
	private CardInfo cardInfo;

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Linkman getLinkman() {
		return linkman;
	}

	public void setLinkman(Linkman linkman) {
		this.linkman = linkman;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CardInfo getCardInfo() {
		return cardInfo;
	}

	public void setCardInfo(CardInfo cardInfo) {
		this.cardInfo = cardInfo;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", age=" + age + ", linkman=" + linkman + ", cardInfo=" + cardInfo + "]";
	}

	public User() {
		super();	
	}

	public User(Integer id, Integer age, Linkman linkman, CardInfo cardInfo) {
		super();
		this.id = id;
		this.age = age;
		this.linkman = linkman;
		this.cardInfo = cardInfo;
	}
}
