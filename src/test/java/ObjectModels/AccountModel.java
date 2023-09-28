package ObjectModels;

import javax.xml.bind.annotation.XmlElement;

public class AccountModel {
    private String email;
    private String password;
    private String browser;

    public String getEmail() {
        return email;
    }

    @XmlElement
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    @XmlElement
    public void setPassword(String password) {
        this.password = password;
    }

    public String getBrowser() {
        return browser;
    }

    @XmlElement
    public void setBrowser(String browser) {
        this.browser = browser;
    }
}
