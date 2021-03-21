package service.Imp;

import dao.EmployeeDao;
import dao.ManagerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import po.Employee;
import po.Manager;
import service.BaseService;
import vo.BaseVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
@Transactional
@Service("baseService")
public class BaseServiceIml implements BaseService {
    @Autowired
    private ManagerDao managerDao;
    @Autowired
    private EmployeeDao employeeDao;
    @Override
    public int findUser(BaseVO baseVO, HttpServletRequest request) {
        Manager manager = new Manager();
        manager.setName(baseVO.getName());
        manager.setPassword(baseVO.getPassword());
        Employee employee = new Employee();
        employee.setName(baseVO.getName());
        employee.setPassword(baseVO.getPassword());
        List<Employee> employees = employeeDao.selectEmployee(employee);
        List<Manager> managers = managerDao.selectManager(manager);
        if(employees.size()>0){
            request.getSession().setAttribute("user",employees.get(0));
            return 1;
        }else if(managers.size()>0){
            request.getSession().setAttribute("user",managers.get(0));
            return 2;
        }else{
            return 0;
        }
    }
}
