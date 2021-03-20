package qurtz;

import dao.AttendDao;
import org.springframework.beans.factory.annotation.Autowired;
import po.Employee;
import service.AttendService;

public class PunchJob {
    @Autowired
    private AttendService attendService;

    public void punchJob(){
        attendService.autoSetPunchAttendType();
    }
}
