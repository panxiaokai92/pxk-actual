package com.panxk.actual.design.patterns.strategy;

import lombok.Data;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2020-04-15
 **/
public class ComparatorTest {

    public static void main(String[] args) {

        Person[] persons = new Person[] {
                new Person(10, 111), new Person(18, 99), new Person(15, 122)
        };

        Arrays.sort(persons, new SortByAge());
        print(persons);

        Arrays.sort(persons, new SortByHeight());
        print(persons);
    }

    //打印
    static void print(Person[] persons) {
        for (Person person : persons) {
            System.out.println(person);
        }
    }


    //根据年龄排序
    static class SortByAge implements Comparator<Person> {
        @Override
        public int compare(Person o1, Person o2) {
            if (o1.getAge() > o2.getAge()) {
                return 1;
            } else if (o1.getAge() < o2.getAge()) {
                return -1;
            }
            return 0;
        }
    }

    //根据体重排序
    static class SortByHeight implements Comparator<Person> {
        @Override
        public int compare(Person o1, Person o2) {
            if (o1.getHeight() > o2.getHeight()) {
                return 1;
            } else if (o1.getHeight() < o2.getHeight()) {
                return -1;
            }
            return 0;
        }
    }

    //Person类
    @Data
    static class Person{
        int age;
        int height;

        public Person(int age, int height) {
            this.age = age;
            this.height = height;
        }
    }

}
