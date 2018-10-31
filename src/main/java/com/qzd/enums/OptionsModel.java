package org.ieforex.enums;
/**
 * 
 * 图片所属模块【0首页顶部、1首页交易商优惠文字形式、2首页交易商优惠图文形式、3交易商顶部、4交易商横幅】
 * 
 */
public enum OptionsModel {
	IndexTop("0","首页顶部"),
	IndexDealerLiteral("1","首页交易商优惠文字形式"),
	IndexDealerPic("2","首页交易商优惠图文形式"),
	IndexDealerScrollPic("3","首页交易商优惠滚动形式"),
	Indexlitchi("4","首页励志课堂"),
	DealerTop("5","交易商顶部"),
	DealerBanner("6","交易商横幅"),
	IndianaAdv("7","抽奖活动赞助商(可能会超过3个)"),
	ExchangeAct("8","外汇资讯优惠活动(大于1个)"),
	ExchangeDetailAct("9","外汇资讯详情广告(大于1个)"),
	CustomerHomeActThree("10","个人中心底部广告（3个）"),
	CustomerHomeActTwo("11","个人中心底部广告（2个）");
//	ForeignTop("3","外汇服务顶部"),
//	PromotionTop("4","优惠活动顶部"),
//	PromoIndexRight("5","优惠首页右侧"),
//	PromoDetailRight("6","优惠明细右侧"),
//	ForeignDetailRight("7","外汇明细右侧");
	
	private String value;
	private String describe;

	private OptionsModel(String value, String describe) {
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
		OptionsModel[] enums = OptionsModel.values();
		for (OptionsModel item : enums) {
			if (item.value().equals(value)) {
				return item.describe;
			}
		}
		return "";
	}
}
