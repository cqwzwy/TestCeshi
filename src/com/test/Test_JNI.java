package com.test;
import static java.lang.System.*;
public class Test_JNI {
	
	static {
		System.loadLibrary("Dll1");
	}
	
	public native void testHello();
	
	public static void main(String[]a) {
		
		Test_JNI tj=new Test_JNI();
		
		tj.testHello();
		
		
		
	}
	
	
	
}
