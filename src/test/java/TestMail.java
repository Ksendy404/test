import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Jubillee on 25.04.2018.
 */
public class TestMail {

    @Test(description = "Test mail")
    public void testMail() throws Exception {
        String login = "randomtestsel";
        String password = "testingselenium";

        //Set property
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        //Go to yandex
        driver.get("https://yandex.ru");

        //Go to email
        MailPage mailPage = new MailPage(driver);
        mailPage.goToMail();

        //Login
        mailPage.loginAction(login, password);

        //Search email
        mailPage.searchEmail("Команда");
        mailPage.clickEmailByIndex(0);

        //Check email text
        Assert.assertTrue(mailPage.getEmailTitle().contains("Как читать почту с мобильного"));
        Assert.assertTrue(mailPage.getEmailSender().contains("Команда Яндекс.Почты"));
        Assert.assertTrue(mailPage.getEmailText().contains("Как установить с компьютера"));

        //Logout
        mailPage.logout();
        Assert.assertTrue(mailPage.isGoToMailButtonDisplayed());

        //Exit browser
        driver.quit();
    }
}
