package de.retest.recheck.example.demoapp;

import com.epam.healenium.SelfHealingDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.common.base.Strings;


import de.retest.recheck.example.Util;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

class ExplicitChecksTest {

    static protected SelfHealingDriver driver;

    @BeforeAll
    static public void setUp() throws MalformedURLException {

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        WebDriver delegate = new ChromeDriver();
        driver = SelfHealingDriver.create(delegate);

        //driver = new DriverContext(DriverType.LOCAL).getDriver(BrowserType.CHROME);

        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1200, 800));

    }

    @Test
    public void loginCheck() throws Exception {

        final String file = "pages/app/login-form.html";
        //final String file = "pages/app/login-form_css-broken.html";
        //final String file = "pages/app/login-form_id-change.html";
        //final String file = "pages/app/login-form_btn-change.html";

        driver.get(Util.toClasspathUrl(file));
        Util.sleep(3);

        driver.findElement(By.id("username")).sendKeys("Simon");
        driver.findElement(By.id("password")).sendKeys("secret");
        Util.sleep(3);

    }

    @AfterAll
    static public void afterAll() {
        if (driver != null) {
            driver.quit();
        }
    }
}
