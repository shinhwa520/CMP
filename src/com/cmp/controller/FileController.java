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

import com.cmp.AppResponse;
import com.cmp.DatatableResponse;
import com.cmp.MenuItem;
import com.cmp.form.BillboardForm;
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
		setActiveMenu(model, MenuItem.MANAGE_FILE);
		return "manage/file";
    }
	
	@RequestMapping(value="/manage/file/getAllPublicFiles.json", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody DatatableResponse getPublicFiles(
			@RequestParam(name="start", required=false, defaultValue="0") Integer start,
			@RequestParam(name="length", required=false, defaultValue="10") Integer length) {
		List<FileServiceVO> datalist = fileService.findAllPublicFiles(true, start, length);
		long total = null==datalist ? 0 : datalist.size();
		return new DatatableResponse(total, datalist, total);
	}
	
	@RequestMapping(value="/manage/file/getFileInfo", method = RequestMethod.POST, produces="application/json")
	public @ResponseBody AppResponse getFileBySeqNo(
			@RequestParam(name="fileType", required=true) String fileType,
			@RequestParam(name="seqNo", required=true) Integer seqNo) {
		try {
			FileServiceVO retVO = fileService.getFileByFileTypeAndSeqNo(fileType, seqNo);
			AppResponse appResponse = new AppResponse(HttpServletResponse.SC_OK, "取得File資料成功");
			appResponse.putData("fileInfo",  retVO);
			return appResponse;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			return new AppResponse(super.getLineNumber(), e.getMessage());
		}
	}
	
	@RequestMapping(value="/manage/file/modify", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public AppResponse modifyBillboard(
			@RequestParam(name="seqNo", required=false) Integer seqNo,
			@RequestParam(name="fileType", required=true) String fileType,
			@RequestParam(name="fullFileName", required=true) String fullFileName,
			@RequestParam(name="fileDescription", required=true) String fileDescription,
			@RequestParam(name="onTopChkbox", required=false) String onTopChkbox,
			@RequestParam(name="beginDateStr", required=false) String beginDateStr,
			@RequestParam(name="beginTimeStr", required=false) String beginTimeStr,
			@RequestParam(name="endDateStr", required=false) String endDateStr,
			@RequestParam(name="endTimeStr", required=true) String endTimeStr) {
		
		FileServiceVO fsVO;
		try {
			fsVO = new FileServiceVO();
			fsVO.setSeqNo(seqNo);
			fsVO.setFileType(fileType);
			fsVO.setFullFileName(fullFileName);
			fsVO.setFileDescription(fileDescription);
			fsVO.setOnTopChkbox(onTopChkbox);
			fsVO.setBeginDateStr(beginDateStr);
			fsVO.setBeginTimeStr(beginTimeStr);
			fsVO.setEndDateStr(endDateStr);
			fsVO.setEndTimeStr(endTimeStr);
			fileService.modifyFile(fsVO);
			
			return new AppResponse(HttpServletResponse.SC_OK, seqNo != null ? "修改成功" : "新增成功");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			return new AppResponse(super.getLineNumber(), e.getMessage());
		}
	}
	
	@RequestMapping(value = { "/manage/file/delete" }, method = RequestMethod.POST)
    public String deleteRecords(Model model, @ModelAttribute("FileForm") FileForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			fileService.deleteFile(form.getFileType(), form.getDelChkbox());
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			setActiveMenu(model, MenuItem.MANAGE_BILLBOARD);
		}
		
		return fileMain(model, form, request, response);
    }

	@Autowired
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}
}
