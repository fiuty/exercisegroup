package com.dayue.common.reflect.service;

import com.dayue.common.reflect.entity.Generic;
import com.dayue.common.reflect.entity.Person;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 反射、类型擦除，以及泛型、通配符、上界通配符、下界通配符一些笔记
 *
 * @author zhengdayue
 */
public class ReflectUtils {

    /**
     * 获取类（包括继承父类）的属性集,用泛型通配符?接收class
     *
     * @param clazz class类
     * @return 反射field属性集
     */
    public static List<Field> getAllFieldsByWildcard(Class<?> clazz) {
        ArrayList<Field> fields = new ArrayList<>(8);
        while (!Objects.equals(clazz, Object.class)) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            // 获取父类class
            clazz = clazz.getSuperclass();
        }
        return fields;
    }

    /**
     * 采用类型参数接收class
     *
     * @param clazz class类
     * @param <T> 泛型参数类型
     * @return 反射field属性集
     */
    public static <T> List<Field> getAllFieldsByGeneric(Class<T> clazz) {
        ArrayList<Field> fields = new ArrayList<>(8);
        fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
        // 下界通配符，?通配符，通配符?类是泛型T类的父类
        Class<? super T> superclass = clazz.getSuperclass();
        while (!Objects.equals(superclass, Object.class)) {
            fields.addAll(Arrays.asList(superclass.getDeclaredFields()));
            superclass = superclass.getSuperclass();
        }
        return fields;
    }

    /**
     * 动态获取某个对象某个属性值
     *
     * @param field 属性变量
     * @param tObject 对象
     * @param <T> 泛型
     * @return 属性值
     */
    public static <T> Object getFieldValue(Field field, T tObject) {
        field.setAccessible(true);
        try {
            return field.get(tObject);
        } catch (IllegalAccessException e) {
            System.out.printf("获取对象属性值异常e:%s", e);
        }
        return null;
    }

    /**
     * 利用多态，父类引用来做方法入参，可接收子类对象，再通过反射获取传入子类对象的反射类class
     *
     * @param person 父类Person对象
     * @return 反射field属性集
     */
    public static List<Field> getAllFieldsByPolymorphism(Person person) {
        // 上界通配符，通配符?类是继承自Person类
        Class<? extends Person> aClass = person.getClass();
        return getAllFieldsByWildcard(aClass);
    }

    /**
     * 泛型的类型擦除，泛型信息只存在于代码编译阶段，在进入jvm之前，与泛型相关的信息会被擦除掉，即泛型擦除。
     * 虽然编译阶段参数类型T一旦确定就不能变更为其他类型，如T为String时候，不能赋值为Integer类型
     * 但在仍可以通过反射在运行时“特意”更改参数类型，因为类型擦除之后最终是Object类型
     *
     * @param generic Generic类
     * @param <T>     泛型
     * @return 泛型T的class类型（最终都是返回class java.lang.Object）
     */
    public static <T> String getGenericType(Generic<T> generic) {
        Field[] declaredFields = generic.getClass().getDeclaredFields();
        Class<?> type = declaredFields[0].getType();
        return type.toString();
    }
}
