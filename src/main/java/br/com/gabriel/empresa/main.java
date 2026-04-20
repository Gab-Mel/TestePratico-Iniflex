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
        employees.removeIf(e -> "João".equals(e.getName()));

        // 3.3 Imprimir
        System.out.println("\n========= FUNCIONÁRIOS =========");
        System.out.printf("%-12s %-12s %-15s %-15s%n", "Nome", "Nascimento", "Salário", "Função");

        employees.forEach(e -> System.out.printf("%-12s %-12s %-15s %-15s%n",
                e.getName(),
                Formatter.formatDate(e.getBirthDate()),
                Formatter.formatCurrency(e.getSalary()),
                e.getFunction()
        ));


        // 3.4 Aumento
        employees.forEach(f ->
                f.setSalary(f.getSalary().multiply(new BigDecimal("1.10")))
        );

        System.out.println("\n========= FUNCIONÁRIOS COM AUMENTO =========");
        System.out.printf("%-12s %-12s %-15s %-15s%n", "Nome", "Nascimento", "Salário", "Função");
        employees.forEach(e -> System.out.printf("%-12s %-12s %-15s %-15s%n",
                e.getName(),
                Formatter.formatDate(e.getBirthDate()),
                Formatter.formatCurrency(e.getSalary()),
                e.getFunction()
        ));

        // 3.5 Agrupamento
        Map<String, List<Employee>> grouped =
                employees.stream().collect(Collectors.groupingBy(Employee::getFunction));

        // 3.6 Impressão agrupada
        System.out.println("\n========= AGRUPADOS POR FUNÇÃO =========");

        grouped.forEach((role, list) -> {
        System.out.println("\nFunção: " + role);
        System.out.printf("%-12s %-12s %-15s%n", "Nome", "Nascimento", "Salário");

        list.forEach(e -> System.out.printf("%-12s %-12s %-15s%n",
                e.getName(),
                Formatter.formatDate(e.getBirthDate()),
                Formatter.formatCurrency(e.getSalary())
        ));
        });

        // 3.8 Aniversários
        System.out.println("\n========= ANIVERSARIANTES (OUT/DEZ) =========");
        System.out.printf("%-12s %-12s%n", "Nome", "Nascimento");

        employees.stream()
                .filter(e -> {
                int month = e.getBirthDate().getMonthValue();
                return month == 10 || month == 12;
                })
                .forEach(e -> System.out.printf("%-12s %-12s%n",
                        e.getName(),
                        Formatter.formatDate(e.getBirthDate())
                ));

        // 3.9 Mais velho
        Employee maisVelho = Collections.min(employees,
                Comparator.comparing(Employee::getBirthDate));

        int idade = Period.between(maisVelho.getBirthDate(), LocalDate.now()).getYears();

        System.out.println("\n========= FUNCIONÁRIO MAIS VELHO =========");
        System.out.printf("%-12s %-10s%n", "Nome", "Idade");
        System.out.printf("%-12s %-10d%n", maisVelho.getName(), idade);

        // 3.10 Ordem alfabética
        System.out.println("\n========= ORDEM ALFABÉTICA =========");
        System.out.printf("%-12s%n", "Nome");

        employees.stream()
                .sorted(Comparator.comparing(Employee::getName))
                .forEach(e -> System.out.printf("%-12s%n", e.getName()));

        // 3.11 Total salários
        BigDecimal total = employees.stream()
                .map(Employee::getSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("\n========= TOTAL DOS SALÁRIOS =========");
        System.out.printf("%-20s %-15s%n", "Total", Formatter.formatCurrency(total));

        // 3.12 Salários mínimos
        BigDecimal minimo = new BigDecimal("1212.00");

        System.out.println("\n========= SALÁRIOS MÍNIMOS =========");
        System.out.printf("%-12s %-15s%n", "Nome", "Qtd. Salários");

        employees.forEach(e -> {
        BigDecimal qtd = e.getSalary().divide(minimo, 2, RoundingMode.HALF_UP);

        System.out.printf("%-12s %-15s%n",
                e.getName(),
                qtd
        );
        });
    }
}