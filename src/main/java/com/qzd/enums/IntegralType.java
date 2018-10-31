package org.ieforex.enums;

/**积分流水枚举*/
public enum IntegralType {

	LotteryOut("0","抽奖(消耗)"),
	ChangeGoods("1","兑换商品"),
	Sign("2","签到"),
	Invite("3","邀请奖励"),
	Rebate("4","返佣"),
	FreeLottery("5","抽奖劵抽奖"),
	LotteryGet("6","抽奖(增加)"),
	Bulu("7","积分补录");
	private String value;
	private String describe;
	
	private IntegralType(String value, String describe){
		this.value = value;
		this.describe = describe;
	}
	
	public String value(){
		return value;
	}
	
	public String describe(){
		return describe;
	}
	
	public static IntegralType getMapValue(String value){
		IntegralType[] enums = IntegralType.values();
		if(value !=null && !"".equals(value)){
			for(IntegralType item : enums){
				if(item.value.equals(value)){
					return item;
				}
			}
		}
		return null;
	}
}
