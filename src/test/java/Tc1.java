import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sang Nguyen on 5/21/2017.
 */
public class Tc1 {
    AndroidDriver driver;

    @BeforeClass
    public void setUp()throws MalformedURLException{
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION,"6.0");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME,"android");
        caps.setCapability("appPackage","com.vector.guru99");
        caps.setCapability("appActivity","com.vector.guru99.BaseActivity");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),caps);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void Tc1(){
        // 1.App name Guru99 is exist on screen
        if(driver.findElement(By.id("android:id/action_bar_title")).isDisplayed()){
            System.out.println("guru99 header is displayed");
        }else {
            System.out.println("guru99 header is not displayed");
        }
        // 2.Course Category is displayed, clickable & selected (hilghlighted)
        WebElement eleCourseCate = driver.findElement(By.xpath("//android.widget.TextView[@text='Course Category']"));
        if (eleCourseCate.isDisplayed()&&eleCourseCate.isSelected()){
            System.out.println("Course Category is currently selected");
        }else {
            System.out.println("Course Category is not selected");
        }
        // 3.Content of Course Category is displayed in list
        //get all the courses and store them in a List
        List<WebElement> listCategory = driver.findElements(By.id("com.vector.guru99:id/lblListHeader"));
        //iterating the list to perform action on each webelement
        System.out.println("coourse offered are and their state ");
        for(WebElement course : listCategory){
            String cours = course.getText();
            if(course.isEnabled()){
                // Print each course name
                System.out.println(cours+ " and it is enabled");
            }else {
                System.out.println(" and it is not anabled");
            }
        }
        // 4.Course List is displayed, clickable & not selected
        WebElement eleCourseList = driver.findElement(By.xpath("//android.widget.TextView[@text='Course List']"));
        if (eleCourseList.isDisplayed()&&!eleCourseList.isSelected()){
            System.out.println("Course List is is not selected");
        }else {
            System.out.println("Course List is selected");
        }
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

}
