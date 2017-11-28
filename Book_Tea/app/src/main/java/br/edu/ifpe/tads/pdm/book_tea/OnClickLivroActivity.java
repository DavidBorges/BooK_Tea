package br.edu.ifpe.tads.pdm.book_tea;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OnClickLivroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_click_livro);

        TextView txtTitulo= (TextView) findViewById(R.id.tituloLivro);
        TextView txtAutor= (TextView) findViewById(R.id.autorLivro);

        txtTitulo.setText(getIntent().getStringExtra("Titulo"));
        txtAutor.setText(getIntent().getStringExtra("Autor"));
    }
}
