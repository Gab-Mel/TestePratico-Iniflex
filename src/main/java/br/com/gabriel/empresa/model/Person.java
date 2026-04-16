package br.com.gabriel.empresa.model;

import java.time.LocalDate;

public class Person {

    protected String name;
    protected LocalDate birthDay;

    public Person(String name, LocalDate birthDay) {
        this.name = name;
        this.birthDay = birthDay;
    }

    public String getname() {
        return name;
    }

    public LocalDate getbirthDay() {
        return birthDay;
    }
}