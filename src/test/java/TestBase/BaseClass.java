package TestBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger; //Log4J
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	static public WebDriver driver;
	public Logger logger;
	public Properties p;

	@BeforeClass(groups = { "Sanity", "Regression" })
	@Parameters({ "OS", "browser" })
	public void setup(String OS, String Browser) throws IOException {
		// Loading Properties
		FileInputStream file = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
		p = new Properties();
		p.load(file);

		// Loading log4J2 file

		logger = LogManager.getLogger(this.getClass()); // Log4J

		if (p.getProperty("execution_env").equalsIgnoreCase("remote"))

		{
			DesiredCapabilities cap = new DesiredCapabilities();
			// OS

			if (OS.equalsIgnoreCase("windows")) {
				cap.setPlatform(Platform.WIN11);

			}

			else if (OS.equalsIgnoreCase("mac")) {
				cap.setPlatform(Platform.MAC);
			} else {
				System.out.println("No Matching OS");
				return;
			}

			// Browser

			//browser
			switch(Browser.toLowerCase())
			{
			case "chrome" : cap.setBrowserName("chrome"); break;
			case "edge" : cap.setBrowserName("MicrosoftEdge"); break;
			default: System.out.println("No matching browser.."); return;
			}
			
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);

		}
		
		else if (p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch (Browser.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			default:
				System.out.println("No Matching browser");
				return;

			}
		
		}
		// Launching browser based on parameter - Locally
		

		driver.manage().deleteAllCookies();
		driver.get(p.getProperty("appURL"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}

	


	@AfterClass(groups = { "Sanity", "Regression" })
	public void tearDown() {
		driver.quit();
	}

	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);

		sourceFile.renameTo(targetFile);

		return targetFilePath;

	}

}
