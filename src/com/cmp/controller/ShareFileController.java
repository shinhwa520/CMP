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
import com.cmp.service.FileService;
import com.cmp.service.vo.FileServiceVO;

@Controller
@RequestMapping("/share/file")
public class ShareFileController extends BaseController {
	private static Log log = LogFactory.getLog(ShareFileController.class);
	
	@Autowired
	FileService fileService;
	
	@RequestMapping(value = { "" }, method = RequestMethod.GET)
    public String fileMain(Model model, @ModelAttribute("FileForm") FileForm form, HttpServletRequest request, HttpServletResponse response) {
		setActiveMenu(model, MenuItem.SHARE_FILE);
		return "share/file";
    }
	
	@RequestMapping(value="getAllPublicFiles.json", method = RequestMethod.GET, produces="application/json")
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
}
