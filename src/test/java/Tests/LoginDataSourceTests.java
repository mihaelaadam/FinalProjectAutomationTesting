package Tests;

import ObjectModels.LoginModel;
import PageObjects.AccountPage;
import PageObjects.LoginPage;
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
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src\\test\\resources\\Data\\testdata.json");

        LoginModel[] lms = objectMapper.readValue(file, LoginModel[].class);

        for (LoginModel lm : lms)
            dp.add(new Object[]{lm});

        return dp.iterator();
    }

    @Test(dataProvider = "loginNegativeDp")
    public void loginWithJsonAsDataSource(LoginModel lm) {
        loginLmNeg(lm);
    }


    @DataProvider(name = "loginPositiveDp")
    public Iterator<Object[]> mysqlDpCollection() throws Exception {
        System.out.println("Use dbHostname:"+dbHostname);
        System.out.println("Use dbUser:"+dbUser);
        System.out.println("Use dbPort:"+dbPort);
        System.out.println("Use dbSchema:"+dbSchema);
        Collection<Object[]> dp = new ArrayList<>();
        Connection connection = DriverManager.getConnection("jdbc:mysql://"+dbHostname+":"+dbPort+
                "/"+dbSchema, dbUser,dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM login_positive");
        while (resultSet.next()){
            LoginModel lm = new LoginModel(resultSet.getString("email"), resultSet.getString("password"),
                    resultSet.getString("browser"), resultSet.getString("generalError"));
            dp.add(new Object[]{lm});
        }
        return dp.iterator();
    }

    @Test(dataProvider = "loginPositiveDp")
    public void loginWithSQLAsDataSource(LoginModel lm) {
        loginLmPos(lm);
    }


    private void loginLmNeg(LoginModel lm) {
        System.out.println(lm);
        loginNegative(lm.getAccount().getEmail(), lm.getAccount().getPassword(), lm.getAccount().getBrowser(), lm.getGeneralError());
    }
    private void loginLmPos(LoginModel lm) {
        System.out.println(lm);
        loginPositive(lm.getAccount().getEmail(), lm.getAccount().getPassword(), lm.getAccount().getBrowser());
    }


        private void loginNegative(String email, String password, String browser, String error) {
            System.out.println("Login with email:" + email + "/password:" + password + "=> on browser:" + browser);
            setUpDriver(browser);
            driver.get(baseUrl);
            loginPage = new LoginPage(driver);
            loginPage.goToLoginPage();
            loginPage.login(email, password);

            System.out.println("Login Finished, verify error message");
            Assert.assertEquals(loginPage.getGeneralErr(), error);
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
        Assert.assertTrue(accountPage.logoutButtonIsDisplayed());
    }

}
