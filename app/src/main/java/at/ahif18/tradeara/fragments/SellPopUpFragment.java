package at.ahif18.tradeara.fragments;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import at.ahif18.tradeara.R;
import at.ahif18.tradeara.data.Stock;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SellPopUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SellPopUpFragment extends DialogFragment {

    private static final String TAG = "SellPopUpFragment";
    private static final String ARG_PARAM_STOCK = "stock";

    private Stock stock;
    private String stockName;
    private String stockSymbol;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SellPopUpFragment() {
        // Required empty public constructor
    }


    public static SellPopUpFragment newInstance(Stock stock) {
        SellPopUpFragment fragment = new SellPopUpFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_PARAM_STOCK, stock);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            stock = getArguments().getParcelable(ARG_PARAM_STOCK);
        }

        stockName = stock.getName();
        stockSymbol = stock.getSymbol();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sell_pop_up, container, false);
    }

    @Override
    public String toString() {
        return TAG;
    }
}