package com.ufenqi.data;

import com.ufenqi.base.BaseLib;

/**
 * 用来封装测试需要的数据
 * @author 杨少佳
 * 2015-08-28
 *
 */
public interface DataInputStoreI
{
	public static final String login_url=BaseLib.getProperties("login_url");
	public static final String h5_login_url=BaseLib.getProperties("h5_login_url");
	public static final String register_url=BaseLib.getProperties("register_url");
	
	//登录的用户名和密码
	public static final String login_username=BaseLib.getProperties("login_username");
	public static final String login_password=BaseLib.getProperties("login_password");
	
	
	//设置收货地址信息
	public static final String contactsName="";//收货人姓名
	public static final String contactsMobile="";//收货人电话
	public static final String dormAddress="";//宿舍地址
	
	
	//基础认证信息
	public static final String realName="";//个人真实姓名
	public static final String IDCardNo="";//个人身份证号
	
	
	//U基金
	public static final String UFundSum="20";//U基金借款金额
	public static final String UBankName="上地支行";//绑定的银行卡的开户行名称
	//public static final int USignContractBtnNum="上地支行";//绑定的银行卡的开户行名称
	
	
	
	//支付层
	public static final String paymentPassword="456789";//交易密码
	
	
	
	//H5端
	//酒店业务
	public static final String liveDate="2015-09-25";//入住日期
	public static final String leaveDate="2015-09-26";//离店日期
	//public static final String hotelId="914858";//酒店id（现付）914858  1862228
	public static final String hotelId="1427270";//酒店id（预付）
	public static final String guestName="张三";//入住人姓名
	public static final String contactMobile="18411115555";//联系人手机
	public static final String contactName="张三";//联系人姓名
	
	//酒店订单列表页
	public static final int hotelPayBtnNum=1;//信用支付按钮所在页面的元素位置
	
	
	
}
