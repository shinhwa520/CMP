package com.cmp.service.impl;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.CustomerDAO;
import com.cmp.dao.SalonDAO;
import com.cmp.dao.StatusDAO;
import com.cmp.dao.UserDAO;
import com.cmp.dao.VisitDAO;
import com.cmp.model.Customer;
import com.cmp.model.Status;
import com.cmp.model.User;
import com.cmp.security.SecurityUtil;
import com.cmp.service.CustService;
import com.cmp.service.vo.CustServiceVO;

@Service("custService")
@Transactional
public class CustServiceImpl implements CustService {
	@Autowired
	private CustomerDAO customerDAO;
	@Autowired
	private UserDAO userDao;
	@Autowired
	private StatusDAO statusDAO;
	@Autowired
	private VisitDAO visitDAO;
	@Autowired
	private SalonDAO salonDAO;
	
	private String[] monthWord = new String[] {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	
	@Override
	public Customer findCustById(int id){
		return customerDAO.findCustById(id);
	}
	
	@Override
	public List<Customer> findCustByUserId(String userId, Integer start,Integer length){
//		SecurityUser securityUser = SecurityUtil.getSecurityUser();
		return customerDAO.findCustByUserId(userId, start, length);
	}
	
	@Override
	public long countCustByUserId(String userId){
		return customerDAO.countCustByUserId(userId);
	}
	
	@Override
	public List<Customer> findCust4MA(String roleName, Integer start,Integer length){
//		SecurityUser securityUser = SecurityUtil.getSecurityUser();
		return customerDAO.findCust4MA(roleName, start, length);
	}
	
	@Override
	public long countCust4MA(String roleName){
		return customerDAO.countCust4MA(roleName);
	}
	
	@Override
	public List<Customer> findCust4Search(String keyword, Integer start,Integer length){
//		SecurityUser securityUser = SecurityUtil.getSecurityUser();
		return customerDAO.findCust4Search(keyword, start, length);
	}
	
	@Override
	public long countCust4Search(String keyword){
		return customerDAO.countCust4Search(keyword);
	}
	
	@Override
	public void createCust(String name, String gender, Date birthday, String phone, String email, String weChat
			, Integer identity1_id, String identity1_code, String identity1_name, Integer identity2_id, String identity2_code, String identity2_name
			, String city, String census, String address, String remark){
		User user = userDao.findUserById(SecurityUtil.getSecurityUser().getUser().getId());
		Customer cust = new Customer();
		cust.setName(name);
		cust.setGender(gender);
		cust.setBirthday(birthday);
		cust.setPhone(phone);
		cust.setEmail(email);
		cust.setWeChat(weChat);
		
		cust.setIdentity1_id(null==identity1_id ? 0 : identity1_id);
		cust.setIdentity1_code(identity1_code);
		cust.setIdentity1_name(identity1_name);
		cust.setIdentity2_id(null==identity2_id ? 0 : identity2_id);
		cust.setIdentity2_code(identity2_code);
		cust.setIdentity2_name(identity2_name);
		
		cust.setCity(city);
		cust.setCensus(census);
		cust.setAddress(address);
		cust.setRemark(remark);
		cust.setUser(user);
		
		cust.setStatus(statusDAO.findStatus("CUST", 1));
		cust.setCreateTime(new Timestamp(System.currentTimeMillis()));
		cust.setCreateBy(user.getAccount());
		cust.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		cust.setUpdateBy(user.getAccount());

		cust.setDataStatus(statusDAO.findStatus("DATA", 1));
		
		customerDAO.saveCust(cust);
	}
	
	
	
	@Override
	public void updateCust(int id, String name, String gender, Date birthday, String phone, String email, String weChat
			, Integer identity1_id, String identity1_code, String identity1_name, Integer identity2_id, String identity2_code, String identity2_name
			, String city, String census, String address, String remark, String status){
		User user = userDao.findUserById(SecurityUtil.getSecurityUser().getUser().getId());
		Customer cust = customerDAO.findCustById(id);
		cust.setName(name);
		cust.setGender(gender);
		cust.setBirthday(birthday);
		cust.setPhone(phone);
		cust.setEmail(email);
		cust.setWeChat(weChat);
		
		cust.setIdentity1_id(null==identity1_id ? 0 : identity1_id);
		cust.setIdentity1_code(identity1_code);
		cust.setIdentity1_name(identity1_name);
		cust.setIdentity2_id(null==identity2_id ? 0 : identity2_id);
		cust.setIdentity2_code(identity2_code);
		cust.setIdentity2_name(identity2_name);
		
		cust.setCity(city);
		cust.setCensus(census);
		cust.setAddress(address);
		cust.setRemark(remark);
		cust.setUser(user);
		if(null!=status){
			cust.setStatus(statusDAO.findStatus("CUST", Integer.valueOf(status)));
		}
//		cust.setCreateTime(new Timestamp(System.currentTimeMillis()));
//		cust.setCreateBy(user.getName());
		cust.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		cust.setUpdateBy(user.getAccount());
		customerDAO.saveCust(cust);
	}

	@Override
	public void deleteCust(CustServiceVO custServiceVO) {
		Customer customer = customerDAO.findCustById(custServiceVO.getCust_Id());
		Status status = statusDAO.findStatus("DATA", 2);
		customer.setDataStatus(status);

		User user = userDao.findUserById(SecurityUtil.getSecurityUser().getUser().getId());
		customer.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		customer.setUpdateBy(user.getAccount());

		customerDAO.saveCust(customer);
	}

	@Override
	public List<CustServiceVO> findCustAndVisitByUserId(Integer visitId, String userId, Integer start, Integer length) {
		List<Customer> custList = null;
		List<CustServiceVO> reList = new ArrayList<CustServiceVO>();
		
		try {
			custList = customerDAO.findCustByUserId(userId, start, length);
			
			CustServiceVO custVO = null;
			for (Customer cust : custList) {
				custVO = new CustServiceVO();
				BeanUtils.copyProperties(cust, custVO);
				
				long count = visitDAO.checkCustHasJoinTheVisitOrNot(visitId, cust.getId());
				if (count > 0) {
					custVO.setJoined(true);
				}
				
				reList.add(custVO);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reList;
	}

	@Override
	public List<CustServiceVO> findCustAndSalonByUserId(Integer visitId, String userId, Integer start, Integer length) {
		List<Customer> custList = null;
		List<CustServiceVO> reList = new ArrayList<CustServiceVO>();
		
		try {
			custList = customerDAO.findCustByUserId(userId, start, length);
			
			CustServiceVO custVO = null;
			for (Customer cust : custList) {
				custVO = new CustServiceVO();
				BeanUtils.copyProperties(cust, custVO);
				
				long count = salonDAO.checkCustHasJoinTheSalonOrNot(visitId, cust.getId());
				if (count > 0) {
					custVO.setJoined(true);
				}
				
				reList.add(custVO);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reList;
	}

	@Override
	public CustServiceVO findUserRecentlySixMonthCustCountPerMonth(String userId, String currentYearMonth) {
		CustServiceVO retVO = new CustServiceVO();
		
		try {
			int year = Integer.parseInt(currentYearMonth.substring(0, 4));
			int month = Integer.parseInt(currentYearMonth.substring(4, currentYearMonth.length())) - 5;
			if (month <= 0) {
				year = year - 1;
				month = 12 + month;
			}
			String sixMonthBefore = String.valueOf(year).concat(StringUtils.leftPad(String.valueOf(month), 2, "0"));
			
			year = Integer.parseInt(currentYearMonth.substring(0, 4));
			month = Integer.parseInt(currentYearMonth.substring(4, currentYearMonth.length())) + 1;
			if (month > 12) {
				year = year + 1;
				month = month - 12;
			}
			String oneMonthAfter = String.valueOf(year).concat(StringUtils.leftPad(String.valueOf(month), 2, "0"));
			
			
			List<Object[]> perMonthList = customerDAO.retrieveCustCountPerMonth(userId, sixMonthBefore, oneMonthAfter);

			String perMonthWord = "";
			String perMonthCustCount = "";
			Integer maxCount = 0;
			
			for (int i=5; i>=0; i--) {
				year = Integer.parseInt(currentYearMonth.substring(0, 4));
				month = Integer.parseInt(currentYearMonth.substring(4, currentYearMonth.length())) - i;
				
				if (month <= 0) {
					year = year - 1;
					month = 12 + month;
				}
				
				String yearMonth = String.valueOf(year).concat(StringUtils.leftPad(String.valueOf(month), 2, "0"));
				
				for (Object[] obj : perMonthList) {
					String yyyyMM = ObjectUtils.toString(obj[0]);
					int count = 0;
					
					if (yearMonth.equals(yyyyMM)) {
						count = ((BigInteger)obj[1]).intValue();
					}
					
					perMonthWord = perMonthWord.concat("'").concat(monthWord[month-1]).concat("'");
					perMonthCustCount = perMonthCustCount.concat(String.valueOf(count));
					
					maxCount = count > maxCount ? count : maxCount;
				}
				
				if (i != 0) {
					perMonthWord = perMonthWord.concat(",");
					perMonthCustCount= perMonthCustCount.concat(",");
				}
			}
			
			retVO.setCustMonthWord(perMonthWord);
			retVO.setCustCountPerMonth(perMonthCustCount);
			retVO.setMaxCustCount(String.valueOf(maxCount+5));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retVO;
	}
}
