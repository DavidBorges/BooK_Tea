package br.edu.ifpe.tads.pdm.book_tea.domain;


    public class Livro {
        private String titulo;
        private String autor;
        private String anoPublicacao;
        private String editora;


        public Livro (){}

        public Livro(String titulo, String autor, String anoPublicacao, String editora){
            this.anoPublicacao = anoPublicacao;
            this.autor = autor;
            this.editora = editora;
            this.titulo = titulo;
        }

        public String getTitulo() {
            return titulo;
        }

        public String getAutor() {
            return autor;
        }

        public String getAnoPublicacao() {
            return anoPublicacao;
        }

        public String getEditora() {
            return editora;
        }
    }
