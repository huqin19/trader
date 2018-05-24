package io.renren.common.utils;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;

import io.renren.modules.job.entity.ResultEntity;
import io.renren.modules.job.entity.UsersEntity;
import io.renren.modules.job.entity.ResultEntity;
/**
 * 用来解析json的工具类
 * @author DHB
 * @date 2018年5月4日
 */
public class GsonUtils {
	/**
	 * 当为对象时
	 * @param str 传入json字符串
	 * @param clazz 实体类class
	 * @return 实体类对象 
	 */
	public static <T> ResultEntity<T> fromJsonObject(String str, Class<T> clazz){
		Gson gson = new Gson();
		Type type = new ParameterizedTypeImpl(ResultEntity.class, new Class[]{clazz});
		return gson.fromJson(str, type);	
	}
	/**
	 * 当为数组时
	 * @param str 传入json字符串
	 * @param clazz 实体类class
	 * @return 实体类对象 
	 */
	public static <T> ResultEntity<List<T>> fromJsonArray(String str, Class<T> clazz){
		Gson gson = new Gson();
	    // 生成List<T> 中的 List<T>
	    Type listType = new ParameterizedTypeImpl(List.class, new Class[]{clazz});
	    // 根据List<T>生成完整的Result<List<T>>
	    Type type = new ParameterizedTypeImpl(ResultEntity.class, new Type[]{listType});
	    return gson.fromJson(str, type);
	}
	
}
