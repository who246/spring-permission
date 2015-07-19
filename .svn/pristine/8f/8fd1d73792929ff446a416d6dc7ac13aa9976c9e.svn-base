
package com.demo.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

import org.springframework.beans.BeanUtils;
import org.springframework.util.ReflectionUtils;

import com.demo.web.back.sys.entity.Role;
/**
 * 
 * 
 *反射工具
 * 
 * 
 * 池超凡
 * 
 * 2015年5月25日 下午2:27:26
 * 
 * @version 1.0.0
 *
 */
 
public class ReflectUtils {

    /**
     * 得到指定类型的指定位置的泛型实参
     *
     * @param clazz
     * @param index
     * @param <T>
     * @return
     */
    public static <T> Class<T> findParameterizedType(Class<?> clazz, int index) {
        Type parameterizedType = clazz.getGenericSuperclass();
        //CGLUB subclass target object(泛型在父类上)
        if (!(parameterizedType instanceof ParameterizedType)) {
            parameterizedType = clazz.getSuperclass().getGenericSuperclass();
        }
        if (!(parameterizedType instanceof  ParameterizedType)) {
            return null;
        }
        Type[] actualTypeArguments = ((ParameterizedType) parameterizedType).getActualTypeArguments();
        if (actualTypeArguments == null || actualTypeArguments.length == 0) {
            return null;
        }
        return (Class<T>) actualTypeArguments[index];
    }
    /**
     * 将值不为空的设置进对象
     * setValueIfNotNull(这里用一句话描述这个方法的作用)
     * (这里描述这个方法适用条件 – 可选)
     * @param res
     * @param target
     * @param clazz
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws SecurityException 
     *T
     * @exception 
     * @since  1.0.0
     */
    public static <T> T setValueIfNotNull(Object res, Object target,Class<T> clazz) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException {
        Method[] ms = target.getClass().getDeclaredMethods();
        Class resClass = res.getClass();
        for (Method method : ms) {
            String methodName = method.getName();
            if(methodName.indexOf("get") !=-1){
                Object object = method.invoke(target);
                
                if(object != null){
                    if(object instanceof Collection ){
                        if(((Collection)object).isEmpty()) continue;
                    }
                    String filedStr = methodName.replace("get", "");
                   String filed = filedStr.substring(0, 1).toLowerCase()+filedStr.substring(1, filedStr.length());
                   try {
                    Field f=  resClass.getDeclaredField(filed);
                    f.setAccessible(true);
                    f.set(res, object);
                } catch (NoSuchFieldException e) {
                    
                   continue;
                }
                    
                }
            }
        }
        return (T)res;
    }
      
     
}
