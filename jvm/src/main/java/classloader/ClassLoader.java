package classloader;

import domain.Student;

import java.util.HashSet;

/**
 *
 * @author zhengdayue
 */
public class ClassLoader {

    public static void main(String[] args) {
        //类是模板，对象是具体的
        Student student1 = new Student();
        Class<? extends Student> aClass = student1.getClass();
        java.lang.ClassLoader classLoader = aClass.getClassLoader();
        System.out.println(classLoader);//appclassloader
        System.out.println(classLoader.getParent());//extclassloader
        System.out.println(classLoader.getParent().getParent());//nu    ll java程序获取不到

        //jvm 试图使用的最大内存
        long max = Runtime.getRuntime().maxMemory();//字节为单位 bytes KB MB GB
        //jvm总内存
        long total = Runtime.getRuntime().totalMemory();

        //默认情况下：分配的总内存是电脑内存的1/4，初始化的总内存是1/64
        System.out.println(max / 1024 / 1024);
        System.out.println(total / 1024 / 1024);
    }
}
