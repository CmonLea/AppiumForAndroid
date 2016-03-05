package com.ufenqi.testcase;
/**
 * 该类用于编写测试用例
 * @author 杨少佳
 * 2015-11-11
 */
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.ufenqi.appiumlib.AppiumBaseLibImpl;
import com.ufenqi.base.BaseLib;
import com.ufenqi.business.BusinessLib;

public class AndroidTestCase
{
	AppiumBaseLibImpl appiumBaseLibImpl = new AppiumBaseLibImpl();
	AppiumDriver driver = appiumBaseLibImpl.getDriver();
	BusinessLib businessLib = new BusinessLib(driver);
	
	@Before
	public void setUp() throws Exception
	{
		//配置app测试环境，并启动被测app
		appiumBaseLibImpl.newSetUp("安卓端测试");
		
	}


	@Test
	public void test() 
	{
		
		BaseLib.newSleep(5);
		
		businessLib.login_Testcase();
		
		businessLib.goodsOrderPay("5628677110");
		
		
		
	}

	@After
	public void tearDown() throws Exception
	{
		
		BaseLib.closeLog();
		//driver.close();
		driver.quit();
	}
}
