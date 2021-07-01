package pojo;

import annotation.ApiModelProperty;

/**
 * @author RongTongxin
 */
public class Linkman {

    /**
     * 不加@ApiModelProperty则不对比
     */
    private Integer id;

    @ApiModelProperty("联系人姓名")
    private String linkmanName;

    @ApiModelProperty("联系人邮箱")
    private String linkmanEmail;

    @ApiModelProperty("联系人电话号码")
    private String linkmanTel;

    public Linkman() {
    }

    public Linkman(Integer id, String linkmanName, String linkmanEmail, String linkmanTel) {
        this.id = id;
        this.linkmanName = linkmanName;
        this.linkmanEmail = linkmanEmail;
        this.linkmanTel = linkmanTel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLinkmanName() {
        return linkmanName;
    }

    public void setLinkmanName(String linkmanName) {
        this.linkmanName = linkmanName;
    }

    public String getLinkmanEmail() {
        return linkmanEmail;
    }

    public void setLinkmanEmail(String linkmanEmail) {
        this.linkmanEmail = linkmanEmail;
    }

    public String getLinkmanTel() {
        return linkmanTel;
    }

    public void setLinkmanTel(String linkmanTel) {
        this.linkmanTel = linkmanTel;
    }

    @Override
    public String toString() {
        return "Linkman [id=" + id + ", linkmanName=" + linkmanName + ", linkmanEmail=" + linkmanEmail + ", linkmanTel="
                + linkmanTel + "]";
    }
}
