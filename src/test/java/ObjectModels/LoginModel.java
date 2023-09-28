package ObjectModels;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LoginModel {
    private AccountModel account;
    private String emailError;
    private String passwordError;
    public LoginModel() {
    }

    public LoginModel(String email, String password, String browser, String emailError, String passwordError) { //  pentru CSV. Se dezactiveaza pentru JSON si XML
        AccountModel accountM = new AccountModel();
        accountM.setPassword(password);
        accountM.setEmail(email);
        accountM.setBrowser(browser);
        this.account = accountM;
        this.emailError = emailError;
        this.passwordError = passwordError;
    }

    public AccountModel getAccount() {
        return account;
    }

    @XmlElement
    public void setAccount(AccountModel account) {
        this.account = account;
    }

    public String getEmailError() {
        return emailError;
    }

    @XmlElement
    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    @XmlElement
    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }


    @Override
    public String toString() {
        return "LoginData{" +
                "account={email:" + account.getEmail() + ",password:" + account.getPassword() +
                ", browser:" + account.getBrowser() + "}, emailError='" + emailError + '\'' +
                ", passwordError='" + passwordError + '\'' +
                '}';
    }
}
