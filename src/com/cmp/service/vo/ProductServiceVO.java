package com.cmp.service.vo;

import java.util.List;

import com.cmp.model.FilesProduct;
import com.cmp.model.ProductInfo;

public class ProductServiceVO {

	private Integer productId;
	private Integer userId;
	private Integer fileSeqNo;
	private ProductInfo productInfo;
	private List<FilesProduct> filesProduct;
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getFileSeqNo() {
		return fileSeqNo;
	}
	public void setFileSeqNo(Integer fileSeqNo) {
		this.fileSeqNo = fileSeqNo;
	}
	public ProductInfo getProductInfo() {
		return productInfo;
	}
	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}
	public List<FilesProduct> getFilesProduct() {
		return filesProduct;
	}
	public void setFilesProduct(List<FilesProduct> filesProduct) {
		this.filesProduct = filesProduct;
	}
}
