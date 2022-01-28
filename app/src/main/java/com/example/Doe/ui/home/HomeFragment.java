package com.example.Doe.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.Doe.R;
import com.example.Doe.adapter.AdapterItem;
import com.example.Doe.models.Doacao;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    ListView lista;
    ArrayList<Doacao> listaDoacoes;
    ArrayAdapter<Doacao> adapter;

    FirebaseAuth mAuth;
    FirebaseFirestore db;

    int selected = -1;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        lista = root.findViewById(R.id.ListaCasosHome);
        listaDoacoes = new ArrayList<Doacao>();
        loadData(listaDoacoes);



        adapter = new AdapterItem(getContext(), R.layout.item_list, listaDoacoes);

        lista.setAdapter(adapter);


        lista.setSelector(R.color.color_background_item_list );



        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selected = position;


                Intent telaDathe = new Intent(getActivity(), TelaDetalhada.class);


                telaDathe.putExtra("titulo", listaDoacoes.get(selected).getTitulo());
                telaDathe.putExtra("descricao", listaDoacoes.get(selected).getDescricao());
                telaDathe.putExtra("etiqueta", listaDoacoes.get(selected).getEtiqueta());
                telaDathe.putExtra("id", listaDoacoes.get(selected).getIdDoacao());

                startActivity(telaDathe);
            }

        });


        return root;
    }

    private void loadData(ArrayList<Doacao> list){
        list.clear();

        db.collection("doacoes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (QueryDocumentSnapshot document : task.getResult()) {

                            Doacao doacao = document.toObject(Doacao.class);

                            list.add(doacao);
                            adapter.notifyDataSetChanged();
                        }


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