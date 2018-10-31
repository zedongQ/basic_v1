package org.ieforex.enums;
/**
 * 数据状态【0未发布，1已发布，2作废】
 * 
 *
 */

public enum PubStatus {
	Unpublished("0","未发布"),
	Published("1","已发布");
	//Cancel("2","作废");
	private String value;
	private String describe;

	private PubStatus(String value, String describe) {
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
		PubStatus[] enums = PubStatus.values();
			for (PubStatus item : enums) {
				if (item.value().equals(value)) {
					return item.describe;
				}
			}
		return "";
	}

}
