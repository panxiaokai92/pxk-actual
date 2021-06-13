package com.panxk.base.annotation.filedCheck;

import org.springframework.util.StringUtils;

import java.lang.reflect.Field;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2021-06-13
 **/
public class EntityTestUtils {


    public static void main(String[] args) {

        EntityTestUtils entityTestUtils = new EntityTestUtils();
        Entity entity = new Entity("123456", "12");
        try {
            entityTestUtils.checkFiled(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 校验方法
     * @param o
     * @throws IllegalAccessException
     */
    private void checkFiled(Object o) throws IllegalAccessException {

        Class aClass = o.getClass();
        //校验是否有注解
        if (aClass.isAnnotationPresent(EntityCheck.class)) {
            Field[] fields = aClass.getDeclaredFields();
            if (fields.length <= 0) {
                return;
            }
            //遍历属性
            for (Field field : fields) {
                if (!field.isAnnotationPresent(FiledCheck.class)) {
                    continue;
                }
                //私有属性设置属性可见
                field.setAccessible(true);
                //解析注解
                FiledCheck fieldCheck = field.getAnnotation(FiledCheck.class);
                if (field.getGenericType().toString().equals("class java.lang.String")) {
                    String str = (String) field.get(o);
                    if (!StringUtils.isEmpty(str) && (str.length() < fieldCheck.min()
                            || str.length() > fieldCheck.max())) {
                        System.out.println(fieldCheck.error());
                    }
                }
            }
        }
    }
}