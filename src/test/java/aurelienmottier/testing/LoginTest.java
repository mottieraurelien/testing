package aurelienmottier.testing;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlToBe;

class LoginTest {

    @Test
    void should_redirect_to_homepage_when_logging_with_right_credentials() {

        // [Arrange] Open browser, maximize and go to website
        final ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito", "start-maximized");
        final WebDriver driver = new ChromeDriver(options);
        driver.get("https://practicetestautomation.com/practice-test-login/");

        // [Arrange] Wait for browser to render HTML
        final WebDriverWait wait = new WebDriverWait(driver, ofSeconds(5));
        wait.until(presenceOfElementLocated(By.name("username"))).sendKeys("student");
        wait.until(presenceOfElementLocated(By.name("password"))).sendKeys("Password123");
        wait.until(presenceOfElementLocated(By.id("submit"))).click();

        // [Assert]
        wait.until(urlToBe("https://practicetestautomation.com/logged-in-successfully/"));
        final WebElement postHeaderH1 = wait.until(presenceOfElementLocated(By.className("post-header")))
                .findElement(By.className("post-title"));
        assertThat(postHeaderH1.getText()).isEqualTo("Logged In Successfully");

        driver.close();

    }

}