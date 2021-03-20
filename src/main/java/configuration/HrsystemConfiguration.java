package configuration;
import email.EmailSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

@Configuration
public class HrsystemConfiguration {
    private String from = "xxxx@qq.com";
    private String password = "xxx";
    @Bean
    public EmailSession session(){
        final Properties properties = new Properties();
        //Session session = Session.getDefaultInstance(properties);
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", "smtp.qq.com"); //设置协议主机，qq邮箱的 SMTP 服务器地址为: smtp.qq.com

        properties.put("mail.user", from); // 发件人的账号
        properties.put("mail.password", password); //发件人的密码
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(properties.getProperty("mail.user"),properties.getProperty("mail.password"));
            }
        };
        return new EmailSession(Session.getDefaultInstance(properties,authenticator));
    }
}
