package week4.day2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Myntra {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement men = driver.findElement(By.xpath("(//a[text()='Men'])[1]"));
		Actions builder = new Actions(driver);
		builder.moveToElement(men).perform();
		men.sendKeys(Keys.F8);
		driver.findElement(By.linkText("Jackets")).click();
	    WebElement text = driver.findElement(By.xpath("//span[@class='title-count']"));
		String replacetext = text.getText().replaceAll("[^0-9]", "");
		System.out.println(replacetext);
		int totalcount = Integer.parseInt(replacetext);
		System.out.println("The Number of jackets Are : " +totalcount);
		WebElement jacketcount = driver.findElement(By.xpath("(//span[@class='categories-num'])[1]"));
		String jackettext = jacketcount.getText().replaceAll("[^0-9]", "");
		System.out.println(jackettext);
		int jacket = Integer.parseInt(jackettext);
		System.out.println("The Number of Jackets Alone : " +jacket);
		WebElement rainjacket = driver.findElement(By.xpath("(//span[@class='categories-num'])[2]"));
		String raintext = rainjacket.getText().replaceAll("[^0-9]", "");
		System.out.println(raintext);
		int rainjkt = Integer.parseInt(raintext);
		System.out.println("The Number of Jackets Alone : " +rainjkt);
		int total = jacket + rainjkt;
		System.out.println("The total is : " +total);
		if(total==totalcount)
		{
			System.out.println("The jacets count Matches");
		}
		else {
			System.out.println("The jacets count not  Matches");
		}
		driver.findElement(By.xpath("(//div[@class='common-checkboxIndicator'])[1]")).click();
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollBy(0,400)");
        Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='brand-more']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Search brand']")).sendKeys("Duke");
		js.executeScript("window.scrollBy(0,100)");
		driver.findElement(By.xpath("//label[text()='Duke']")).click();
		js.executeScript("window.scrollBy(0,100)");
		Thread.sleep(1000);
		js.executeScript("window.scrollBy(0,100)");
		driver.findElement(By.xpath("//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove']")).click();
		List<WebElement> branDuke = driver.findElements(By.tagName("h3"));
		for(int i =0;i<branDuke.size();i++)
		{
			  String textDuke = branDuke.get(i).getText();
			  if(textDuke.equals("Duke"))
			  {
				  System.out.println("you have searched Duke Brand");
			  }
			  else
			  {
				  System.out.println("you have not searched Duke Brand");
			  }
				  
		}
		//Sort by Better Discount
		WebElement sortby = driver.findElement(By.xpath("//span[@class='myntraweb-sprite sort-downArrow sprites-downArrow']"));
		builder.moveToElement(sortby).build().perform();
        driver.findElement(By.xpath("//label[contains(text(),'Better Discount')]")).click();
		driver.findElement(By.xpath("(//div[@class='product-price']/span)[1]")).click();
		Set<String> windowhandleset = driver.getWindowHandles();
		List<String> windowhandlelist = new ArrayList<String>(windowhandleset);
		driver.switchTo().window(windowhandlelist.get(1));
		WebElement price = driver.findElement(By.xpath("//span[@class='pdp-price']/strong"));
		String text2 = price.getText();
		System.out.println(text2);
		/*for(int i =0;i<price.size();i++)
		{
		String text2 = price.get(0).getText();
		System.out.println(text2);
		}*/
		//System.err.println("The price of first duke brand is : " +price);
		File screenshotAs = driver.getScreenshotAs(OutputType.FILE);
	    File dst = new File("./myntra/screen.png");
	    FileUtils.copyFile(screenshotAs, dst);
	   // driver.findElement(By.xpath("//span[text()='WISHLIST']")).click();
	    driver.close();
	
						
	}

}
