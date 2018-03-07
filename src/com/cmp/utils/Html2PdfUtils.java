package com.cmp.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.layout.font.FontProvider;

public class Html2PdfUtils {

	public static final String HTML = "";
	public static final String DEST = "";
	public static final String[] FONTS = {
			"C:\\Users\\User\\Desktop\\MAKA\\test_PDF\\NotoSansCJKjp-Regular.otf",
			"C:\\Users\\User\\Desktop\\MAKA\\test_PDF\\NotoSansCJKjp-Bold.otf"
	};
	
	public static void convertToPdf(String template, List<String> content, String destPath) {
		// IO
        File htmlSource = new File(HTML);
        File pdfDest = new File(DEST);
 
        // pdfHTML specific code
        ConverterProperties converterProperties = new ConverterProperties();
        
        FontProvider fp = new DefaultFontProvider(false, false, false); // 提供解析用的字體
        
        for (String font : FONTS) {
            FontProgram fontProgram;
			try {
				fontProgram = FontProgramFactory.createFont(font);
				fp.addFont(fontProgram);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        converterProperties.setFontProvider(fp);
        
        try {
			HtmlConverter.convertToPdf(new FileInputStream(htmlSource), new FileOutputStream(pdfDest), converterProperties);
		
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
