package com.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.junit.Test;

public class MethodTest {
    
    @Test
    public void testMethod() {

        Class<? extends Person> personCalass = Person.class;

        Method[] methods = personCalass.getDeclaredMethods();

        for (Method method : methods) {
            System.out.println(method.getName());
            System.out.println(method.getReturnType().getSimpleName());
            System.out.println(Arrays.toString(method.getParameterTypes()));
            System.out.println(Arrays.toString(method.getExceptionTypes()));
            System.out.println("------------------------");
        }

    }

    @Test
    public void testGetMethodValue() throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {
        
        Class<? extends Person> personClass = Person.class;

        Method firstName = personClass.getDeclaredMethod("getFristname");

        Person person = new Person("alliano", "Alfarez");

        String firstNameValue = (String) firstName.invoke(person);

        System.out.println(firstNameValue);

    }
}
