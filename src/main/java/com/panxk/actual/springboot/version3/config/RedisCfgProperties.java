package com.panxk.actual.springboot.version3.config;

import lombok.Data;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2020-04-08
 **/
@Data
public class RedisCfgProperties {

    Integer maxTotal;
    Integer maxIdle;
}
