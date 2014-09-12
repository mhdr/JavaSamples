package com.nasimeshomal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Mahmood on 9/12/2014.
 */
public class MethodInvoker {
    private Class aClass;
    private Method method;

    public MethodInvoker(Class aClass,Method method)
    {
        this.setaClass(aClass);
        this.setMethod(method);
    }

    public MethodInvoker(Class aClass,String methodName) throws NoSuchMethodException {
        this.setaClass(aClass);
        this.setMethod(methodName);
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public void setMethod(String methodName) throws NoSuchMethodException {
        Method matchedMethod=aClass.getMethod(methodName,Object.class);
        this.method = matchedMethod;
    }

    public Object Invoke(Object parameter) throws InvocationTargetException, IllegalAccessException {
        return this.method.invoke(null,parameter);
    }

    public Object Invoke(Object instance,Object parameter) throws InvocationTargetException, IllegalAccessException {
        return this.method.invoke(instance,parameter);
    }
}
