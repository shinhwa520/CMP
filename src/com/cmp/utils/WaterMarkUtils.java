package com.cmp.utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.imageio.ImageIO;

public class WaterMarkUtils {  
	
	private final static String FONT_NAME = "新細明體";
	private final static int FONT_STYLE = Font.PLAIN;
	private final static int FONT_SIZE = 18;
	private final static int PADDING_WIDTH = 3;
	private final static Color FONT_COLOR = Color.WHITE;
	private final static Color BACKGROUD_COLOR = Color.BLACK;
	private final static float ALPHA_VALUE = 0.5f;
	
    /** 
     * 图片添加水印 
     * @param srcImgPath 需要添加水印的图片的路径 
     * @param outImgPath 添加水印后图片输出路径 
     * @param markContentColor 水印文字的颜色 
     * @param waterMarkContent 水印的文字 
     * @throws IOException 
     */  
    public static void mark(InputStream in, List<String> contents, String outputPath, String outputFileName) throws IOException {
        try {  
        	
            // 读取原图片信息  
//            File srcImgFile = new File(srcImgPath);  
            BufferedImage srcImg = ImageIO.read(in);  
            int srcImgWidth = srcImg.getWidth(null);  
            int srcImgHeight = srcImg.getHeight(null);  
            // 加水印  
            BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);  
            Graphics2D g = bufImg.createGraphics();  
            g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);  
            //Font font = new Font("Courier New", Font.PLAIN, 12);  
            Font font = new Font(FONT_NAME, FONT_STYLE, FONT_SIZE);
            g.setFont(font);
            
            if (contents != null && !contents.isEmpty()) {
            	int maxContentLength = getMaxWatermarkLength(contents, g);
            	
            	int idx = 0;
            	int x = srcImgWidth - maxContentLength - PADDING_WIDTH;
            	int y = srcImgHeight - (font.getSize() * contents.size() + PADDING_WIDTH);

            	g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, ALPHA_VALUE)); // 透明度設置開始,1.0是不透明,0是全透
            	g.setColor(BACKGROUD_COLOR);
            	g.fillRect(
	            			(x - PADDING_WIDTH), 
	            			(y - font.getSize() - PADDING_WIDTH), 
	            			(maxContentLength + PADDING_WIDTH), 
	            			(font.getSize() * contents.size() + font.getSize() + PADDING_WIDTH)
            			);
            	g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER)); // 透明度設置結束 
            	
                g.setColor(FONT_COLOR); //根据图片的背景设置水印颜色  
            	for (String content : contents) {
            		//x = srcImgWidth - getWatermarkLength(content, g) - 3;  
                    y += idx == 0 ? 0 : font.getSize() + PADDING_WIDTH;
                    g.drawString(content, x, y);  
                    idx += 1;
            	}
            }
            
            g.dispose();  
            
            OutputStream out = null;
            try {
            	File outputFile = new File(outputPath);
            	if (!outputFile.exists()) {
            		outputFile.mkdirs();
            	}
            	
            	outputPath = outputPath.concat(File.separator).concat(outputFileName);
            	
            	out = new FileOutputStream(new File(outputPath));
                ImageIO.write(bufImg, "jpg", out);  
                
            } catch (Exception e) {
            	e.printStackTrace();
            	
            } finally {
            	if (out != null) {
            		out.flush();  
                    out.close(); 
            	}
            }
            
        } catch (Exception e) {  
            e.printStackTrace();  
            
        }
    }  
      
    /** 
     * 获取水印文字总长度 
     * @param waterMarkContent 水印的文字 
     * @param g 
     * @return 水印文字总长度 
     */  
    private static int getWatermarkLength(String waterMarkContent, Graphics2D g) {  
        return g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0, waterMarkContent.length());  
    }  
    
    private static int getMaxWatermarkLength(List<String> contents, Graphics2D g) {
    	int maxLength = 0;
    	for (String content : contents) {
    		int tmp = getWatermarkLength(content, g);
    		maxLength = tmp > maxLength ? tmp : maxLength;
    	}
    	
    	return maxLength;
    }
    /*
    public static void main(String[] args) { 
    	List<String> contents = Arrays.asList(
    			"王曉明", "0911-222-333", "WeChat:shinhwa520", "E-mail:shinhwa520@gmail.com");
        // 原图位置, 输出图片位置, 水印文字颜色, 水印文字  
        new WaterMarkUtils().mark("C:/Users/User/Desktop/MAKA/套件/S__17530888.jpg", "C:/Users/User/Desktop/MAKA/套件/S__17530888_2.jpg", contents);
        new WaterMarkUtils().mark("C:/Users/User/Desktop/MAKA/套件/S__17530889.jpg", "C:/Users/User/Desktop/MAKA/套件/S__17530889_2.jpg", contents);
   }  
   */
}  