package com.demo.test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.demo.base.Base;
import com.opencsv.CSVReader;

public class Demo extends Base {

	String contactCSV_PATH = "src/main/resources/data/contact.csv";
	String accountCSV_PATH = "src/main/resources/data/accounts.csv";

	By userNameField = getBy("xpath=//*[@id='user_name']");
	By passwordField = getBy("xpath=//*[@id='user_password']");
	By loginBtn = getBy("xpath=//*[@id='login_button']");
	By contactTab = getBy("xpath=//a[.='Contacts']");
	By accountTab = getBy("xpath=//a[.='Accounts']");
	By createContactLink = getBy("xpath=//a/span[.='Create Contact']");
	By createAccountLink = getBy("xpath=//a/span[.='Create Account']");
	By nameTextbx = getBy("xpath=//*[@id='name']");
	By websiteTextbx = getBy("xpath=//*[@id='website']");

	By saveBtn = getBy("xpath=//*[@id='SAVE_HEADER']");
	By saveFooterBtn = getBy("xpath=//*[@id='SAVE_FOOTER']");
	By validationMsgTxt = getBy("xpath=//*[@class='required validation-message']");

	By resultCountText = getBy("xpath=//*[@id='resultStats']");
	By websiteLink = getBy("xpath=//span[.='messi.com']");

	By nameInput = getBy("xpath=//*[@class='moduleTitle']/h2");
	By editBtn = getBy("xpath=//*[@id='edit_button']");
	By accountNameinput = getBy("xpath=.//*[@id='name']");

	By streetTextbxAcc = getBy("xpath=//*[@id='billing_address_street']");
	By cityTextbxAcc = getBy("xpath=//*[@id='billing_address_city']");
	By zipTextbxAcc = getBy("xpath=//*[@id='billing_address_postalcode']");
	By stateTextbxAcc = getBy("xpath=//*[@id='billing_address_country']");
	By emailstreetTextbxAcc = getBy("xpath=//input[@id='Accounts0emailAddress0']");
	By emailstreetTextbxcont = getBy("xpath=//*[@id='Contacts0emailAddress0']");

	By salutationDropdown = getBy("xpath=//*[@id='salutation']");
	By firstNameTextbox = getBy("xpath=//*[@id='first_name']");
	By lastNameTextbox = getBy("xpath=//*[@id='last_name']");
	By titleTextbox = getBy("xpath=//*[@id='title']");
	By accountNameDropdown = getBy("xpath=//*[@id='account_name']");

	public WebElement getEmailstreetTextbxCont() {
		return driver.findElement(emailstreetTextbxcont);
	}

	public WebElement getSalutationDropdown() {
		return driver.findElement(salutationDropdown);
	}

	public WebElement getFirstNameTextbox() {
		return driver.findElement(firstNameTextbox);
	}

	public WebElement getLastNameTextbox() {
		return driver.findElement(lastNameTextbox);
	}

	public WebElement getWebsiteTextbox() {
		return driver.findElement(websiteTextbx);
	}

	public WebElement getTitleTextbox() {
		return driver.findElement(titleTextbox);
	}

	public WebElement getAccountNameDropdown() {
		return driver.findElement(accountNameDropdown);
	}

	public WebElement getAccountName() {
		return driver.findElement(accountNameinput);
	}

	public WebElement getEmailstreetTextbxAcc() {
		return driver.findElement(emailstreetTextbxAcc);
	}

	public WebElement getStateTextbxAcc() {
		return driver.findElement(stateTextbxAcc);
	}

	public WebElement getZipTextbxAcc() {
		return driver.findElement(zipTextbxAcc);
	}

	public WebElement getCityTextbxAcc() {
		return driver.findElement(cityTextbxAcc);
	}

	public WebElement getStreetTextbxAcc() {
		return driver.findElement(streetTextbxAcc);
	}

	public WebElement getNameInput() {
		return driver.findElement(nameInput);
	}

	public WebElement getNameTextbx() {
		return driver.findElement(nameTextbx);
	}

	public WebElement getValidationMsgText() {
		return driver.findElement(validationMsgTxt);
	}

	public WebElement getSaveBtn() {
		return driver.findElement(saveBtn);
	}

	public WebElement getSaveFooterBtn() {
		return driver.findElement(saveFooterBtn);
	}

	public WebElement getCreateContactLink() {
		return driver.findElement(createContactLink);
	}

	public WebElement getCreateAccountLink() {
		return driver.findElement(createAccountLink);
	}

	public WebElement getContactTab() {
		return driver.findElement(contactTab);
	}

