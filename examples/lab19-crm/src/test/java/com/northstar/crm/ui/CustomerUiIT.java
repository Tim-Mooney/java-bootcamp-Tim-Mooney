package com.northstar.crm.ui;

import com.northstar.crm.ui.pages.CustomerFormPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TakesScreenshot;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.OutputType;
import org.junit.jupiter.api.extension.AfterEachCallback;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerUiIT {

    @LocalServerPort
    int port;

    WebDriver driver;
    WebDriverWait wait;

    @RegisterExtension
    AfterEachCallback screenshotAndTeardown = context -> {
        try {
            if (context.getExecutionException().isPresent() && driver != null) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Path dir = Path.of("target");
                Files.createDirectories(dir); // ensure it exists
                Files.write(dir.resolve("ui-failure-" + context.getDisplayName() + ".png"), screenshot);
            }
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    };

    @BeforeAll
    static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new", "--window-size=1280,900");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

//    @AfterEach
//    void tearDown() {
//        if (driver != null) driver.quit();
//    }

    @Test
    void createCustomerViaUi() {
        String baseUrl = "http://localhost:" + port;
        var page = new CustomerFormPage(driver).open(baseUrl);
        page.fill("CUS-2001", "Ui Customer", "ui.customer@example.com", "PROSPECT").submit();
        assertThat(page.resultText()).contains("CUS-2001");

        var page2 = new CustomerFormPage(driver).open(baseUrl);
        page2.fill("CUS-2001", "Ui Customer2", "ui.customer2@example.com", "PROSPECT").submit();
        assertThat(page.resultText()).contains("Ui Customer2");
    }

    @Test
    void failOnPurpose(){
        String baseUrl = "http://localhost:" + port;
        var page = new CustomerFormPage(driver).open(baseUrl);
        page.fill("CUS-9999", "Deliberate Fail", "fail@example.com", "PROSPECT").submit();
        assertThat(page.resultText()).contains("THIS_WILL_NEVER_MATCH");
    }
}
