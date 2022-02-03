package com.example.Doe.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Doe.R;
import com.example.Doe.models.Doacao;
import com.example.Doe.models.Usuario;
import com.example.Doe.ui.mapa.localizacao;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class TelaDetalhada extends AppCompatActivity {
    TextView edTextTitulo;
    TextView edTextDescricao;
    TextView edTextEtiqueta;
    String latitude;
    String longitude;
    String idUsuario;

    FirebaseAuth mAuth;
    FirebaseFirestore db;
    String numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_detalhada);

        edTextTitulo = findViewById(R.id.titulo);
        edTextDescricao = findViewById(R.id.descricao);
        edTextEtiqueta = findViewById(R.id.etiqueta);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        if(getIntent().getExtras() != null) {

            String titulo = getIntent().getExtras().get("titulo").toString();
            String descricao = getIntent().getExtras().get("descricao").toString();
            String etiqueta = getIntent().getExtras().get("etiqueta").toString();
            latitude = getIntent().getExtras().get("latitude").toString();
            longitude = getIntent().getExtras().get("longitude").toString();
            idUsuario = getIntent().getExtras().get("idUsuario").toString();

            edTextTitulo.setText(titulo);
            edTextDescricao.setText(descricao);
            edTextEtiqueta.setText(etiqueta);



        }

        numeroCelular();

    }

    public void numeroCelular(){

        db.collection("usuarios")
                .whereEqualTo("id",idUsuario)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {


                        for (QueryDocumentSnapshot document : task.getResult()) {

                            Usuario usuario = document.toObject(Usuario.class);

                            numero = usuario.getTelefone();

                        }


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });

    }

    public void ajudar(View view){


        Intent wpp = new Intent("android.intent.action.MAIN");
        wpp.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
        wpp.putExtra("jid", PhoneNumberUtils.stripSeparators("55"+numero) + "@s.whatsapp.net");


        startActivity(wpp);
    }

    public void mapa(View view){
        Intent mapa = new Intent(this, localizacao.class);

        mapa.putExtra("latitude", latitude);
        mapa.putExtra("longitude", longitude);

        startActivity(mapa);
    }
}