	public WebElement getAccountTab() {
		return driver.findElement(accountTab);
	}

	public WebElement getUsernameTxtBx() {
		return driver.findElement(userNameField);
	}

	public WebElement getPasswordTxtBx() {
		return driver.findElement(passwordField);
	}

	public WebElement getLoginBtn() {
		return driver.findElement(loginBtn);
	}

	@BeforeTest
	public void openPage() throws InterruptedException {
		driver.get("http://buzzinglab.com/wr/");
		driver.manage().window().maximize();
		waitForElementToPresent(loginBtn, 5);
		getUsernameTxtBx().sendKeys("tester2");
		getPasswordTxtBx().sendKeys("kLSrQOa");
		getLoginBtn().click();
	}

	@Test(enabled = false)
	public void verifyLastNameField() throws InterruptedException {
		getContactTab().click();
		waitForElementToPresent(createContactLink, 10);
		getCreateContactLink().click();
		getSaveBtn().click();
		verifyTrue(
				getValidationMsgText().getText().equals(
						"Missing required field: Last Name"),
				"Last Name field of the contact does not accept blanks",
				"Last Name field of the contact accepts blanks");

	}

	@Test(enabled = false)
	public void verifyAccountNameFieldLimit() throws InterruptedException {
		String maxLimit = getStringWithLengthAndFilledWithCharacter(1000);
		System.out.println(maxLimit);
		getAccountTab().click();
		waitForElementToPresent(createAccountLink, 10);
		getCreateAccountLink().click();
		getNameTextbx().sendKeys(maxLimit);
		getSaveBtn().click();
		waitForElementToPresent(editBtn, 10);
		System.out.println(getNameInput().getText());
		verifyTrue(maxLimit.contains(getNameInput().getText()),
				"Account Name field can accept 1000 characters",
				"Account Name field does not accept 1000 characters");
	}

	@Test(enabled = true)
	public void verifyAccountAndContactCreation() throws IOException,
			InterruptedException {
		@SuppressWarnings("resource")
		CSVReader accountReader = new CSVReader(new FileReader(accountCSV_PATH));
		@SuppressWarnings("resource")
		CSVReader contactReader = new CSVReader(new FileReader(contactCSV_PATH));

		String[] csvCell;
		String[] contactCsvCell;
		String accountName = "";
		// while loop will be executed till the last line In CSV.
		while ((csvCell = accountReader.readNext()) != null
				&& (contactCsvCell = contactReader.readNext()) != null) {
			getAccountTab().click();
			waitForElementToPresent(createAccountLink, 10);
			getCreateAccountLink().click();
			Random random = new Random();
			accountName = csvCell[0] + random.nextInt(1234);
			System.out.println("Account name :" + accountName);
			String street = csvCell[1];
			String city = csvCell[2];
			String state = csvCell[3];
			String zip = csvCell[4];
			String website = csvCell[5];
			getAccountName().sendKeys(accountName);
			getStreetTextbxAcc().sendKeys(street);
			getCityTextbxAcc().sendKeys(city);
			getZipTextbxAcc().sendKeys(zip);
			getStateTextbxAcc().sendKeys(state);
			getWebsiteTextbox().sendKeys(website);
			waitForElementToPresent(editBtn, 10);

			String salutation = contactCsvCell[0];
			String firstName = contactCsvCell[1];
			String latsName = contactCsvCell[2];
			String title = contactCsvCell[3];
			String contactEmail = contactCsvCell[4];
			getContactTab().click();
			waitForElementToPresent(createContactLink, 10);
			getCreateContactLink().click();
			Select s = new Select(getSalutationDropdown());
			s.selectByValue(salutation);
			getFirstNameTextbox().sendKeys(firstName);
			getLastNameTextbox().sendKeys(latsName);
			getTitleTextbox().sendKeys(title);
			getEmailstreetTextbxCont().sendKeys(contactEmail);
			getAccountNameDropdown().sendKeys(accountName);
			getAccountNameDropdown().sendKeys(Keys.TAB);
			((JavascriptExecutor) driver).executeScript(
					"arguments[0].scrollIntoView(true);", getSaveFooterBtn());
			getSaveFooterBtn().click();
		}

	}

	@AfterTest
	public void cleanup() {
		driver.close();
	}

	protected String getStringWithLengthAndFilledWithCharacter(int length) {
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		Random random = new Random();
		char c = chars[random.nextInt(chars.length)];
		char[] array = new char[length];
		int pos = 0;
		while (pos < length) {
			array[pos] = c;
			pos++;
		}
		return new String(array);
	}

}
