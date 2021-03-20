package vo;

public class BaseVO {
    private String name;
    private String password;
    private String vercode;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVercode() {
        return vercode;
    }

    public void setVercode(String vercode) {
        this.vercode = vercode;
    }

    @Override
    public String toString() {
        return "BaseVO{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", vercode='" + vercode + '\'' +
                '}';
    }
}
