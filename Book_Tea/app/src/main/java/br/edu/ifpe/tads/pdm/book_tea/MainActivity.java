package br.edu.ifpe.tads.pdm.book_tea;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import br.edu.ifpe.tads.pdm.book_tea.adapters.LivroAdapter;
import br.edu.ifpe.tads.pdm.book_tea.domain.Livro;
import java.util.List;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar mainToolbar;
    private Button button;
    private Drawer.Result navigationDrawer;
    private AccountHeader.Result headerNavigation;
    private FirebaseAuth mAuth;
    private FirebaseAuthListener authListener;
    private FirebaseUser mUser;
    private List<Livro> livroList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mAuth = FirebaseAuth.getInstance();
        this.authListener = new FirebaseAuthListener(this);

        mUser = mAuth.getCurrentUser();

        mainToolbar= (Toolbar) findViewById(R.id.toolbar_main);
        mainToolbar.setTitle("Book&Tea");
        mainToolbar.setSubtitle("Para amantes da leitura");
        //mainToolbar.setLogo(R.drawable.logo_book_tea_min);
        setSupportActionBar(mainToolbar);

        ListView listViewLivros =(ListView) findViewById(R.id.main_list_view);
        List<Livro> list = livrosListados();

        LivroAdapter adapterLivros = new LivroAdapter(this, R.layout.titulos_layout,livroList);
        listViewLivros.setAdapter(adapterLivros);
        listViewLivros.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent= new Intent();
                intent.putExtra("Titulo", livroList.get(position).getTitulo());
                intent.putExtra("Autor", livroList.get(position).getAutor());

                intent.setClass(MainActivity.this, OnClickLivroActivity.class);
                startActivity(intent);
            }
        });

        headerNavigation = new AccountHeader()
                .withActivity(this)
                .withCompactStyle(false)
                .withSavedInstance(savedInstanceState)
                .withThreeSmallProfileImages(true)
                .withHeaderBackground(R.drawable.bt_bg)
                .addProfiles(
                        new ProfileDrawerItem().withName(" ").withEmail(mUser.getEmail()).withIcon(getResources().getDrawable(R.drawable.avatar_m))
                )
                .build();

        navigationDrawer = new Drawer()
                .withActivity(this)
                .withToolbar(mainToolbar)
                .withDisplayBelowToolbar(true)
                .withActionBarDrawerToggleAnimated(true)
                .withDrawerGravity(Gravity.LEFT)
                .withSavedInstance(savedInstanceState)
                .withSelectedItem(0)
                .withActionBarDrawerToggle(true)
                .withAccountHeader(headerNavigation)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {
                        logout();
                    }
                })
                .withOnDrawerItemLongClickListener(new Drawer.OnDrawerItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {
                        Toast.makeText(MainActivity.this, "onItemLongClick: " + i, Toast.LENGTH_SHORT).show();
                        return false;
                    }
                })
                .build();

        navigationDrawer.addItem(new PrimaryDrawerItem().withIdentifier(1).withName("Página inicial").withIcon(getResources().getDrawable(R.drawable.homepage)));
        navigationDrawer.addItem(new DividerDrawerItem());
        navigationDrawer.addItem(new PrimaryDrawerItem().withIdentifier(2).withName("Livros").withIcon(getResources().getDrawable(R.drawable.logo_book_tea_min)));
        navigationDrawer.addItem(new PrimaryDrawerItem().withIdentifier(3).withName("Amigos").withIcon(getResources().getDrawable(R.drawable.logo_book_tea_min)));
        navigationDrawer.addItem(new DividerDrawerItem());
        navigationDrawer.addItem(new PrimaryDrawerItem().withIdentifier(4).withName("Configuraões").withIcon(getResources().getDrawable(R.drawable.configs)));
        navigationDrawer.addItem(new PrimaryDrawerItem().withIdentifier(5).withName("Ajuda! Fale conosco").withIcon(getResources().getDrawable(R.drawable.help)));
        navigationDrawer.addItem(new PrimaryDrawerItem().withIdentifier(6).withName("Sair").withIcon(getResources().getDrawable(R.drawable.logo_book_tea_min)));

    }

    private void logout() {
        if(navigationDrawer.equals(navigationDrawer.getPositionFromIdentifier(6))){
            buttonSignOutClick(getCurrentFocus());
        }
    }

    private List<Livro> livrosListados(){
        livroList= new ArrayList<>();
        livroList.add(new Livro("King M", "Canavial dos macacos", R.drawable.book_icon));
        livroList.add(new Livro("King M", "Canavial dos macacos 2", R.drawable.book_icon));
        livroList.add(new Livro("King M", "Canavial dos macacos 3", R.drawable.book_icon));
        livroList.add(new Livro("King M", "Canavial dos macacos 4", R.drawable.book_icon));
        livroList.add(new Livro("King M", "Canavial dos macacos 5", R.drawable.book_icon));
        livroList.add(new Livro("King M", "Canavial dos macacos 6", R.drawable.book_icon));

        return livroList;
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

    public void buttonSignOutClick(View view) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            mAuth.signOut();
            Intent i = new Intent(MainActivity.this, SignInActivity.class);
            startActivity(i);
            this.finish();
        } else {
            Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
        }
    }
}
