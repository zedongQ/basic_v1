package org.ieforex.enums;

public enum RebateStatusType {
	Estimate("0","等待入账");//预估返现
	//Transferred("1","已入账");//返现到账
	private String value;
	private String describe;
	
	private RebateStatusType(String value, String describe){
		this.value = value;
		this.describe = describe;
	}
	
	public String value(){
		return value;
	}
	
	public String describe(){
		return describe;
	}
	
	public static RebateStatusType getMapValue(String value){
		RebateStatusType[] enums = RebateStatusType.values();
		if(value !=null && !"".equals(value)){
			for(RebateStatusType item : enums){
				if(item.value.equals(value)){
					return item;
				}
			}
		}
		return null;
	}
}
