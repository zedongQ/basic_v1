package com.qzd.auth;

import java.util.ArrayList;
import java.util.List;

import org.bcics.sso.SSOAuthorization;
import org.bcics.sso.Token;

/**
 * @author cuiyuguo
 *
 */
public final class SysAuthorization implements SSOAuthorization{

	private static List<String> permissionList = new ArrayList<String>();
	static {
		// 正常情况，该部分数据从数据库中加载。
		permissionList.add("usr:logined");
	}


	/**
	 * 开启配置 sso.permission.uri=true 支持、先验证 url 地址，后验证注解。
	 */
	@Override
	public boolean isPermitted( Token token, String permission ) {
		// 循环判断权限编码是否合法，token 获取登录用户ID信息、判断相应权限也可作为缓存主键使用。
		for ( String perm : permissionList ) {
			if ( perm.equals(permission) ) {
				return true;
			}
		}
		return false;
	}

}
