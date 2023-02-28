package com.reflection;

import org.junit.Test;

public class SuperClassTest {
    
    @Test
    public void testSuperClass() {

        Class<? extends Person> personClass = Person.class;

        // ini akan menadapatkan super class nya adalah Object karna Class Person yang kita buat tidak
        // meng extends kelas lain. dan secara default class yang tidak meng extends kelas lain, maka di java
        // itu artinya meng extens kelas java.lang.Object
        Class<?> objSuperClass = personClass.getSuperclass();

        System.out.println(objSuperClass);

        // ini akan mendapatkan nilai null karna Object adalah tingkatan yang tertinggi di permograman java
        Class<?> objSUperClass2 = objSuperClass.getSuperclass();

        System.out.println(objSUperClass2);
    }
}
