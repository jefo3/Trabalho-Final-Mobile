package com.example.Doe.ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.Doe.R;
import com.example.Doe.constantes.Constantes;

public class AddDoacao extends AppCompatActivity {

    TextView edTitulo;
    TextView edDescricao;
    TextView edEtiqueta;
    TextView edLongitude;
    TextView edLatitude;
    TextView edData;

    String idDoacoes;

    boolean isEditar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_caso);

        edTitulo = findViewById(R.id.titulo);
        edDescricao = findViewById(R.id.descricao);
        edEtiqueta = findViewById(R.id.edTextEtiqueta);
        edLongitude = findViewById(R.id.edTextLongitude);
        edLatitude = findViewById(R.id.edTextLatitude);
        edData = findViewById(R.id.edTextDate);

        if(getIntent().getExtras() != null) {

            String titulo = getIntent().getExtras().get("titulo").toString();
            String descricao = getIntent().getExtras().get("descricao").toString();
            String etiqueta = getIntent().getExtras().get("etiqueta").toString();
            String longitude = getIntent().getExtras().get("longitude").toString();
            String latitude = getIntent().getExtras().get("latitude").toString();
            String data = getIntent().getExtras().get("data").toString();
            idDoacoes = getIntent().getExtras().get("idDoacoes").toString();

            edTitulo.setText(titulo);
            edDescricao.setText(descricao);
            edEtiqueta.setText(etiqueta);
            edLongitude.setText(longitude);
            edLatitude.setText(latitude);
            edData.setText(data);

            edLatitude.setEnabled(false);
            edLongitude.setEnabled(false);
            edData.setEnabled(false);

            isEditar = true;

        }
    }

    public void cancelar(View view){
        setResult(Constantes.RESULT_CANCEL);
        finish();
    }

    public void adicionar(View view){
        Intent intent = new Intent();

        String titulo = edTitulo.getText().toString();
        String descricao = edDescricao.getText().toString();
        String etiqueta = edEtiqueta.getText().toString();
        String longitude = edLongitude.getText().toString();
        String latitude = edLatitude.getText().toString();
        String data = edData.getText().toString();

        intent.putExtra("titulo", titulo);
        intent.putExtra("descricao", descricao);
        intent.putExtra("etiqueta", etiqueta);
        intent.putExtra("longitude", longitude);
        intent.putExtra("latitude", latitude);
        intent.putExtra("data", data);


        if(isEditar){
            intent.putExtra("idDoacoes", idDoacoes);
        }

        setResult(Constantes.RESULT_ADD, intent);
        finish();
    }


}