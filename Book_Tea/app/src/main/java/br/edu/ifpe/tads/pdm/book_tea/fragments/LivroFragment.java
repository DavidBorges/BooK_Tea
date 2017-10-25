package br.edu.ifpe.tads.pdm.book_tea.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import br.edu.ifpe.tads.pdm.book_tea.adapters.LivroAdapter;
import br.edu.ifpe.tads.pdm.book_tea.domain.Livro;
import java.util.List;

import br.edu.ifpe.tads.pdm.book_tea.R;
import br.edu.ifpe.tads.pdm.book_tea.MainActivity;
import br.edu.ifpe.tads.pdm.book_tea.interfaces.RV_OnClickListener;

public class LivroFragment extends Fragment{



    public LivroFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_livro, container, false);

        String[] menuLivros = {"Livro 1", "Livro 2", "Livro 3"};

        ListView listView = (ListView) view.findViewById(R.id.mainmenu);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, menuLivros);

        listView.setAdapter(listViewAdapter);
        return view;

        }


}
