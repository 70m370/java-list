package Desafio;

//imports
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class Principal {
    // like array c++
    public static void main(String[] args) {

        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios
                .add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios
                .add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        funcionarios.removeIf(f -> f.getName().equals("João"));

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Locale localeBR = new Locale("pt", "BR");

        for (Funcionario f : funcionarios) {
            System.out.println(String.format(localeBR, "Nome: %s, Data de Nascimento: %s, Salário: %,.2f, Função: %s",
                    f.getName(), f.getDataNascimento().format(dateFormatter), f.getSalario(), f.getCargo()));
        }
        // 10% salario
        for (Funcionario f : funcionarios) {
            BigDecimal aumento = f.getSalario().multiply(new BigDecimal("0.10"));
            f.changeSalario(f.getSalario().add(aumento));
        }

        // mapping funcionarios
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getCargo));

        for (String funcao : funcionariosPorFuncao.keySet()) {
            System.out.println("Função: " + funcao);
            for (Funcionario f : funcionariosPorFuncao.get(funcao)) {
                System.out.println(String.format(localeBR,
                        "Nome: %s, Data de Nascimento: %s, Salário: %,.2f, Função: %s",
                        f.getName(), f.getDataNascimento().format(dateFormatter), f.getSalario(), f.getCargo()));
            }
        }

        for (Funcionario f : funcionarios) {
            int mesAniversario = f.getDataNascimento().getMonthValue();
            if (mesAniversario == 10 || mesAniversario == 12) {
                System.out.println(String.format(localeBR, "Nome: %s, Data de Nascimento: %s",
                        f.getName(), f.getDataNascimento().format(dateFormatter)));
            }
        }

        // idade
        Funcionario maisVelho = funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .orElse(null);

        if (maisVelho != null) {
            int idade = Period.between(maisVelho.getDataNascimento(), LocalDate.now()).getYears();
            System.out.println("Funcionário com maior idade:");
            System.out.println(String.format(localeBR, "Nome: %s, Idade: %d anos", maisVelho.getName(), idade));
        }

        funcionarios.stream().sorted(Comparator.comparing(Funcionario::getName))
                .forEach(f -> System.out.println(String.format(localeBR,
                        "Nome: %s, Data de Nascimento: %s, Salário: %,.2f, Função: %s", f.getName(),
                        f.getDataNascimento().format(dateFormatter), f.getSalario(), f.getCargo())));

        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println(String.format(localeBR, "Total dos salários: %,.2f", totalSalarios));

        BigDecimal salarioMinimo = new BigDecimal("1212.00");

        for (Funcionario f : funcionarios) {
            BigDecimal salariosMinimos = f.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
            System.out
                    .println(String.format(localeBR, "Nome: %s, Salários Mínimos: %.2f", f.getName(), salariosMinimos));
        }
    }
}