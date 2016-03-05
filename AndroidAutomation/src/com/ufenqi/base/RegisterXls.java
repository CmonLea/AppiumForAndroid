package com.ufenqi.base;
/**
 * 该类用来封装读取到的注册的xls文档中的信息
 * @author 杨少佳
 * 2015-11-11
 *
 */
public class RegisterXls
{
	private String mobile;
	private String crePassword;
	private String rePassword;
	private String verification_code;
	
	//��ȡ�ֻ���֤��
	public static String getMobile_verificationcode(String mobile)
	{
		String mobile_verificationcode = "";
		
		return mobile_verificationcode;
	}
	
	
	public String getMobile()
	{
		return mobile;
	}
	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}
	public String getCrePassword()
	{
		return crePassword;
	}
	public void setCrePassword(String crePassword)
	{
		this.crePassword = crePassword;
	}
	public String getRePassword()
	{
		return rePassword;
	}
	public void setRePassword(String rePassword)
	{
		this.rePassword = rePassword;
	}
	public String getVerification_code()
	{
		return verification_code;
	}
	public void setVerification_code(String verification_code)
	{
		this.verification_code = verification_code;
	}
	
	
	
}
