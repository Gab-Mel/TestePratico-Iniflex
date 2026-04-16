package br.com.gabriel.empresa.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Employee extends Person {

    private BigDecimal salary;
    private String function;

    public Employee(String name, LocalDate birthDay, BigDecimal salary, String function) {
        super(name, birthDay);
        this.salary = salary;
        this.function = function;
    }

    public BigDecimal getsalary() {
        return salary;
    }

    public void setsalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getfunction() {
        return function;
    }
}