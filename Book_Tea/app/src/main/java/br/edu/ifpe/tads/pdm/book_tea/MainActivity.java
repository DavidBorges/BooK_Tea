package br.edu.ifpe.tads.pdm.book_tea;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import br.edu.ifpe.tads.pdm.book_tea.domain.Livro;
import java.util.List;
import java.util.ArrayList;

import br.edu.ifpe.tads.pdm.book_tea.fragments.LivroFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar mainToolbar;
    private Button button;
    private Drawer.Result navigationDrawer;
    private AccountHeader.Result headerNavigation;
    private FirebaseAuth mAuth;
    private FirebaseAuthListener authListener;
    private FirebaseUser mUser;

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

        //criar Fragment
        LivroFragment fragment= (LivroFragment) getSupportFragmentManager().findFragmentByTag("fragment_main");
        if(fragment==null){
            fragment= new LivroFragment();
            FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.rl_fragment_container,fragment, "fragment_main");
            fragmentTransaction.commit();
        }

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

        navigationDrawer.addItem(new PrimaryDrawerItem().withName("Página inicial").withIcon(getResources().getDrawable(R.drawable.homepage)));
        navigationDrawer.addItem(new DividerDrawerItem());
        navigationDrawer.addItem(new PrimaryDrawerItem().withName("Opção B").withIcon(getResources().getDrawable(R.drawable.logo_book_tea_min)));
        navigationDrawer.addItem(new PrimaryDrawerItem().withName("Opção C").withIcon(getResources().getDrawable(R.drawable.logo_book_tea_min)));
        navigationDrawer.addItem(new DividerDrawerItem());
        navigationDrawer.addItem(new PrimaryDrawerItem().withName("Configuraões").withIcon(getResources().getDrawable(R.drawable.configs)));
        navigationDrawer.addItem(new PrimaryDrawerItem().withName("Ajuda! Fale conosco").withIcon(getResources().getDrawable(R.drawable.help)));
        navigationDrawer.addItem(new PrimaryDrawerItem().withName("Sair").withIcon(getResources().getDrawable(R.drawable.logo_book_tea_min)));

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
