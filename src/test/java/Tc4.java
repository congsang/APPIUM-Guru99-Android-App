import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sang Nguyen on 5/24/2017.
 */
public class Tc4 {
    AndroidDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException{
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("noReset","true");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME,"Android");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION,"6.0");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME,"android");
        caps.setCapability("appPackage","com.vector.guru99");
        caps.setCapability("appActivity","com.vector.guru99.BaseActivity");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),caps);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void Tc4(){
        // 1. verify home page
        if(driver.findElement(By.id("android:id/home")).isDisplayed())
            System.out.println("Home page is displayed");
        else
            System.out.println("home page is not displayed");
        // 2. click the Interview button and questions are displayed
        driver.findElement(By.id("com.vector.guru99:id/action_interview")).click();

        List<WebElement> interviewList = driver.findElements(By.id("com.vector.guru99:id/lblinterviewHeader"));
        interviewList.get(2).click();
        // click struts
        driver.findElement(By.xpath("//android.widget.TextView[@text='Top 50 Struts Interview Questions']")).click();
        // question 1 is displayed
        WebElement eleInterviewQ = driver.findElement(By.id("com.vector.guru99:id/interview_question"));
        if(eleInterviewQ.isDisplayed()){
            System.out.println("Question 1 is displayed");
        }else {
            System.out.println("Question 1 is not displayed");
        }
        // click on show answer button
        driver.findElement(By.id("com.vector.guru99:id/show_answer")).click();
        // answer is display
        WebElement eleAnswerQ = driver.findElement(By.id("com.vector.guru99:id/interview_answer"));
        if(eleAnswerQ.isDisplayed()){
            System.out.println("Answer 1 is displayed");
        }else {
            System.out.println("Answer 1 is not displayed");
        }
        // click on next button
        driver.findElement(By.id("com.vector.guru99:id/next")).click();
        // question 2 is displayed
        WebElement eleInterviewQ2 = driver.findElement(By.id("com.vector.guru99:id/interview_question"));
        if(eleInterviewQ2.isDisplayed()){
            System.out.println("Question 2 is displayed");
        }else {
            System.out.println("Question 2 is not displayed");
        }
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

}
