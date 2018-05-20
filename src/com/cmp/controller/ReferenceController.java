package com.cmp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmp.DatatableResponse;
import com.cmp.MenuItem;
import com.cmp.service.FileService;
import com.cmp.service.vo.FileServiceVO;

@Controller
@RequestMapping("/reference")
public class ReferenceController extends BaseController {
	private static Log log = LogFactory.getLog(ReferenceController.class);
	
	@Autowired
	private FileService fileService;

	@RequestMapping(value = { "list" }, method = RequestMethod.GET)
    public String fileMain(Model model, HttpServletRequest request, HttpServletResponse response) {
		setActiveMenu(model, MenuItem.PRODUCT_INFO);

		return "reference/list";
    }
	
	@RequestMapping(value="getReferenceFiles.json", method = RequestMethod.POST, produces="application/json")
	public @ResponseBody DatatableResponse getPublicFiles(
			@RequestParam(name="referenceId", required=false) Integer referenceId,
			@RequestParam(name="isAdmin", required=false) boolean isAdmin,
			@RequestParam(name="start", required=false, defaultValue="0") Integer start,
			@RequestParam(name="length", required=false, defaultValue="10") Integer length) {
		
		long count = 0;
		List<FileServiceVO> datalist = new ArrayList<FileServiceVO>();
		
		try {
			count = fileService.countReferenceFilesByReferenceId(isAdmin, referenceId);
			
			if (count > 0) {
				datalist = fileService.findReferenceFilesByReferenceId(isAdmin, referenceId, start, length);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		
		return new DatatableResponse(count, datalist, count);
	}
}
