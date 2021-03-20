package service;

import po.Application;
import vo.ApplicationVo;
import vo.OparationVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ApplicationService
{
    OparationVO addApplication(List<Application> applications);

    OparationVO<List<ApplicationVo>> viewEmpApps(HttpServletRequest request);

    OparationVO updateApplication(Application application);

    // OparationVO<List<Application>> getAllApplication();


}
