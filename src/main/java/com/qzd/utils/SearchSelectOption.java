/**
 * 
 */
package org.ieforex.utils;

import java.io.Serializable;

/**
 * @author cuiyuguo
 * 搜索选择项节点数据模型
 */
public class SearchSelectOption implements Serializable{
	/**
     * 搜索选择项标题
     */
	String title;
	/**
     * 搜索选择项字段值
     */
	String value;
	public SearchSelectOption(){}
	public SearchSelectOption(String title){
		this.title = title;
	}
	public SearchSelectOption(String title, String value){
		this.title = title;
		this.value = value;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
