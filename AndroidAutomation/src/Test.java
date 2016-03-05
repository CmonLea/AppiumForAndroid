import java.io.File;
import java.net.URL;
import org.junit.After;
import org.junit.Before;
import io.appium.java_client.android.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Test
{

	private AndroidDriver AndroidDriver;

	@Before
	// Appium测试环境准备，包括：待测app信息设置、测试机（真机/模拟器）信息设置，一般放在setUp()方法
	public void setUp() throws Exception
	{
		// 待测apk 存放路径
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "apps");
		File app = new File(appDir, "zhihu.apk");
		// 真机信息
		DesiredCapabilities capabilities = new DesiredCapabilities();
		// 启动哪种设备，是真机还是模拟器？
		capabilities.setCapability("deviceName", "Android Emulator");
		// 移动浏览器的名称。比如Safari' for iOS and 'Chrome', 'Chromium', or 'Browser' for
		// Android；与app属性互斥。
		// capabilities.setCapability(BrowserType.ANDROID, "");
		// 版本
		capabilities.setCapability(CapabilityType.VERSION, "4.3");
		// 使用哪种移动平台。iOS, Android
		capabilities.setCapability("ANDROID", Platform.ANDROID);
		// 应用的绝对路径，注意一定是绝对路径。此条非必填
		capabilities.setCapability("app", app.getAbsolutePath());
		// 设置支持输入中文
		capabilities.setCapability("unicodeKeyboard", "True");
		capabilities.setCapability("resetKeyboard", "True");

		// 待测apk 包名
		capabilities.setCapability("app-package", "com.zhihu.android");
		// 待测apk 入口类;注意，原生app的话要在activity前加个"."。
		capabilities.setCapability("app-activity",
				"com.zhihu.android/.ui.activity.GuideActivity");
		// capabilities.setCapability("app-package", "com.ufenqi.app");
		// capabilities.setCapability("app-activity",
		// "com.ufenqi.app.ui.activity.SplashActivity");
		// AppiumDriver
		URL URL = new URL("http://127.0.0.1:4723/wd/hub");
		AndroidDriver = new AndroidDriver(URL, capabilities);
	}

	@After
	// Appium用例执行完成后的环境清理，一般放在tearDown()方法
	public void tearDown() throws Exception
	{
		// AndroidDriver.quit();
		// AndroidDriver.removeApp("");
	}

	@org.junit.Test
	// 测试用例编写，一般以testXX开头
	public void test()
	{
		// AndroidDriver.execute_script('mobile: keyevent', :keycode => 4)
		// AndroidDriver.sendKeyEvent(AndroidKeyCode.MENU);
		// WebElement el = AndroidDriver.findElement(By.name("Add note"));
		// el.click();
		// findElementByName是通过控件上的文字识别
		// WebElement el= AndroidDriver.findElementByName( "buttonText");
		// el.click();
		try
		{
			// 知乎登录测试
			// AndroidDriver.findElement(By.id("com.zhihu.android:id/login")).click();
			// AndroidDriver.findElement(By.id("com.zhihu.android:id/email")).sendKeys("test@126.com");
			// AndroidDriver.findElement(By.id("com.zhihu.android:id/password")).sendKeys("123456");
			// AndroidDriver.findElement(By.id("android:id/button2")).click();
			// 知乎注册测试
			AndroidDriver.findElement(By.id("com.zhihu.android:id/register"))
					.click();
			AndroidDriver.findElement(By.id("com.zhihu.android:id/last_name"))
					.sendKeys("wang");
			AndroidDriver.findElement(By.id("com.zhihu.android:id/first_name"))
					.sendKeys("���");
			AndroidDriver.findElement(By.id("com.zhihu.android:id/email"))
					.sendKeys("wangfeng01897@163.com");
			AndroidDriver.findElement(By.id("com.zhihu.android:id/password"))
					.sendKeys("wang5315");
			AndroidDriver.findElement(By.id("com.zhihu.android:id/done"))
					.click();
		} catch (Exception e)
		{

		}
	}
}
