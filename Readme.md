# Java Reflection

java.lang.Class<T> merupakan representasi dari reflection unutk java class, interface dan enum
Saat kita membuat java Class, Interface atau Enum, kadang kita menambahkan field atau method
dengan kemampuan java.lang.Class<T>, kita bisa membaca seluruh data member yang terdapat pada java Class, Interface, ataupun Enum pada saat applikasi nya berjalan.

refrensi : https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Class.html

# Membuat object java.lang.Class<T>

Untuk membuat Class<T> bisa dilakukan dengan beberapa cara
Cara ke 1 dengan menggunakan kata kunci .class setelah nama java class, Interface, Enum nya
Misalnya Babi.class, Anjing.class, Kontl.class Repository.class, Gender.class, Role.class
``` java
Class<Repository> classRepository = Repository.class
```
Cara ke 2 dengan menggunkan static method yang ada pada Object Class<?> yaitu method forClass(base package)
Misalnya Class.forClass("com.java.reflection.body.Kontol")
``` java
Class<?> classKontl = Class.forClass("com.java.reflection.body.Kontl");
```

Atau kita juga bisa mengambil Class<T> dari object, dengan menggunakan getClass();
Misal kita punya Class Babi.java maka kita bisa mendapatkan Class nya dengan cara meng inisialisasi kemudian mengambil class nya dengan menggunakan getClass();
```java
Babi babi = new babi();
Calss<Babi> babiClass = babi.getClass(); 
```