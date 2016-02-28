package net.snsk;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.containsString;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RunClicker {

	private WebDriver driver;

	@Before
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver",
				"./chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
	}

	@After
	public void afterTest() {
		driver.quit();
	}

	@Test
	public void CookieMainScenario() {
		driver.get("http://orteil.dashnet.org/cookieclicker/");
		PageCookieClicker page = new PageCookieClicker(driver);

		int aimCookieTotalCount = 10000;
		int buildBBACost = 100;
		for (int i = 0; i < aimCookieTotalCount; i++) {
			page.clickCookie(1);
			if (page.getCookieCountInt() > buildBBACost) {
				page.product1Click();
				buildBBACost *= 1.15;
			}
		}
		//containsString‚Í•”•ªˆê’v‚È‚Ì‚ÅŒµ–§‚È”»’è‚É‚ÍNG e.g."20" == "200".
		assertThat(page.getCookieCount(), containsString("10,000"));
	}
}
