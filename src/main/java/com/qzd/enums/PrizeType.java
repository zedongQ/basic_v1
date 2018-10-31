package org.ieforex.enums;

public enum PrizeType {
	
	NormalUser("0","实物"),
	ActiveUser("1","虚拟"),
	UntradeUser("2","积分"),
	OnceAgin("3","再来一次"),
	Thanks("4","谢谢参与");
	
	private String value;
	private String describe;
	
	private PrizeType(String value, String describe){
		this.value = value;
		this.describe = describe;
	}
	
	public String value(){
		return value;
	}
	
	public String describe(){
		return describe;
	}
	
	public static PrizeType getMapValue(String value){
		PrizeType[] enums = PrizeType.values();
		if(value !=null && !"".equals(value)){
			for(PrizeType item : enums){
				if(item.value.equals(value)){
					return item;
				}
			}
		}
		return null;
	}
}
