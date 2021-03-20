package service;

import po.Employee;
import vo.OparationVO;
import vo.PaymentVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ManagerService {
    OparationVO punch(HttpServletRequest request);

    OparationVO<List<Employee>> getDptEmployee(Integer id);

    OparationVO<List<PaymentVo>> viewDeptSal(Integer id);

    OparationVO addEmp(Employee employee);
}
