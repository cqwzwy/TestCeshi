package com.test;
import static java.lang.System.*;
import java.io.*;
import java.io.IOException;
import java.util.*;
public class Properties {
	
	static {
		
		java.util.Properties properties=getProperties();
		
		properties.list(out);
		
		out.println("CPU个数："+Runtime.getRuntime().availableProcessors());
		out.println("虚拟机内存总量："+(Runtime.getRuntime().totalMemory())/1024/1024);
		
		try {
			Process process=Runtime.getRuntime().exec("tasklist");
			BufferedReader br=new BufferedReader(new InputStreamReader(process.getInputStream()));
			
			String len;
			while((len=br.readLine())!=null) {
				
				out.println(len);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[]a) {
		
	}
}
