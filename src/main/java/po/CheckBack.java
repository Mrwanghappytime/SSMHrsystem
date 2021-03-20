package po;

/**
 * 表示批复
 */
public class CheckBack {
    private Integer id;
    // 是否同意
    private boolean result;
    // 批复理由
    private String reason;
    // 对应的申请
    private Integer applicationId;
    // 对应的经理
    private Integer managerId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    @Override
    public String toString() {
        return "CheckBack{" +
                "id=" + id +
                ", result=" + result +
                ", reason='" + reason + '\'' +
                ", applicationId=" + applicationId +
                ", managerId=" + managerId +
                '}';
    }
}
