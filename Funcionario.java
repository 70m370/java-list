package Desafio;

//imports
import java.math.BigDecimal;
import java.time.LocalDate;

public class Funcionario extends Pessoa {
    private String cargo;
    private BigDecimal salario;

    // funcionario constructor
    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String cargo) {
        super(nome, dataNascimento);// super class constructor
        this.cargo = cargo;
        this.salario = salario;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void changeSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }

    public void changecargo(String newCargo) {
        this.cargo = newCargo;
    }

}
