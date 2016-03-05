package com.ufenqi.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ufenqi.base.BaseLib;
import com.ufenqi.report.HTMLReport;

/**
 * 封装通用的功能方法
 * @author 杨少佳
 * 2015-11-11
 */
public class BaseLib
{
	
	static HTMLReport hr = new HTMLReport();
	public static String HtmlLog;
	public static String AppiumBaseLibLog;

	// 加载配置文件
	public static String getProperties(String key)
	{
		InputStream in = null;
		String result = "";
		try
		{
			in = BaseLib.class.getResourceAsStream("/config/init.properties");
			Properties prop = new Properties();
			prop.load(in);

			result = prop.getProperty(key);
			return result;
		} catch (IOException e)
		{
			throw new RuntimeException("加载配置文件失败" + e);
		}
	}

	// ********************************************************************************//

	// 读取Excel文件信息
	public static List readXls(String cfgPath) throws IOException
	{
		InputStream in = new FileInputStream(cfgPath);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(in);
		LoginXls xlsDto = null;
		List<LoginXls> list = new ArrayList<LoginXls>();
		// 循环Sheet
		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++)
		{
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (hssfSheet == null)
			{
				continue;
			}
			// 循环Row
			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++)
			{
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow == null)
				{
					continue;
				}
				xlsDto = new LoginXls();
				// 获取Cell
				// 从0开始
				HSSFCell username = hssfRow.getCell(0);
				if (username == null)
				{
					continue;
				}
				xlsDto.setUsername(getValue(username));//封装单元格数据

