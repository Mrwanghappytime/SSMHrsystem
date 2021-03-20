package service.Imp;

import dao.AttendDao;
import email.EmailSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import po.Attend;
import po.Employee;
import po.Manager;
import vo.AttendVo;
import vo.OparationVO;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.mail.internet.*;

@Service("utilsService")
public class UtilsService {
    @Autowired
    private EmailSession emailSession;
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm:ss");
    public  OparationVO punch(HttpServletRequest request, int type, AttendDao attendDao){
        Date date = new Date();
        String str = simpleDateFormat.format(date);
        //attend.setDutyDate(simpleDateFormat1.parse(str.substring(0,10)));
        Date dutyDate = null;
        try {
            dutyDate = simpleDateFormat1.parse(str.substring(0, 10));
        }catch (Exception e){
            return new OparationVO(1, "插入打卡记录失败");
        }
        String punchTiem = str.substring(11);
        boolean iscome = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) >= 11 ? false:true;
        Attend attend = new Attend();
        attend.setCommed(iscome);
        attend.setPunchTime(punchTiem);
        attend.setDutyDate(dutyDate);
        fillAttend(request,type,attend);
        try {
            attendDao.insertAttend(attend);
            return new OparationVO(0,"插入打卡记录成功");
        }catch (Exception e) {
            e.printStackTrace();
            return new OparationVO(1, "插入打卡记录失败");
        }
    }


    public OparationVO<List<AttendVo>> viewUnPunch(HttpServletRequest request, int i, AttendDao attendDao) {
        Attend attend = new Attend();
        fillAttend(request,i,attend);
        try {
            List<AttendVo> attends = attendDao.selectAttendUnPunch(attend);
            return new OparationVO<>(0,"查询异常记录成功",attends);
        }catch (Exception e){
            e.printStackTrace();
            return new OparationVO<>(0,"查询异常记录失败");
        }
    }

    private void fillAttend(HttpServletRequest request, int i, Attend attend) {
        if(i == 0){
            Employee employee = (Employee) request.getSession().getAttribute("user");
            attend.setMan(false);
            attend.setEmployeeId(employee.getId());
        }else{
            Manager employee = (Manager) request.getSession().getAttribute("user");
            attend.setMan(true);
            attend.setEmployeeId(employee.getId());
        }
    }

    public void sendEmailHelloToEmp(Employee employee) {
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        sendEmailToEmps(employees);
    }

    public void sendEmailToEmps(List<Employee> employees) {
        for(Employee e : employees){
            sendEmail("欢迎" + e.getName() + "加入我们公司,望君工作愉快",e.getEmail(),"欢迎");
        }
    }
    public void sendEmail(String text,String to,String subject){
        MimeMessage mimeMessage = new MimeMessage(emailSession.getSession());
        try {
            mimeMessage.setFrom(new InternetAddress(emailSession.getSession().getProperty("mail.user")));
            mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(to));
            mimeMessage.setContent(text,"text/html;charset=UTF-8");
            mimeMessage.setSubject(subject);
            mimeMessage.setSentDate(new Date());
            mimeMessage.saveChanges();
            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }

}
