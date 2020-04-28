package selenium.practice;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MakeMyTrip {

	public static void main(String[] args) {

//Launch Browser		
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(" https://www.makemytrip.com/");
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
//To disable the notification
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
//Click Hotel and Enter Goa, India
		driver.findElementByXPath("//span[text()='Hotels']").click();
		driver.findElementByXPath("//span[@data-cy='hotelCityLabel']").click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElementByXPath("//input[@placeholder='Enter city/ Hotel/ Area/ Building']").sendKeys("Goa,India",Keys.TAB);
		
//LocalDate function is to get the current date -  in a string format
				String datestamp = LocalDate.now().toString();
				System.out.println("Current date is "+datestamp);
				//substring function to get the date from that time stamp
				String date1 = datestamp.substring(8, 10);
				System.out.println("Date:" +date1);
				int date = Integer.parseInt(date1)+5;
				System.out.println("Date+1:" +date);
				//substring function to get the month from that date stamp
				String month = datestamp.substring(5, 7);
				System.out.println("Month:" +month);
				//Converting the received date stamp into int
				int monthInt = Integer.parseUnsignedInt(month);
				System.out.println("Month Int :"+monthInt);
				//getting the next Month by adding 1
				int nextMonth = monthInt+1;
				System.out.println("Next Month is " + nextMonth);
				String nextMonthString = Month.of(nextMonth).toString();
				nextMonthString = nextMonthString.substring(0, 1)+nextMonthString.substring(1, nextMonthString.length()).toLowerCase();
				
//Pick May 15
				WebElement pickStartDate = driver.findElementByXPath("//div[text()='"+nextMonthString+"']//ancestor::div[@class='DayPicker-Month']//div[text()='"+date+"']");
				String startDate = pickStartDate.getText();
				System.out.println(" Start Date is on:" +startDate);
				pickStartDate.click();

//Plus 5 from pick date
				int endDate = Integer.parseInt(startDate);
				int  endDateSelect = endDate+4;
				
				WebElement pickEndDate = driver.findElementByXPath("//div[text()='"+nextMonthString+"']/ancestor::div[@class='DayPicker-Month']//div[text()='"+endDateSelect+"']");
				pickEndDate.click();
				System.out.println("End date is on "+pickEndDate.getText());
				
//Click on Rooms
				driver.findElementByXPath("//label[text()='ROOMS & GUESTS']").click();
				driver.findElementByXPath("(//li[text()='1'])[1]").click();
				driver.findElementByXPath("(//li[text()='1'])[2]").click();
				driver.findElementByXPath("//button[text()='APPLY']").click();
				driver.findElementByXPath("//button[text()='Search']").click();
				
//Search Locality as Baga and 5 star in star category		
				driver.findElementByXPath("//label[text()='Baga']").click();
				System.out.println("Baga");
				driver.findElementByXPath("//label[text()='5 Star']").click();
				System.out.println("5 star hotels");
				
//Click on first Result
				driver.findElementByXPath("//span[text()='The Park Baga River Goa']").click();

//Go to New Window
				Set<String> winSet = driver.getWindowHandles();
				List<String> listWindow = new ArrayList<String>(winSet);
				driver.switchTo().window(listWindow.get(1));
				
//Print the hotel Name
				WebElement printTitle = driver.findElementByXPath("//h1[text()='The Park Baga River Goa']");
				String hotelName = printTitle.getText();
				System.out.println("The Hotel Name :"+hotelName);
				
//Click More Options
				driver.findElementByXPath("//span[text()='MORE OPTIONS']").click();
				driver.findElementByXPath("(//span[text()='SELECT'])[1]").click();
				driver.findElementByXPath("//span[@class='close']").click();
				
//Book this
				driver.findElementByXPath("//a[text()='BOOK THIS NOW']").click();
				String totalAmt = driver.findElementByXPath("//div[@class='makeFlex column textRight']//span").getText();
				System.out.println("The Toal PAyable Amount is:"+totalAmt);
				
//Close Driver
				driver.quit();
	}

}
