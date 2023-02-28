package com.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.junit.Test;

public class ParameterTest {
    
    @Test
    public void testGetParameter() {

        Class<? extends Person> personClass = Person.class;

        Method[] methods = personClass.getDeclaredMethods();

        for (Method method : methods) {
            System.out.println("Method name : "+method.getName());
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter : parameters) {
                System.out.println("Paramter name : "+ parameter.getName());
                System.out.println("Paramter type : "+ parameter.getType().getSimpleName());
                System.out.println("----------------------------");
            }
        }
    }

    @Test
    public void testIvokeMethodWithParamter() throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {

        Class<? extends Person> personClass = Person.class;

        Person person = new Person("Alliano", "Alfarez");
       
        // untuk mendapatkan methdo yang memiliki parameter disini kita harus ikutkan juga tipe 
        // dari parameter nya dalam konteks ini tipe paramter nya String dan hanya meiliki 1 parameter
        Method setFristName = personClass.getDeclaredMethod("setFristname", String.class);

        // disini Alliano akan di ubah dengan kata Uchiha
        setFristName.invoke(person, "Uchiha");

        System.out.println(person.getFristname());
    }
}
