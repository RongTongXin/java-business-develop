package pojo;

/**
 * 自定义对比结果类
 * @author RongTongxin
 * @date 2019年5月15日
 */
public class HistoryDesc {
    private String name;
    private String description;
    private Object oldValue;
    private Object newValue;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public void setOldValue(Object oldValue) {
        this.oldValue = oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }

    public void setNewValue(Object newValue) {
        this.newValue = newValue;
    }

    @Override
    public String toString() {
        return "HistoryDesc [name=" + name + ", description=" + description + ", oldValue=" + oldValue + ", newValue="
                + newValue + "]";
    }

    public HistoryDesc(String name, String description, Object oldValue, Object newValue) {
        super();
        this.name = name;
        this.description = description;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public HistoryDesc() {
    }
}
