package maven_test.Web_Automation_Demo;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
//import org.testng.Assert.*;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

public class Demo_1 {
	
	
	ChromeDriver driver ;
	JavascriptExecutor scrolldown;
	JavascriptExecutor scrollup;
	
	@BeforeTest
	public void intro() throws InterruptedException {
		String url= System.getProperty("user.dir")+"\\resources_demo\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver",url);
		driver = new ChromeDriver();
		driver.navigate().to("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		driver.manage().window().maximize();
		
		WebElement username= driver.findElement(By.id("email"));
		WebElement password= driver.findElement(By.id("passwd"));
		username.clear();
		username.sendKeys("ititesting21@gmail.com");
		password.clear();
		password.sendKeys("Testing_21");
		WebElement btnlogin= driver.findElement(By.id("SubmitLogin"));
		
		btnlogin.click();
		Thread.sleep(10000);
	}
	
	@Test(priority=1)
	public void shopping() throws InterruptedException, IOException
	{
		/* monitoring */ 
		WebElement home = driver.findElement(By.xpath("//*[@id=\"header_logo\"]/a/img"));
		home.click();
		
		Thread.sleep(4000);
		
		WebElement bestseller = driver.findElement(By.xpath("//*[@id=\"home-page-tabs\"]/li[2]/a"));
		bestseller.click();
		
		Thread.sleep(4000);
		
		scrolldown = (JavascriptExecutor) driver;
		scrolldown.executeScript("window.scrollBy(0,780)", "");
		
		Thread.sleep(4000);
		
		scrollup = (JavascriptExecutor) driver;
		scrollup.executeScript("window.scrollBy(0,70)", "");
		
		Thread.sleep(6000);
		
		WebElement popular = driver.findElement(By.xpath("//*[@id=\"home-page-tabs\"]/li[1]/a"));
	    popular.click();
	    
	    Thread.sleep(3000);  
	    
		scrollup.executeScript("window.scrollBy(0,20)", "");
		
		Thread.sleep(3000);
	    
	    /*search box*/
		WebElement post = driver.findElement(By.id("search_query_top"));
		post.clear();
		post.sendKeys("blouse");
		WebElement search = driver.findElement(By.xpath("//*[@id=\"searchbox\"]/button"));
		search.click();
	    
		Thread.sleep(2000);
	    /*buy*/
	    WebElement hover = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li/div/div[2]/h5/a"));
		Actions action = new Actions(driver);
		action.moveToElement(hover).perform();
		
		WebElement openproduct = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li/div/div[2]/div[2]/a[2]/span"));
		openproduct.click();
		
		WebElement quantityadd = driver.findElement(By.xpath("//*[@id=\"quantity_wanted_p\"]/a[2]/span"));
		WebElement quantitysub = driver.findElement(By.xpath("//*[@id=\"quantity_wanted_p\"]/a[1]/span"));
		int i;
		for( i =0; i<=4 ; i++)
		{
				quantityadd.click();
				Thread.sleep(800);
				if(i==4)
				{
					quantitysub.click();
				}
		}
		Thread.sleep(2000);
		WebElement textboxquantity = driver.findElement(By.id("quantity_wanted"));
		textboxquantity.clear();
		textboxquantity.sendKeys("1");
		
		Thread.sleep(2000);
		WebElement testDropDown = driver.findElement(By.xpath("//*[@id=\"group_1\"]"));  
		Select dropdown = new Select(testDropDown);  
		Thread.sleep(1000);
		dropdown.selectByValue("2");
	   }
		
		@Test(dependsOnMethods="shopping")
		public void cartproceeding() throws InterruptedException, IOException
		{
		WebElement cart = driver.findElement(By.xpath("//*[@id=\"add_to_cart\"]/button/span"));
		cart.click();
		Thread.sleep(3000);
		driver.navigate().to("http://automationpractice.com/index.php?controller=order");
		
		Thread.sleep(3000);
		
		WebElement proceed_2 = driver.findElement(By.xpath("//*[@id=\"center_column\"]/p[2]/a[1]/span"));
		proceed_2.click();
		
		Thread.sleep(3000);
		
		WebElement proceed_3 = driver.findElement(By.xpath("//*[@id=\"center_column\"]/form/p/button/span"));
		proceed_3.click();
		
		Thread.sleep(5000);
		
		
		try {
		WebElement checkbox = driver.findElement(By.id("cgv"));
		Thread.sleep(5000);
		checkbox.click();
		Assert.assertTrue(checkbox.isSelected());
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("--------------------");
		System.out.println("Terms Checkbox selection: "+ checkbox.isSelected());
		System.out.println("--------------------");
		}
		catch(NoSuchElementException e) {
			System.out.println("no such element");
		}
	
		Thread.sleep(3000);
		
		WebElement proceed_4 = driver.findElement(By.xpath("//*[@id=\"form\"]/p/button/span"));
		proceed_4.click();
		
		Thread.sleep(3000);
		
		WebElement proceed_5 = driver.findElement(By.xpath("//*[@id=\"HOOK_PAYMENT\"]/div[1]/div/p/a"));
		proceed_5.click();
		
		Thread.sleep(3000);
		
		WebElement proceed_6 = driver.findElement(By.xpath("//*[@id=\"cart_navigation\"]/button/span"));
		proceed_6.click();
		
		Thread.sleep(400);
		
		scrolldown = (JavascriptExecutor) driver;
		scrolldown.executeScript("window.scrollBy(0,480)", "");
		
		Thread.sleep(2000);
		}
		
		@Test(dependsOnMethods="cartproceeding")
		public void screenshottest() throws IOException, InterruptedException
		{
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source,new File("C:\\Users\\mhela\\eclipse-workspace\\Web_Automation_Demo\\screenshot\\"+".jpg"));
		
		Thread.sleep(3000);
		}
		
		@Test(priority=2)
		public void signouttest()
		{
		WebElement signout = driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[2]/a"));
		signout.click();
		}
		
		@Test(priority=3)
		public void createaccounttest()
		{
		/*create account case*/
		WebElement createacc = driver.findElement(By.xpath("//*[@id=\"create-account_form\"]/h3"));
		System.out.println("output is "+createacc.getText());
		WebElement createaccount = driver.findElement(By.id("email_create"));
		createaccount.clear();
		createaccount.sendKeys("itiTesting21@gmail.com");
		
		WebElement createaccountbtn = driver.findElement(By.xpath("//*[@id=\"SubmitCreate\"]/span"));
		createaccountbtn.click();
		
		//Alert alert = driver.switchTo().alert();
		//System.out.println("output of warning is " + alert.getText());
		
	   }
		
		@AfterTest
		public void closing() throws InterruptedException
		{
			//WebElement errorcreateacc = driver.findElement(By.xpath("//*[@id=\"create-account_form\"]/div/p"));
			//System.out.println("output is "+errorcreateacc.getText());
			Thread.sleep(8000);
			driver.close();
		}
	
}

