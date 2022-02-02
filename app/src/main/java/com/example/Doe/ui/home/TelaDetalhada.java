package com.example.Doe.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.TextView;

import com.example.Doe.R;
import com.example.Doe.ui.mapa.localizacao;

public class TelaDetalhada extends AppCompatActivity {
    TextView edTextTitulo;
    TextView edTextDescricao;
    TextView edTextEtiqueta;
    String latitude;
    String longitude;
    String idUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_detalhada);

        edTextTitulo = findViewById(R.id.titulo);
        edTextDescricao = findViewById(R.id.descricao);
        edTextEtiqueta = findViewById(R.id.etiqueta);

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

    }

    public void ajudar(View view){
        String numero = "8681638647";

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