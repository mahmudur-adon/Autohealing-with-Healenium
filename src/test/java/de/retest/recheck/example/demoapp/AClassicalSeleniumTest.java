package de.retest.recheck.example.demoapp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import de.retest.recheck.example.Util;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

class AClassicalSeleniumTest {

	private static WebDriver browserDriver;

	@BeforeEach
	void setup() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--start-maximized");
		chromeOptions.addArguments("--remote-allow-origins=*");
		//driver.Manage().Window.Maximize();
		browserDriver = new ChromeDriver(chromeOptions);
		browserDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@Test
	public void loginCheck() throws Exception {

		//final String file = "pages/app/login-form.html";
		//final String file = "pages/app/login-form_css-broken.html";
		final String file = "pages/app/login-form_id-change.html";
		//final String file = "pages/app/login-form_btn-change.html";

		browserDriver.get( Util.toClasspathUrl( file ) );
		Util.sleep( 6 );

		browserDriver.findElement( By.id( "username" ) ).sendKeys( "Simon" );
		browserDriver.findElement( By.id( "password" ) ).sendKeys( "secret" );
		Util.sleep( 6 );

		browserDriver.findElement( By.id( "sign-in" ) ).click();

		assertEquals( browserDriver.findElement( By.tagName( "h4" ) ).getText(), "Success!" );
		Util.sleep( 6 );
	}

	@AfterEach
	public void tearDown() throws InterruptedException {
		browserDriver.quit();
	}
}
