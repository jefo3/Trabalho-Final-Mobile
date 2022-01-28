package com.example.Doe.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Doe.R;
import com.example.Doe.models.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Cadastrar extends AppCompatActivity {

    TextView edNome;
    TextView edEmail;
    TextView edTelefone;
    TextView edSenha;

    FirebaseAuth mAuth;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        edNome = findViewById(R.id.edNome);
        edEmail = findViewById(R.id.edEmail);
        edTelefone = findViewById(R.id.edTelefone);
        edSenha = findViewById(R.id.edSenha);
    }

    public void cadastro(View view){
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        Usuario user = new Usuario();
        String nome = edNome.getText().toString();
        String email = edEmail.getText().toString();
        String telefone = edTelefone.getText().toString();
        String senha = edSenha.getText().toString();

        user.setNome(nome);
        user.setEmail(email);
        user.setTelefone(telefone);
        user.setSenha(senha);

        mAuth.createUserWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                user.setId(mAuth.getUid());
                db.collection("usuarios")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d("###", "DocumentSnapshot added with ID: " + documentReference.getId());
                                Toast.makeText(getApplication(), "Usuario "+user.getNome()+" criado com sucesso", Toast.LENGTH_LONG).show();
                                finish();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
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
                }
        });


    }

    public void Cancelar(View view ){
        finish();
    }
}