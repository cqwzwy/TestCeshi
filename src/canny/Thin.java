package canny;
import static java.lang.System.*;
import java.util.*;

import javax.imageio.ImageIO;

import java.awt.image.*;
import java.io.*;
public class Thin {
	public static int[][] getThin(int[][] msg) throws InterruptedException {
		
		int width=msg.length;
		int height=msg[0].length;
		boolean flag=true;
		while(flag) {
			System.out.println("细化");
			flag=false;
		for(int j=1;j<width-1;j++) {
			for(int i=1;i<height-1;i++) {
				
				int pixel=(int) msg[j][i];
				//if(pixel!=0&&pixel!=255)
				//out.println(pixel);
				if(pixel==0) {
					//out.println(pixel);
					int sum=0;
					int z_f=0;
					if(msg[j-1][i]==1&&msg[j-1][i+1]!=1) {
						z_f++;
					}
					if(msg[j-1][i+1]==1&&msg[j][i+1]!=1) {
						z_f++;
					}
					if(msg[j][i+1]==1&&msg[j+1][i+1]!=1) {
						z_f++;
					}
					if(msg[j+1][i+1]==1&&msg[j+1][i]!=1) {
						z_f++;
					}
					if(msg[j+1][i]==1&&msg[j+1][i-1]!=1) {
						z_f++;
					}
					if(msg[j+1][i-1]==1&&msg[j][i-1]!=1) {
						z_f++;
					}
					if(msg[j][i-1]==1&&msg[j-1][i-1]!=1) {
						z_f++;
					}
					for(int x=-1;x<2;x++) {
						for(int y=-1;y<2;y++) {
							if((int)msg[j+x][i+y]==0) {
								sum++;
							}
						}
					}
					if(sum>=3&&sum<=7&&z_f==1) {
						if((msg[j-1][i]==1||msg[j][i+1]==1||msg[j+1][i]==1)&&
								(msg[j][i-1]==1||msg[j][i+1]==1||msg[j+1][i]==1)) {
							//out.println("一次 : "+msg[i][j]);
							msg[j][i]=1;
							
							flag=true;
						}
					}
				}
				
			}
		}
		
		
		
		
		
		
		for(int j=1;j<width-1;j++) {
			for(int i=1;i<height-1;i++) {
				
				int pixel=(int) msg[j][i];
				//if(pixel!=0&&pixel!=255)
				//out.println(pixel);
				if(pixel==0) {
					int sum=0;
					int z_f=0;
					if(msg[j-1][i]==1&&msg[j-1][i+1]!=1) {
						z_f++;
					}
					if(msg[j-1][i+1]==1&&msg[j][i+1]!=1) {
						z_f++;
					}
					if(msg[j][i+1]==1&&msg[j+1][i+1]!=1) {
						z_f++;
					}
					if(msg[j+1][i+1]==1&&msg[j+1][i]!=1) {
						z_f++;
					}
					if(msg[j+1][i]==1&&msg[j+1][i-1]!=1) {
						z_f++;
					}
					if(msg[j+1][i-1]==1&&msg[j][i-1]!=1) {
						z_f++;
					}
					if(msg[j][i-1]==1&&msg[j-1][i-1]!=1) {
						z_f++;
					}
					for(int x=-1;x<2;x++) {
						for(int y=-1;y<2;y++) {
							if((int)msg[j+x][i+y]==0&&x!=0&&y!=0) {
								sum++;
							}
						}
					}
					if(sum>=2&&sum<=6&&z_f==1) {
						if((msg[j-1][i]==1||msg[j][i+1]==1||msg[j][i-1]==1)&&
								(msg[j+1][i]==1||msg[j-1][i]==1||msg[j][i-1]==1)) {
							msg[j][i]=1;
							flag=true;
						}
					}
				}
				
			}
		}
		
		
		
		if(!flag) {
			break;
		}	
	}
	
	return msg;
	}
	
}
