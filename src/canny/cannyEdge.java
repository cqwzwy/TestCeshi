package canny;
import java.util.*;

import javax.imageio.ImageIO;

import java.io.*;
import static java.lang.System.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class cannyEdge {
	
	private static int qs=0;

	public static int getPixel(int alp,int weight) {
		int result=alp;
		result=result<<8;
		result+=weight;
		result=result<<8;
		result+=weight;
		result=result<<8;
		result+=weight;
		return result;
	}
	
	public static int getR(int weight) {
		int result=weight;
		result=(result>>16)&0xff;
		return result;
	}
	
	public static double[][] getGray(BufferedImage bi) {
		double[][] source=new double[bi.getWidth()][bi.getHeight()];
		for(int i=bi.getMinX();i<bi.getWidth();i++) {
			for(int j=bi.getMinY();j<bi.getHeight();j++) {
				int pixel=bi.getRGB(i, j);
				double r=(pixel>>16)&0xff;
				double g=(pixel>>8)&0xff;
				double b=(pixel&0xff);
				int weight=(int)(0.3*r+0.59*g+0.11*b);
				source[i][j]=weight;
				weight=getPixel(255,weight);
				
				bi.setRGB(i,j,weight);
				
			}
			
			
		}
		out.println("二值化样本！");
		int[][] p=new int[bi.getHeight()][bi.getWidth()];
		getBin(bi,p);
		/*try {
			ImageIO.write(bi, "jpg", new File("C:\\Users\\22682\\Desktop\\灰度.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return source;
		
	}
	
	public static double[][] getSobelX(BufferedImage bi,double[][] sources) {
		double[][] Imgx=new double[bi.getWidth()][bi.getHeight()];
		double[][] Imgy=new double[bi.getWidth()][bi.getHeight()];
		double[][] source=new double[bi.getWidth()+2][bi.getHeight()+2];
		for(int i=0;i<source.length;i++) {
			for(int j=0;j<source[i].length;j++) {
				if(i==0||j==0||i==(source.length-1)||j==(source[i].length-1)) {
					source[i][j]=0;
				}else{
					source[i][j]=sources[i-1][j-1];
				}
			}
		}
		out.println("初始化结束!");
		int[][] sobelx={{-1,-2,-1},{0,0,0},{1,2,1}};
		int[][] sobely={{-1,0,1},{-2,0,2},{-1,0,1}};
		for(int x=1;x<=bi.getWidth();x++) {
			for(int y=1;y<=bi.getHeight();y++) {
				int sum=0;
				for(int i=-1;i<2;i++) {
					for(int j=-1;j<2;j++) {
						sum+=(sobely[j+1][i+1]*source[x+i][y+j]);
					}
				}
				Imgy[x-1][y-1]=sum;
			}
		}
		out.println("对Y卷积结束!");
		for(int y=1;y<=bi.getHeight();y++) {
			for(int x=1;x<=bi.getWidth();x++) {
				int sum=0;
				for(int i=-1;i<2;i++) {
					for(int j=-1;j<2;j++) {
						sum+=(sobely[i+1][j+1]*source[x+i][y+j]);
					}
				}
				Imgx[x-1][y-1]=sum;
				
			}
		}
		out.println("对X卷积结束!");
		double[][] result=new double[bi.getWidth()][bi.getHeight()];
		for(int i=0;i<result.length;i++) {
			for(int j=0;j<result[i].length;j++) {
				result[i][j]=Math.sqrt((Imgx[i][j]*Imgx[i][j])+(Imgy[i][j]*Imgy[i][j]));
				if(result[i][j]>255) {
					result[i][j]=255;
				}
				//out.println(result[i][j]);
			}
		}
		out.println("边缘运算结束!");
		getEdge(bi,Imgx,Imgy,result);
		return result;
		
	}
	public static void getEdge(BufferedImage bi,double[][] x,double[][] y,double[][] result) {
		out.println("开始插值寻找梯度！");
		double[][] edge=new double[x.length][x[0].length];
		for(int i=1;i<x.length-1;i++) {
			
			for(int j=1;j<x[i].length-1;j++) {
				if(result[i][j]==0) {
					edge[i][j]=255;
				}else {
					double temp=result[i][j];
					int gx=(int) x[i][j];
					int gy=(int) y[i][j];
					double g1,g2,g3,g4;
					double weight;
					if(Math.abs(x[i][j])<Math.abs(y[i][j]))
					{
						weight=Math.abs(x[i][j])/Math.abs(y[i][j]);
						
						g2=result[i-1][j];
						g4=result[i+1][j];
						
						if(gx*gy>0) {
							
							g1=result[i-1][j-1];
							g3=result[i+1][j+1];
							
						}else {
							g1=result[i-1][j+1];
							g3=result[i+1][j-1];
						}
						
					}else {
						weight=Math.abs(y[i][j])/Math.abs(x[i][j]);
						
						g2=result[i][j+1];
						g4=result[i][j-1];
						
						if(gx*gy>0) {
							g1=result[i+1][j+1];
							g3=result[i-1][j-1];
							
							
						}else {
							g1=result[i-1][j+1];
							g3=result[i+1][j-1];
						}
					}
					
					double temp1=(weight*g1)+(1-weight)*g2;
					double temp2=(weight*g3)+(1-weight)*g4;
					if(temp>=temp1&&temp>=temp2&temp>240) {
						edge[i][j]=0;
					}else{
						edge[i][j]=255;
					}
				}
				
				
				
				
			}
			
			
		}
		out.println("边缘检测结束！");
		try {
			getImg(bi,edge);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void getImg(BufferedImage bi,int[][] msg) throws IOException {
		BufferedImage bis =new BufferedImage(bi.getWidth(),bi.getHeight(),bi.getType());
		
		for(int x=0;x<msg.length;x++) {
			for(int y=0;y<msg[x].length;y++) {
				if(msg[x][y]!=0) {
					msg[x][y]=255;
				}
				
				int pixel=getPixel(255,(int)msg[x][y]);
				//out.println((int)msg[x][y]);
				bis.setRGB(y, x, pixel);
			}
		}
		ImageIO.write(bis, "jpg", new File("C:\\Users\\22682\\Desktop\\结果"+(qs++)+".jpg"));
	}
	
	public static void main(String[]a) throws IOException {
		long current=currentTimeMillis();
		BufferedImage bi=ImageIO.read(new File("C:\\Users\\22682\\Desktop\\SIFT测试\\第三组_2.jpg"));
		out.println("灰度化结束！");
		double[][] source=cannyEdge.getGray(bi);
		
		//double[][] sources=getSobelX(bi,source);
		out.println("卷积结束!");
		//getImg(bi,sources);
		out.println("共用时："+(currentTimeMillis()-current)/1000);
	}
	
	public static void getBin(BufferedImage bi,int[][] p) {
		for(int y=0;y<bi.getHeight();y++) {
			for(int x=0;x<bi.getWidth();x++) {
				int pixel=bi.getRGB(x, y);
				int r=(pixel>>16)&0xff;
				int g=(pixel>>8)&0xff;
				int b=(pixel&0xff);
				
				if((r+b+g)<400) {
					p[y][x]=1;
				}else {
					p[y][x]=0;
				}
				
			}
		}
		/*try {
			getImg(bi,p);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		try {
			
			//for(int i=0;i<5;i++)
			p=Thin.getThin(p);	
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			getImg(bi,p);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
