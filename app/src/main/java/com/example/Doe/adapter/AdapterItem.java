package com.example.Doe.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.Doe.R;
import com.example.Doe.models.Doacao;

import java.util.ArrayList;

public class AdapterItem extends ArrayAdapter<Doacao> {
    private Context context;
    int layoutId;
    public AdapterItem(Context context, int layoutId, ArrayList<Doacao> itens) {
        super(context, layoutId, itens);
        this.context = context;
        this.layoutId = layoutId;
    }
    @NonNull
    @Override
    public View getView(int position, @NonNull View view, @NonNull ViewGroup parent) {
        Doacao item = getItem(position);
        String titulo = item.getTitulo();
        String descricao = item.getDescricao();
        System.out.println("titulo : " + titulo);
        System.out.println("descr : " + descricao);
        if (view == null) {
            view = LayoutInflater.from(this.context).inflate(layoutId,parent,false);
        }

        TextView edTitulo =  view.findViewById(R.id.edTextTitulo);
        TextView edDescricao = view.findViewById(R.id.edTextDescrição);

        edTitulo.setText(titulo);
        edDescricao.setText(descricao);

        return view;
    }
}