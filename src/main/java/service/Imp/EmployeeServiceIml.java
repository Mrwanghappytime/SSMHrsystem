package service.Imp;


import dao.AttendDao;
import dao.EmployeeDao;
import dao.ManagerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import po.Attend;
import po.Employee;
import service.EmployeeService;
import vo.AttendVo;
import vo.OparationVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
@Transactional
@Service("employeeService")
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


}
