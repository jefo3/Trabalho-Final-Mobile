package com.example.Doe.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Doe.R;
import com.example.Doe.ui.Casos;
import com.example.Doe.ui.login.Cadastrar;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {

    TextView edTextEmail;
    TextView edTextSenha;

    FirebaseAuth mAuth;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edTextEmail = findViewById(R.id.edTextEmail);
        edTextSenha = findViewById(R.id.edTextSenha);

        mAuth = FirebaseAuth.getInstance();

        db = FirebaseFirestore.getInstance();
    }

    public void TelaCadastro(View view){
        Intent telaCastro = new Intent(this, Cadastrar.class);
        startActivity(telaCastro);
    }

    public void TelaPrincipal(View view) {
        String email = edTextEmail.getText().toString();
        String senha = edTextSenha.getText().toString();

        if (TextUtils.isEmpty(email) && TextUtils.isEmpty(senha)) {
            Toast.makeText(this, "Email ou Senha vazios", Toast.LENGTH_LONG).show();
        } else {
            mAuth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        db.collection("users")
                                .whereEqualTo("email", mAuth.getCurrentUser().getEmail())
                                .get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(getApplicationContext(), "LOGIN COM SUCESSO", Toast.LENGTH_LONG).show();

                                            Intent telaPrincipal = new Intent(getApplicationContext(), Casos.class);
                                            startActivity(telaPrincipal);
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("----", "Error adding document", e);
                                Toast.makeText(
                                        getApplication(),
                                        "Erro: " + e.getMessage(),
                                        Toast.LENGTH_LONG
                                ).show();
                            }
                        });

                    } else {
                        Toast.makeText(getApplicationContext(), "Email ou Senha incorreto", Toast.LENGTH_LONG).show();
                    }
                }
            });


        }
    }

}