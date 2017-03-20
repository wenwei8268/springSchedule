
package com.johj.common.utils;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class JsonUtils {

	public static String DATE_FORMET = "yyyy-MM-dd HH:mm:ss";
	public static GsonBuilder GSON_BUILDER = new GsonBuilder().setDateFormat(DATE_FORMET);

	/**
	 * 把对象转成json字符串，对特殊字符做转义
	 * 
	 * @param obj
	 * @return
	 */
	public static String toJsonString(Object obj) {
		String jsonStr = null;

		if (obj == null) {
			throw new RuntimeException("对象转字符串失败：对象为空。");
		} else {
			//Gson gson = GSON_BUILDER.create();
			//将取值为null的字段也输出到json字符串中
			Gson gson = GSON_BUILDER.serializeNulls().create();
			jsonStr = gson.toJson(obj);
		}

		return jsonStr != null ? jsonStr : "";
	}

	public static String toJsonStringNoException(Object obj) {
		try {
			String jsonStr = null;

			if (obj == null) {
				return null;
			}

			Gson gson = GSON_BUILDER.create();
			jsonStr = gson.toJson(obj);

			return jsonStr != null ? jsonStr : "";

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 把对象转成json字符串，不对特殊字符做转义
	 * 
	 * @param obj
	 * @return
	 */
	public static String toJsonStringDHE(Object obj) {
		String jsonStr = null;

		if (obj == null) {
			throw new RuntimeException("对象转字符串失败：对象为空。");
		} else {
			Gson gson = GSON_BUILDER.disableHtmlEscaping() // 不做特殊字符转义
					.create();
			jsonStr = gson.toJson(obj);
		}

		return jsonStr != null ? jsonStr : "";
	}

	/**
	 * 把json字符串转成对象返回
	 * 
	 * @param jsonStr
	 * @return
	 * @return
	 */
	public static <T> T toObject(String jsonStr, Type type) {
		Gson gson = GSON_BUILDER.create();
		return gson.fromJson(jsonStr, type);
	}

	/**
	 * 把json字符串转成对象返回
	 * 
	 * @param jsonStr
	 * @param classOfT
	 * @return
	 */
	public static <T> T toObject(String jsonStr, Class<T> classOfT) {
		Gson gson = GSON_BUILDER.create();
		return gson.fromJson(jsonStr, classOfT);
	}

	/**
	 * 把相关数据组装成前台JQGrid所能识别的分页格式
	 * 
	 * @param total
	 *            总页数
	 * @param page
	 *            当前页
	 * @param records
	 *            总记录数
	 * @param datas
	 *            数据集
	 * @return
	 */
	public static String toJQGridPagingString(int total, int page, long records, List<?> datas) {
		Gson gson = new GsonBuilder().setDateFormat(DATE_FORMET).create();

		StringBuilder jsonStr = new StringBuilder();
		jsonStr.append("{\"total\": ");
		jsonStr.append(total);
		jsonStr.append(", \"page\": ");
		jsonStr.append(page);
		jsonStr.append(", \"records\": ");
		jsonStr.append(records);
		jsonStr.append(", \"datas\": ");
		jsonStr.append(gson.toJson(datas));
		jsonStr.append("}");

		return jsonStr.toString();
	}

	public static String toDataTablePAgingString(String sEcho, long pageSize, int index, long total, List<?> records) {
		Gson gson = new GsonBuilder().setDateFormat(DATE_FORMET).serializeNulls() // 值为空时name:
																					// ""，默认是没有这个name
				.create();

		StringBuilder jsonStr = new StringBuilder();
		jsonStr.append("{\"sEcho\": ");
		jsonStr.append(sEcho);
		jsonStr.append(", \"iTotalRecords\": ");
		jsonStr.append(total);
		jsonStr.append(", \"iTotalDisplayRecords\": ");
		jsonStr.append(total);
		jsonStr.append(", \"aaData\": ");
		jsonStr.append(gson.toJson(records));
		jsonStr.append("}");

		return jsonStr.toString();
	}

	public static void main(String[] args) {
		String str = "{\"openid\":\"OPENID\",\"nickname\":\"NICKNAME\",\"sex\":\"1\",\"province\":\"PROVINCE\",\"city\":\"CITY\",\"country\":\"COUNTRY\",\"headimgurl\":\"http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/46\",\"privilege\":[\"PRIVILEGE1\",\"PRIVILEGE2\"],\"unionid\":\"o6_bmasdasdsad6_2sgVt7hMZOPfL\"}";
		System.out.println(str);
		Map<String, String> infoMap = JsonUtils.toObject(str, new TypeToken<Map<String, String>>() {
		}.getType());
		System.out.println(infoMap.get("openid"));
	}

}
