package org.ieforex.enums;

/*
 * 返佣周期类型【0日返、1周返、2双周返、3月返】
 */
public enum RebatePeriodType {
	Day("0", "日返"), 
	Week("1", "周返"), 
	DbWeek("2", "双周返"), 
	Month("3", "月返");

	private String value;
	private String describe;

	private RebatePeriodType(String value, String describe) {
		this.value = value;
		this.describe = describe;
	}

	public String value() {
		return value;
	}

	public String describe() {
		return describe;
	}

	public static RebatePeriodType getDisplayName(String value) {
		RebatePeriodType[] enums = RebatePeriodType.values();
		if (value != null && !"".equals(value)) {
			for (RebatePeriodType item : enums) {
				if (item.value.equals(value)) {
					return item;
				}
			}
		}
		return null;
	}
}
