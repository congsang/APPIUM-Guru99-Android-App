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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sang Nguyen on 5/21/2017.
 */
public class Tc2 {
    AndroidDriver driver;

    @BeforeClass
    public void setUp()throws MalformedURLException{
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("noReset","true");
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION,"6.0");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME,"android");
        caps.setCapability("appPackage","com.vector.guru99");
        caps.setCapability("appActivity","com.vector.guru99.BaseActivity");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),caps);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void Tc2(){
        Set<String> allElementText = new HashSet<String>();
        // 1.Home Page Verification
        if(driver.findElement(By.id("android:id/home")).isDisplayed()) {
            System.out.println(" Guru 99 App Home Page is displayed ");
        }
        // 2. Click on a item on listview of Course Category - SAP
        List<WebElement> CourseList = driver.findElements(By.id("com.vector.guru99:id/lblListHeader"));
        CourseList.get(0).click();
        // 2.1 Assert actual and expected of course
        List<WebElement> SAPChildList = driver.findElements(By.id("com.vector.guru99:id/lblListItem"));
        for(WebElement eleChild :SAPChildList){
            String listText = eleChild.getText();
            if(eleChild.isDisplayed()) {
                System.out.println(listText + " is clickable");
            }
            // add to HashSet
            allElementText.add(listText);
        }
        // 2.2 New window is appears to display its content: text, youtube video...
        List<WebElement> ItemList = driver.findElements(By.id("com.vector.guru99:id/lblListItem"));
        ItemList.get(0).click();

        List<WebElement> HeadingTextList= driver.findElements(By.id("com.vector.guru99:id/heading_text"));
        for (WebElement ele:HeadingTextList){
            String text = ele.getText();
            if (ele.isDisplayed()){
                System.out.println(text + " is display");
            }
        }
        // 2.3 click lesson #1
        HeadingTextList.get(0).click();

        WebElement eleVideo = driver.findElement(By.id("com.vector.guru99:id/youtube_view"));
        if(eleVideo.isDisplayed()&&eleVideo.isEnabled()){
            System.out.println("Youtube is display and allowed to enable click");
        }

        int childElementsCount = allElementText.size();

        Assert.assertEquals(childElementsCount,11,"Sap course and Sub Contents are equal");

    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

}
