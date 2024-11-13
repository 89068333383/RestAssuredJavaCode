import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TestSelenium {

    @Test
    @DisplayName("Авторизация в портале")
    public static void login() {
//        System.setProperty("webdriver.chrome.driver", "/path/to/driver/chrome-driver");
        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get("https://aqa-admin.javacode.ru/login");

// ввод логина

            WebElement inputFieldName = driver.findElement(By.id("username"));
            inputFieldName.sendKeys("klyuev_alexey");
//ввод пароля
            WebElement inputFieldPass = driver.findElement(By.id("password"));
            inputFieldPass.sendKeys("4~gBt(K4siN<bTR");
//нажатик кнопли ввод
            WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/form/div/div/div[3]/div[2]/div/button"));
            searchButton.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
//выбор меню "Интервью"
            WebElement linkOne = driver.findElement(By.linkText("Интервью"));

            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Интервью")));
            element.click();
//поиск и нажати кнопки добавить
            WebElement searchButtonTwo = driver.findElement(By.xpath("//*[@id=\"example2_length\"]/div[1]/div[4]/div/div/span/button"));
            searchButtonTwo.click();
//поле ввода названия
            WebElement inputFieldNameInter = driver.findElement(By.xpath("//input[@type='input' and @class='form-control ']"));
            inputFieldNameInter.sendKeys("KlyuevAlexeyTest");

            WebElement searchButtonAdd = driver.findElement(By.xpath("/html/body/div[3]/div/div/div/div/div[3]/button"));
            searchButtonAdd.click();



        } finally {
            driver.close();
            driver.quit();
        }
    }

}
