package org.ieforex.enums;

/**
 * 站内信消息是否读取
 */
public enum ReadOrUnRead {

	UnRead("0","UnRead"),
	Read("1","Read");
	
	private String value;
	private String describe;
	
	
	private ReadOrUnRead(String value, String describe){
		this.value = value;
		this.describe = describe;
	}
	
	public String value(){
		return value;
	}
	
	public String describe(){
		return describe;
	}
	
	public static ReadOrUnRead getMapValue(String value){
		ReadOrUnRead[] enums = ReadOrUnRead.values();
		if(value !=null && !"".equals(value)){
			for(ReadOrUnRead item : enums){
				if(item.value.equals(value)){
					return item;
				}
			}
		}
		return null;
	}
}
