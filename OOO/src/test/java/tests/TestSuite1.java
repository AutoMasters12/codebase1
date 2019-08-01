package tests;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestSuite1 {

	@Test
	public void test() throws Exception {

		ExtentReports report = new ExtentReports("C:\\Users\\suman\\k99\\OOO\\target\\report1.html");
		ExtentTest test = report.startTest("test case1");
		WebDriverManager.chromedriver().setup();
		test.log(LogStatus.INFO, "drivers has been set");

		WebDriver driver = new ChromeDriver();
		test.log(LogStatus.INFO, "launching chrome browser");

		driver.get("https://www.google.com/");
		test.log(LogStatus.INFO, "invoke url");

		driver.manage().window().maximize();
		test.log(LogStatus.INFO, "maximizing browser");

		if (driver.getTitle().equals("Google")) {
			test.log(LogStatus.PASS,
					"i am into google search page"
			+ test.addScreenCapture(capturescreenshot(driver, "screen1")));
		} else {
			test.log(LogStatus.FAIL, "i amnot into google seach page");
		}
		driver.quit();
		report.endTest(test);
		report.flush();
		report.close();

	}

	private String capturescreenshot(WebDriver driver, String name) throws Exception {
		String path = "C:\\Users\\suman\\k99\\OOO\\target" + name + ".png";
		File temp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(temp, new File(path));
		return path;
	}

}
