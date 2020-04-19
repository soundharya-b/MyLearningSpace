package selenium.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Nykaa {
	
	//Launch Browser
	
	/*
	 * public void launchBrowser() { public static ChromeDriver driver; }
	 */

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.nykaa.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// mousehover on brands
		WebElement brandMouseHover = driver.findElementByXPath("//li[@class='menu-dropdown-icon']");
		Actions brand = new Actions(driver);
		brand.moveToElement(brandMouseHover).perform();
		
		//mousehover on Popular
		WebElement popularMouseHover = driver.findElementByXPath("//a[text()='Popular']");
		Actions popular = new Actions(driver);
		popular.moveToElement(popularMouseHover).perform();
		
		//Click Loreal paris
		driver.findElementByXPath("//img[@src='https://adn-static2.nykaa.com/media/wysiwyg/2019/lorealparis.png']").click();
		
		//Switch to new window
		Set<String> winSet = driver.getWindowHandles();
		List<String> winList = new ArrayList<String>(winSet);
		driver.switchTo().window(winList.get(1));
		
		//get Title
		String pageTitle = driver.getTitle();
		System.out.println(pageTitle);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//sort by
		driver.findElementByXPath("//i[@class='fa fa-angle-down']").click();

		// Select Customer Top rated

		driver.findElementByXPath("//span[text()='customer top rated']").click();
		
		// click categories
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElementByXPath("//div[text()='Category']").click();
		
		//click Shmapoo
		driver.findElementByXPath("//label[@for='chk_Shampoo_undefined']//span[1]").click();
		
		//check if the filter value has selected shampoo
		if(driver.findElementByXPath("//li[text()='Shampoo']").isDisplayed())
		{
			System.out.println("The filtered product is shampoo");
		}
		
		// click Color protected
		driver.findElementByXPath("//img[@src='https://images-static.nykaa.com/media/catalog/product/tr:w-276,h-276,cm-pad_resize/8/9/8901526102518_color_protect_shampoo_75ml_82.5ml__i1_1.png']").click();
		
		// Switch to new window
		Set<String> secondWindow = driver.getWindowHandles();
		List<String> windowList = new ArrayList<String>(secondWindow);
		driver.switchTo().window(windowList.get(2));
		
		//Select Size as 175 ML
		driver.findElementByXPath("//span[text()='175ml']").click();
		
		//Print the MRP
		String productMRP = driver.findElementByXPath("//span[text()='MRP:']").getText();
		System.out.println("Product Price:" +productMRP);
		
		//add to bag
		driver.findElementByXPath("(//button[text()='ADD TO BAG'])[1]").click();
		
		//click shopping bag
		driver.findElementByClassName("BagItems").click();
		
		//Print Grand Total
		String grandTotal = driver.findElementByXPath("//div[text()='Grand Total:']").getText();
		System.out.println("Grand Total:" +grandTotal);
		
		//Click Proceed
		driver.findElementByXPath("//span[text()='Proceed']").click();
		
		//Click on Continue as guest
		driver.findElementByXPath("//button[text()='CONTINUE AS GUEST']").click();
		
		//Print Warning Message
		String warnMessage = driver.findElementByXPath("//div[text()='Please expect delay in shipments because of the national lockdown']").getText();
		System.out.println(warnMessage);
		
		//Close Window
		driver.quit();
	}

}
