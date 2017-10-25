package br.edu.ifpe.tads.pdm.book_tea.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import br.edu.ifpe.tads.pdm.book_tea.R;


public class ListaAmigosFragment extends Fragment {


    public ListaAmigosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_livro, container, false);

        String[] menuAmigos = {"Amigo 1", "Amigo 2", "Amigo 3"};

        ListView listView = (ListView) view.findViewById(R.id.mainmenu);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, menuAmigos);

        listView.setAdapter(listViewAdapter);

        return view;

    }


}