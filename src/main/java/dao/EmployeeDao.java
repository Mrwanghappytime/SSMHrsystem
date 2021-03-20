package dao;

import po.Employee;
import vo.PaymentVo;

import java.util.List;

public interface EmployeeDao {
    public List<Employee> selectEmployee(Employee employee);

    List<Employee> selectEmployeeByManagerId(Integer id);

    List<PaymentVo> selectDepSal(Integer id);

    void insertEmp(Employee employee);
}
