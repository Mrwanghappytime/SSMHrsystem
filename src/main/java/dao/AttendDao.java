package dao;

import po.Attend;
import vo.AttendVo;

import java.util.List;

public interface AttendDao {
    public List<Attend> selectAttend(Attend attend);
    public void insertBatch(List<Attend> list);
    public void insertAttend(Attend attend);
    public void updateAttend(Attend attend);
    public Integer selectAttendCount(Attend attend);

    List<AttendVo> selectAttendUnPunch(Attend attend);

    void autoSetAttendType();
}
