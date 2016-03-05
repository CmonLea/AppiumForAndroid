package com.ufenqi.report;
/**
 * 该类用于封装结果输出信息
 * @author 杨少佳
 * 2015-11-11
 *
 */
public interface MessageOutput {
	
	public String mPass=" 成功! ";
	public String mFail=" 失败! ";
	public String mMatch=" 匹配! ";
	public String mNot=" 不 ";
	
	public String mObject=" 元素: ";
	public String mContent=" 字段: ";
	
	public String mExpectedResult=" 期望值 ";
	public String mActualResult=" 实际值 ";
	
	public String mCreateLog=" 创建日志 ";
	public String mInit=" 程序初始化 ";
	public String mHTMl="";
	public String mDebug="SeleniumBaseLibLog";
	
	public String mFind=" 寻找 ";
	public String mClick=" 点击 ";
	public String mInput=" 写入信息 ";
	public String mSelect=" 选择 ";
	
	
	//BussinessLib中的信息
	
	public String mVerify=" 校验 ";
	public String mLogin=" 用户登录 ";
	public String mLogout="退出";
	public String mSetPassword=" 设置密码 ";
	public String mSearvhGoods=" 搜索商品 ";
	public String mToMyGoodsOrder="跳转到我的商品订单页面";
	public String mToMyUFundOrder="跳转到我的U基金订单页面";
	public String mToMyHotelOrder="跳转到我的酒店订单页面";
	public String mToUFundSignContractPage="跳转到U基金签订合同页面";
	public String mToSubmitHotelOrderPage="跳转到提交酒店订单页面";
	public String mRoomTotalPrice="房间总费用";
	public String mToCheckStand="跳转到收银台页面";
	
	public String mToApplyUFundPage="跳转到U基金申请页面";
	public String mToSubmtiUFundOrderPage="跳转到U基金订单提交页面";
	public String mUFundSum="U基金借款金额总价";
	public String mCreateUFundOrderSucc="U基金下单成功";
	public String mBankName="绑定的银行名称";
	public String mBankCustomerName="绑定的银行的客户姓名";
	public String mBankCardNum="绑定的银行卡号";
	
	//订单提交页面
	public String mToSubmitOrderPage="跳转到提交订单页面";
	public String mSubmitOrderPage="提交订单页面";
	public String mGoodsTotalPrice="商品总价格";
	public String mCreateGoodsOrderSuccess="商品订单创建成功";
	//收银台页面
	public String mCheckStandPage="收银台页面";
	
	//酒店列表页面
	public String mHotel_City="酒店所在城市";
	public String mHotel_Area="酒店所在区域";
	public String mHotel_star="酒店星级";
	public String mHotel_Price="酒店价格";
	
	//充值页面
	public String mToRechargePage="跳转到充值页面";
	public String mrealPrice="话费充值应付金额";
	public String mCreateRechargeOrder="话费充值订单下单";
	public String mToMyRechargeOrderPage="跳转到我的充值订单页面";
	public String mCheckStandPrice="收银台金额";
	
	//哆啦钱包
	public String mCreditAvailableSum = "个人可用额度";
	
	
	
	
}
