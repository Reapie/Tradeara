package at.ahif18.tradeara.fragments;

import android.app.ActionBar;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import at.ahif18.tradeara.R;
import at.ahif18.tradeara.data.Stock;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BuyPopUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuyPopUpFragment extends DialogFragment {

    private static final String TAG = "BuyPopUpFragment";
    private static final String ARG_PARAM_STOCK = "stock";

    private Stock stock;
    private String stockName;
    private String stockSymbol;

    public BuyPopUpFragment() {

    }


    public static BuyPopUpFragment newInstance(Stock stock) {
        BuyPopUpFragment fragment = new BuyPopUpFragment();
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
        View view = inflater.inflate(R.layout.fragment_buy_pop_up, container, false);
        TextView tvBuyStockName = view.findViewById(R.id.tvBuyStockName);
        TextView tvBuyStockSymbol = view.findViewById(R.id.tvBuyStockSymbol);

        tvBuyStockName.setText(stockName);
        tvBuyStockSymbol.setText(stockSymbol);

        tvBuyStockName.setSelected(true);

        return view;
    }

    @Override
    public String toString() {
        return TAG;
    }
}