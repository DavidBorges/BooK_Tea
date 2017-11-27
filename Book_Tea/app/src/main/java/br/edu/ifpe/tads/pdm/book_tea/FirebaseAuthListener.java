package br.edu.ifpe.tads.pdm.book_tea;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.edu.ifpe.tads.pdm.book_tea.MainActivity;
import br.edu.ifpe.tads.pdm.book_tea.SignInActivity;


public class FirebaseAuthListener implements FirebaseAuth.AuthStateListener {
    private final Activity activity;

    public FirebaseAuthListener(Activity activity){
        this.activity = activity;
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth){
        FirebaseUser user = firebaseAuth.getCurrentUser();

        Intent intent = null;
        if((user != null) && !(activity instanceof MainActivity)){
            intent = new Intent(activity, MainActivity.class);
        }

        if((user == null) && (activity instanceof MainActivity)){
            intent = new Intent(activity, SignInActivity.class);
        }

        if (intent != null){
            activity.startActivity(intent);
            activity.finish();
        }
    }
}
