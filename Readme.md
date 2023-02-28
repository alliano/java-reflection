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

# Parameter

Paramter merupakan representasi dari Java parameter pada java method
Cara mendapatkan parameter, kita bisa mengambilnya daru method, karna memang parameter hanya terdapat pada method dan constrtuctor 
Parameter memiliki banyak sekali method, seperti untuk mendapatkan tipe parameter, nama parameter, dan lain-lain.
ref : https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/reflec/Parameter.html

example :
``` java
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

```

# Memanggil Method Object dengan Parameter

Sama seperti method tampa parameter 
kita bisa memanggil method yang memiliki parameter pada java reflection
example : 
``` java
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
```

# Constructor<T>

Constructor<T> merupakan representasi dari Java Constructor pada java class
Constructor<T> ini mirip dengan method, yang mana constructor memiliki parameter
Untuk membuat Constructor kita mendapatkanya melalui Class<T>
Constructor merupakan tipedata generic mengikuti tipe data dari Class<T> nya
ref : https://docs.oracle.com/java/javase/11/dosc/api/java.base/java/lang/reflec/Constructor.html

example : 
``` java
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
```

# Membuat Object dengan Constructor

Kita sudah tau bahwa Constructor merupakan method yang di eksekusi ketika Object pertamakali dibuat
Dengan menggunakan constructor, kita bisa juga membuat object baru
Caranya dengan menggunakan method newInstance(parameter)

example : 
``` java
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
```

# Super Class 

Dengan menggunakan java reflection, kita bisa mengetahui super class dari sebuah java Class 
Ada methoh getSuperClass() pada Class<T> untuk mendapatkan super class nya 
Perlu diingat, bahwa saat kita membuat Class, jika kita menambahkan super class, secara otomatis super class nya adalah java.lang.Object
ref : https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Class.html#getSuperclass

example : 
``` java
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
```