package week4.day2;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Erail {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://erail.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement from = driver.findElement(By.id("txtStationFrom"));
		from.clear();
		from.sendKeys("ms",Keys.ENTER);
		WebElement to = driver.findElement(By.id("txtStationTo"));
		to.clear();
		to.sendKeys("mdu",Keys.ENTER);
		Thread.sleep(1000);
		driver.findElement(By.id("chkSelectDateOnly")).click();
		Thread.sleep(2000);
		List<String> trainnames = new ArrayList<String>();
		int size =  driver.findElements(By.xpath("//table[@class='DataTable TrainList TrainListHeader']//tr")).size();
		for(int i = 1 ;i <=size; i++) 
		{
		String text = driver.findElement(By.xpath("//table[@class='DataTable TrainList TrainListHeader']//tr[" + i + "]/td[2]"))
					.getText();
			trainnames.add(text);
			System.out.println(text);
			// copy the list including duplicates
			int listsize = trainnames.size();
			Set<String> setTrainNames = new LinkedHashSet<String>(trainnames);
			int setsize = setTrainNames.size();

			if (listsize == setsize) {

				System.out.println("There is no Duplicate");
			} else {

				System.out.println("There are duplicates");
			}

		}

	}
}
