package org.ieforex.enums;

/*
 * 返佣周期类型【0返佣、1支付、2补录、3绑定、4、推荐】
 */
public enum IncomeType {
	CashBack("0","返佣"),//返现
	Supplement("2","资金补录"),//补录账户余额
	Bind("3","绑定"),//绑定
	Referral("4","推荐费"),//邀请奖励
	RegisterIncentive("5","注册奖励");//注册奖励
	private String value;
	private String describe;

	private IncomeType(String value, String describe) {
		this.value = value;
		this.describe = describe;
	}

	public String value() {
		return value;
	}

	public String describe() {
		return describe;
	}

	public static IncomeType getDisplayName(String value) {
		IncomeType[] enums = IncomeType.values();
		if (value != null && !"".equals(value)) {
			for (IncomeType item : enums) {
				if (item.value.equals(value)) {
					return item;
				}
			}
		}
		return null;
	}
}
