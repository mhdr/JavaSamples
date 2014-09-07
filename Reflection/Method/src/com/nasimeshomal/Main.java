package com.nasimeshomal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
	    Class<Greeting> aClass=Greeting.class;
        Method[] methods=aClass.getMethods();

        System.out.println("All methods of Greeting class : ");
        for (Method method:methods)
        {
            System.out.println(method);
        }

        Method method1=aClass.getMethod("sayHello",String.class);
        System.out.println("sayHello Method : ");
        System.out.print("Name : ");
        System.out.println(method1.getName());
        System.out.println("Parameters type: ");

        for (Class c:method1.getParameterTypes())
        {
            System.out.println(c);
        }

        System.out.print("Return type: ");
        System.out.println(method1.getReturnType());

        Method method2=aClass.getMethod("getGreeting",String.class);
        System.out.println("getGreeting Method : ");
        System.out.print("Name : ");
        System.out.println("Parameters type: ");
        System.out.println(method2.getName());

        for (Class c:method2.getParameterTypes())
        {
            System.out.println(c);
        }

        System.out.println("Return type: ");
        System.out.println(method2.getReturnType());


        Greeting greeting=new Greeting();

        // if class was static greeting was replaced with null
        //Object returnValue1= method1.invoke(null,"Mahmood");

        System.out.println("sayHello invoked : ");
        Object returnValue1= method1.invoke(greeting,"Mahmood");
        System.out.println("sayHello return type : ");
        System.out.println(returnValue1);

        System.out.println("getGreeting invoked : ");
        Object returnValue2= method2.invoke(greeting,"Mahmood");
        System.out.println("getGreeting return type : ");
        System.out.println(returnValue2);

        Method method3=null;

        for (Method method:methods)
        {
            if (method.getName()=="sayHello")
            {
                method3=method;
            }
        }

        System.out.println("End");
    }
}
