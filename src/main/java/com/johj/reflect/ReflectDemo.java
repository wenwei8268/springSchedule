package com.johj.reflect;

/**
 * @author:wenwei
 * @date:2020/02/12
 * @description:
 */
public class ReflectDemo {

    public static void main(String[] args) throws ClassNotFoundException {
        //使用类的class属性，获取该类的Class对象
        Class<Student> studentClass = Student.class;
        System.out.println(studentClass);

        Class<Student> studentClass1 = Student.class;
        System.out.println(studentClass == studentClass1);

        System.out.println("---------");

        //调用对象的getClass（）方法，返回该对象所属的类的对应class对象
        Student student = new Student();
        Class<? extends Student> c3 = student.getClass();
        System.out.println(studentClass == c3);
        System.out.println("---------");

        //使用class类中的静态方法forName(String className)
       Class<?> c4 =  Class.forName("com.johj.reflect.Student");
        System.out.println(c4 == studentClass);



    }
}
