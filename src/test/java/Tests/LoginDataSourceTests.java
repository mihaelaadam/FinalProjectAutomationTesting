package Tests;

import ObjectModels.LoginModel;
import PageObjects.AccountPage;
import PageObjects.LoginPage;
import Utils.Tools;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class LoginDataSourceTests extends BaseTest {
    LoginPage loginPage;
    AccountPage accountPage;

    @DataProvider(name = "loginNegativeDp")
    public Iterator<Object[]> jsonDpCollection() throws IOException {
        Collection<Object[]> dp = new ArrayList<>();
//      here we start json deserialization of json into LoginModel obj
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src\\test\\resources\\Data\\testdata.json");

        LoginModel[] lms = objectMapper.readValue(file, LoginModel[].class);

        for (LoginModel lm : lms)
            dp.add(new Object[]{lm});

        return dp.iterator();
    }

    @Test(dataProvider = "loginNegativeDp")
    public void loginWithJsonAsDataSource(LoginModel lm) {
        loginLm(lm);
    }


    //#####################################MYSQL#######################
    @DataProvider(name = "loginPositiveDp")
    public Iterator<Object[]> mysqlDpCollection() throws Exception {
//        show DB connection details
        System.out.println("Use dbHostname:"+dbHostname);
        System.out.println("Use dbUser:"+dbUser);
        System.out.println("Use dbPort:"+dbPort);
        System.out.println("Use dbSchema:"+dbSchema);
        Collection<Object[]> dp = new ArrayList<>();
//        db connection
        Connection connection = DriverManager.getConnection("jdbc:mysql://"+dbHostname+":"+dbPort+  //  jdbc - se pune by default
                "/"+dbSchema, dbUser,dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM login_positive");
        while (resultSet.next()){
            LoginModel lm = new LoginModel(getEscapedElement(resultSet,"email"),
                    getEscapedElement(resultSet,"password"),
                    getEscapedElement(resultSet,"browser"),
                    getEscapedElement(resultSet,"emailError"),
                    getEscapedElement(resultSet,"passwordError"));
            dp.add(new Object[]{lm});
        }
        return dp.iterator();
    }

    @Test(dataProvider = "loginPositiveDp")
    public void loginWithSQLAsDataSource(LoginModel lm) {
        loginLmPos(lm);
    }
    //   login with loginModel
    private void loginLm(LoginModel lm) {   //  2
        System.out.println(lm);
        loginNegative(lm.getAccount().getEmail(), lm.getAccount().getPassword(), lm.getAccount().getBrowser(), lm.getEmailError(), lm.getPasswordError());
    }
    private void loginLmPos(LoginModel lm) {   //  2
        System.out.println(lm);
        loginPositive(lm.getAccount().getEmail(), lm.getAccount().getPassword(), lm.getAccount().getBrowser());
    }

        private void loginNegative(String email, String password, String browser, String emailErr, String passErr) {  //  1
            System.out.println("Login with email:" + email + "/password:" + password + "=> on browser:" + browser);
            setUpDriver(browser);
            driver.get(baseUrl);
            loginPage = new LoginPage(driver);
            loginPage.goToLoginPage();
            loginPage.login(email, password);

            System.out.println("Login Finished, verify error message");
            Assert.assertEquals(loginPage.getEmailErr(), emailErr);
            Assert.assertEquals(loginPage.getPassErr(), passErr);
        }
    private void loginPositive(String email, String password, String browser) {  //  1
        System.out.println("Login with email:" + email + "/password:" + password + "=> on browser:" + browser);
        setUpDriver(browser);
        driver.get(baseUrl);
        loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
        loginPage.login(email, password);

        System.out.println("Login Finished");
        accountPage = new AccountPage(driver);
        Assert.assertEquals(accountPage.getLogoutBtn(), "Logout");
    }

    private String getEscapedElement(ResultSet resultSet, String element) throws SQLException {
        return Tools.replaceElements(resultSet.getString(element), "''", "");
    }

}
