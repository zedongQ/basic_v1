package org.ieforex.enums;

public enum WithDrawlStatusType {
	Pending("2","处理中"),//提现处理中	
	Payment("3","已支付"),//提现已支付
	Failure("4","失败");//提现失败
	private String value;
	private String describe;
	
	private WithDrawlStatusType(String value, String describe){
		this.value = value;
		this.describe = describe;
	}
	
	public String value(){
		return value;
	}
	
	public String describe(){
		return describe;
	}
	
	public static WithDrawlStatusType getMapValue(String value){
		WithDrawlStatusType[] enums = WithDrawlStatusType.values();
		if(value !=null && !"".equals(value)){
			for(WithDrawlStatusType item : enums){
				if(item.value.equals(value)){
					return item;
				}
			}
		}
		return null;
	}
}
