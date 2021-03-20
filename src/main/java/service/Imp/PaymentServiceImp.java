package service.Imp;

import dao.PaymentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import po.Employee;
import po.Manager;
import po.Payment;
import service.PaymentService;
import vo.OparationVO;
import vo.PaymentVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Service("paymentService")
public class PaymentServiceImp implements PaymentService {
    @Autowired
    private PaymentDao paymentDao;
    @Autowired
    private UtilsService utilsService;

    @Override
    public OparationVO<List<Payment>> getPayment(HttpServletRequest request,int type) {
        Payment payment = new Payment();
        fillPayment(request,type,payment);
        if(payment.getEid() == null){
            return new OparationVO<>(1,"查询工资失败");
        }
        try {
            List<Payment> payments = paymentDao.SelectAllPayment(payment);
            return new OparationVO<>(0,"查询工资成功",payments);
        }catch (Exception e){
            e.printStackTrace();
            return new OparationVO<>(1,"查询工资失败");
        }
    }

    @Override
    public void balanceSalary() {
        paymentDao.balancePayment();
        new Thread(()->{
            sendPaymentEmailToEverybody();
        }).start();
    }
    private void sendPaymentEmailToEverybody(){
        List<PaymentVo> lists = paymentDao.getEmployeePayment();
        List<PaymentVo> lists1 = paymentDao.getEmployeeManager();
        lists.addAll(lists1);
        for(PaymentVo paymentVo : lists){
            if(paymentVo.getEmail() != null) {
                String text = "亲爱的" + paymentVo.getName() + (paymentVo.getEmployee() ? "员工" : "经理") + "你上月的工资为" + paymentVo.getAmount() + "，如有疑问请找10086";
                utilsService.sendEmail(text, paymentVo.getEmail(), "工资条");
            }
        }
    }
    private void fillPayment(HttpServletRequest request,int type,Payment payment){
        if(type == 0){
            Employee employee = (Employee) request.getSession().getAttribute("user");
            payment.setEmployee(true);
            payment.setEid(employee.getId());
        }else{
            Manager employee = (Manager) request.getSession().getAttribute("user");
            payment.setEmployee(false);
            payment.setEid(employee.getId());
        }
    }
}
