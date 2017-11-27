package br.edu.ifpe.tads.pdm.book_tea;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import br.edu.ifpe.tads.pdm.book_tea.fragments.ListaAmigosFragment;
import br.edu.ifpe.tads.pdm.book_tea.fragments.LivroFragment;

public class FriendListActivity extends AppCompatActivity {
    private Toolbar mainToolbar;
    private ImageButton search;
    private ImageButton add;
    private FirebaseAuth mAuth;
    private FirebaseAuthListener authListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);

        mainToolbar= (Toolbar) findViewById(R.id.toolbar_main);
        mainToolbar.setTitle("Book&Tea");
        mainToolbar.setSubtitle("Para amantes da leitura");
        setSupportActionBar(mainToolbar);


        if(getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }



        ListaAmigosFragment fragment = (ListaAmigosFragment) getSupportFragmentManager().findFragmentByTag("fragment_main");
        if (fragment == null) {
            fragment = new ListaAmigosFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.rl_fragment_container, fragment, "fragment_main");
            fragmentTransaction.commit();
        }

        search = (ImageButton) findViewById(R.id.search);
        add = (ImageButton) findViewById(R.id.add);

        search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(FriendListActivity.this, "Implementação_Futura", Toast.LENGTH_SHORT).show();
            }
        });

        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(FriendListActivity.this, "Implementação_Futura", Toast.LENGTH_SHORT).show();
            }
        });

        this.mAuth = FirebaseAuth.getInstance();
        this.authListener = new FirebaseAuthListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()== android.R.id.home)
        finish();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(authListener);
    }
}
