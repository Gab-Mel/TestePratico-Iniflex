package br.com.gabriel.empresa;

import br.com.gabriel.empresa.model.Employee;
import br.com.gabriel.empresa.util.Formatter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public class main {

    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>();

        // 3.1 Inserção (ajuste conforme tabela real do teste)
        // 3.1 Inserção conforme tabela do teste
        employees.add(new Employee("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        employees.add(new Employee("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        employees.add(new Employee("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        employees.add(new Employee("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        employees.add(new Employee("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        employees.add(new Employee("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        employees.add(new Employee("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        employees.add(new Employee("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        employees.add(new Employee("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        employees.add(new Employee("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        // 3.2 Remover João
        employees.removeIf(f -> f.getname().equals("João"));

        // 3.3 Imprimir
        System.out.println("FUNCIONÁRIOS:");
        employees.forEach(f -> System.out.println(
                f.getname() + " | " +
                Formatter.date(f.getbirthDay()) + " | " +
                Formatter.valor(f.getsalary()) + " | " +
                f.getfunction()
        ));

        // 3.4 Aumento
        employees.forEach(f ->
                f.setsalary(f.getsalary().multiply(new BigDecimal("1.10")))
        );

        // 3.5 Agrupamento
        Map<String, List<Employee>> map =
                employees.stream().collect(Collectors.groupingBy(Employee::getfunction));

        // 3.6 Impressão agrupada
        System.out.println("\nAGRUPADOS:");
        map.forEach((k, v) -> {
            System.out.println(k + ":");
            v.forEach(f -> System.out.println(" - " + f.getname()));
        });

        // 3.8 Aniversários
        System.out.println("\nANIVERSARIANTES:");
        employees.stream()
                .filter(f -> {
                    int mes = f.getbirthDay().getMonthValue();
                    return mes == 10 || mes == 12;
                })
                .forEach(f -> System.out.println(f.getname()));

        // 3.9 Mais velho
        Employee maisVelho = Collections.min(employees,
                Comparator.comparing(Employee::getbirthDay));

        int idade = Period.between(maisVelho.getbirthDay(), LocalDate.now()).getYears();

        System.out.println("\nMAIS VELHO:");
        System.out.println(maisVelho.getname() + " - " + idade + " anos");

        // 3.10 Ordem alfabética
        System.out.println("\nORDEM ALFABÉTICA:");
        employees.stream()
                .sorted(Comparator.comparing(Employee::getname))
                .forEach(f -> System.out.println(f.getname()));

        // 3.11 Total salários
        BigDecimal total = employees.stream()
                .map(Employee::getsalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("\nTOTAL: " + Formatter.valor(total));

        // 3.12 Salários mínimos
        BigDecimal minimo = new BigDecimal("1212.00");

        System.out.println("\nSALÁRIOS MÍNIMOS:");
        employees.forEach(f -> {
            BigDecimal qtd = f.getsalary().divide(minimo, 2, RoundingMode.HALF_UP);
            System.out.println(f.getname() + ": " + qtd);
        });
    }
}