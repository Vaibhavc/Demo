package com.demo.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Base {

	public WebDriver driver;

	public Base() {
		System.setProperty("webdriver.chrome.driver",
				"src/main/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver();
	}

	public void waitForElementToPresent(By by, int timeout) {

		new WebDriverWait(driver, timeout).until(ExpectedConditions
				.presenceOfElementLocated(by));
	}

	public void waitForElementToDisplay(By by, int timeout) {

		new WebDriverWait(driver, timeout).until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(by));
	}
	public boolean isElementDisplayed(WebElement e) {
		try {
			return e.isDisplayed();

		} catch (Exception element) {

			return false;
		}
	}

	public void verifyTrue(boolean condition, String passMsg, String failMsg) {
		if (condition) {
			System.out.println("Passed - " + passMsg);
			Assert.assertTrue(condition, passMsg);
		} else {
			System.out.println("Error - " + failMsg);
			Assert.assertTrue(condition, failMsg);
		}
	}

	public By getBy(String locator) {
		String strategy = "";
		String value = "";
		By by;
		if (!locator.isEmpty()) {
			int index = locator.indexOf('=');
			strategy = locator.substring(0, index).trim().toUpperCase();
			value = locator.substring(index + 1).trim();
		}
		switch (strategy) {
		case "XPATH":
			by = By.xpath(value);
			break;
		case "ID":
			by = By.id(value);
			break;

		default:
			by = By.id(value);
			break;
		}
		return by;

	}
}
