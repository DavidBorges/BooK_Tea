package br.edu.ifpe.tads.pdm.book_tea;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import br.edu.ifpe.tads.pdm.book_tea.domain.Livro;
import java.util.List;
import java.util.ArrayList;

import br.edu.ifpe.tads.pdm.book_tea.R;
import br.edu.ifpe.tads.pdm.book_tea.fragments.LivroFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar mainToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainToolbar= (Toolbar) findViewById(R.id.toolbar_main);
        mainToolbar.setTitle("Book&Tea");
        mainToolbar.setSubtitle("Para amantes da leitura");
        mainToolbar.setLogo(R.drawable.logo_book_tea_min);
        setSupportActionBar(mainToolbar);

        //criar Fragment
        LivroFragment fragment= (LivroFragment) getSupportFragmentManager().findFragmentByTag("fragment_main");
        if(fragment==null){
            fragment= new LivroFragment();
            FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.rl_fragment_container,fragment, "fragment_main");
            fragmentTransaction.commit();
        }
    }

    //Metodo de teste para modelo navegacional
    public List<Livro> listBookSimulator(int quantidade){
        String[] autores= new String[] {"Autor A"," Autor B","Autor C"};
        String[] titulos= new String[] {"titulo A","titulo B","titulo C"};
        int[] imagens= new int[]{R.drawable.book_icon};
        List<Livro> lista= new ArrayList<>();

        for(int i=0; i< quantidade; i++){
            Livro livro= new Livro (titulos[i % titulos.length], autores[i % autores.length], imagens[0]);
        }

        return lista;
    }
}
