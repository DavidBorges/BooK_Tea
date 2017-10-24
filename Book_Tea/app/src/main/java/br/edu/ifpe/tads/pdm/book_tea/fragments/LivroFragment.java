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
import android.widget.Toast;

import br.edu.ifpe.tads.pdm.book_tea.adapters.LivroAdapter;
import br.edu.ifpe.tads.pdm.book_tea.domain.Livro;
import java.util.List;

import br.edu.ifpe.tads.pdm.book_tea.R;
import br.edu.ifpe.tads.pdm.book_tea.MainActivity;
import br.edu.ifpe.tads.pdm.book_tea.interfaces.RV_OnClickListener;

public class LivroFragment extends Fragment implements RV_OnClickListener{

    private RecyclerView mRecyclerView;
    private List<Livro> mList;
    private OnFragmentInteractionListener mListener;

    public LivroFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_livro, container, false);

        mRecyclerView= (RecyclerView) view.findViewById(R.id.rv_lista);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager llm = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                LivroAdapter adapter = (LivroAdapter) mRecyclerView.getAdapter();

                if (mList.size() == llm.findLastCompletelyVisibleItemPosition() + 1) {
                    List<Livro> listAux = ((MainActivity) getActivity()).listBookSimulator(10);

                    for (int i = 0; i < listAux.size(); i++) {
                        adapter.addListItem(listAux.get(i), mList.size());
                    }
                }
            }
        });

        LinearLayoutManager lm_linear= new LinearLayoutManager(getActivity());
        lm_linear.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(lm_linear);

        mList = ((MainActivity) getActivity()).listBookSimulator(10);
        LivroAdapter adapter = new LivroAdapter(getActivity(), mList);
        adapter.setRV_OnClickListener(this);
        mRecyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onClickListener(View view, int position) {
        Toast.makeText(getActivity(), "Position: "+position, Toast.LENGTH_SHORT).show();

        LivroAdapter adapter = (LivroAdapter) mRecyclerView.getAdapter();
        adapter.removeItemLista(position);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
