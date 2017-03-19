package com.johj.common.utils.datatype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectUtils {

	/**
	 * 对象转byte数组
	 * 
	 * @param object
	 * @return
	 * @throws OssException 
	 */
	public static byte[] toByteArray(Object object) throws Exception {
		if(object == null) {
			return null;
		}
		byte[] bytes = null;
		ByteArrayOutputStream bos = null;
		ObjectOutputStream oos = null;
		try {
			bos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(bos);
			oos.writeObject(object);
			oos.flush();
			bytes = bos.toByteArray();
		}  finally {
			try {
				if (oos != null) {
					oos.close();
				}
				if (bos != null) {
					bos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			}
		}
		return bytes;
	}

	/**
	 * byte数组转对象
	 * 
	 * @param bytes
	 * @return
	 * @throws OssException 
	 */
	public static Object toObject(byte[] bytes) throws Exception {
		if(bytes == null) {
			return null;
		}
		Object obj = null;
		ByteArrayInputStream bis = null;
		ObjectInputStream ois = null;
		try {
			bis = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bis);
			obj = ois.readObject();
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
				if (bis != null) {
					bis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			}
		}
		return obj;
	}
	
}
