package com.i000phone.tools.network;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

/**
 * Created by Administrator on 2016/4/1.
 */
public class NetworkLib {
    public static<T> T getService(Class<T> cl){
        T o = (T) Proxy.newProxyInstance(cl.getClassLoader(), new Class[]{cl},
                new NetworkInvocationHandler());
        return o;
    }
    private static class NetworkInvocationHandler implements InvocationHandler{
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            ParameterizedType returnType = (ParameterizedType) method.getGenericReturnType();
            UrlString string = method.getAnnotation(UrlString.class);
            String url = String.format(string.value(), args);
            Type[] types = returnType.getActualTypeArguments();
            NetworkTask<Object> task = new NetworkTask<>(url,types[0]);
            return task;
        }
    }
}
