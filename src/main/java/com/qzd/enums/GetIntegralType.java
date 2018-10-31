package org.ieforex.enums;

/**获取积分的流水枚举*/
public enum GetIntegralType {
	LotteryGet("6","抽奖"),
	Sign("2","签到"),
	Invite("3","邀请奖励"),
	Rebate("4","返佣"),
	Bulu("7","积分补录");
	private String value;
	private String describe;
	
	private GetIntegralType(String value, String describe){
		this.value = value;
		this.describe = describe;
	}
	
	public String value(){
		return value;
	}
	
	public String describe(){
		return describe;
	}
	
	public static GetIntegralType getMapValue(String value){
		GetIntegralType[] enums = GetIntegralType.values();
		if(value !=null && !"".equals(value)){
			for(GetIntegralType item : enums){
				if(item.value.equals(value)){
					return item;
				}
			}
		}
		return null;
	}
}
