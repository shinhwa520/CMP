package com.cmp.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.multipart.MultipartFile;

import com.cmp.AppResponse;
import com.cmp.DatatableResponse;
import com.cmp.MenuItem;
import com.cmp.form.FileForm;
import com.cmp.model.FilesBaseConfig;
import com.cmp.service.FileService;
import com.cmp.service.vo.FileServiceVO;
import com.cmp.utils.GetObject2Aliyun;
import com.cmp.utils.PostObject2Aliyun;

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
			@RequestParam(name="isAdmin", required=true) boolean isAdmin,
			@RequestParam(name="start", required=false, defaultValue="0") Integer start,
			@RequestParam(name="length", required=false, defaultValue="10") Integer length) {
		
		List<FileServiceVO> datalist = fileService.findAllPublicFiles(isAdmin, start, length);
		
		long total = 0;
		if (datalist != null && !datalist.isEmpty()) {
			 total = datalist.size();
		}
		
		return new DatatableResponse(total, datalist, total);
	}
	
	@RequestMapping(value="/manage/file/getFileInfo", method = RequestMethod.POST, produces="application/json")
	public @ResponseBody AppResponse getFileBySeqNo(
			@RequestParam(name="fileType", required=true) String fileType,
			@RequestParam(name="seqNo", required=true) Integer seqNo) {
		try {
			FileServiceVO retVO = fileService.getFileByFileTypeAndSeqNoOrFileName(fileType, seqNo, null);
			AppResponse appResponse = new AppResponse(HttpServletResponse.SC_OK, "取得File資料成功");
			appResponse.putData("fileInfo",  retVO);
			return appResponse;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			return new AppResponse(super.getLineNumber(), e.getMessage());
		}
	}
	
	private FileServiceVO transParameterMap2VO(Map<String, String[]> paraMap, FileServiceVO fsVO) {
		try {
			for (Entry<String, String[]> et : paraMap.entrySet()) {
				/*
				 	fileDescription=[Ljava.lang.String;@13b5f736
					beginDateStr=[Ljava.lang.String;@46a5fc34
					onTopChkbox=[Ljava.lang.String;@22fe3cc
					seqNo=[Ljava.lang.String;@2d2dd714
					endTimeStr=[Ljava.lang.String;@111d89c0
					beginTimeStr=[Ljava.lang.String;@7830838b
					isAdd=[Ljava.lang.String;@57c2ec2
					fileType=[Ljava.lang.String;@462adc0e
					fullFileName=[Ljava.lang.St9ring;@3b040e82
					endDateStr=[Ljava.lang.String;@4754feb2
				 */
				BeanUtils.setProperty(fsVO, et.getKey(), 
						StringUtils.isNotBlank(((String[])et.getValue())[0]) ? new String(et.getValue()[0].getBytes("iso-8859-1"), "utf-8") : null);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fsVO;
	}
	
	@RequestMapping(value = "/manage/file/upload")
	@ResponseBody
	public AppResponse uploadFile(
			@RequestParam(value = "uploadFile") MultipartFile uploadFile,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			if (uploadFile != null && !uploadFile.isEmpty()) {
				//获取保存的路径，
				String realPath = request.getSession().getServletContext()
						.getRealPath("/upload/temp");
				// 文件原名称
				String originFileName = uploadFile.getOriginalFilename();
				// 文件大小
				BigDecimal fileSize = new BigDecimal(uploadFile.getSize()).divide(new BigDecimal(1024), 2, BigDecimal.ROUND_HALF_UP);
				
				FileServiceVO fsVO = new FileServiceVO();
				fsVO.setOriginFileName(originFileName);
				fsVO = transParameterMap2VO(request.getParameterMap(), fsVO);
				fsVO.setFileSize(fileSize.intValue());
				FileServiceVO retVO = fileService.addFile(fsVO);
				
				if (StringUtils.isNotBlank(retVO.getErrMsg())) {
					return new AppResponse(HttpServletResponse.SC_CONFLICT, retVO.getErrMsg());
					
				} else {
					FilesBaseConfig config = fileService.findFilesConfig(fsVO.getFileType());
					// 上傳檔案
					try {
						//这里使用Apache的FileUtils方法来进行保存
						FileUtils.copyInputStreamToFile(uploadFile.getInputStream(),
								new File(realPath, originFileName));
						
						new PostObject2Aliyun().postObject(config, realPath.concat(File.separator).concat(originFileName), originFileName);
						
					} catch (IOException e) {
						e.printStackTrace();
						
						// 檔案上傳失敗 >> 刪除前面已新增的資料
						fileService.deleteFile(fsVO.getFileType(), new Integer[]{retVO.getAddedSeqNo()}, false);
						
						throw new Exception("文件上传失败");
					}
				}
				 
			} else {
				if (uploadFile.isEmpty()) {
					// 未选择文件
				}
			}
			
			return new AppResponse(HttpServletResponse.SC_OK, "上傳成功");
			
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
			fileService.deleteFile(form.getFileType(), form.getDelChkbox(), true);
			model.addAttribute("message", "刪除成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			setActiveMenu(model, MenuItem.MANAGE_BILLBOARD);
		}
		
		return fileMain(model, form, request, response);
    }
	
	@RequestMapping(value="/manage/file/download", method = RequestMethod.GET)
//	@ResponseBody
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

	@Autowired
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}
}
