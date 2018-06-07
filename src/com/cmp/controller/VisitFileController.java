package com.cmp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cmp.i18n.DatabaseMessageSourceBase;
import com.cmp.model.FilesBaseConfig;
import com.cmp.model.FilesVisit;
import com.cmp.model.User;
import com.cmp.security.SecurityUtil;
import com.cmp.service.FileService;
import com.cmp.service.UserService;
import com.cmp.service.vo.FileServiceVO;
import com.cmp.service.vo.VisitServiceVO;
import com.cmp.utils.GetObject2Aliyun;
import com.cmp.utils.Html2PDF;
import com.cmp.utils.PDFMerge;

@Controller
@RequestMapping("/manage/visitFile")
public class VisitFileController extends BaseController {
	private static Log log = LogFactory.getLog(VisitFileController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private DatabaseMessageSourceBase databaseMessageSourceBase;
	@Autowired
	private FileService fileService;
	
	@RequestMapping(value="downloadVisitFiles", method = RequestMethod.GET)
	public String downloadVisitFiles(
			@RequestParam(name="visitId", required=true) Integer visitId,
			@RequestParam(name="fileType", required=true) String fileType,
			@RequestParam(name="fileCategory", required=true) String fileCategory,
			@RequestParam(name="useZip", required=true) boolean useZip,
			@RequestParam(name="fromPage", required=true) String fromPage,
			HttpServletRequest request, HttpServletResponse response) {
		
		boolean isOutputed = false;
		try {
			FilesBaseConfig config = fileService.findFilesConfig(fileType);
			
			if (config == null) {
				throw new Exception("[ERROR]未設定FilesBaseConfig >> configName: " + fileType);
			}
			
			//查找產品檔案
			VisitServiceVO vsVO = new VisitServiceVO();
			vsVO.setVisitId(visitId);
			vsVO.setFileCategory(fileCategory);
			VisitServiceVO retVisit = fileService.findVisitInfoByDAOVO(vsVO);
			
			Map<String, String> fileMap = null;	//檔案清單
			List<String> waterMarks = null;	//浮水印資料
			if (retVisit != null 
					&& (retVisit.getFilesVisit() != null && !retVisit.getFilesVisit().isEmpty())) {
				
				fileMap = new HashMap<String, String>();
				
				for (FilesVisit fp : retVisit.getFilesVisit()) {
					FileServiceVO retVO = fileService.modifyDownloadCount(fileType, fp.getSeqNo());
					
					String downloadFileName = "";
					//處理中文檔名問題
					String userAgent = request.getHeader("User-Agent");
					if((userAgent.contains("MSIE")) || (userAgent.contains("Trident")) || (userAgent.contains("Edge"))) {
						downloadFileName = java.net.URLEncoder.encode(fp.getOriginFileName(),"UTF-8");
						//IE6.11正常、FF的中文部分會出現%XX%XX的代碼
					}else{
						downloadFileName = new String(fp.getOriginFileName().getBytes("UTF-8"),"ISO-8859-1");
						//Firefox / google Chrome正常，IE6檔名整個亂碼 (連副檔名都看不見)
					}
					fileMap.put(fp.getUpperFileName(), downloadFileName);
				}
				
				if (fileMap != null && !fileMap.isEmpty()) {
					//查找渠道商個人資料
					String userId = SecurityUtil.getSecurityUser().getUser().getId();
					User user = userService.findUserById(userId);
					
					if (user != null) {
						waterMarks = new ArrayList<String>();
						
						if (StringUtils.isNotBlank(user.getName())) {	//Name
							waterMarks.add(databaseMessageSourceBase.getText("contactPerson", Locale.CHINA).concat(": ").concat(user.getName()));
						}
						if (StringUtils.isNotBlank(user.getPhone())) {	//Phone
							waterMarks.add(databaseMessageSourceBase.getText("phoneNo", Locale.CHINA).concat(": ").concat(user.getPhone()));
						}
						if (StringUtils.isNotBlank(user.getWeChat())) {	//WeChat
							waterMarks.add(databaseMessageSourceBase.getText("wechatID", Locale.CHINA).concat(": ").concat(user.getWeChat()));
						}
						if (StringUtils.isNotBlank(user.getEmail())) {	//E-mail
							waterMarks.add(databaseMessageSourceBase.getText("email", Locale.CHINA).concat(": ").concat(user.getEmail()));
						}
					}
					
					//获取保存的路径
					String downloadTmpPath = request.getSession().getServletContext().getRealPath("/download/temp/"+userId+"/"+System.currentTimeMillis());
					
					isOutputed = new GetObject2Aliyun().getObjectWithWaterMark(
							config, fileMap, waterMarks, downloadTmpPath, fileCategory, useZip, response);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		
		return isOutputed ? null : "redirect:/"+fromPage;
	}

	@RequestMapping(value="downloadVisitPdf", method = RequestMethod.GET)
	public String downloadPdfFile(
			@RequestParam(name="visitId", required=true) Integer visitId,
			@RequestParam(name="fileType", required=true) String fileType,
			@RequestParam(name="fileCategory", required=true) String fileCategory,
			@RequestParam(name="fromPage", required=true) String fromPage,
			Model model,
			HttpServletRequest request, HttpServletResponse response) {

		boolean isOutputed = true;
		try {
			String userId = SecurityUtil.getSecurityUser().getUser().getId();
			User user = userService.findUserById(userId);

//			FilesBaseConfig config = fileService.findFilesConfig(fileType);
//
//			if (config == null) {
//				throw new Exception("[ERROR]未設定FilesBaseConfig >> configName: " + fileType);
//			}
//
//			FileServiceVO retVO = fileService.modifyDownloadCount(fileType, seqNo);
//			Map<String, String> downloadKeyMap = new HashMap<String, String>();
			try {
//				if (retVO != null) {
//					String downloadFileName = "";
//					//處理中文檔名問題
//					String userAgent = request.getHeader("User-Agent");
//					if((userAgent.contains("MSIE")) || (userAgent.contains("Trident")) || (userAgent.contains("Edge"))) {
//						downloadFileName = java.net.URLEncoder.encode(retVO.getOriginFileName(),"UTF-8");
//						//IE6.11正常、FF的中文部分會出現%XX%XX的代碼
//					}else{
//						downloadFileName = new String(retVO.getOriginFileName().getBytes("UTF-8"),"ISO-8859-1");
//						//Firefox / google Chrome正常，IE6檔名整個亂碼 (連副檔名都看不見)
//					}
//					downloadKeyMap.put(retVO.getUpperFileName(), downloadFileName);
//
//					isOutputed = new GetObject2Aliyun().getObject(
//							config, downloadKeyMap, response);
//				}

				String tempPath = request.getSession().getServletContext().getRealPath("/upload/temp/" + userId);
				File file = new File(tempPath);
//				if (!file.exists() && !file.isDirectory()) {
				    file.mkdirs();
//                }

                String templatePath = request.getSession().getServletContext().getRealPath("/template");

				Html2PDF.convertPdfTemplate(user, tempPath, templatePath);
				PDFMerge.manipulatePdf(user, tempPath, templatePath);

				response.setContentType("multipart/form-data");
				response.setHeader("Content-Disposition","attachment; filename=\"" + "Malaysia_MM2H.pdf" + "\"");

                InputStream in = new FileInputStream(tempPath + "/Malaysia_MM2H.pdf");
				OutputStream output = response.getOutputStream();

				try {

					byte[] b = new byte[2048];
					int len;

					while((len = in.read(b)) > 0){
						output.write(b, 0, len);
					}

				} catch (Exception e) {
					throw e;
				} finally {
					in.close();
					output.flush();
					output.close();   //關閉串流
				}

			} catch (Exception e) {
				e.printStackTrace();
				isOutputed = false;
				model.addAttribute("message", "檔案下載失敗");
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		return isOutputed ? null : "redirect:/"+fromPage;
	}

	@RequestMapping(value="downloadSchedule", method = RequestMethod.GET)
	public String downloadSchedule(
			@RequestParam(name="visitId", required=true) Integer visitId,
			@RequestParam(name="fileType", required=true) String fileType,
			@RequestParam(name="fileCategory", required=true) String fileCategory,
			@RequestParam(name="fromPage", required=true) String fromPage,
			HttpServletRequest request, HttpServletResponse response) {
		
		boolean isOutputed = false;
		try {
			FilesBaseConfig config = fileService.findFilesConfig(fileType);
			
			if (config == null) {
				throw new Exception("[ERROR]未設定FilesBaseConfig >> configName: " + fileType);
			}
			
			//查找產品檔案
			VisitServiceVO vsVO = new VisitServiceVO();
			vsVO.setVisitId(visitId);
			vsVO.setFileCategory(fileCategory);
			VisitServiceVO retVisit = fileService.findVisitInfoByDAOVO(vsVO);
			
			Map<String, String> fileMap = null;	//檔案清單
			if (retVisit != null 
					&& (retVisit.getFilesVisit() != null && !retVisit.getFilesVisit().isEmpty())) {
				
				fileMap = new HashMap<String, String>();
				
				for (FilesVisit fp : retVisit.getFilesVisit()) {
					FileServiceVO retVO = fileService.modifyDownloadCount(fileType, fp.getSeqNo());
					
					String downloadFileName = "";
					//處理中文檔名問題
					String userAgent = request.getHeader("User-Agent");
					if((userAgent.contains("MSIE")) || (userAgent.contains("Trident")) || (userAgent.contains("Edge"))) {
						downloadFileName = java.net.URLEncoder.encode(fp.getOriginFileName(),"UTF-8");
						//IE6.11正常、FF的中文部分會出現%XX%XX的代碼
					}else{
						downloadFileName = new String(fp.getOriginFileName().getBytes("UTF-8"),"ISO-8859-1");
						//Firefox / google Chrome正常，IE6檔名整個亂碼 (連副檔名都看不見)
					}
					fileMap.put(fp.getUpperFileName(), downloadFileName);
				}
				
				if (fileMap != null && !fileMap.isEmpty()) {
					isOutputed = new GetObject2Aliyun().getObject(config, fileMap, response);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		
		return isOutputed ? null : "redirect:/"+fromPage;
	}

}
