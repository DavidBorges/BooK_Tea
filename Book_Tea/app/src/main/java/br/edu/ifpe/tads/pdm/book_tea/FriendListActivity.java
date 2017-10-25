package br.edu.ifpe.tads.pdm.book_tea;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import br.edu.ifpe.tads.pdm.book_tea.fragments.ListaAmigosFragment;
import br.edu.ifpe.tads.pdm.book_tea.fragments.LivroFragment;

public class FriendListActivity extends AppCompatActivity {
    private Toolbar mainToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);

        mainToolbar= (Toolbar) findViewById(R.id.toolbar_main);
        mainToolbar.setTitle("Book&Tea");
        mainToolbar.setSubtitle("Para amantes da leitura");
        mainToolbar.setLogo(R.drawable.logo_book_tea_min);
        setSupportActionBar(mainToolbar);

        ListaAmigosFragment fragment = (ListaAmigosFragment) getSupportFragmentManager().findFragmentByTag("fragment_main");
        if (fragment == null) {
            fragment = new ListaAmigosFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.rl_fragment_container, fragment, "fragment_main");
            fragmentTransaction.commit();
        }
    }
}
