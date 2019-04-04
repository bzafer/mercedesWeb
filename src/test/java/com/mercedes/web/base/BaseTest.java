package com.mercedes.web.base;

import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import org.apache.commons.lang3.StringUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BaseTest {

	public static WebDriver driver;
	//public static String environment = System.getProperty("environment","PROD");

	@BeforeScenario
	public void setUp() throws MalformedURLException, Exception {

		String baseUrl = "https://atlasimtest1.mbfh.com.tr/login.aspx?ReturnUrl=";
//		String baseUrl="https://atlasimtest1.mbfh.com.tr/DocumentUpload.aspx?CreditFileVersionId=231147&FromProposal=True";
//		String baseUrl = "http://pp.koton.com";

		DesiredCapabilities capabilities;

		if (StringUtils.isEmpty(System.getenv("key"))){
			capabilities = DesiredCapabilities.chrome();
			//capabilities = DesiredCapabilities.safari();
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("profile.default_content_setting_values.notifications", 2);


			final String[] aa = {""};

			Thread thread2 = new Thread() {
				public void run(){
					if (aa[0].equals("a")) {
						try {

							System.out.println("Thread2 Running");
							System.out.println(345436476);
							Robot robot = new Robot();
							robot.delay(3000);
							robot.keyPress(KeyEvent.VK_DOWN);
							robot.keyRelease(KeyEvent.VK_DOWN);
							robot.delay(1000);
							Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
							robot.delay(3000);
							robot.mouseMove(dimension.width / 2, dimension.height / 3);
							robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
							robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

							robot.keyPress(KeyEvent.VK_ESCAPE);
							robot.delay(300);
							robot.keyRelease(KeyEvent.VK_ESCAPE);

						} catch (Exception e) {

						}
						currentThread().interrupt();
					}
				}

			};

			thread2.start();
			aa[0] ="a";

			ChromeOptions options = new ChromeOptions();
			//SafariOptions options = new SafariOptions();
			//options.setUseCleanSession(true);
			//options.setUseTechnologyPreview(true);
            options.setExperimentalOption("prefs", prefs);
			//options.addArguments("--kiosk");
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
			driver = new ChromeDriver(capabilities);
			//driver = new SafariDriver(capabilities);
		}
		else {
			capabilities =  DesiredCapabilities.firefox();
			capabilities.setCapability("key", System.getenv("key"));
			driver = new RemoteWebDriver(new URL("http://hub.testinium.io/wd/hub"), capabilities);
			((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
		}


		driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS).implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
		driver.get(baseUrl);
	}

	@AfterScenario
	public void tearDown() throws Exception {
		driver.quit();
	}
}