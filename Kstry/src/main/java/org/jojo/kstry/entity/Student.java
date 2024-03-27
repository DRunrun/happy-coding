package org.jojo.kstry.entity;

import lombok.Data;

/**
 * 学生全量信息
 *
 * @author djq
 * @date 2024-03-26 15:43
 **/
@Data
public class Student {
    private Long id;

    private String name;

    private String address;

    private String idCard;

    private String birthday;
}