				HSSFCell password = hssfRow.getCell(1);
				if (password == null)
				{
					continue;
				}
				xlsDto.setPassword(getValue(password));
				list.add(xlsDto);
			}
		}
		return list;
	}

	private static String getValue(HSSFCell hssfCell)
	{
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN)
		{
			// 返回布尔类型的值
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC)
		{
			// 返回数值类型的值
			return String.valueOf((long)(hssfCell.getNumericCellValue()));
		}else
		{
			// 返回字符串类型的值
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}

	// ************************************************************//

	// 发送邮件
	public static void sendMail()
	{
		String[] tos =
		{ "aaa@aa.com", "bbb@aa.com" };

		// 创建Properties对象，并配置参数
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", "localhost");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");

		// 创建Session对象
		Session session = Session.getDefaultInstance(props, new Authenticator()
		{
			// 使用匿名内部类实现，并复写其方法
			@Override
			protected PasswordAuthentication getPasswordAuthentication()
			{
				// PasswordAuthentication类对象用来封装发送者的邮箱账户的用户名和密码
				return new PasswordAuthentication("yangshaojia@ufenqi.com","123456");
			}
		});

		try
		{
			//设置邮件地址
			Address from = new InternetAddress("yangshaojia@ufenqi.com");// 发件人

			String toList = getMailList(tos);
			InternetAddress[] iaToList = new InternetAddress().parse(toList);

			// 创建Message对象
			Message message = new MimeMessage(session);

			// 设置邮件信息头
			message.setFrom(from);
			message.setRecipients(Message.RecipientType.TO, iaToList);// 收件人
			message.setSubject("****测试报告");

			// 编写邮件正文
			// 邮件正文内容
			MimeBodyPart bodyPart = new MimeBodyPart();
			bodyPart.setContent("附件是测试报告", "text/html;charset=UTF-8");

			// 附件部分
			MimeBodyPart attachmentBodyPart = new MimeBodyPart();
			// 创建DataSource
			File attachment = new File("****************");
			FileDataSource dataSource = new FileDataSource(attachment);
			// 创建DataHandler
			DataHandler dataHandler = new DataHandler(dataSource);
			attachmentBodyPart.setDataHandler(dataHandler);

			// 设置附件名称编码
			attachmentBodyPart.setFileName(MimeUtility.encodeWord(attachment
					.getName()));

			// 合成
			MimeMultipart multipart = new MimeMultipart();
			multipart.addBodyPart(bodyPart);
			multipart.addBodyPart(attachmentBodyPart);

			// 将合成信息添加到邮件体中
			message.setContent(multipart);

			// 创建Transport对象发送邮件
			Transport transport = session.getTransport("smtp");
			transport.connect();
			// message.getAllRecipients()获取邮件中所有的收件人
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();

		} catch (Exception e)
		{
			throw new RuntimeException("发送邮件失败：" + e);
		}

	}

	private static String getMailList(String[] mailArray)
	{

		StringBuffer toList = new StringBuffer();
		int length = mailArray.length;
		if (mailArray != null && length < 2)
		{
			toList.append(mailArray[0]);
		} else
		{
			for (int i = 0; i < length; i++)
			{
				toList.append(mailArray[i]);
				if (i != (length - 1))
				{
					toList.append(",");
				}

			}
		}
		return toList.toString();

	}

	// **********************************************************************************

	// 创建随机字符串
	public static String getRandomstring(int length)
	{
		String baseStr = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < baseStr.length(); i++)
		{
			int index = random.nextInt(baseStr.length());
			buffer.append(baseStr.charAt(index));
		}
		return buffer.toString();
	}

	// ************************************************************************************

	// 封装线程等待
	public static void newSleep(int p_time)
	{
		try
		{
			Thread.sleep(p_time * 1000);
		} catch (InterruptedException e)
		{
			
		}

	}

	// 获取当前时间信息
	public static String getCurrentTime() throws Exception
	{

		Calendar ca = Calendar.getInstance();
		int year = ca.get(Calendar.YEAR);// 获取年份
		int month = ca.get(Calendar.MONTH);// 获取月份
		int day = ca.get(Calendar.DATE);//  获取日
		int minute = ca.get(Calendar.MINUTE);// 分
		int hour = ca.get(Calendar.HOUR);// 小时
		int second = ca.get(Calendar.SECOND);// 秒
		return (String.valueOf(year) + "-" + String.valueOf(month + 1) + "-"
				+ String.valueOf(day) + "-" + String.valueOf(hour) + "-"
				+ String.valueOf(minute) + "-" + String.valueOf(second));

	}
	//获取当前日期
	public static int getCurrentDate()
	{
		Calendar ca = Calendar.getInstance();
		return ca.get(Calendar.DATE);
	}

	// 创建HTML类型的测试报告页面信息
	public static void createHtmlLog(String caseName) throws Exception
	{

		String RESULTS_BASE_PATH = "Log" + File.separator + "loggingResults";

		String resultsPath = new File(RESULTS_BASE_PATH).getAbsolutePath();

		String resultHtmlFileName = resultsPath + File.separator + caseName
				+ "_" + BaseLib.getCurrentTime() + "_"+ "_log.html";
		HtmlLog = resultHtmlFileName;

		String resultAppiumLogFileName = resultsPath + File.separator
				+ caseName + "_" + BaseLib.getCurrentTime() + "_"
				+ "_seleniumLog.log";
		AppiumBaseLibLog = resultAppiumLogFileName;

		if (!new File(RESULTS_BASE_PATH).exists())
		{
			new File(RESULTS_BASE_PATH).mkdirs();
		}

		System.out.println("创建 HTML日志 ---" + resultHtmlFileName);
		hr.setup(resultHtmlFileName);

	}

	/*
	// 获取浏览器名称
	private static String getBrowserName()
	{
		String driverName = BaseLib.getProperties("driverName");
		if (driverName.contains("IE"))
		{
			return "iexplore";
		} else if (driverName.contains("chrome"))
		{
			return "googleChrome";
		} else
		{
			return "FireFox";
		}
	}
	*/
	
	public static void closeLog()
	{
		hr.closeLog();
	}

	public static void writeToLog(String info, Object expected, Object actual,String result)
	{
		hr.logWriter(info, expected, actual, result);
	}
	
	
	
	
	//比较两个价格相等的方法
	public static String isPriceEquality(int priceA,int priceB)
	{
		if(priceA==priceB)
		{
			return "通过";
		}
		else
		{
			return "不通过";
		}
	}
	public static String isPriceEquality(double priceA,double priceB)
	{
		if(priceA==priceB)
		{
			return "通过";
		}
		else
		{
			return "不通过";
		}
	}
	
	
	/**
	 * 判断两个字符串是否相等
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static String isStringEquals(String s1,String s2)
	{
		if(s1!=null && s2!=null)
		{
			if(s1.equals(s2))
			{
				return "通过";
			}else
			{
				return "不通过";
			}
		}else
		{
			return "不通过";
		}
	}
	
	
	/**
	 * 验证银行卡号
	 * @param bankCardNum_db
	 * @param bankCardNum
	 * @return
	 */
	public static String isStringContains(String bankCardNum_db,String ... bankCardNum)
	{
		String s1 = bankCardNum[0];
		String s2 = bankCardNum[1];
		boolean b1 = (bankCardNum_db.substring(0, 4)).equals(s1);
		boolean b2 = (bankCardNum_db.substring(15)).equals(s2);
		if(b1 & b2)
		{
			return "通过";
		}
		else
		{
			return "不通过";
		}
	}
	
	
	
	
	
	
	
	

}
