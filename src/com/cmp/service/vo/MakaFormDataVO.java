package com.cmp.service.vo;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class MakaFormDataVO {

	@SerializedName("code")
	private int code;
	
	@SerializedName("data")
	private Data data;
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public class Data {
		
		@SerializedName("pageNumber")
		private int pageNumber;
		
		@SerializedName("perPage")
		private int perPage;
		
		@SerializedName("totalNum")
		private String totalNum;
		
		@SerializedName("todayNum")
		private int todayNum;
		
		@SerializedName("dataList")
		private List<DataList> dataList;
		
		public int getPageNumber() {
			return pageNumber;
		}
		public void setPageNumber(int pageNumber) {
			this.pageNumber = pageNumber;
		}
		public int getPerPage() {
			return perPage;
		}
		public void setPerPage(int perPage) {
			this.perPage = perPage;
		}
		public String getTotalNum() {
			return totalNum;
		}
		public void setTotalNum(String totalNum) {
			this.totalNum = totalNum;
		}
		public int getTodayNum() {
			return todayNum;
		}
		public void setTodayNum(int todayNum) {
			this.todayNum = todayNum;
		}
		public List<DataList> getDataList() {
			return dataList;
		}
		public void setDataList(List<DataList> dataList) {
			this.dataList = dataList;
		}
	}
	
	public class DataList {
		@SerializedName("red")
		private String red;
		
		@SerializedName("push")
		private String push;
		
		@SerializedName("create_time")
		private String createTime;
		
		@SerializedName("content")
		private String content;
		
		public String getRed() {
			return red;
		}
		public void setRed(String red) {
			this.red = red;
		}
		public String getPush() {
			return push;
		}
		public void setPush(String push) {
			this.push = push;
		}
		public String getCreateTime() {
			return createTime;
		}
		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
	}
}
