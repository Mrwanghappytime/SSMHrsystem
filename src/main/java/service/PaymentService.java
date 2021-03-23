package service;

import po.Payment;
import vo.OparationVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface PaymentService {
    OparationVO<List<Payment>> getPayment(HttpServletRequest request, int type);

    void balanceSalary();
}
