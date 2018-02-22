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

import com.cmp.MenuItem;
import com.cmp.form.FileForm;
import com.cmp.service.FileService;
import com.cmp.service.vo.FileServiceVO;

@Controller
@RequestMapping("/")
public class FileController extends BaseController {
	private static Log log = LogFactory.getLog(BillboardController.class);
	
	FileService fileService;
	
	@RequestMapping(value = { "/manage/file" }, method = RequestMethod.GET)
    public String fileMain(Model model, @ModelAttribute("FileForm") FileForm form, HttpServletRequest request, HttpServletResponse response) {
		List<FileServiceVO> fileList;
		try {
			fileList = fileService.findAllPublicFiles(true, 0, 500);
			form.setFileList(fileList);
			form.setAdminRole(true);
			model.addAttribute("message", "coming soon...");
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			setActiveMenu(model, MenuItem.MANAGE_FILE);
		}
		
		return "manage/file";
    }

	@Autowired
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}
}
