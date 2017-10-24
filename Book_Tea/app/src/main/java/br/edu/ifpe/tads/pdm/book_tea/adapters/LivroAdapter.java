package br.edu.ifpe.tads.pdm.book_tea.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.edu.ifpe.tads.pdm.book_tea.R;
import br.edu.ifpe.tads.pdm.book_tea.domain.Livro;
import br.edu.ifpe.tads.pdm.book_tea.interfaces.RV_OnClickListener;


public class LivroAdapter extends RecyclerView.Adapter<LivroAdapter.viewHolder> {

    private List<Livro> listalivros;
    private LayoutInflater layoutInflater;
    private static RV_OnClickListener rv_onClickListener;

    public LivroAdapter(Context c, List<Livro> listalivros){
        this.listalivros = listalivros;
        this.layoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.titulos_layout, parent, false);
        viewHolder viewHolder= new viewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return listalivros.size();
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        viewHolder.capaLivro.setImageResource(listalivros.get(position).getImagem());
        viewHolder.tituloLivro.setText(listalivros.get(position).getTitulo());
        viewHolder.autorLivro.setText(listalivros.get(position).getAutor());

    }

    public void addListItem(Livro c, int position){
        listalivros.add(c);
        notifyItemInserted(position);
    }

    public void removeItemLista(int position) {
        listalivros.remove(position);
        notifyItemRemoved(position);
    }

    public void setRV_OnClickListener(RV_OnClickListener r){
        rv_onClickListener = r;
    }

    public static class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public static ImageView capaLivro;
        public static TextView tituloLivro;
        public static TextView autorLivro;

        public viewHolder(View itemView) {
            super(itemView);
            capaLivro = (ImageView) itemView.findViewById(R.id.capaLivro);
            tituloLivro = (TextView) itemView.findViewById(R.id.tituloLivro);
            autorLivro = (TextView) itemView.findViewById(R.id.autorLivro);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(rv_onClickListener != null){
                rv_onClickListener.onClickListener(view, getPosition());
            }
        }
    }
}
