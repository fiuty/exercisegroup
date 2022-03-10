package com.dayue.common.reflect;

import com.dayue.common.reflect.entity.Generic;
import com.dayue.common.reflect.entity.Person;
import com.dayue.common.reflect.entity.Teacher;
import com.dayue.common.reflect.service.ReflectUtils;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author zhengdayue
 */
public class Main {

    public static void main(String[] args) {
        Teacher teacher = new Teacher();
        teacher.setJobYears(3);
        teacher.setAge(27);
        teacher.setName("教师1");
        List<Field> allFields = ReflectUtils.getAllFieldsByWildcard(Teacher.class);
        allFields.forEach(item -> System.out.printf("属性名称：{%s}，类型：{%s}，属性值：{%s}\n", item.getName(),
                item.getType(), ReflectUtils.getFieldValue(item, teacher)));

        System.out.println("--------多态------------");
        Person person = new Teacher();
        System.out.printf("多态父类引用指向子类对象，反射获取class类型：{%s}\n", person.getClass());

        System.out.println("-------传入对象，利用多态、反射获取属性集--------");
        List<Field> allFieldsByPolymorphism = ReflectUtils.getAllFieldsByPolymorphism(teacher);
        allFieldsByPolymorphism.forEach(item -> System.out.printf("属性名称：{%s}，类型：{%s}，属性值：{%s}\n", item.getName(),
                item.getType(), ReflectUtils.getFieldValue(item, teacher)));

        System.out.println("--------泛型类型参数------------");
        List<Field> allFieldsByGeneric = ReflectUtils.getAllFieldsByGeneric(Teacher.class);
        allFieldsByGeneric.forEach(item -> System.out.printf("属性名称：{%s}，类型：{%s}，属性值：{%s}\n", item.getName(),
                item.getType(), ReflectUtils.getFieldValue(item, teacher)));

        System.out.println("---------泛型的类型擦除-------------------");
        Generic<Person> personGeneric = new Generic<>();
        Person person1 = new Person();
        personGeneric.settObject(person1);
        Generic<String> stringGeneric = new Generic<>();
        stringGeneric.settObject("123456");
        System.out.printf("personGeneric.getClass() == stringGeneric.getClass() :{%s}\n",
                personGeneric.getClass() == stringGeneric.getClass());
        System.out.printf("personGeneric的泛型T类型：{%s},stringGeneri的泛型T类型：{%s}\n",
                ReflectUtils.getGenericType(personGeneric), ReflectUtils.getGenericType(stringGeneric));

    }
}
