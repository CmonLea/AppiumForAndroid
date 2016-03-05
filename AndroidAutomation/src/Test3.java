import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;


public class Test3
{
	@Test
	public  void  show (){
		/*
		 * 项目：从D盘复制一个文件到E盘
		 * 使用InputStream 字节输入流 和 OutputStream  字节输出流
		 */
		InputStream  fis = null;
		FileOutputStream fos = null;
		try{
			fis = new FileInputStream("E:\\a.txt");
			fos = new FileOutputStream("E:\\123.txt");
			int x =0;
			while( (x = fis.read())!= -1 ){
				fos.write(x);
			}
			
			
		}catch(IOException ex ){
			ex.printStackTrace();
			throw new RuntimeException("复制失败");
		}finally{
			try{
				if(fis != null)
					fis.close();
				if(fos != null)
					fos.close();
			}catch(IOException ex){
				ex.printStackTrace();
				throw new RuntimeException("资源关闭失败");
			}

		}
		
		
	}

}

	
	
	
	
