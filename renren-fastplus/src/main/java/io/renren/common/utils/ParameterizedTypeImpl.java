package io.renren.common.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
/**
 * 用来解析json防止泛型不能识别
 * @author DHB
 * @date 2018年5月4日
 */
public class ParameterizedTypeImpl implements ParameterizedType {
    private final Class raw;
    private final Type[] args;
    public ParameterizedTypeImpl(Class raw, Type[] args) {
        this.raw = raw;
        this.args = args != null ? args : new Type[0];
    }
    @Override
    public Type[] getActualTypeArguments() {
        return args;
    }
    @Override
    public Type getRawType() {
        return raw;
    }
    @Override
    public Type getOwnerType() {return null;}
}
