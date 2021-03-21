package service.Imp;

import dao.AttendDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.AttendService;
import org.springframework.transaction.annotation.Transactional;
@Transactional
@Service("attendService")
public class AttendServiceImp implements AttendService {

    @Autowired
    private AttendDao attendDao;
    @Override
    public void autoSetPunchAttendType() {
        attendDao.autoSetAttendType();
    }
}
