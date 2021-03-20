package vo;

public class PaymentVo {
    private String name;
    private Double amount;
    private String email;
    private Boolean employee;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEmployee() {
        return employee;
    }

    public void setEmployee(Boolean employee) {
        this.employee = employee;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "PaymentVo{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", email='" + email + '\'' +
                ", employee=" + employee +
                '}';
    }
}
