package org.jojo.kstry.entity;

/**
 * 学生敏感信息
 *
 * @author djq
 * @date 2024-03-26 15:42
 **/
public class StudentPrivacy {
    private Long id;

    private String idCard;

    private String birthday;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
