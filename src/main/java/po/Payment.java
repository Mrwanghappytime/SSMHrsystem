package po;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * 对应的薪水
 */
public class Payment {

    private Integer id;
    // 对应月份
    private Integer month;
    // 薪水总数
    private Double amount;
    // 是否员工
    private Boolean employee;
    // 对应的经理id 或者 员工id
    private Integer eid;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public boolean isEmployee() {
        return employee;
    }

    public void setEmployee(boolean employee) {
        this.employee = employee;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", month=" + month +
                ", amount=" + amount +
                ", employee=" + employee +
                ", eid=" + eid +
                '}';
    }
}
