package br.edu.ifpe.tads.pdm.book_tea.domain;


import java.util.ArrayList;

public class Usuario {
    private String nome;
    private String email;
    private String idade;
    private ArrayList<Livro> livros;

    public Usuario(){
        livros = new ArrayList<>();
    }

    public Usuario(String nome, String email, String idade, ArrayList<Livro> livros){
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.livros = livros;
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

    public ArrayList<Livro> getLivros() {
        return livros;
    }
}
