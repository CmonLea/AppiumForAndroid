package com.ufenqi.appiumlib;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

/**
 * 该类用于封装appium的API
 * @author 杨少佳
 * 2015-11-11
 */
import com.ufenqi.base.BaseLib;
import com.ufenqi.objectstore.ObjectStoreI;
import com.ufenqi.report.MessageOutput;

public class AppiumBaseLibImpl extends BaseLib
{

	AppiumDriver driver;
	FileHandler fileHandler;
	static Logger logger = Logger.getLogger(AppiumBaseLibImpl.class.getName());
	
	// 获取驱动driver
	public AppiumDriver getDriver()
	{
		try
		{
			File app = new File(BaseLib.getProperties("appPath"));
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.APP,app.getAbsolutePath());
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,BaseLib.getProperties("device"));
			String ipAddress = BaseLib.getProperties("ipAddress");
			String port = BaseLib.getProperties("port");
			if (ipAddress != null & port != null)
			{
				driver = new AndroidDriver(new URL("http://" + ipAddress + ":"+ port + "/wd/hub"), capabilities);
			}
			return driver;
		} catch (Exception e)
		{
			throw new RuntimeException("driver驱动初始化失败：" + e.getMessage());
		}

	}

	// 自动截图并保存，参数指定某个具体的driver和图片的名称
	// 调用截图方法需要将浏览器的语言手动设置成中文；
	public void screenShort(AppiumDriver driver, String captureName)
	{
		File screenShortFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy_MM_dd_hh_mm_ss");
		String dateStr = dateFormat.format(new Date());
		try
		{
			FileUtils.copyFile(screenShortFile, new File("Log/snapshot/"+ dateStr+ "_" + captureName));
		} catch (IOException e)
		{
			throw new RuntimeException("截图失败：" + e);
		}
	}
	
	
	
	// 封装setUp方法
	public void newSetUp(String caseName)
	{
		try
		{
			BaseLib.newSleep(5);
			BaseLib.createHtmlLog(caseName);

			// 创建AppiumBaseLibLog.log
			fileHandler = new FileHandler(BaseLib.AppiumBaseLibLog);
			fileHandler.setLevel(Level.INFO);
			fileHandler.setFormatter(new SimpleFormatter());

			logger.addHandler(fileHandler);
			logger.info("****************" + caseName + "****************");
		} catch (Exception e)
		{
			logger.severe(MessageOutput.mInit + MessageOutput.mFail);
			logger.severe(e.getMessage());
			screenShort(driver, "脚本初始化截图.png");
			System.exit(1);
			throw new RuntimeException(e);
		}

	}

	//判断页面元素是否存在
	public String isElementsExits(AppiumDriver driver, By by)
	{
		try
		{
			driver.findElement(by);
			logger.info(MessageOutput.mFind + MessageOutput.mObject + by+ MessageOutput.mPass);
			return "通过";
		} catch (Exception e)
		{
			logger.severe(e.getMessage());
			logger.severe(MessageOutput.mFind + MessageOutput.mObject + by+ MessageOutput.mFail);
			return "不通过";
		}
	}
	
	
	/**
	 * 封装AssertEquals方法
	 * @param driver
	 * @param info：验证信息说明
	 * @param expectedValue：期望结果
	 * @param actualValue：实际结果
	 */
	public String newAssertEquals(AppiumDriver driver, String info,Object expectedValue, Object actualValue)
	{
		if (expectedValue.equals(actualValue))
		{
			logger.info(MessageOutput.mActualResult+ MessageOutput.mExpectedResult + MessageOutput.mMatch);
			BaseLib.writeToLog(info, expectedValue, actualValue, "PASS");
			return "通过";

		} else
		{
			logger.severe(MessageOutput.mActualResult+ MessageOutput.mExpectedResult + MessageOutput.mNot
					+ MessageOutput.mMatch);
			BaseLib.writeToLog(info, expectedValue, actualValue, "FAIL");
			screenShort(driver, info + MessageOutput.mActualResult+ MessageOutput.mExpectedResult 
					+ MessageOutput.mNot+ MessageOutput.mMatch + "__截图");
			driver.closeApp();
			driver.quit();// Assert结果为false，脚本结束运行
			return "失败";
		}
	}

	/**
	 * 封装verifyEquals方法
	 * @param driver
	 * @param info：验证信息说明
	 * @param expectedValue：期望结果
	 * @param actualValue：实际结果
	 */
	public String newVerifyEquals(AppiumDriver driver, String info,Object expectedValue, Object actualValue)
	{
		if (expectedValue != null & expectedValue.equals(actualValue))
		{
			logger.info(MessageOutput.mActualResult+ MessageOutput.mExpectedResult + MessageOutput.mMatch);
			BaseLib.writeToLog(info, expectedValue, actualValue, "PASS");
			return "通过";
		} else
		{
			logger.severe(MessageOutput.mActualResult+ MessageOutput.mExpectedResult + MessageOutput.mNot
					+ MessageOutput.mMatch);
			BaseLib.writeToLog(info, expectedValue, actualValue, "FAIL");
			screenShort(driver, info + MessageOutput.mActualResult+ MessageOutput.mExpectedResult
					+ MessageOutput.mNot+ MessageOutput.mMatch + "__截图");
			return "失败";
		}
	}
	
	
	/**
	 * 根据具体坐标进行屏幕滑动
	 * @param driver
	 * @param startX：起始x坐标
	 * @param startY：起始y坐标
	 * @param endX：结束x坐标
	 * @param endY：结束y坐标
	 * @param repeat：需要滑动几次
	 */
	public void swipe(AppiumDriver driver,double startX,double startY,double endX,double endY,int repeat)
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		HashMap<String, Double> swipeObj = new HashMap<String, Double>();
		swipeObj.put("startX", startX);
		swipeObj.put("startY", startY);
		swipeObj.put("endX", endX);
		swipeObj.put("endY", endY);
		//滑动
		for (int i = 0; i <repeat; i++)
		{
			 jse.executeScript("mobile: swipe", swipeObj);
		}
	}
	
	
	
	
	/**
	 * 封装屏幕滑动的方法
	 * @param direction：滑动的方向
	 */
	public void swipe(AppiumDriver driver,String direction)
	{
		//获取屏幕宽度和高度，为了兼容不同分辨率的屏幕，不使用坐标进行定位
		int width = driver.manage().window().getSize().width;//宽度
		int height = driver.manage().window().getSize().height;//高度
		if (direction!=null)
		{
			if ("left".equals(direction))
			{
				driver.swipe(width * 3 / 4, height / 2, width / 4, height / 2, 200);
			} 
			else if("right".equals(direction))
			{
				driver.swipe(width / 4, height / 2, width * 3 / 4, height / 2, 200);
			}
			else if("up".equals(direction))
			{
				driver.swipe(width / 2, height * 3 / 4, width / 2, height / 4, 200);
			}
			else if("down".equals(direction))
			{
				driver.swipe(width / 2, height / 4, width / 2, height * 3 / 4, 200);
			}
			BaseLib.newSleep(1);
		} else
		{
			throw new RuntimeException("请指定滑动的方向!");
		}
	}
	
	/**
	 * 判断指定字段是否存在
	 * @param driver
	 * @param content
	 * @return
	 */
	public String isContentExistForSearch(AppiumDriver driver,String content)
	{
		logger.info("获取查询结果中的关键字");
		WebElement ele = driver.findElementById(ObjectStoreI.hintMessage_search);
		//判断结果页面中是否有“没有查询到时的提示语”
		if("通过".equals(isElementsExits(driver, By.id(ObjectStoreI.hintMessage_search))))
		{
			return "不通过";
		}
		else
		{
			return "";
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
