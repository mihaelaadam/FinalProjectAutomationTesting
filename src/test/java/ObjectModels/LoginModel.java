package ObjectModels;


public class LoginModel {
    private AccountModel account;
    private String generalError;
    public LoginModel() {
    }

    public LoginModel(String email, String password, String browser, String generalError) {
        AccountModel accountM = new AccountModel();
        accountM.setPassword(password);
        accountM.setEmail(email);
        accountM.setBrowser(browser);
        this.account = accountM;
        this.generalError = generalError;
    }

    public AccountModel getAccount() {
        return account;
    }

    public String getGeneralError() {
        return generalError;
    }

    @Override
    public String toString() {
        return "LoginData{" +
                "account={email:" + account.getEmail() + ",password:" + account.getPassword() +
                ", browser:" + account.getBrowser() + "}, passwordError='" + generalError + '\'' +
                '}';
    }
}
