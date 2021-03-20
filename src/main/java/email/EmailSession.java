package email;

import javax.mail.Authenticator;
import javax.mail.Session;
import java.util.Properties;

public class EmailSession{
    private Session session;

    public Session getSession() {
        return session;
    }

    public EmailSession(Session session) {
        this.session = session;
    }

    public EmailSession() {
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
