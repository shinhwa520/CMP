package com.cmp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmp.DatatableResponse;
import com.cmp.MenuItem;
import com.cmp.form.FileForm;
import com.cmp.model.FilesBaseConfig;
import com.cmp.service.FileService;
import com.cmp.service.vo.FileServiceVO;
import com.cmp.utils.GetObject2Aliyun;

@Controller
@RequestMapping("/")
public class ShareFileController extends BaseController {
	private static Log log = LogFactory.getLog(ShareFileController.class);
	
	@Autowired
	FileService fileService;
	
	@RequestMapping(value = { "/share/file" }, method = RequestMethod.GET)
    public String fileMain(Model model, @ModelAttribute("FileForm") FileForm form, HttpServletRequest request, HttpServletResponse response) {
		setActiveMenu(model, MenuItem.SHARE_FILE);
		return "share/file";
    }
	
	@RequestMapping(value="/share/file/getAllPublicFiles.json", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody DatatableResponse getPublicFiles(
			@RequestParam(name="start", required=false, defaultValue="0") Integer start,
			@RequestParam(name="length", required=false, defaultValue="10") Integer length) {
		
		List<FileServiceVO> datalist = fileService.findAllPublicFiles(false, start, length);
		
		long total = 0;
		if (datalist != null && !datalist.isEmpty()) {
			 total = datalist.size();
		}
		
		return new DatatableResponse(total, datalist, total);
	}
	
	@RequestMapping(value="/share/file/download", method = RequestMethod.GET)
	public String downloadBillboard(
			@RequestParam(name="seqNo", required=true) Integer seqNo,
			@RequestParam(name="fileType", required=true) String fileType,
			Model model, @ModelAttribute("FileForm") FileForm form, 
			HttpServletRequest request, HttpServletResponse response) {
		
		try {
			FilesBaseConfig config = fileService.findFilesConfig(fileType);
			
			if (config == null) {
				throw new Exception("[ERROR]未設定FilesBaseConfig >> configName: " + fileType);
			}
			
			String key = fileService.modifyDownloadCount(fileType, seqNo);
			
			new GetObject2Aliyun().getObject(config, key, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		
		return null;
	}
}
