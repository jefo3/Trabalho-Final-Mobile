package com.example.Doe.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.Doe.ui.login.MainActivity;
import com.example.Doe.adapter.AdapterItem;
import com.example.Doe.constantes.Constantes;
import com.example.Doe.R;
import com.example.Doe.models.Doacao;
import com.example.Doe.models.RepositorioDoacaoes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class DashboardFragment extends Fragment {
    ListView lista;
    ArrayList<Doacao> doacoesList;
    ArrayAdapter<Doacao> adapter;

    FirebaseAuth mAuth;
    FirebaseFirestore db;

    int selected = -1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        lista = root.findViewById(R.id.ListaCasosDash);

        doacoesList = new ArrayList<Doacao>();

        adapter = new AdapterItem(getContext(), R.layout.item_list, doacoesList);

        lista.setAdapter(adapter);

        lista.setSelector(R.color.color_background_item_list);

        loadData(doacoesList);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selected = position;
            }
        });


        return root;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inf){
        inf.inflate(R.menu.menu_dashboard, menu);

        super.onCreateOptionsMenu(menu, inf);
    }



    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.add:
                adicionar();
                break;
            case R.id.editar:
                editar();
                break;
            case R.id.del:
                deletar();
                break;
            case R.id.sigout:
                signOut();
                break;
        }

        return true;
    }

    private void adicionar(){
        Intent telaAddCaso = new Intent(getContext(), AddDoacao.class);
        startActivityForResult(telaAddCaso, Constantes.REQUEST_ADD);
    }

    private void editar(){
        Intent telaAddCaso = new Intent(getContext(), AddDoacao.class);

        telaAddCaso.putExtra("titulo", doacoesList.get(selected).getTitulo());
        telaAddCaso.putExtra("descricao", doacoesList.get(selected).getDescricao());
        telaAddCaso.putExtra("etiqueta", doacoesList.get(selected).getEtiqueta());
        telaAddCaso.putExtra("idDoacoes", doacoesList.get(selected).getIdDoacao());
        telaAddCaso.putExtra("data", doacoesList.get(selected).getData());
        telaAddCaso.putExtra("longitude", doacoesList.get(selected).getLongitude());
        telaAddCaso.putExtra("latitude", doacoesList.get(selected).getLatitude());

        startActivityForResult(telaAddCaso, Constantes.REQUEST_EDIT);
    }

    private void deletar(){
        if(selected >= 0){

            Doacao doacao = doacoesList.get(selected);


            db.collection("doacoes").document(doacao.getIdDoacao())
                    .delete()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            loadData(doacoesList);
                            Toast.makeText(
                                    getContext(),
                                    "Deletado com sucesso",
                                    Toast.LENGTH_LONG
                            ).show();

                            //adapter.notifyDataSetChanged();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(
                                    getContext(),
                                    "Erro: " + e.getMessage(),
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                    });

                selected = -1;

        }
    }

    private void signOut(){
        mAuth.signOut();
        Intent telaInicial = new Intent(getContext(), MainActivity.class);
        startActivity(telaInicial);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == Constantes.REQUEST_ADD && resultCode == Constantes.RESULT_ADD){
            String titulo = data.getExtras().get("titulo").toString();
            String descricao = data.getExtras().get("descricao").toString();
            String etiqueta = data.getExtras().get("etiqueta").toString();
            String longitude = data.getExtras().get("longitude").toString();
            String latitude = data.getExtras().get("latitude").toString();
            String date = data.getExtras().get("data").toString();

            Doacao doacoes = new Doacao(titulo, descricao, etiqueta, longitude, latitude, date, mAuth.getUid());

            doacoesList.add(doacoes);

            db.collection("doacoes")
                    .add(doacoes)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            doacoes.setIdDoacao(documentReference.getId());
                            documentReference.set(doacoes);
                            loadData(doacoesList);

                            Toast.makeText(
                                    getContext(),
                                    "inserido com sucesso",
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(
                                    getContext(),
                                    "" + e.getMessage(),
                                    Toast.LENGTH_LONG
                            ).show();;
                        }
                    });


            adapter.notifyDataSetChanged();

        }else if(requestCode == Constantes.REQUEST_EDIT && resultCode == Constantes.RESULT_ADD){
            String titulo = data.getExtras().get("titulo").toString();
            String descricao = data.getExtras().get("descricao").toString();
            String etiqueta = data.getExtras().get("etiqueta").toString();

            String idDoacao = data.getExtras().get("idDoacoes").toString();




            HashMap<String, Object> updateDoacao = new HashMap<>();
            updateDoacao.put("titulo", titulo);
            updateDoacao.put("descricao", descricao);
            updateDoacao.put("etiqueta", etiqueta);


            db.collection("doacoes").document(idDoacao).update(updateDoacao)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            loadData(doacoesList);
                            Toast.makeText(
                                    getContext(),
                                    "Editado com sucesso",
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            
                        }
                    });


            adapter.notifyDataSetChanged();
        }



    }


    private void loadData(ArrayList<Doacao> list){
        list.clear();

        db.collection("doacoes")
                .whereEqualTo("idUsuario", mAuth.getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (QueryDocumentSnapshot document : task.getResult()) {

                            Doacao doacao = document.toObject(Doacao.class);

                            list.add(doacao);
                            adapter.notifyDataSetChanged();
                        }

                        adapter.notifyDataSetChanged();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(
                                getContext(),
                                "Erro: " + e.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                });
    }






}