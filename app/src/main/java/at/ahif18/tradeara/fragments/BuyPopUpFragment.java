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
 * Use the {@link BuyPopUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuyPopUpFragment extends DialogFragment {

    private Stock stock;


    public BuyPopUpFragment(Stock stock) {
        this.stock = stock;
    }

    public static BuyPopUpFragment newInstance(Stock stock) {
        BuyPopUpFragment fragment = new BuyPopUpFragment(stock);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buy_pop_up, container, false);

        return view;
    }
}