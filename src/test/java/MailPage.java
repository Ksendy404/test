import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by Jubillee on 22.04.2018.
 */
public class MailPage {

    private final WebDriver driver;

    public MailPage(WebDriver driver) {
        this.driver = driver;
    }

    By LOCATOR_MAIL_BUTTON = By.xpath("//a[text()='Почта']");
    By LOCATOR_LOGIN_INPUT = By.xpath("//input[@name='login']");
    By LOCATOR_LOGIN_BUTTON = By.xpath("//span[text()='Войти']");
    By LOCATOR_PASSWORD = By.xpath("//input[@type='password']");
    By LOCATOR_SEARCH_MAIL_INPUT = By.xpath("//input[@class='textinput__control']");
    By LOCATOR_MAILS_LIST = By.xpath("//div[@class='mail-MessageSnippet-Content']");
    By LOCATOR_MAIL_SENDER = By.xpath("//div[@class='mail-Message-Sender']");
    By LOCATOR_MAIL_TEXT = By.xpath("//table/tbody/tr[2]/td");
    By LOCATOR_MAIL_TITLE = By.xpath("//span[@class='mail-Message-Toolbar-Subject-Wrapper']");
    By LOCATOR_USER_ACCOUNT = By.xpath("//div[@class='mail-User-Name']");
    By LOCATOR_LOGOUT_BUTTON = By.xpath("//a[@data-metric='Выйти из сервисов Яндекса']");

    protected int timeout = 15;

    /****************************************
     **************Page method****************
     ***************************************/

    /**
     * Go to mail login page
     *
     * @param
     */
    public MailPage goToMail() {
        driver.findElement(LOCATOR_MAIL_BUTTON).click();
        return this;
    }

    /**
     * Login action
     *
     * @param user
     * @param password
     */
    public MailPage loginAction(String user, String password) throws Exception {
        driver.findElement(LOCATOR_LOGIN_INPUT).sendKeys(user);
        driver.findElement(LOCATOR_PASSWORD).sendKeys(password);
        driver.findElement(LOCATOR_LOGIN_BUTTON).click();
        WebElement dynamicElement = (new WebDriverWait(driver, timeout))
                .until(ExpectedConditions.presenceOfElementLocated(LOCATOR_USER_ACCOUNT));
        return this;
    }

    /**
     * Search mail by text
     *
     * @param text
     */
    public MailPage searchEmail(String text) {
        driver.findElement(LOCATOR_SEARCH_MAIL_INPUT).clear();
        driver.findElement(LOCATOR_SEARCH_MAIL_INPUT).sendKeys(text + Keys.ENTER);
        return this;
    }

    /**
     * Click email by index
     */
    public MailPage clickEmailByIndex(int index) throws Exception {
        driver.findElements(LOCATOR_MAILS_LIST).get(index).click();
        WebElement dynamicElement = (new WebDriverWait(driver, timeout))
                .until(ExpectedConditions.presenceOfElementLocated(LOCATOR_MAIL_TITLE));
        return this;
    }

    /**
     * Get email title
     */
    public String getEmailTitle() {
        return driver.findElement(LOCATOR_MAIL_TITLE).getText();
    }

    /**
     * Get email sender
     */
    public String getEmailSender() {
        return driver.findElement(LOCATOR_MAIL_SENDER).getText();
    }

    /**
     * Get email text
     */
    public String getEmailText() {
        return driver.findElement(LOCATOR_MAIL_TEXT).getText();
    }

    /**
     * Logout
     */
    public MailPage logout() {
        driver.findElement(LOCATOR_USER_ACCOUNT).click();
        driver.findElement(LOCATOR_LOGOUT_BUTTON).click();
        return this;
    }

    /**
     * Check login button
     *
     * @param
     */
    public boolean isGoToMailButtonDisplayed() {
        return driver.findElement(LOCATOR_MAIL_BUTTON).isDisplayed();
    }

}
