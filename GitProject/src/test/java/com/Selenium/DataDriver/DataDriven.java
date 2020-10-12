package com.Selenium.DataDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDriven {
	@DataProvider (name = "FB-login")
	public Object [][] credentials(){
		return new Object[][] {{"abc@gmail.com","123@Test"},{"def@gmail.com","456@test"}};
	}
		WebDriver driver;

	public static String testURL = "https://www.facebook.com";
// test
	// sprint3 added new things
	@BeforeMethod
		public void setUp() {
			System.setProperty("webdriver.chrome.driver", "C:\\seleniumdriver\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
			
		}
	@Test(dataProvider = "FB-login")
	public void testFBcredentials(String unames, String password) {
		driver.get("https://www.facebook.com");
		
		//driver.findElement(By.xpath("//*[@id='email']")).sendKeys(unames);
		WebElement emailEl = driver.findElement(By.xpath("//*[@id='email']"));
		emailEl.sendKeys(unames);
		
		driver.findElement(By.cssSelector("#pass")).sendKeys(password);
		//driver.findElement(By.cssSelector("#u_0_2")).click();
		WebElement submitEl = driver.findElement(By.cssSelector("#u_0_2"));
		submitEl.click();
		
		String actualresult = driver.getTitle();
		Assert.assertTrue(actualresult.contains("Log into Facebook"));
		//Assert.assertEquals(actualresult, "Log into Facebook|Facebook");
		
		
	}

}
