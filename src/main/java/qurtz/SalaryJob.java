package qurtz;

import org.springframework.beans.factory.annotation.Autowired;
import po.Payment;
import service.PaymentService;

public class SalaryJob {
    @Autowired
    private PaymentService paymentService;
    public void salaryJob(){
        paymentService.balanceSalary();
    }
}
