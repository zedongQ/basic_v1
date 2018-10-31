package org.ieforex.enums;

/**
 * 提现方式
 */
public enum WithdrawalWay {

	BankCard("0","银行卡"),
	DollarTT("1","电汇"),
	Alipay("2","支付宝"),
	MBAccount("3","Skrill账号"),
	PaypalAccount("4","贝宝");
	
	
	private String value;
	private String describe;
	
	private WithdrawalWay(String value, String describe){
		this.value = value;
		this.describe = describe;
	}
	
	public String value(){
		return value;
	}
	
	public String describe(){
		return describe;
	}
	
	public static WithdrawalWay getMapValue(String value){
		WithdrawalWay[] enums = WithdrawalWay.values();
		if(value !=null && !"".equals(value)){
			for(WithdrawalWay item : enums){
				if(item.value.equals(value)){
					return item;
				}
			}
		}
		return null;
	}
}
