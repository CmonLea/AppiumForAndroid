package com.ufenqi.objectstore;

import io.appium.java_client.AppiumDriver;

import com.ufenqi.data.DataInputStoreI;

/**
 * 用来封装页面元素对象
 * @author 杨少佳 
 * 时间：2015-11-11
 * 
 */
public interface ObjectStoreI
{
	/**
	 * 安卓客户端
	 */
	
	//引导图页面
	public static final String  immediatelyExperienceBtn="com.ufenqi.app:id/view_feel_now";//立即体验按钮
	
	// 首页元素
	public static final String duolaIcon = "com.ufenqi.app:id/rb_duola_pay";//哆啦图标
	public static final String connectServer = "com.ufenqi.app:id/tb_category";//连接服务
	public static final String serverAddress1 = "http://172.16.50.251:8081";//服务器地址，默认是8081的
	public static final String serverAddress = "//TextView[contains(@Text,'http://172.16.50.251:8081')]";//服务器地址，默认是8081的
	public static final String searchIcon = "com.ufenqi.app:id/search_icon";//查询图标
	public static final String searchSendkeysTxt = "com.ufenqi.app:id/et_search";//查询关键字输入框
	public static final String searchButton = "com.ufenqi.app:id/tv_search";//搜索按钮
	public static final String hintMessage_search = "com.ufenqi.app:id/tv_warm_tips";//查询结果提示语
	
	// 登录，登出元素
	public static final String gotoLoginButton = "com.ufenqi.app:id/login";//进入登录页面按钮
	public static final String usernameTxt = "com.ufenqi.app:id/username";//用户名输入框
	public static final String passwordTxt = "com.ufenqi.app:id/password";//密码输入框
	public static final String loginButton = "com.ufenqi.app:id/login";//登录按钮
	public static final String logoutButton = "com.ufenqi.app:id/tv_exit";//退出按钮

	// 注册元素
	public static final String register_mobile = "mobile_input_text";//手机号码
	public static final String register_crePassword = "password";//密码
	public static final String register_rePasseord = "password_repeat";//确认密码
	public static final String register_mobile_verificationcode = "validcode_text";//短信验证码

	
	//哆啦钱包元素
	public static final String creditAmount = "com.ufenqi.app:id/credit";//信用额度
	public static final String myOrder = "com.ufenqi.app:id/tv_order";//我的订单
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
