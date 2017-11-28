package br.edu.ifpe.tads.pdm.book_tea;



public class Usuario {
    private String nome;
    private String email;
    private String idade;

    public Usuario(){}

    public Usuario(String nome, String email, String idade){
        this.nome = nome;
        this.email = email;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getIdade() {
        return idade;
    }
}
