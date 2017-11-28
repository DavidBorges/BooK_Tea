package br.edu.ifpe.tads.pdm.book_tea;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.edu.ifpe.tads.pdm.book_tea.domain.Usuario;

public class SignUpActivity extends AppCompatActivity {
   // EditText edName = (EditText) findViewById(R.id.editName);
    EditText edEmail;
    EditText edPassword;
    EditText edIdade;
    EditText edNome;
    private FirebaseAuth mAuth;
    private FirebaseAuthListener authListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        edPassword = (EditText) findViewById(R.id.editPassword);
        edEmail = (EditText) findViewById(R.id.editEmail);
        edIdade = (EditText) findViewById(R.id.editIdade);
        edNome = (EditText) findViewById(R.id.editNome);

        this.mAuth = FirebaseAuth.getInstance();
        this.authListener = new FirebaseAuthListener(this);
    }

    public void buttonSignUpClick(View view){
        final String nome = edNome.getText().toString();
        final String email = edEmail.getText().toString();
        final String password = edPassword.getText().toString();
        final String idade = edIdade.getText().toString();

        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        String msg = task.isSuccessful() ? "SIGN UP OK!": "SIGN UP ERROR!";
                        Toast.makeText(SignUpActivity.this, msg, Toast.LENGTH_SHORT).show();
                        if (task.isSuccessful()) {
                            Usuario tempUser = new Usuario(nome, email, idade);
                            DatabaseReference drUsers = FirebaseDatabase.
                                    getInstance().getReference("users");
                            drUsers.child(mAuth.getCurrentUser().getUid()).
                                    setValue(tempUser);
                        }

                        Intent i = new Intent(SignUpActivity.this, SignInActivity.class);
                        startActivity(i);
                    }
                });


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
