package service;

import po.Employee;
import vo.AttendVo;
import vo.OparationVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface EmployeeService {
    OparationVO punch(HttpServletRequest request) throws Exception;
    OparationVO<List<AttendVo>> viewUnPunch(HttpServletRequest request);

    List<Employee> getEmployee(Employee employee);
}
