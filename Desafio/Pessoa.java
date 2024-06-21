package Desafio;

//imports
import java.time.LocalDate;

//classe pessoa
public class Pessoa {
    private String nome;
    private LocalDate dataNascimento;

    // class constructor
    public Pessoa(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public String getName() {
        return nome;
    }

    public void changeName(String newname) {
        this.nome = newname;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void changeDataNascimento(LocalDate newDataNascimento) {
        this.dataNascimento = newDataNascimento;
    }
}
