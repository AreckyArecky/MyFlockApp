/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mychurch.mychurchapp.entity;

import jakarta.persistence.*;

/**
 *
 * @author arkadiuszkuzma
 */
@Entity
@Table(name = "Members")
public class ChurchMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "SecondName")
    private String secondName;

    @Column(name = "Age")
    private int age;

    @Column(name = "isMember")
    private boolean isMember;

    @Column(name = "Func")
    private String function;

    @Column(name = "PhoneNumber")
    private int phoneNumber;

    public ChurchMember() {

    }

    public ChurchMember(String firstName, String secondName, int age) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMember() {
        return isMember;
    }

    public void setIsMember(boolean isMember) {
        this.isMember = isMember;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    @Override
    public String toString() {
        return "ChurchMember{" + "id=" + id + ", firstName=" + firstName + ", secondName=" + secondName + ", age=" + age + ", isMember=" + isMember + ", function=" + function + ", phoneNumber=" + phoneNumber + '}';
    }

}
