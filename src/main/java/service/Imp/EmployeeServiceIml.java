package service.Imp;


import dao.AttendDao;
import dao.EmployeeDao;
import dao.ManagerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import po.Employee;
import service.EmployeeService;
import vo.AttendVo;
import vo.OparationVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service("employeeService")
@Transactional
public class EmployeeServiceIml implements EmployeeService {
    @Autowired
    private AttendDao attendDao;
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private ManagerDao managerDao;
    @Autowired
    private UtilsService utilsService;


    @Override
    public OparationVO punch(HttpServletRequest request) {
        return utilsService.punch(request,0,attendDao);
    }

    @Override
    public OparationVO<List<AttendVo>> viewUnPunch(HttpServletRequest request) {
        return utilsService.viewUnPunch(request,0,attendDao);
    }

    @Override
    public List<Employee> getEmployee(Employee employee) {
        return employeeDao.selectEmployee(employee);
    }


}
