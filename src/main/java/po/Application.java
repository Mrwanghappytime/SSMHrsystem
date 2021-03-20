package po;

/**
 * 对应员工提出的申请
 */
public class Application  {

    private Integer id;
    // 对应原因
    private String reason;
    // 是否处理
    private boolean result;
    // 对应的出勤记录
    private Integer attendId;
    // 希望改成的出勤类型
    private Integer attendTypeId;
    // 申请的结果
    private Boolean results;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Integer getAttendId() {
        return attendId;
    }

    public void setAttendId(Integer attendId) {
        this.attendId = attendId;
    }

    public Integer getAttendTypeId() {
        return attendTypeId;
    }

    public void setAttendTypeId(Integer attendTypeId) {
        this.attendTypeId = attendTypeId;
    }

    public Boolean isResults() {
        return results;
    }

    public void setResults(Boolean results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", reason='" + reason + '\'' +
                ", result=" + result +
                ", attendId=" + attendId +
                ", attendTypeId=" + attendTypeId +
                ", results=" + results +
                '}';
    }
}
