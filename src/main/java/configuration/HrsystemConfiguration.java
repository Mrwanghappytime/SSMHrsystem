package configuration;
import email.EmailSession;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import realms.EmployeeRealm;
import realms.ManagerRealm;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

@Configuration
@ImportResource("classpath:spring-shiro.xml")
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
    @Bean("credentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(1024);
        return hashedCredentialsMatcher;
    }
    @Bean("employeeRealm")
    @Autowired
    public EmployeeRealm employeeRealm(HashedCredentialsMatcher hashedCredentialsMatcher){
        EmployeeRealm employeeRealm = new EmployeeRealm();
        employeeRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return employeeRealm;
    }
    @Bean("managerRealm")
    @Autowired
    public ManagerRealm managerRealm(HashedCredentialsMatcher hashedCredentialsMatcher){
        ManagerRealm managerRealm = new ManagerRealm();
        managerRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return managerRealm;
    }
}
