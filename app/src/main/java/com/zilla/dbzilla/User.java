/*
 * Copyright 2017. Zilla Chen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zilla.dbzilla;


import com.zilla.dbzilla.annotations.AutoAssist;
import com.zilla.dbzilla.core.Id;
import com.zilla.dbzilla.core.Table;

import java.io.Serializable;

/**
 * Description:
 *
 * @author zilla
 * @version 1.0
 * @date 2017-01-22
 */

@AutoAssist
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
