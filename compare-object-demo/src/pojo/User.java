package pojo;

import annotation.ApiModelProperty;
import annotation.ComparableObjectField;

/**
 * @author RongTongXin
 * @version 1.0
 */
public class User extends BaseUser {

    private Integer id;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("联系人信息")
    @ComparableObjectField
    private Linkman linkman;

    @ApiModelProperty("银行卡信息")
    @ComparableObjectField(false)
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
