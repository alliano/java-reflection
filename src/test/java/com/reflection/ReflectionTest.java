package com.reflection;

import org.junit.Test;

public class ReflectionTest {
    
    
    @Test
    public void testCereateReflection() throws ClassNotFoundException {
        // contoh membuat java reflection menggunakan Namaclass.class
        Class<Person> personClass = Person.class;

        // contoh membuat java reflection menggunkan static method
        // disini kita harus bungkus menggunakan try cath karna bisa saja class yang kita inginkan nga ada
         Class<?> personClass2 = Class.forName("com.reflection.Person");
     
        // contoh membuat java reflection menggunkan nama Class yang sudah kita instansiasi
        Person person = new Person();
        Class<? extends Person> personClass3 = person.getClass();

        System.out.println(personClass.getSimpleName()+ " Cara pertama");
        System.out.println(personClass2.getSimpleName()+ " Cara ke 2");
        System.out.println(personClass3.getSimpleName()+ " Cara ke 3");
    }

    
    
}
