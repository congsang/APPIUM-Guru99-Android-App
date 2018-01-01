import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sang Nguyen on 5/24/2017.
 */
public class Tc5 {
    AndroidDriver driver;

    @BeforeClass
    public void setUp()throws MalformedURLException{
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.DEVICE_NAME,"Android");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION,"6.0");
        caps.setCapability(MobileCapabilityType.PLATFORM,"android");
        caps.setCapability("appPackage","com.vector.guru99");
        caps.setCapability("appActivity","com.vector.guru99.BaseActivity");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),caps);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void Tc5(){
        // 1. verify home page
        if(driver.findElement(By.id("android:id/home")).isDisplayed())
            System.out.println("Home page is displayed");
        else
            System.out.println("home page is not displayed");
        // 2. click on Course List
        WebElement eleCourseList = driver.findElement(By.xpath("//android.widget.TextView[@text='Course List']"));
        if(eleCourseList.isDisplayed()&&!eleCourseList.isSelected()){
            eleCourseList.click();
            System.out.println("Click on Course List"+eleCourseList.getText());
        }
        // scroll to PHP
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("
                + "new UiSelector().text(\"PHP\"));");
        // 3. click on PHP Button
        driver.findElement(By.xpath("//android.widget.TextView[@text='PHP']")).click();
        // verify Title of Lesson 1
        driver.findElement(By.xpath("//android.widget.TextView[@text='Lesson #1']")).click();
        String lessText1 = driver.findElement(By.id("com.vector.guru99:id/lesson_title")).getText();
        Assert.assertEquals(lessText1,"What is PHP? Write your first PHP Program");
        // click on Next
        driver.findElement(By.id("com.vector.guru99:id/next")).click();
        // verify Title of Lesson 2
        String lessText2 = driver.findElement(By.id("com.vector.guru99:id/lesson_title")).getText();
        Assert.assertEquals(lessText2,"Step by step instruction on XAMPP & Netbeans installation");
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

}
