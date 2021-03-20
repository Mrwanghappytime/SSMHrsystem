package vo;

public class AttendVo {
    private Integer id;
    private String dutyDate;
    private String punchTime;
    private String attendType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDutyDate() {
        return dutyDate;
    }

    public void setDutyDate(String dutyDate) {
        this.dutyDate = dutyDate;
    }

    public String getPunchTime() {
        return punchTime;
    }

    public void setPunchTime(String punchTime) {
        this.punchTime = punchTime;
    }

    public String getAttendType() {
        return attendType;
    }

    public void setAttendType(String attendType) {
        this.attendType = attendType;
    }

    @Override
    public String toString() {
        return "AttendVo{" +
                "id=" + id +
                ", dutyDate=" + dutyDate +
                ", punchTime='" + punchTime + '\'' +
                ", attendType='" + attendType + '\'' +
                '}';
    }
}
