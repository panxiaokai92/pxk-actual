package com.panxk.base.annotation.filedCheck;

import lombok.Data;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2021-06-13
 **/
@EntityCheck(entityName = "entityName")
@Data
public class Entity {

    @FiledCheck(min = 5, max = 10, error = "f1 not in range!")
    private String filed1;

    @FiledCheck(min = 3, max = 8, error = "f2 not in rang!")
    private String filed2;

    public Entity() {
    }

    public Entity(String filed1, String filed2) {
        this.filed1 = filed1;
        this.filed2 = filed2;
    }
}
