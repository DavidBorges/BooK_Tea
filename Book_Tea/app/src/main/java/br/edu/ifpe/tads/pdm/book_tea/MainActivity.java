package br.edu.ifpe.tads.pdm.book_tea;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
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
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpe.tads.pdm.book_tea.adapters.LivroAdapter;
import br.edu.ifpe.tads.pdm.book_tea.domain.Livro;
import br.edu.ifpe.tads.pdm.book_tea.domain.Usuario;


public class MainActivity extends AppCompatActivity {

    private Toolbar mainToolbar;
    private Drawer.Result navigationDrawer;
    private AccountHeader.Result headerNavigation;
    private FirebaseAuth mAuth;
    private FirebaseAuthListener authListener;
    private FirebaseUser mUser;
    private DatabaseReference drUsers;
    private Usuario user;
<<<<<<< HEAD
    private ArrayList<Livro> livroList;
    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3;
=======
    private ArrayList<Livro> loadBooks;
>>>>>>> 4c6c249f714b63066c6280dc9840cb65cfeefa55

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listViewLivros =(ListView) findViewById(R.id.main_list_view);

        this.mAuth = FirebaseAuth.getInstance();
        this.authListener = new FirebaseAuthListener(this);

        mUser = mAuth.getCurrentUser();
        drUsers = FirebaseDatabase.getInstance().getReference("users/");


        mainToolbar= (Toolbar) findViewById(R.id.toolbar_main);
        mainToolbar.setTitle("Book&Tea");
        mainToolbar.setSubtitle("Para amantes da leitura");
        setSupportActionBar(mainToolbar);

<<<<<<< HEAD
        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        floatingActionButton1 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item1);
        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item2);
=======
>>>>>>> 4c6c249f714b63066c6280dc9840cb65cfeefa55

        drUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    user = childSnapshot.getValue(Usuario.class);
                    if (user != null) {
                        if ((mAuth.getCurrentUser().getEmail()).equals(user.getEmail())) {
<<<<<<< HEAD
                            ArrayList<Livro> loadBooks = (ArrayList<Livro>) childSnapshot.child("/livros").getValue();
                            break;
=======
                            loadBooks = (ArrayList<Livro>) childSnapshot.child("/livros").getValue();
>>>>>>> 4c6c249f714b63066c6280dc9840cb65cfeefa55
                        }
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError){
            }
        });

        ArrayList<Livro> list = loadBooks;
        LivroAdapter adapterLivros = new LivroAdapter(this, R.layout.titulos_layout,list);
        listViewLivros.setAdapter(adapterLivros);

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

        navigationDrawer.addItem(new PrimaryDrawerItem().withIdentifier(1).withName("Página inicial").withIcon(getResources().getDrawable(R.drawable.homepage)));
        navigationDrawer.addItem(new DividerDrawerItem());
        navigationDrawer.addItem(new PrimaryDrawerItem().withIdentifier(2).withName("Livros").withIcon(getResources().getDrawable(R.drawable.logo_book_tea_min)));
        navigationDrawer.addItem(new PrimaryDrawerItem().withIdentifier(3).withName("Amigos").withIcon(getResources().getDrawable(R.drawable.logo_book_tea_min)));
        navigationDrawer.addItem(new DividerDrawerItem());
        navigationDrawer.addItem(new PrimaryDrawerItem().withIdentifier(4).withName("Configuraões").withIcon(getResources().getDrawable(R.drawable.configs)));
        navigationDrawer.addItem(new PrimaryDrawerItem().withIdentifier(5).withName("Ajuda! Fale conosco").withIcon(getResources().getDrawable(R.drawable.help)));
        navigationDrawer.addItem(new PrimaryDrawerItem().withIdentifier(6).withName("Sair").withIcon(getResources().getDrawable(R.drawable.logo_book_tea_min)));
    }

//    private ArrayList<Livro> livrosListados(){
//        livroList = new ArrayList<>();
//        livroList.add(new Livro("Canavial dos macacos","King M", "1996", "Rock"));
//        livroList.add(new Livro("Canavial dos macacos","King M", "1997", "Rock"));
//        livroList.add(new Livro("Canavial dos macacos","King M", "1998", "Rock"));
//        livroList.add(new Livro("Canavial dos macacos","King M", "1999", "Rock"));
//        livroList.add(new Livro("Canavial dos macacos","King M", "2001", "Rock"));
//        livroList.add(new Livro("Canavial dos macacos","King M", "2004", "Rock"));
//
//        return livroList;
//    }

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

    public void buttonBookRegister(View view){
        Intent i = new Intent(MainActivity.this, BookRegisterActivity.class);
        startActivity(i);
    }

<<<<<<< HEAD

=======
    public void loadBooks(){

    }
>>>>>>> 4c6c249f714b63066c6280dc9840cb65cfeefa55
}
