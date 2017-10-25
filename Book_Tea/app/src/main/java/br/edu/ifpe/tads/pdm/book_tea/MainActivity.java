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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainToolbar= (Toolbar) findViewById(R.id.toolbar_main);
        mainToolbar.setTitle("Book&Tea");
        mainToolbar.setSubtitle("Para amantes da leitura");
        //mainToolbar.setLogo(R.drawable.logo_book_tea_min);
        setSupportActionBar(mainToolbar);

        button = (Button) findViewById(R.id.button);
                button.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View view){
                                Intent i = new Intent(MainActivity.this, FriendListActivity.class);
                                startActivity(i);
             }
         });

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
                        new ProfileDrawerItem().withName("David Borges").withEmail("davidborges@gmail.com").withIcon(getResources().getDrawable(R.drawable.avatar_m))
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
        navigationDrawer.addItem(new SectionDrawerItem().withName("Ajuda! Fale conosco"));


    }

    //Metodo de teste para modelo navegacional
//    public List<Livro> listBookSimulator(int quantidade){
//        String[] autores= new String[] {"Autor A"," Autor B","Autor C"};
//        String[] titulos= new String[] {"titulo A","titulo B","titulo C"};
//        int[] imagens= new int[]{R.drawable.book_icon};
//        List<Livro> lista= new ArrayList<>();
//
//        for(int i=0; i< quantidade; i++){
//            Livro livro= new Livro (titulos[i % titulos.length], autores[i % autores.length], imagens[0]);
//        }
//
//        return lista;
//    }
}
