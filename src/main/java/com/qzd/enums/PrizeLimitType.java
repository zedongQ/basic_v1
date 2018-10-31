package org.ieforex.enums;

public enum PrizeLimitType {
	
	NormalUser("0","所有用户"),//所有用户
	ActiveUser("1","三个月内活跃用户"),//三个月内活跃用户
	UntradeUser("2","无交易用户"),//无交易用户
	ZeroUser("3","未绑定交易账号");//未绑定交易账号
	
	private String value;
	private String describe;
	
	private PrizeLimitType(String value, String describe){
		this.value = value;
		this.describe = describe;
	}
	
	public String value(){
		return value;
	}
	
	public String describe(){
		return describe;
	}
	
	public static PrizeLimitType getMapValue(String value){
		PrizeLimitType[] enums = PrizeLimitType.values();
		if(value !=null && !"".equals(value)){
			for(PrizeLimitType item : enums){
				if(item.value.equals(value)){
					return item;
				}
			}
		}
		return null;
	}

}
