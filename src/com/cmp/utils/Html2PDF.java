package com.cmp.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import com.cmp.model.User;
import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.forms.fields.PdfTextFormField;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.annot.PdfAnnotation;
import com.itextpdf.kernel.pdf.annot.PdfStampAnnotation;
import com.itextpdf.layout.font.FontProvider;
import org.apache.commons.lang3.StringUtils;

public class Html2PDF {

	public static final String HTML
		= "C:\\Users\\User\\Desktop\\MAKA\\test_PDF\\input.html";
	
	public static final String DEST
	    = "C:\\Users\\User\\Desktop\\MAKA\\test_PDF\\a.pdf";
	
	public static final String[] FONTS = {
			"C:\\Users\\User\\Desktop\\MAKA\\test_PDF\\NotoSansCJKjp-Regular.otf",
			"C:\\Users\\User\\Desktop\\MAKA\\test_PDF\\NotoSansCJKjp-Bold.otf"
	};
	
	public static void main(String[] args) throws IOException, java.io.IOException {
		/*
		// IO
        File htmlSource = new File(HTML);
        File pdfDest = new File(DEST);
 
        // pdfHTML specific code
        ConverterProperties converterProperties = new ConverterProperties();
        
        FontProvider fp = new DefaultFontProvider(false, false, false); // 提供解析用的字體
        
        for (String font : FONTS) {
            FontProgram fontProgram = FontProgramFactory.createFont(font);
            fp.addFont(fontProgram);
        }
        converterProperties.setFontProvider(fp);
        
        HtmlConverter.convertToPdf(new FileInputStream(htmlSource), new FileOutputStream(pdfDest), converterProperties);
        */
    }
	
	public Html2PDF() {
	    System.out.println(this.getClass().getResource("name"));
	}
	
    public static byte[] convert(String html) throws IOException, java.io.IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ConverterProperties props = new ConverterProperties();
        FontProvider fp = new FontProvider(); // 提供解析用的字體
        fp.addStandardPdfFonts(); // 添加標準字體庫、無中文
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        fp.addDirectory(classLoader.getResource("fonts").getPath()); // 自定義字體路徑、解決中文,可先用絕對路徑測試。
        props.setFontProvider(fp);
//         props.setBaseUri(baseResource); // 設置html資源的相對路徑
        HtmlConverter.convertToPdf(html, outputStream, props); // 無法靈活設置頁邊距等
//        HtmlConverter.convertToPdf(html, outputStream);
        byte[] result = outputStream.toByteArray();
        outputStream.close();
        return result;
    }

    public static void convertPdfTemplate(User user, String tempPath, String realPath) throws IOException, java.io.IOException {
        PdfReader reader = new PdfReader(realPath + "/contact_info.pdf");
        PdfDocument doc = new PdfDocument(reader, new PdfWriter(tempPath + "/contact_info.pdf"));
        PdfAcroForm form = PdfAcroForm.getAcroForm(doc, true);
        Map<String, PdfFormField> fields = form.getFormFields();
        fields.get("name").setValue(user.getName() != null ? user.getName() : "");
        fields.get("wechatID").setValue(user.getWeChat() != null ? user.getWeChat() : "");
        fields.get("phoneNo").setValue(user.getPhone() != null ? user.getPhone() : "");
        fields.get("email").setValue(user.getEmail() != null ? user.getEmail() : "");
        form.flattenFields();
        doc.close();

        /*使用中文字体 */
//        BaseFont bf = BaseFont.createFont(PDFTicket.class.getResource("/") +"org/csun/ns/util/simsun.ttc,1",
//                BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
//        ArrayList<BaseFont> fontList = newArrayList<BaseFont>();
//        fontList.add(bf);
    }

}
