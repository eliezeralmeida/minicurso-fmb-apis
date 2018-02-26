package com.minicurso.eliezer.app.feature;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ListViewPessoaAdapter extends ArrayAdapter<PessoaApi> {
    private Context context;
    private List<PessoaApi> pessoas;

    public ListViewPessoaAdapter(Context context, List<PessoaApi> objects) {
        super(context, -1, objects);
        this.context = context;
        this.pessoas = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.listview_pessoas_row, parent, false);
        TextView textNome = rowView.findViewById(R.id.nomePessoaID);
        TextView textEmail = rowView.findViewById(R.id.emailPessoaID);

        PessoaApi current = pessoas.get(position);

        textNome.setText(current.nome);
        textEmail.setText(current.email);

        return rowView;
    }
}
