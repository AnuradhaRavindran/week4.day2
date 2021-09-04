package week4.day2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Snapdeal {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.snapdeal.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//Go to Mens Fashion
		driver.findElement(By.xpath("//button[text()='NOT NOW']")).click();
		WebElement mens = driver.findElement(By.linkText("Men's Fashion"));
		Actions builder = new Actions(driver);
		builder.moveToElement(mens).perform();
		//Thread.sleep(1000);
		
		//Get the count of the sports shoes
		Thread.sleep(1000);
		
		driver.findElement(By.linkText("Sports Shoes")).click();
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		//get the list of prices of shoes  Sort by Low to High
		List<WebElement> price = driver.findElements(By.xpath("//span[contains(@id,'display-price')]"));
		for (WebElement eachpr : price) {
			System.out.println(eachpr.getText());
			
		}
		List<String> pricelist = new ArrayList<String>();
		List<Integer>  priceval = new ArrayList<Integer>();
		for(int i = 0 ;i<price.size();i++)
		{
			String eachprice = price.get(i).getText().replaceAll("[^0-9]", "");
			pricelist.add(eachprice);
			priceval.add(Integer.parseInt(eachprice));
									
		}
		System.out.println(pricelist);
		Integer min = Collections.min(priceval);
		System.out.println(min);
		Collections.sort(priceval);
		System.out.println(priceval);
		if(priceval.get(0).equals(min))
		{
			System.out.println("values sorted");
		}
		else
		{
			System.out.println("value not sorted");
		}
		
		
		driver.findElement(By.xpath("(//i[@class='sd-icon sd-icon-plus'])[1]")).click();
		driver.findElement(By.xpath("//label[@for='Brand-VSS']")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,400)");
        Thread.sleep(2000);
		driver.findElement(By.xpath("//label[@for='Color_s-Blue']")).click();
		js.executeScript("window.scrollBy(0,-400)");
		//Thread.sleep(1000);
		Thread.sleep(3000);
		js.executeScript("window.scrollBy(0,550)");
		Thread.sleep(1000);
		WebElement vss = driver.findElement(By.xpath("//img[@title='VSS Blue Training Shoes']"));
		Thread.sleep(3000);
		builder.moveToElement(vss).perform();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[contains(text(),'Quick View')]")).click();
		Thread.sleep(1000);
		//driver.findElement(By.xpath("(//i[@class='sd-icon sd-icon-delete-sign'])[3]")).click();
		//driver.findElement(By.xpath("(//p[@title='VSS Blue Training Shoes'])[1]")).click();
		//Set<String> windowHandlesset1 = driver.getWindowHandles();
		//List<String> windowhandleList1 = new ArrayList<String>(windowHandlesset1);
		//driver.switchTo().window(windowhandleList1.get(1));
		WebElement price1 = driver.findElement(By.xpath("//span[@class='payBlkBig']/parent::div"));
		String text = price1.getText();
		text.replaceAll("[^0-9]", " ");
		System.out.println(text);
		WebElement discount = driver.findElement(By.xpath("//span[@class='percent-desc ']"));
		String text2 = discount.getText();
		System.out.println(text2);
		//snapshot
		//System.err.println("The price of first duke brand is : " +price);
				File screenshotAs = driver.getScreenshotAs(OutputType.FILE);
			    File dst = new File("./snapdeal/vss.png");
			    FileUtils.copyFile(screenshotAs, dst);
			    driver.close();
			    driver.quit();
		
		
		
				
	}

}
