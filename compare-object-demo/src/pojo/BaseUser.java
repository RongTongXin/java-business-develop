package pojo;

import java.util.Date;

import annotation.ApiModelProperty;

public class BaseUser {
	@ApiModelProperty("生日")
	private Date birthday;

	@ApiModelProperty("是否是管理员")
	private Boolean root;

	@ApiModelProperty("姓名")
	private String name;

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Boolean getRoot() {
		return root;
	}

	public void setRoot(Boolean root) {
		this.root = root;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "BaseUser [birthday=" + birthday + ", root=" + root + ", name=" + name + "]";
	}
}
