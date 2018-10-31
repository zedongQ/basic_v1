package org.ieforex.enums;

/**使用积分的流水枚举*/
public enum OutIntegralType {

	Lottery("0","积分抽奖"),
	ChangeGoods("1","积分兑换");
	
	private String value;
	private String describe;
	
	private OutIntegralType(String value, String describe){
		this.value = value;
		this.describe = describe;
	}
	
	public String value(){
		return value;
	}
	
	public String describe(){
		return describe;
	}
	
	public static OutIntegralType getMapValue(String value){
		OutIntegralType[] enums = OutIntegralType.values();
		if(value !=null && !"".equals(value)){
			for(OutIntegralType item : enums){
				if(item.value.equals(value)){
					return item;
				}
			}
		}
		return null;
	}
}
