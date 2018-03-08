package com.cmp.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.cmp.model.User;
import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.utils.PdfMerger;
import com.itextpdf.layout.Document;

public class PDFMerge {
	
	public static final String COVER
	    = "C:\\Users\\User\\Desktop\\MAKA\\test_PDF\\a.pdf";
	public static final String DEST
	    = "C:\\Users\\User\\Desktop\\MAKA\\test_PDF\\DEST.pdf";
	public static final String RESOURCE
	    = "C:\\Users\\User\\Desktop\\MAKA\\test_PDF\\RESOURCE.pdf";

//	public static void main(String[] args) {
//		try {
//			File file = new File(DEST);
//	        file.getParentFile().mkdirs();
//	        new PDFMerge().manipulatePdf(DEST);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	public static void manipulatePdf(User user, String tempPath, String templatePath) throws IOException {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(tempPath + "/Malaysia_MM2H.pdf"));
        PdfMerger merger = new PdfMerger(pdfDoc);
        PdfDocument resource = new PdfDocument(new PdfReader(templatePath + "/Malaysia_MM2H.pdf"));
        PdfDocument cover = new PdfDocument(new PdfReader(tempPath + "/contact_info.pdf"));

        resource.copyPagesTo(1,resource.getNumberOfPages(), pdfDoc);
        cover.copyPagesTo(1,1, pdfDoc);

        cover.close();
        resource.close();
        pdfDoc.close();

//        merger.merge(cover, 1, 1);
//        merger.merge(resource, 1, resource.getNumberOfPages());
//        pdfDoc.close();
    }

}
