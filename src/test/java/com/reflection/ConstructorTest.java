package com.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;

import org.junit.Test;

public class ConstructorTest {
    
    @Test
    public void testGetConstructor() {

        Class<? extends Person> personClass = Person.class;

        Constructor<?>[] construcors = personClass.getDeclaredConstructors();

        for (Constructor<? extends Object> constructor : construcors) {
            Parameter[] parameters = constructor.getParameters();
            for (Parameter parameter : parameters) {
                System.out.println("Constructor name : "+constructor.getName());
                System.out.println("Parameter name : "+parameter.getName());
                System.out.println("Paramter type : "+parameter.getType().getSimpleName());
                System.out.println("Parameter modifier : "+parameter.getModifiers());
                System.out.println("-------------------------");
            }
        }
    }

    @Test
    public void testCreateObjectConstructor() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        Class<? extends Person> personClass = Person.class;

        // mengambil constructor yang tidak memiliki parameter
        Constructor<? extends Person> constructor = personClass.getConstructor();

        // mengambil constructor yang memiliki 2 parameter
        Constructor<? extends Person> constructorWithParameters = personClass.getConstructor(String.class, String.class);

        // membuat Object Person dengan tampa parameter pada constructor nya (java reflection) 
        // ini sebenarnya sama dengan new Person();
        Person personNoParam = (Person) constructor.newInstance();

        // membuat object person dengan 2 parameter pada constructor nya (java reflection)
        // ini sebenarnya sama dengan new Person("Alliano", "Alfarez")
        Person personWithParam = (Person) constructorWithParameters.newInstance("Alliano", "alfarez");

        System.out.println("firstname : "+personNoParam.getFristname());

        System.out.println("firstname : "+personWithParam.getFristname());
    }
}
