package com.cmp.pdf;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import com.cmp.pdf.AgreementPdfTemplate;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

@Component("agreementPdfView")
public class AgreementPdfView extends AbstractView {

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model,
                                           HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {
    	AgreementPdfTemplate template = (AgreementPdfTemplate) model.get("agreementPdf");
    	response.setContentType("application/pdf");
//    	response.setHeader("Content-Disposition", "attachment; filename=myReport.pdf");
    	response.setHeader("Content-Disposition", "inline; filename=myReport.pdf");
    	
//        HttpHeaders headers = new HttpHeaders();
//    	headers.setContentType(MediaType.parseMediaType("application/pdf"));
//        String filename = "pdf1.pdf";
//
//        headers.add("content-disposition", "inline;filename=" + filename);
//
//        headers.setContentDispositionFormData(filename, filename);
//        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
    	
    	
    	
    	
        

        //IText API
        PdfWriter pdfWriter = new PdfWriter(response.getOutputStream());
        PdfDocument pdf = new PdfDocument(pdfWriter);
        Document pdfDocument = new Document(pdf);

        //title
//        Paragraph title = new Paragraph(template.getName());
//        title.setFont(PdfFontFactory.createFont(FontConstants.HELVETICA));
//        title.setFontSize(18f);
//        title.setItalic();
//        pdfDocument.add(title);

        //date
//        Paragraph date = new Paragraph(template.getDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));
//        date.setFontSize(16f);
//        pdfDocument.add(date);

        //content
        Paragraph content = new Paragraph(template.getContent());
        pdfDocument.add(content);

        pdfDocument.close();
    }
}