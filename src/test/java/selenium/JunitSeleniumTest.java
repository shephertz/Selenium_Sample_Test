package selenium;

import java.io.File;
import java.net.URL;
import java.util.Map;

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class JunitSeleniumTest {
    @Test
    public void myTest() throws Exception {
    	
    	WebDriver driver = new RemoteWebDriver(
                                new URL("http://SeleniumServerIP/wd/hub/"), 
                                DesiredCapabilities.firefox());
        
        driver.get("http://WEB_Node_IP/");
        
        
        // RemoteWebDriver does not implement the TakesScreenshot class
        // if the driver does have the Capabilities to take a screenshot
        // then Augmenter will add the TakesScreenshot methods to the instance
        WebDriver augmentedDriver = new Augmenter().augment(driver);
        File screenshot = ((TakesScreenshot)augmentedDriver).
                            getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("/home/paasadmin/testimage.png"));
        Assert.assertEquals(driver.getTitle(), "App42 Sample Java-MySql Application");
		System.out.println("Selenium Test Successfull");
		System.out.println(driver.getTitle());
        
    }
}
