package com.panxk.actual.demo.redis;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.nio.charset.Charset;

/**
 * @description: 布隆过滤器
 * @author: Mr.pxk
 * @create: 2020-03-30
 **/
public class BloomFilterTest {


    public static void main(String[] args) {

        long expectedInsertions = 1000000;
        double fpp = 0.00001;

        //字符
        BloomFilter<String> filter = BloomFilter.create(Funnels.stringFunnel(Charset.forName("utf-8")),expectedInsertions, fpp);
        filter.put("A");
        filter.put("B");
        filter.put("C");

        if (filter.mightContain("A")) {
            System.out.println("has exist A");
        } else {
            System.out.println("has not exist A");
        }

        //邮箱
        BloomFilter<Email> emailBloomFilter = BloomFilter
                .create((Funnel<Email>) (from, into) -> into.putString(from.getDomain(), Charsets.UTF_8),
                        expectedInsertions, fpp);

        emailBloomFilter.put(new Email("sage.wang", "quanr.com"));
        boolean containsEmail = emailBloomFilter.mightContain(new Email("sage.wangaaa", "quanr.com"));
        System.out.println(containsEmail);

    }

    @Data
    @Builder
    @ToString
    @AllArgsConstructor
    public static class Email {
        private String userName;
        private String domain;
    }
}
