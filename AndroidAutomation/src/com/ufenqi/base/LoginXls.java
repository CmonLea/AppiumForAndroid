package com.ufenqi.base;
/**
 * 该类用来封装读取到的登录xls文档中的信息
 * @author 杨少佳
 * 2015-11-11
 *
 */
public class LoginXls
{
	private String username;
	private String password;
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
}
