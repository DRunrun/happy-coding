package org.jojo.thinking.in.spring.ioc.overview.domain;

import org.jojo.thinking.in.spring.ioc.overview.annotaion.Super;

/**
 * 超级用户
 *
 * @author djq
 * @date 2024-03-20 17:06
 **/
@Super
public class SupperUser extends User{
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SupperUser{" +
                "address='" + address + '\'' +
                "} " + super.toString();
    }
}
