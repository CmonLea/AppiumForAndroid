package com.ufenqi.business;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.service.DriverCommandExecutor;
import org.openqa.selenium.server.browserlaunchers.DrivenSeleniumLauncher;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import com.ufenqi.appiumlib.AppiumBaseLibImpl;
import com.ufenqi.base.BaseLib;
import com.ufenqi.objectstore.ObjectStoreI;
import com.ufenqi.report.MessageOutput;

import java.util.logging.Logger;
/**
 * 该类用于封装安卓客户端的业务场景
 * @author 杨少佳
 * time 2015-11-11
 */
public class BusinessLib extends AppiumBaseLibImpl
{
	Logger logger =java.util.logging.Logger.getLogger(BusinessLib.class.getName());
	AppiumDriver driver;
	public BusinessLib(AppiumDriver driver)
	{
		this.driver = driver;
	}
	
	/**
	 * 连接服务
	 */
	public void connectServer()
	{
		driver.findElementById(ObjectStoreI.connectServer).click();
		BaseLib.newSleep(1);
		driver.findElementByXPath(ObjectStoreI.serverAddress).click();
		BaseLib.newSleep(1);
	}
	
	
	/**
	 * 跳过APP启动时的引导图
	 */
	public void skipGuide()
	{
		while(true)
		{
			//一直执行向左滑动
			logger.info("滑动引导图");
			//driver.swipe(900, 1000, 100, 1000, 500);
			super.swipe(driver,"left");
			//如果找到了“立即体验”按钮，则停止滑动，然后点击进入
			if("通过".equals(super.isElementsExits(driver, By.id(ObjectStoreI.immediatelyExperienceBtn))))
			{
				logger.info("找到【立即体验】按钮");
				break;
			}
		}
		logger.info("点击【立即体验】进入客户端首页");
		driver.findElement(By.id(ObjectStoreI.immediatelyExperienceBtn)).click();
		BaseLib.newSleep(5);
	}
	
	
	/**
	 * 登录测试用例
	 */
	public void login_Testcase()
	{
		skipGuide();
		
		//点击哆啦钱包图标
		logger.info("点击【哆啦钱包】图标");
		driver.findElementById(ObjectStoreI.duolaIcon).click();
		BaseLib.newSleep(2);
		logger.info("进入登录页面");
		driver.findElementById(ObjectStoreI.gotoLoginButton).click();
		
		BaseLib.newSleep(2);
		logger.info("输入用户名");
		WebElement usernameTxt = driver.findElementById(ObjectStoreI.usernameTxt);
		if(usernameTxt.getText().length()>0)
		{
			usernameTxt.clear();
		}
		usernameTxt.sendKeys(BaseLib.getProperties("login_username"));
		BaseLib.newSleep(1);
		logger.info("输入密码");
		driver.findElementById(ObjectStoreI.passwordTxt).sendKeys(BaseLib.getProperties("login_password"));
		BaseLib.newSleep(1);
		
		driver.navigate().back();//将手机键盘退出
		//driver.hideKeyboard();//收起键盘
		logger.info("点击登录按钮");
		driver.findElementById(ObjectStoreI.loginButton).click();
		
		//校验是否登录成功
		logger.info("校验登录是否成功");
		super.newAssertEquals(driver, MessageOutput.mVerify+MessageOutput.mLogin, "通过", 
				super.isElementsExits(driver, By.id(ObjectStoreI.creditAmount)));
		logger.info("登录成功");
		
	}
	
	/**
	 * 退出登录业务
	 */
	public void logout()
	{
		
		//进入哆啦钱包
		logger.info("点击【哆啦钱包】图标");
		driver.findElementById(ObjectStoreI.duolaIcon).click();
		BaseLib.newSleep(2);
		logger.info("点击【信用额度】,进入【个人资料】页面");
		driver.findElementById(ObjectStoreI.creditAmount).click();
		BaseLib.newSleep(2);
		logger.info("点击【退出】按钮");
		driver.findElementById(ObjectStoreI.logoutButton).click();
		BaseLib.newSleep(2);
		//校验退出是否成功
		logger.info("校验退出是否成功");
		super.newVerifyEquals(driver, MessageOutput.mVerify+MessageOutput.mLogout,"通过",
				super.isElementsExits(driver, By.id(ObjectStoreI.gotoLoginButton)));
		logger.info("退出成功");
		
	}

	
	/**
	 * 封装登录业务
	 */
	public void login()
	{
		logger.info("输入用户名");
		WebElement usernameTxt = driver.findElementById(ObjectStoreI.usernameTxt);
		if(usernameTxt.getText().length()>0)
		{
			usernameTxt.clear();
		}
		usernameTxt.sendKeys(BaseLib.getProperties("login_username"));
		BaseLib.newSleep(1);
		logger.info("输入密码");
		driver.findElementById(ObjectStoreI.passwordTxt).sendKeys(BaseLib.getProperties("login_password"));
		BaseLib.newSleep(1);
		
		driver.navigate().back();//将手机键盘退出
		logger.info("点击登录按钮");
		driver.findElementById(ObjectStoreI.loginButton).click();
		
	}
	
	
//--------------------------------商品业务-----------------------------------------------	
	
	/**
	 * 商品查询业务
	 * @param keywords：要查询的商品的关键字
	 */
	public void searchGoods(String keywords)
	{
		logger.info("点击查询图标进入商品搜索页面");
		driver.findElementById(ObjectStoreI.searchIcon).click();
		BaseLib.newSleep(2);
		logger.info("输入关键字");
		driver.findElementById(ObjectStoreI.searchSendkeysTxt).sendKeys(keywords);
		BaseLib.newSleep(1);
		logger.info("点击【搜索】按钮");
		driver.findElementById(ObjectStoreI.searchButton).click();
		BaseLib.newSleep(5);
		//校验查询结果
		logger.info("校验查询结果");
		super.newVerifyEquals(driver, MessageOutput.mVerify+MessageOutput.mSearvhGoods,"通过",
				super.isContentExistForSearch(driver, keywords));
		
	}
	
	/**
	 * 商品订单支付
	 * @param goodsOrderNum：商品订单号
	 */
	public void goodsOrderPay(String goodsOrderNum)
	{
		
		//先进入到哆啦钱包，
		
		//然后向上滑一下
		super.swipe(driver, "up");
		BaseLib.newSleep(1);
		
		//点击【我的订单】
		logger.info("点击【我的订单】");
		driver.findElementById(ObjectStoreI.myOrder).click();
		BaseLib.newSleep(5);
		
	    WebElement element = driver.findElementByXPath("//android.widget.TextView[contains(@text,'5628677110')]/parent::node()/following-sibling::LinearLayout[contains(@text,'签订合同')]");
	    String index = element.getAttribute("index");
		String text = element.getText();
		///parent:node()/following-sibling::LinearLayout[2]/TextView[1]
	   
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
