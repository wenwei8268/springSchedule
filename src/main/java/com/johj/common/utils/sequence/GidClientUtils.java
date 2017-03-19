package com.johj.common.utils.sequence;
public class GidClientUtils {

	private static GidClientUtils inst = new GidClientUtils();
	
	private GidClientUtils(){
		
	}
	
	public static GidClientUtils getInstance(){
		return inst;
	}
	
	public Long getGidValue(String gidCode){
		return GidClientManager.getInstance().getGidValue(gidCode);
	}
	
	public static void main(String[] args){
		
		for(int i=0;i<10000;i++){
			long t0 = System.currentTimeMillis();
			Long gidValue = inst.getGidValue("TEST_SEQ");
			long t1 = System.currentTimeMillis();
			System.out.println("gidValue:"+gidValue+" cost:"+(t1-t0)+"ms");
			
		}
		
	}
}
