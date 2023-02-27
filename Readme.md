# Java Reflection

java.lang.Class<T> merupakan representasi dari reflection unutk java class, interface dan enum
Saat kita membuat java Class, Interface atau Enum, kadang kita menambahkan field atau method
dengan kemampuan java.lang.Class<T>, kita bisa membaca seluruh data member yang terdapat pada java Class, Interface, ataupun Enum pada saat applikasi nya berjalan.

refrensi : https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Class.html

# Membuat object java.lang.Class<T>

Untuk membuat Class<T> bisa dilakukan dengan beberapa cara
Cara ke 1 dengan menggunakan kata kunci .class setelah nama java class, Interface, Enum nya
Misalnya Babi.class, Anjing.class, Repository.class, Gender.class, Role.class
``` java
Class<Repository> classRepository = Repository.class
```
Cara ke 2 dengan menggunkan static method yang ada pada Object Class<?> yaitu method forClass(base package)
Misalnya Class.forClass("com.java.reflection.body.Person")
``` java
Class<?> classKontl = Class.forClass("com.java.reflection.body.Person");
```
Atau kita juga bisa mengambil Class<T> dari object, dengan menggunakan getClass();
Misal kita punya Class Babi.java maka kita bisa mendapatkan Class nya dengan cara meng inisialisasi kemudian mengambil class nya dengan menggunakan getClass();
```java
Babi babi = new babi();
Calss<Babi> babiClass = babi.getClass(); 
```

example : 
``` java
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
```
# Class<T> Method

Calss<T> memiliki banyak sekali method
contoh nya seperi method untk mendapatkan semua field (property pada suatu class), method, constructor, super class, interface, dan masih banyak lagi
semua detail dari method nya bisa kita lihat pada java docs nya https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Class.html

# Fields

Field merupakan representasi dari java Field yang terdapat pada java class 
Untuk mendapatkan public field kita bisa menggunakan getFields();
dan jika ingin mendapatkan semua field tampa memperdulikan visibility nya bisa pake getDeclaredFileds();
Atau kita bisa juga mendapatkan field berdasarkan nama field menggunakan method getField(name) atau getDeclaredField(name);
Field sama seperti Class<T>, memiliki banyak sekali method yang bisa kita gunakan untuk melihat detail dari field tersebut, seperti tipe data, nama field, annotasion, dan lain lain.

contoh : 
``` java
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
```

ref : https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/reflec/Field.html

# Menggambil atau Mengubah Field Object

Field memiliki kemampuan untuk mengambil atau mengubah field dari object yang ada 
Misal kitas sudah membuat Field, lalu kita memiliki Object Person1 lalu kita ingin mengambil nilai field tersebut atau mengubahnya kita bisa menggunakan Method set dan get pada Field

``` java
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
```

# Method

Selain Field, kita juga bisa mendapatkan method yang tersedia pada Class<T>
Cara mendapatkanya pun sama seperti Field kita bisa menggunakan getMethods() atau getDeclaredMethods()
getMethod(name), getDeclaredMethod(name)
Method juga banyak memiliki method yang bisa kita gunakan unutuk mendapatkan informasi method itu sendiri seperti return value, nama method, annotation yang dimiliki method tersebut, parameter dan lain lain.
https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/reflec/Method.html

example : 
``` java
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
```

# Memanggil Method Object

Method bisa digunakan untuk memanggil method pada sebuah object
Hampir mirip dengan Field yang bisa digunakan untuk mengambil atau mengubah Field didalam Object
Unutk memanggil Method object, kita bisa menggunakan method invoke(Object, parameter)

example :
``` java
    @Test
    public void testGetMethodValue() throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {
        
        Class<? extends Person> personClass = Person.class;

        Method firstName = personClass.getDeclaredMethod("getFristname");

        Person person = new Person("alliano", "Alfarez");

        String firstNameValue = (String) firstName.invoke(person);

        System.out.println(firstNameValue);

    }
```