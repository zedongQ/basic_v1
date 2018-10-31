/**
 * 
 */
package org.ieforex.exception;


/**
 * @author cuiyuguo
 *
 */
@SuppressWarnings("serial")
public class AESCryptException extends Exception {
	public AESCryptException(Throwable throwable) {
		super("数据格式不正确！", throwable);
	}
}
