package org.ieforex.service.impl;

import java.util.Date;

import org.ieforex.dao.ECustomerMapper;
import org.ieforex.dao.ExternalRegisterMapper;
import org.ieforex.entity.ECustomer;
import org.ieforex.entity.ExternalRegister;
import org.ieforex.service.ExternalRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 外部注冊接口
 */
@Transactional
@Service("externalRegisterService")
public class ExternalRegisterServiceImpl implements ExternalRegisterService {
	
	@Autowired
	private ExternalRegisterMapper mapper;
	@Autowired
	private ECustomerMapper customerMapper;

	/**
	 * 新增外部注冊用戶
	 * @param externalRegister 外部用戶
	 * @return 外部用戶編號
	 */
	public void insert(ExternalRegister externalRegister) {
		mapper.insert(externalRegister);
	}
	
	/**
	 * 根據電話號碼查詢外部注冊用戶
	 * @param phone 手機號碼
	 * @return 外部注冊用戶
	 */
	public ExternalRegister getUserByPhone(String phone) {
		return mapper.getUserByPhone(phone);
	}
	
	/**
	 * 更新外部注冊用戶
	 * @param externalRegister 外部注冊用戶
	 */
	public void update(ExternalRegister externalRegister) {
		mapper.update(externalRegister);
	}
}
