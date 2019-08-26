package com.test;

import java.util.*;
import java.util.regex.Pattern;

import static java.lang.System.*;

public class Hex {
	public static void main(String[]a) {
		
	    Scanner scan=new Scanner(in);
        int stu=scan.nextInt();
        int opation=scan.nextInt();
        List list=new ArrayList();
        for(int i=0;i<stu;i++){
            int ns=scan.nextInt();
            list.add(i,ns);
        }
        for(int i=0;i<opation;i++){
            for(int j=0;j<3;j++){
                char op=scan.next().charAt(0);
                int start=scan.nextInt();
                int end=scan.nextInt();
                if(op=='Q'){
                    int max=(int) list.get(start-1);
                    for(int x=(start-1);x<=(end-1);x++){
                        if(max<(int)list.get(x)){
                            max=(int) list.get(x);
                        }
                    }
                    out.println(max);
                }else if(op=='U'){
                    int result=0;
                    result=(int) list.get(end-1);
                    list.remove(start-1);
                    list.add(start-1, result);
                    result=(int) list.get(start-1);
                    out.println(result);
                }
            }
        }
	        
	        
		
		
		
		
		/*Scanner scan=new Scanner(in);
		String str=scan.next();
		int b=scan.nextInt();
		out.println(str+":"+b);
		while(scan.hasNext()) {
		String d=scan.nextLine();
		if(Pattern.matches("0x.*", d)) {
			
			String dx=d.substring(2);
			out.println(Long.parseLong(dx, 16));
			
		}else {
			out.println(Long.parseLong(d, 16));
		}
		}*/
	}
}
