package at.ahif18.tradeara.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import at.ahif18.tradeara.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BuySellFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuySellFragment extends BottomSheetDialogFragment {

    private static final String TAG = "BuySellFragment";
    private static final String ARG_PARAM_NAME = "stockName";
    private static final String ARG_PARAM_SYMBOL = "stockSymbol";

    private String stockName;
    private String stockSymbol;

    public BuySellFragment() {
        // Required empty public constructor
    }


    
    public static BuySellFragment newInstance(String stockName, String stockSymbol) {
        BuySellFragment fragment = new BuySellFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_NAME, stockName);
        args.putString(ARG_PARAM_SYMBOL, stockSymbol);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            stockName = getArguments().getString(ARG_PARAM_NAME);
            stockSymbol = getArguments().getString(ARG_PARAM_SYMBOL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_buy_sell, container, false);

        TextView tvStockName = view.findViewById(R.id.tvStockName);
        TextView tvStockSymbol = view.findViewById(R.id.tvStockSymbol);

        tvStockName.setText(stockName);
        tvStockSymbol.setText(stockSymbol);

        return view;
    }

    public boolean allowBackPress(){
        return true;
    }
}