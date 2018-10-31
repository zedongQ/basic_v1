package org.ieforex.dao;

import org.ieforex.entity.ExternalRegister;
import org.springframework.stereotype.Repository;

/**
 * 外部注冊接口
 */
@Repository
public interface ExternalRegisterMapper {
	/**
	 * 新增外部注冊用戶
	 * @param externalRegister 外部用戶
	 * @return 外部用戶編號
	 */
	int insert(ExternalRegister externalRegister);
	/**
	 * 根據電話號碼查詢外部注冊用戶
	 * @param phone 手機號碼
	 * @return 外部注冊用戶
	 */
	ExternalRegister getUserByPhone(String phone);
	/**
	 * 更新外部注冊用戶
	 * @param externalRegister 外部注冊用戶
	 */
	void update(ExternalRegister externalRegister);
	/**
	 * 刪除外部注冊用戶
	 * @param phone 手機號碼
	 */
	void deleteByPhone(String phone);
}
