package org.ieforex.enums;

public enum CategoryType {
	
	TradeNews("0","行业新闻"),
	TechnicalAnalysis("1","技术分析"),
	DealerNews("2","交易商新闻");

	private String value;
	private String describe;

	private CategoryType(String value, String describe) {
		this.value = value;
		this.describe = describe;
	}

	public String value() {
		return value;
	}

	public String describe() {
		return describe;
	}

	public static String getDisplayName(String value) {
		CategoryType[] enums = CategoryType.values();
		if (value != null && !"".equals(value)) {
			for (CategoryType item : enums) {
				if ((item.value).equals(value)) {
					return item.describe;
				}
			}
		}
		return null;
	}
}
