package org.ieforex.enums;

public enum TradeType {
	CashBack("0","返佣"),//返现
	Payment("1","支付"),//支付
	Supplement("2","资金补录"),//补录账户余额
	Bind("3","绑定"),//绑定
	Referral("4","推荐费"),//邀请奖励
	RegisterIncentive("5","注册奖励");//注册奖励
	private String value;
	private String describe;
	
	private TradeType(String value, String describe){
		this.value = value;
		this.describe = describe;
	}
	
	public String value(){
		return value;
	}
	
	public String describe(){
		return describe;
	}
	
	public static TradeType getMapValue(String value){
		TradeType[] enums = TradeType.values();
		if(value !=null && !"".equals(value)){
			for(TradeType item : enums){
				if(item.value.equals(value)){
					return item;
				}
			}
		}
		return null;
	}
}
