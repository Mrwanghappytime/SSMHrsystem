package po;

public class AttendType {
    private Integer id;
    // 出勤类型的名字
    private String name;
    // 罚款
    private Double amerce;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmerce() {
        return amerce;
    }

    public void setAmerce(Double amerce) {
        this.amerce = amerce;
    }

    @Override
    public String toString() {
        return "AttendType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amerce=" + amerce +
                '}';
    }
}
