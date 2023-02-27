package com.reflection;

import java.lang.reflect.Field;
import java.util.Arrays;

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

    @Test
    public void testReflectionMethod() throws NoSuchMethodException, SecurityException {

        Class<? extends Person> personClass = Person.class;

        // ini untuk mendapatkan super class nya 
        System.out.println(personClass.getSuperclass());

        //jika class person meng implementasi Interface kita bisa gunkan untuk mendapatkan impement 
        // interface nya menggunkan getInterfaces()
        System.out.println(Arrays.toString(personClass.getInterfaces()));
        // ini akan mengambil semua constructor pada class person tampa menghiraukan visibility nya 
        // atau access modifier nya jadi semua construcror entah itu public atau private atau protected 
        // akan di ambil semua
        //
        System.out.println(personClass.getDeclaredConstructor());

        // ini akan mengambil semua method pada class person tampa menghiraukan 
        // visibility nya atau aksess modifier nya jadi semua mehtod public, protected, private akan di ambil semua
        System.out.println(Arrays.toString(personClass.getDeclaredMethods()));

        // ini akan mengambil semua fields (property pada suatu class) tampa menghiraukan access modifiernya
        System.out.println(Arrays.toString(personClass.getDeclaredFields()));
        
        // ini akan mengambil package name nya atau base package pada class Person
        System.out.println(personClass.getPackageName());

        // ini untuk mendapatkan nama Class nya
        System.out.println(personClass.getName());

        // untuk mendapatkan modifier nya
        System.out.println(personClass.getModifiers());
    }

    @Test
    public void testField() {

        Class<? extends Person> person = Person.class;
        // ini kita mengambil semua field/ property yang dimiliki oleh class Person tampa memperdulikan visibility nya
        Field[] filds = person.getDeclaredFields();

        for (Field field : filds) {
            // menampilkan nama field yang dimiliki class Person dan tipe data field nya
            System.out.println(field.getName()+" type : "+field.getType().getSimpleName());
        }
    }

    @Test
    public void testChangeFieldObject() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

        // buat obeject Person
        Person person = new Person("alliano", "alfarez");

        // buat class reflection
        Class<? extends Person> personClass = Person.class;

        // ambil field firstname dari class reflection yang kita buat
        Field firstName = personClass.getDeclaredField("fristname");

        // berhubung field firstname itu memiliki access modifier private maka kita harus setAccessible(true)
        // agar kita bisa operasikan
        firstName.setAccessible(true);

        // 
        String firstNameVal = (String)firstName.get(person);

        System.out.println(firstNameVal);
    }

    
    
}
