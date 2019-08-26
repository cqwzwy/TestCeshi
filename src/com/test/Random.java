package com.test;
import java.util.*;
import static java.lang.System.*;
public class Random {
	
	public static void main(String[]a) {
		Scanner scan=new Scanner(in);
		int N=scan.nextInt();
		
		List list=new ArrayList();
		
		for(int i=0;i<N;i++) {
			int num=(int)(Math.random()*1000)+1;
			if(i==0) {
				list.add(0, num);
			}else {
				if(list.contains(num)) {
					 
				}else {
					int flag=0;
					for(int j=0;j<list.size();j++) {
						int s=(int) list.get(j);
						if(num<s) {
							list.add(j, num);
							flag=1;
							break;
						}
					}
					if(flag==0) {
						
						list.add(list.size(), num);
						
					}

				}
				
				
			}
			
		}
		
		for(int i=0;i<list.size();i++) {
			out.println(list.get(i));
		}
		
		
	}

}
