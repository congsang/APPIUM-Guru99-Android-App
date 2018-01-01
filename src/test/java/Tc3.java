import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sang Nguyen on 5/23/2017.
 */
public class Tc3 {
    AndroidDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException{
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
    public void Tc3(){
        // 1. home Page Verification
        if(driver.findElement(By.id("android:id/home")).isDisplayed()) {
            System.out.println(" Guru 99 App Home Page is displayed ");
        }
        // 2. to click on QUIZ Button
        WebElement eleQuiz = driver.findElement(By.id("com.vector.guru99:id/action_quiz"));
        if(eleQuiz.isDisplayed()&&eleQuiz.isEnabled()){
            System.out.println("QUIZ is exist & clickable on the top screen");
            eleQuiz.click();
        }
        // check List item is display after that click QUIZ Button
        List<WebElement> listItem = driver.findElements(By.id("com.vector.guru99:id/lblListItem"));
        for(WebElement ele:listItem){
            String text = ele.getText();
            if(ele.isDisplayed()){
                System.out.println("Quiz is open " +text);
            }
        }
        // 3. to click on Quality Center
        listItem.get(0).click();
        // 	Questions & Answers Verification
        // verify quiz item name
        String quizName = driver.findElement(By.xpath("//android.widget.TextView[@text='Quality Center']")).getText();
        System.out.println("Quiz item name: "+quizName);
        // first question
        String firstQuestion = driver.findElement(By.id("com.vector.guru99:id/question")).getText();
        System.out.println("First question: "+firstQuestion);
        // option are clickable & not selected yet
        // option 1
        WebElement eleOption1 = driver.findElement(By.xpath("//android.widget.RadioButton[@text='Yes' and @index='0']"));
        if(!eleOption1.isSelected()&eleOption1.isEnabled()){
            eleOption1.getText();
            eleOption1.click();
            System.out.println(eleOption1);
        }
        // option 2
        WebElement eleOption2 =driver.findElement(By.xpath("//android.widget.RadioButton[@text='No' and @index='1']"));
        if(!eleOption2.isSelected()&eleOption2.isEnabled()){
            eleOption2.getText();
            System.out.println(eleOption2);
        }
        // getting Time Remaining
        String timeForQuestion =driver.findElement(By.id("com.vector.guru99:id/timer")).getText();
        System.out.println("Time Remaining:  "+ timeForQuestion);
        // button finish & next are displayed
        String btnFinish = driver.findElement(By.id("com.vector.guru99:id/finish")).getText();
        System.out.println(btnFinish +"is displayed");
        WebElement btnNext = driver.findElement(By.id("com.vector.guru99:id/next"));
        btnNext.getText();
        System.out.println(btnNext +"is displayed");
        // 4. select a correct answer option, click next
        btnNext.click();

        driver.findElement(By.id("com.vector.guru99:id/option1")).click();
        // click on finish
        driver.findElement(By.id("com.vector.guru99:id/finish")).click();
        // result
        WebElement eleResult = driver.findElement(By.id("com.vector.guru99:id/heading"));
        String textResult =eleResult.getText();
        if(eleResult.isDisplayed()){
            System.out.println(" Test Results: "+textResult);
        }

        String score = driver.findElement(By.id("com.vector.guru99:id/score")).getText();
        Assert.assertEquals(score,"You Scored: 0/20");
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

}
