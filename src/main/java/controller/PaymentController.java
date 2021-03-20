package controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import po.Payment;
import service.PaymentService;
import vo.OparationVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @RequestMapping(value = "/getEmployeePayment",method = RequestMethod.POST)
    @ResponseBody
    public OparationVO<List<Payment>> getPayment(HttpServletRequest request){
        return paymentService.getPayment(request,0);
    }
    @RequestMapping(value = "/viewMgrSalary",method = RequestMethod.POST)
    @ResponseBody
    public OparationVO<List<Payment>> getMgrPayment(HttpServletRequest request){
        return paymentService.getPayment(request,1);
    }


}
