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

package com.zilla.dbzilla.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by zilla on 04/02/2017.
 */
@Entity
public class GreenUser {
    @Id
    private Long id;

    private String name;

    private String address;

    private String mail;

    private String birth;

    private boolean gender;

    private int age;

    private short salary;

    private double salary2;

    @Generated(hash = 1176298326)
    public GreenUser(Long id, String name, String address, String mail,
            String birth, boolean gender, int age, short salary, double salary2) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.mail = mail;
        this.birth = birth;
        this.gender = gender;
        this.age = age;
        this.salary = salary;
        this.salary2 = salary2;
    }

    @Generated(hash = 1678257977)
    public GreenUser() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getBirth() {
        return this.birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public boolean getGender() {
        return this.gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public short getSalary() {
        return this.salary;
    }

    public void setSalary(short salary) {
        this.salary = salary;
    }

    public double getSalary2() {
        return this.salary2;
    }

    public void setSalary2(double salary2) {
        this.salary2 = salary2;
    }
}
