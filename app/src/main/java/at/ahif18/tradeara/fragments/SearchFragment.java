package at.ahif18.tradeara.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.HashMap;

import at.ahif18.tradeara.MainActivity;
import at.ahif18.tradeara.R;
import at.ahif18.tradeara.bl.StockAdapter;
import at.ahif18.tradeara.bl.StockGetter;
import at.ahif18.tradeara.data.Stock;
import at.ahif18.tradeara.data.StockManager;

public class SearchFragment extends Fragment {

    private static final String TAG = "SearchFragment";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private static MainActivity mainActivity;

    public SearchFragment(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment(mainActivity);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    private RecyclerView rvListSearchStock;
    private SearchView svSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        rvListSearchStock = view.findViewById(R.id.rvStockSearchItem);
        rvListSearchStock.setHasFixedSize(true);
        rvListSearchStock.setLayoutManager(new LinearLayoutManager(mainActivity));

        StockAdapter stockAdapter = new StockAdapter(mainActivity);
        rvListSearchStock.setAdapter(stockAdapter);

        svSearch = view.findViewById(R.id.svSearch);

        svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                stockAdapter.filter(newText);
                return false;
            }
        });

        System.out.println(StockManager.getInstance().getMap());

        return view;
    }

}