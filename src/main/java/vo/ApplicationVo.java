package vo;

public class ApplicationVo {
    private Integer id;
    private String name;
    private String attendType1;
    private String attendType2;
    private String reason;
    private String result;

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

    public String getAttendType1() {
        return attendType1;
    }

    public void setAttendType1(String attendType1) {
        this.attendType1 = attendType1;
    }

    public String getAttendType2() {
        return attendType2;
    }

    public void setAttendType2(String attendType2) {
        this.attendType2 = attendType2;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ApplicationVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", attendType1='" + attendType1 + '\'' +
                ", attendType2='" + attendType2 + '\'' +
                ", reason='" + reason + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
