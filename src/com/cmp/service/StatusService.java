package com.cmp.service;

import java.util.List;

import com.cmp.model.Status;

public interface StatusService {
	
	/**
	 * 客戶狀態: 1-已登记
	 */
	public static final int CUST_1_REGISTERED = 8;
	/**
	 * 客戶狀態: 2-已收团费
	 */
	public static final int CUST_2_RECEIVED_TOUR_FEE = 9;
	/**
	 * 客戶狀態: 3-已订机票
	 */
	public static final int CUST_3_BOOKED_TICKETS = 10;
	/**
	 * 客戶狀態: 4-已办签证
	 */
	public static final int CUST_4_VISAS_ALREADY_MADE = 11;
	/**
	 * 客戶狀態: 5-已订酒店
	 */
	public static final int CUST_5_HOTEL_RESERVATION = 12;
	/**
	 * 客戶狀態: 6-已参观
	 */
	public static final int CUST_6_VISITED = 13;
	/**
	 * 客戶狀態: 7-签约
	 */
	public static final int CUST_7_CONTRACT = 14;
	/**
	 * 客戶狀態: 8-已付订金
	 */
	public static final int CUST_8_PAID_DEPOSIT = 15;
	/**
	 * 客戶狀態: 9-支付首付
	 */
	public static final int CUST_9_PAY_DOWN_PAYMENT = 16;
	/**
	 * 客戶狀態: 10-贷款申请
	 */
	public static final int CUST_10_LOAN_APPLICATION = 17;
	/**
	 * 客戶狀態: 11-完成付款
	 */
	public static final int CUST_11_PAYMENT_COMPLETED = 18;
	/**
	 * 客戶狀態: 12-已结算佣
	 */
	public static final int CUST_12_BILLED_COMMISSION = 19;
	/**
	 * 開團/沙龍狀態: 23-招團中
	 */
	public static final int VISIT_23_JOIN = 23;
	
	List<Status> listStatus(Integer start, Integer length);
	long countStatus();
	List<Status> findStatus(String type);
}