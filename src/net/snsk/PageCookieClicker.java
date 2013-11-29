package net.snsk;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageCookieClicker {

	private WebDriver driver;
	private final By bigCookie = By.id("bigCookie");
	private final By cookieCount = By.id("cookies");
	private final By product1 = By.id("product1");

	public PageCookieClicker(WebDriver driver) {
		this.driver = driver;
	}

	public String getCookieCount() {
		return driver.findElement(cookieCount).getText();
	}

	public int getCookieCountInt() {
		if (!driver.findElement(cookieCount).getText().split(" ")[0].equals("")) {
			String val;
			val = driver.findElement(cookieCount).getText().split(" ")[0];
			if (val.indexOf(",") > -1) {
				val = val.replace(",", "");
			}
			return Integer.parseInt(val);
		} else {
			return 0;
		}
	}

	public void product1Click() {
		WebDriverWait wait = new WebDriverWait(driver, 1);
		WebElement product1Element = wait.until(ExpectedConditions
				.elementToBeClickable(product1));
		product1Element.click();
	}

	// Viewの数字とシンクロしながらカウントするパターン… あんまり使えない
	public void clickCookieViewSync(int count) {
		WebDriverWait wait = new WebDriverWait(driver, 1);
		WebElement cookieElement = wait.until(ExpectedConditions
				.elementToBeClickable(bigCookie));
		wait.until(ExpectedConditions.elementToBeClickable(cookieCount));

		for (int i = 0; i < count; i++) {
			if (getCookieCountInt() != i) {
				i = getCookieCountInt();
				System.out.println("adjust count var!");
			}
			cookieElement.click();
		}
	}

	public void clickCookie(int count) {
		WebDriverWait wait = new WebDriverWait(driver, 1);
		WebElement cookieElement = wait.until(ExpectedConditions
				.elementToBeClickable(bigCookie));

		for (int i = 0; i < count; i++) {
			cookieElement.click();
		}
	}

}
