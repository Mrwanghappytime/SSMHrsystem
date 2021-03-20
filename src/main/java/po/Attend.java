package po;

import java.util.Date;

/**
 * 对应出勤
 */
public class Attend {
    private Integer id;
    // 对应日期
    private Date dutyDate;
    // 对应打卡时间
    private String punchTime;
    // 对应是否来了
    private boolean commed;

    private boolean isMan;
    // 对应的类型
    private Integer attendTypeId;
    // 出勤对应的员工
    private Integer employeeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDutyDate() {
        return dutyDate;
    }

    public void setDutyDate(Date dutyDate) {
        this.dutyDate = dutyDate;
    }

    public void setPunchTime(String punchTime) {
        this.punchTime = punchTime;
    }

    public String getPunchTime() {
        return punchTime;
    }

    public boolean isComemd() {
        return commed;
    }

    public void setCommed(boolean commed) {
        this.commed = commed;
    }


    public boolean isCommed() {
        return commed;
    }

    public boolean isMan() {
        return isMan;
    }

    public void setMan(boolean man) {
        isMan = man;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getAttendTypeId() {
        return attendTypeId;
    }

    public void setAttendTypeId(Integer attendTypeId) {
        this.attendTypeId = attendTypeId;
    }

    @Override
    public String toString() {
        return "Attend{" +
                "id=" + id +
                ", dutyDate=" + dutyDate +
                ", punchTime=" + punchTime +
                ", commed=" + commed +
                ", isMan=" + isMan +
                ", attendTypeId=" + attendTypeId +
                ", employeeId=" + employeeId +
                '}';
    }
}
