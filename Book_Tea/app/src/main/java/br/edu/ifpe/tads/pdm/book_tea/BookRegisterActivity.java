package br.edu.ifpe.tads.pdm.book_tea;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BookRegisterActivity extends AppCompatActivity {
    EditText edTitulo;
    EditText edAnoPub;
    EditText edAutor;
    EditText edEditora;
    private FirebaseAuth mAuth;
    private FirebaseAuthListener authListener;
    private Usuario user;
    DatabaseReference drUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_register);

        drUsers = FirebaseDatabase.getInstance().getReference("users/");

        edTitulo = (EditText) findViewById(R.id.editTitulo);
        edAnoPub = (EditText) findViewById(R.id.editAnoPublicacao);
        edAutor = (EditText) findViewById(R.id.editAutor);
        edEditora = (EditText) findViewById(R.id.editEditora);

        this.mAuth = FirebaseAuth.getInstance();
        this.authListener = new FirebaseAuthListener(this);

        drUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    user = childSnapshot.getValue(Usuario.class);
                    if (user != null) {
                        if ((mAuth.getCurrentUser().getEmail()).equals(user.getEmail())) {
                            ArrayList<String> loadBooks = (ArrayList<String>) childSnapshot.child("/livros").getValue();
                        }
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError){
            }
        });
    }

    public void buttonBookRegisterClick(View view){
        Map<String, Object> childUpdates = new HashMap<>();

        final String nome = edTitulo.getText().toString();
        final String anoPub = edAnoPub.getText().toString();
        final String autor = edAutor.getText().toString();
        final String editora = edEditora.getText().toString();


        Livro tempBook = new Livro(nome, autor, anoPub, editora);
        user.getLivros().add(tempBook);

        childUpdates.put(mAuth.getCurrentUser().getUid()+"/livros", user.getLivros());
        drUsers.updateChildren(childUpdates);
        tempBook = null;
        Intent i = new Intent(BookRegisterActivity.this, MainActivity.class);
        startActivity(i);
    }
  }