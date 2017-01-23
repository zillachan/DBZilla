package com.zilla.dbzilla;

import com.zilla.dbzilla.db.Id;
import com.zilla.dbzilla.db.Table;

import java.io.Serializable;

/**
 * Description:
 *
 * @author zilla
 * @version 1.0
 * @date 2017-01-22
 */

@Table("t_user")
public class User implements Serializable {

    @Id
    private long id;

    private String name;

    private String address;

    private String mail;

    private String birth;

    private boolean gender;

    private int age;

    private short salary;

    private double salary2;


    public User() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public short getSalary() {
        return salary;
    }

    public void setSalary(short salary) {
        this.salary = salary;
    }

    public double getSalary2() {
        return salary2;
    }

    public void setSalary2(double salary2) {
        this.salary2 = salary2;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", mail='" + mail + '\'' +
                ", birth='" + birth + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", salary=" + salary +
                ", salary2=" + salary2 +
                '}';
    }
}
