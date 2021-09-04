package week4.day2;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Mouseover {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.leafground.com/pages/mouseOver.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement testLeafCourse = driver.findElement(By.linkText("TestLeaf Courses"));
		Actions builder = new Actions(driver);
		builder.moveToElement(testLeafCourse).perform();
		List<WebElement> testCourseList = driver.findElements(By.xpath("//a[@class='listener']"));
		for (WebElement list : testCourseList) {
			String text = list.getText();
			System.out.println(text);
		}
		driver.findElement(By.linkText("Selenium")).click();
		Alert alert = driver.switchTo().alert();
		String text = alert.getText();
		System.out.println(text);
		alert.accept();
			}

}
