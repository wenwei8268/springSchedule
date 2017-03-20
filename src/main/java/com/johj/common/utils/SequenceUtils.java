package com.johj.common.utils;

import java.util.UUID;

/**
 * 序列
 * 
 *
 */
public class SequenceUtils {

	/**
	 * 获取序列
	 * 
	 * @param seqName
	 * @return
	 * @throws Exception
	 */
	public static String getSequence(String seqName) throws Exception {
		UUID uuid = UUID.randomUUID();
		String seqValue = uuid.toString(); 
		return seqValue;
	}
	
	public static void main(String args[]) throws Exception{
		System.out.print(SequenceUtils.getSequence("wenwei"));
	}
}
