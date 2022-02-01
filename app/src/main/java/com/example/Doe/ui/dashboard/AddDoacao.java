package com.example.Doe.ui.dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.Doe.R;
import com.example.Doe.constantes.Constantes;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class AddDoacao extends AppCompatActivity {

    TextView edTitulo;
    TextView edDescricao;
    TextView edEtiqueta;
    String idDoacoes;

    boolean isEditar = false;

    FusedLocationProviderClient cliente;
    String longitude;
    String latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_caso);

        cliente = LocationServices.getFusedLocationProviderClient(this);

        edTitulo = findViewById(R.id.titulo);
        edDescricao = findViewById(R.id.descricao);
        edEtiqueta = findViewById(R.id.edTextEtiqueta);



        if (getIntent().getExtras() != null) {

            String titulo = getIntent().getExtras().get("titulo").toString();
            String descricao = getIntent().getExtras().get("descricao").toString();
            String etiqueta = getIntent().getExtras().get("etiqueta").toString();
            idDoacoes = getIntent().getExtras().get("idDoacoes").toString();

            edTitulo.setText(titulo);
            edDescricao.setText(descricao);
            edEtiqueta.setText(etiqueta);

            isEditar = true;

        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        int statusCode = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);

        switch (statusCode) {
            case ConnectionResult.SERVICE_DISABLED:
                GoogleApiAvailability.getInstance().getErrorDialog(this, statusCode, 0,
                        new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialog) {
                                finish();
                            }
                        }
                ).show();
                break;
            case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED:
                GoogleApiAvailability.getInstance().getErrorDialog(this, statusCode, 0,
                        new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialog) {
                                finish();
                            }
                        }
                ).show();
                break;
            case ConnectionResult.SERVICE_MISSING:
                GoogleApiAvailability.getInstance().getErrorDialog(this, statusCode, 0,
                        new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialog) {
                                finish();
                            }
                        }
                ).show();
                break;
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        cliente.getLastLocation()
            .addOnSuccessListener(new OnSuccessListener<Location>(){
            @Override
            public void onSuccess(Location location) {
                longitude = String.valueOf(location.getLongitude());
                latitude = String.valueOf(location.getLatitude());


                Log.d("teste", location.getLatitude() + " " + location.getLongitude());
            }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("erro", "erro");
                }
            });

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

        intent.putExtra("titulo", titulo);
        intent.putExtra("descricao", descricao);
        intent.putExtra("etiqueta", etiqueta);
        intent.putExtra("longitude", longitude);
        intent.putExtra("latitude", latitude);

        if(isEditar){
            intent.putExtra("idDoacoes", idDoacoes);
        }

        setResult(Constantes.RESULT_ADD, intent);
        finish();
    }


}