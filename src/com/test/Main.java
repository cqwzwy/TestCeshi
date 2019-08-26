package com.test;
import java.util.*;

import static java.lang.System.*;
public class Main {
	
	public static void main(String[]a) {
		Main m=new Main();
		Scanner scan=new Scanner(in);
		int[] input=new int[100];
		int t=0;
		while(scan.hasNext()) {
			
			int num=scan.nextInt();
			if(num!=0) {
				input[t]=num;
				t++;	
			}else {
				break;
			}

		}
		
		for(int i=0;i<t;i++) {
			int num=input[i];
			int battle=0;
			
			int result=m.getNum(num,battle);
			
			out.println(result);
		}
		
		
		
		
	}

	private  int getNum(int num,int battle) {
		if(num==0||num==1) {
			
			return battle;
		}else if(num==2){
			
			battle=battle+1;
			
			return battle;
		}else {
			battle=battle+(num-(num%3))/3;
			num=num%3+((num-(num%3))/3);
			
			return getNum(num,battle);
		}
	}
	
}
