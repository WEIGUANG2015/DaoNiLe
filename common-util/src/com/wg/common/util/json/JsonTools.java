package com.wg.common.util.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

/**
 * 解析JSON工具类
 * 
 * @author ke.wei.quan
 * @date 2015年3月31日
 * @lastModifyDate
 * @version 1.0
 * 
 */
public class JsonTools {
	private static Gson gson;

	private JsonTools() {
	}

	public static Gson getGson() {
		if (gson == null) {
			gson = new Gson();
		}
		return gson;
	}

	/**
	 * 解析json
	 * 
	 * @param jsonData
	 * @param cls
	 * @return
	 */
	public static <T> T jsonObj(JSONObject jsonData, Class<T> cls) {
		T t = getGson().fromJson(jsonData.toString(), cls);
		return t;
	}

	/**
	 * 解析json
	 * 
	 * @param <T>
	 * @param jsonData
	 * @param cls
	 * @return
	 */
	public static <T> T jsonObj(String jsonData, Class<T> cls) {
		if (TextUtils.isEmpty(jsonData)) {
			return null;
		}
		T t = null;
		try {
			t = getGson().fromJson(jsonData, cls);
		} catch (JsonSyntaxException e) {
			return null;
		} catch (JsonParseException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
		return t;
	}

	/**
	 * 解析jsonArray
	 * 
	 * @param <T>
	 * @param jsonArray
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> jsonObjArray(String jsonArray, Class<T> clazz) {
		if (TextUtils.isEmpty(jsonArray)) {
			return null;
		}
		List<T> lists = new ArrayList<T>();
		JsonParser parser = new JsonParser();
		JsonArray array = parser.parse(jsonArray).getAsJsonArray();
		for (JsonElement obj : array) {
			T t = getGson().fromJson(obj, clazz);
			lists.add(t);
		}
		return lists;
	}

	/**
	 * 解析jsonArray
	 * 
	 * @param <T>
	 * @param jsonArray
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> jsonObjArray(JSONArray jsonArray, Class<T> clazz) {
		List<T> lists = jsonObjArray(jsonArray.toString(), clazz);
		return lists;

	}

	/**
	 * 保存json的List对象
	 * 
	 * @param lists
	 */
	public static <T> String listToJson(List<T> lists) {
		String jsonString = getGson().toJson(lists);
		return jsonString;
	}

}
