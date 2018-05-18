package io.renren.modules.job.entity;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;


/**
 * 返回数据状态
 * @author DHB
 *@ date 2018/5/9
 */
public class ResultEntity<T> implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 原因
	 */
	private String cause;
	/**
	 * 返回对象
	 */
	private T obj;
	/**
	 * 返回状态码
	 */
	private Integer code;
	/**
	 * 返回消息
	 */
	private String msg;
	
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}

	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getObj() {
		return obj;
	}
	public void setObj(T obj) {
		this.obj = obj;
	}
	
/*    public static ResultObjListEntity fromJson(String json, Class clazz) {
        Gson gson = new Gson();
        Type objectType = type(ResultObjListEntity.class, clazz);
        return gson.fromJson(json, objectType);
    }

    public String toJson(Class<T> clazz) {
        Gson gson = new Gson();
        Type objectType = type(ResultObjListEntity.class, clazz);
        return gson.toJson(this, objectType);
    }

    static ParameterizedType type(final Class raw, final Type... args) {
        return new ParameterizedType() {
            public Type getRawType() {
                return raw;
            }

            public Type[] getActualTypeArguments() {
                return args;
            }

            public Type getOwnerType() {
                return null;
            }
        };
    }*/
	
}
