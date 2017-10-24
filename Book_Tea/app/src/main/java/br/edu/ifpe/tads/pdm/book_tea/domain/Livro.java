package br.edu.ifpe.tads.pdm.book_tea.domain;


public class Livro {

    private String autor;
    private String titulo;
    private int imagem;

    public Livro(){

    }

    public Livro(String autor, String titulo, int img){
        this.autor= autor;
        this.titulo= titulo;
        this.imagem= img;
    }

    public int getImagem() {
        return imagem;
    }

    public String getAutor() {
        return autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
