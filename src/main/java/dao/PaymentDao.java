package dao;

import po.Payment;
import vo.PaymentVo;

import java.util.List;

public interface PaymentDao {
    List<Payment> SelectAllPayment(Payment payment);
    void balancePayment();

    List<PaymentVo> getEmployeePayment();

    List<PaymentVo> getEmployeeManager();
}
