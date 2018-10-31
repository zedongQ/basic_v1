package com.qzd.enums;

/*
 * 银行卡图片名称（银行名与拼音一致）
 */
public enum BanksHelp {
	中国银行("中国银行","zhongyin"),
	光大银行("中国光大银行","guangda"),
	广发银行("中国广发银行","guangfa"),
	华夏银行("中国华夏银行","huaxia"),
	建设银行("中国建设银行","jianshe"),
	交通银行("中国交通银行","jiaotong"),
	民生银行("中国民生银行","minsheng"),
/*	农业银行("中国农业银行","nongye"),*/
	平安银行("中国平安银行","pingan"),
	浦发银行("上海浦发银行浦东发展银行","pufa"),
	兴业银行("中国兴业银行","xingye"),
	邮政储蓄银行("中国邮政储蓄银行","youzheng"),
	招商银行("中国招商银行","zhaoshang"),
	中信银行("中国中信银行","zhongxin"),
	工商银行("中国工商银行","gongshang");
	private String value;
	private String describe;

	private BanksHelp(String value, String describe) {
		this.value = value;
		this.describe = describe;
	}

	public String value() {
		return value;
	}

	public String describe() {
		return describe;
	}

	public static BanksHelp getBanksPng(String value) {
		BanksHelp[] enums = BanksHelp.values();
		if (value != null && !"".equals(value)) {
			for (BanksHelp item : enums) {
				if (item.value.indexOf(value)>=0) {
					return item;
				}
			}
		}
		return null;
	}
}
