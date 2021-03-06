package service.Imp;

import dao.AttendDao;
import dao.EmployeeDao;
import dao.ManagerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import po.Employee;
import po.Manager;
import service.ManagerService;
import vo.OparationVO;
import vo.PaymentVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service("managerService")
@Transactional
public class ManagerServiceIml implements ManagerService {

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
        return utilsService.punch(request,1,attendDao);
    }

    @Override
    public OparationVO<List<Employee>> getDptEmployee(Integer id) {
        try{
            List<Employee> list = employeeDao.selectEmployeeByManagerId(id);
            return new OparationVO<>(0,"查看成功",list);
        }catch (Exception e){
            e.printStackTrace();
            return new OparationVO<>(1,"查看失败");
        }
    }

    @Override
    public OparationVO<List<PaymentVo>> viewDeptSal(Integer id) {
        try{
            List<PaymentVo> list = employeeDao.selectDepSal(id);
            return new OparationVO<>(0,"查看成功",list);
        }catch (Exception e){
            e.printStackTrace();
            return new OparationVO<>(1,"查看失败");
        }
    }

    @Override
    public OparationVO addEmp(final Employee employee) {
        try {
            employeeDao.insertEmp(employee);
            new Thread(()->{
                utilsService.sendEmailHelloToEmp(employee);
            }).start();
            return new OparationVO(1,"插入成功");
        }catch (Exception e){
            e.printStackTrace();
            return new OparationVO(1,"插入失败");
        }
    }

    @Override
    public List<Manager> getManager(Manager manager) {
        return managerDao.selectManager(manager);
    }


}
