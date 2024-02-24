package steps;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class parabankRegister {

	WebDriver driver;
	
	@Given("User is on Parabank homepage")
	public void userIsOnParabankHomepage() {
		System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\dell\\\\Downloads\\\\chromedriver-win64\\\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://parabank.parasoft.com/parabank/index.htm?ConnType=JDBC/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@When("User click register link button")
	public void userClickRegisterLinkButton() {
		driver.findElement(By.xpath("//*[@id=\"loginPanel\"]/p[2]/a")).click();
	}

	@Then("User is in register page")
	public void userIsInRegisterPage() {
		System.out.println("Page title in register page: " + driver.findElement(By.className("title")).getText());
	}

	@When("User input name")
	public void userInputName() throws InterruptedException {
		// User input first name
		WebElement fillFirstName = driver.findElement(By.id("customer.firstName"));
		fillFirstName.sendKeys("Gladys");
		Thread.sleep(2000);
				
		// User input last name
		WebElement fillLastName = driver.findElement(By.id("customer.lastName"));
		fillLastName.sendKeys("Tanujaya");
		Thread.sleep(2000);
	}

	@When("User input address detail")
	public void userInputAddressDetail() throws InterruptedException {
		driver.findElement(By.name("customer.address.street")).sendKeys("Senen");
		Thread.sleep(2000);
	}
	
	@When("User input city and state")
	public void userInputCityAndState() throws InterruptedException {
		// User input city
		driver.findElement(By.name("customer.address.city")).sendKeys("Jakarta Pusat");
		Thread.sleep(2000);
				
		// User input state
		driver.findElement(By.id("customer.address.state")).sendKeys("DKI Jakarta");
		Thread.sleep(2000);
	}

	@When("User input ZIP code")
	public void userInputZIPCode() throws InterruptedException {
		driver.findElement(By.id("customer.address.zipCode")).sendKeys("10460");
		Thread.sleep(2000);
	}

	@When("User input phone number")
	public void userInputPhoneNumber() throws InterruptedException {
		WebElement fillPhoneNumber = driver.findElement(By.name("customer.phoneNumber"));
		fillPhoneNumber.sendKeys("085864092358");
		Thread.sleep(2000);
	}

	@When("User input SSN")
	public void userInputSSN() throws InterruptedException {
		WebElement fillSSN = driver.findElement(By.name("customer.ssn"));
		fillSSN.sendKeys("123");
		Thread.sleep(2000);
	}

	@When("User fill valid username and password")
	public void userFillValidUsernameAndPassword() throws InterruptedException {
		// Randomized the number for username
		Random rand = new Random();
		int userRand = rand.nextInt(100);
		driver.findElement(By.id("customer.username")).sendKeys("gladystanujaya" + userRand);
		Thread.sleep(2000);
				
		// Input password
		driver.findElement(By.id("customer.password")).sendKeys("abcde12345");
		Thread.sleep(2000);
	}

	@When("User input password confirmation")
	public void userInputPasswordConfirmation() throws InterruptedException {
		driver.findElement(By.id("repeatedPassword")).sendKeys("abcde12345");
		Thread.sleep(2000);
	}

	@When("User click Register button")
	public void userClickRegisterButton() {
		driver.findElement(By.xpath("//*[@id=\"customerForm\"]/table/tbody/tr[13]/td[2]/input")).click();
	}

	@Then("User regist successfully")
	public void userRegistSuccessfully() throws InterruptedException {
		System.out.println("Page title after sign up: " + driver.findElement(By.xpath("//*[@id=\"rightPanel\"]/p")).getText());
		Thread.sleep(2000);		
		driver.quit();
	}
	
	@When("User input invalid password confirmation")
	public void userInputInvalidPasswordConfirmation() throws InterruptedException {
		driver.findElement(By.id("repeatedPassword")).sendKeys("abcdefghijkl");
		Thread.sleep(2000);
	}

	@Then("User get error password did not match")
	public void userGetErrorPasswordDidNotMatch() throws InterruptedException {
		// Explicit Wait
		// Change the JDK to 1.8 to make the wait is not error
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"repeatedPassword.errors\"]")));
		System.out.println("Warning message: " + driver.findElement(By.xpath("//*[contains(text(), 'Passwords did not match.')]")).getText());
		Thread.sleep(2000);
		driver.quit();
	}
}
