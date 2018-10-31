package org.ieforex.enums;

public enum StatusType {
	Estimate("0","等待入账"),//预估返现
	Transferred("1","已入账"),//返现到账
	Pending("2","处理中"),//提现处理中	
	Payment("3","已支付"),//提现已支付
	Failure("4","失败");//提现失败
	private String value;
	private String describe;
	
	private StatusType(String value, String describe){
		this.value = value;
		this.describe = describe;
	}
	
	public String value(){
		return value;
	}
	
	public String describe(){
		return describe;
	}
	
	public static StatusType getMapValue(String value){
		StatusType[] enums = StatusType.values();
		if(value !=null && !"".equals(value)){
			for(StatusType item : enums){
				if(item.value.equals(value)){
					return item;
				}
			}
		}
		return null;
	}
}
