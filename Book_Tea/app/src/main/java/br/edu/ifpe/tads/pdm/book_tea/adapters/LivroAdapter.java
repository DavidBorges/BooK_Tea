package br.edu.ifpe.tads.pdm.book_tea.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.edu.ifpe.tads.pdm.book_tea.R;
import br.edu.ifpe.tads.pdm.book_tea.domain.Livro;


public class LivroAdapter extends ArrayAdapter<Livro> {

    Context context;
    int layoutResourceId;
    List<Livro> livros= null;

    public LivroAdapter(Context context, int resource, List<Livro> objects) {
        super(context, resource,objects);
        this.context= context;
        this.layoutResourceId= resource;
        this.livros= objects;
    }

    @Override
    public int getCount() {
        return livros.size();
    }

    @Override
    public Livro getItem(int i) {
        return livros.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ItemHolder {

        ImageView imgIcon;
        TextView txtTitulo;
        TextView txtAutor;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemHolder holder;
        if (convertView == null) {
            LayoutInflater inflater= ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.titulos_layout, parent);

            //cria um item de suporte para não precisarmos sempre
            //inflar as mesmas informacoes
            holder = new ItemHolder();
            holder.imgIcon = ((ImageView) convertView.findViewById(R.id.capaLivro));
            holder.txtTitulo = ((TextView) convertView.findViewById(R.id.tituloLivro));
            holder.txtAutor = ((TextView) convertView.findViewById(R.id.autorLivro));

            convertView.setTag(holder);
        } else {
            holder = (ItemHolder) convertView.getTag();
        }
        Livro item = livros.get(position);
        holder.imgIcon.setImageResource(item.getImagem());
        holder.txtTitulo.setText(item.getTitulo());
        holder.txtAutor.setText(item.getAutor());

        return convertView;
    }
}
