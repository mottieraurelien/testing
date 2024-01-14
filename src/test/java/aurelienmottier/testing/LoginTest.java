package aurelienmottier.testing;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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

public class LoginTest {

    private static WebDriver driver;
    private static WebDriverWait wait;

    @Given("the user navigated to login page")
    public void given_the_user_navigated_to_login_page() {
        final ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito", "start-maximized", "--headless=new");
        driver = new ChromeDriver(options);
        driver.get("https://practicetestautomation.com/practice-test-login/");
        wait = new WebDriverWait(driver, ofSeconds(10), ofSeconds(1));
    }

    @When("the user inputs a valid username {word} and its password")
    public void when_the_user_inputs_a_valid_username_and_its_password(final String username) {
        wait.until(presenceOfElementLocated(By.name("username"))).sendKeys(username);
        wait.until(presenceOfElementLocated(By.name("password"))).sendKeys("Password123");
    }

    @When("the user submits the login form")
    public void when_the_user_submits_the_login_form() {
        wait.until(presenceOfElementLocated(By.id("submit"))).click();
    }

    @Then("the user is forwarded to homepage")
    public void then_the_user_is_forwarded_to_homepage() {
        wait.until(urlToBe("https://practicetestautomation.com/logged-in-successfully/"));
        final WebElement postHeaderH1 = wait.until(presenceOfElementLocated(By.className("post-header")))
                .findElement(By.className("post-title"));
        assertThat(postHeaderH1.getText()).isEqualTo("Logged In Successfully");
    }

    @Then("the user closes the browser")
    public void then_the_user_closes_the_browser() {
        driver.quit();
    }

}