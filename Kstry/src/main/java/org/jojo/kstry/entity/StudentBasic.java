package org.jojo.kstry.entity;

/**
 * 学生基础信息
 *
 * @author djq
 * @date 2024-03-26 15:41
 **/
public class StudentBasic {
    private Long id;

    private String name;

    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
