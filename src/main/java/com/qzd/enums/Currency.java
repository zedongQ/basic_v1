package org.ieforex.enums;

public enum Currency {
	/**
	 *币种
	 */
		
		Dollar("0", "美元"),
		Rmb("1", "人民币"); 
	
		
		private String value;
		private String describe;

		private Currency(String value, String describe) {
			this.value = value;
			this.describe = describe;
		}

		public String value() {
			return value;
		}

		public String describe() {
			return describe;
		}
		public static Currency getMapValue(String value){
			Currency[] enums = Currency.values();
			if(value !=null && !"".equals(value)){
				for(Currency item : enums){
					if(item.value().equals(value)){
						return item;
					}
				}
			}
			return null;
		}
}
