package stepDefs;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ProductPurchase {
	public static WebDriver driver;
	WebDriverWait wait;
	String product;
	@BeforeAll
	public static void startup() {
		
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(options);
				
//		WebDriverManager.chromedriver().setup();
//	    driver=new ChromeDriver();
	    driver.manage().window().maximize();
		//  WebDriverManager.edgedriver().setup();
		//  WebDriver driver = new EdgeDriver();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
	    
	}

	@Given("Login Into App")
	public void login_into_app() {
    	wait= new WebDriverWait(driver, Duration.ofSeconds(30));
	  driver.get("https://www.demoblaze.com");
	  driver.findElement(By.id("login2")).click();
	  driver.findElement(By.id("loginusername")).sendKeys("Lijo");
	  driver.findElement(By.id("loginpassword")).sendKeys("lijo@123");
	  driver.findElement(By.xpath("//button[text()='Log in']")).click();
	  WebElement wel= driver.findElement(By.xpath("//li/a[text()='Welcome Lijo']"));
	  Assert.assertEquals(wel.getText(), "Welcome Lijo");
	}
	
	
	@When("Add Item to cart")
	public void add_item_to_cart(DataTable dataTable) {
		wait= new WebDriverWait(driver, Duration.ofSeconds(30));
		List<String> data= dataTable.asList();
		String item= data.get(1);
		product=item;
		
		WebElement homebtn= driver.findElement(By.xpath("//li/a[contains(text(),'Home')]"));
		  wait.until(ExpectedConditions.elementToBeClickable(homebtn));
		  homebtn.click();
		  driver.findElement(By.linkText(item)).click();
		  driver.findElement(By.xpath("//a[contains(text(),'Add to cart')]")).click();
		  wait.until(ExpectedConditions.alertIsPresent());
		  Alert alert=driver.switchTo().alert();		  
		  alert.accept();
	    
	}
	@Then("Item must added to the cart")
	public void item_must_added_to_the_cart() {
		boolean count =false;
		driver.findElement(By.xpath("//a[contains(text(),'Cart')]")).click();
		  List<WebElement> listitem=driver.findElements(By.xpath("//td[2]"));
		  for(WebElement list:listitem)
		  {
			  if(list.getText().equalsIgnoreCase(product)) {
			  Assert.assertEquals(list.getText(), product);
			  count=true;
			  }		  
		  }
		  
			  Assert.assertTrue(count);
}
	@When("List of items available in the cart")
	public void list_of_items_available_in_the_cart() {
		  List<WebElement> beforeDelete = driver.findElements(By.xpath("//tr[@class='success']"));
		  int before=beforeDelete.size();
		  Assert.assertTrue(before>0);
	}
	@Then("Delete an item from cart")
	public void delete_an_item_from_cart() {
		driver.findElement(By.xpath("(//td[4]//a)[1]")).click();
	}
	
	@When("Items should be available in the cart")
	public void items_should_be_available_in_the_cart() {
		List<WebElement> beforeDelete = driver.findElements(By.xpath("//tr[@class='success']"));
		  int before=beforeDelete.size();
		  Assert.assertTrue(before>0);
	}
	@Then("Place Order with address")
	public void place_Order() {
		driver.findElement(By.xpath("//button[contains(text(),'Place Order')]")).click();
	}
	@Then("purchase items selected")
	public void purchase_items() throws InterruptedException {
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(),'Place Order')]")).click();
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys("Lijo");
		driver.findElement(By.xpath("//input[@id='country']")).sendKeys("India");
		driver.findElement(By.xpath("//input[@id='city']")).sendKeys("Tirunelveli");
		driver.findElement(By.xpath("//input[@id='card']")).sendKeys("94628462");
		driver.findElement(By.xpath("//input[@id='month']")).sendKeys("May");
		driver.findElement(By.xpath("//input[@id='year']")).sendKeys("2023");
    	Thread.sleep(2000);
    	driver.findElement(By.xpath("//button[contains(text(),'Purchase')]")).click();
    	Thread.sleep(2000);
    	boolean isDisplay = driver.findElement(By.xpath("//h2[(text()='Thank you for your purchase!')]")).isDisplayed();
    	String Display = driver.findElement(By.xpath("//h2[(text()='Thank you for your purchase!')]")).getText();
    	Assert.assertTrue(isDisplay);
    	System.out.println(Display);
    	driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
    	Thread.sleep(2000);
	}
}