package canny;
import java.util.*;

import javax.imageio.ImageIO;

import java.io.*;
import static java.lang.System.*;
import java.awt.*;
import java.awt.image.BufferedImage;
public class Two_Value {
	
	public static void Two(BufferedImage bi) throws IOException {
		int[][] p=new int[bi.getHeight()][bi.getWidth()];
		int width=bi.getWidth();
		int height=bi.getHeight();
		for(int i=0;i<height;i++) {
			for(int j=0;j<width;j++) {
				int pixel=bi.getRGB(j, i);
				if(isBlack(pixel)) {
					bi.setRGB(j, i, Color.BLACK.getRGB());
					p[i][j]=0;
				}else {
					bi.setRGB(j, i, Color.WHITE.getRGB());
					p[i][j]=1;
				}
				
			}
		}
		try {
			p=Thin.getThin(p);
			cannyEdge.getImg(bi, p);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			ImageIO.write(bi, "jpg", new File("C:\\Users\\22682\\Desktop\\t_v.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static boolean isBlack(int pixel) {
		Color color=new Color(pixel);
		if((color.getRed()+color.getBlue()+color.getGreen())<=400) {
			return true;
		}else {
			return false;
		}
		
		
		
	}
	
	public static void main(String[]a) {
		
		try {
			Two_Value.Two(ImageIO.read(new File("C:\\Users\\22682\\Desktop\\SIFT测试\\5013b489c37203181b5ef0a7a14b442.jpg")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
