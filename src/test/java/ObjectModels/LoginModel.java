package ObjectModels;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LoginModel {
    private AccountModel account;
    private String emailError;
    private String passwordError;
    public LoginModel() {
    }

    public LoginModel(String email, String password, String browser, String emailError, String passwordError) {
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

    public String getEmailError() {
        return emailError;
    }

    public String getPasswordError() {
        return passwordError;
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
