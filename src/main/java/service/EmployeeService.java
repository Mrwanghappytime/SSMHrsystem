package service;

import vo.AttendVo;
import vo.OparationVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface EmployeeService {
    OparationVO punch(HttpServletRequest request) throws Exception;
    OparationVO<List<AttendVo>> viewUnPunch(HttpServletRequest request);
}
