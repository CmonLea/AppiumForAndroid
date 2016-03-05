import static org.junit.Assert.*;

import java.io.File;
import java.net.URL;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;


public class Test2 
{

	private static AppiumDriver driver;
	@Test
	public void test() throws Exception {
		
		File app=new File("E:\\zhihu.apk");
		DesiredCapabilities capabilities=new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
		driver=new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
		Thread.sleep(5000);
		System.out.println("启动APP成功");
		Thread.sleep(2000);
		
		/*
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");//这句不是必须的
		capabilities.setCapability("deviceName","Android Emulator");
		capabilities.setCapability("platformVersion", "4.4");
		capabilities.setCapability("platformName","Android");
		capabilities.setCapability("appPackage", "com.android.calculator2");
		capabilities.setCapability("appActivity", ".Calculator");
		AppiumDriver driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		*/
		
		
		
		
	}

	
}
