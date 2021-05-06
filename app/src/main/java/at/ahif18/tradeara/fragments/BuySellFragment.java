package at.ahif18.tradeara.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import at.ahif18.tradeara.R;
import at.ahif18.tradeara.data.Stock;
import at.ahif18.tradeara.data.StockManager;


public class BuySellFragment extends BottomSheetDialogFragment {

    private static final String TAG = "BuySellFragment";
    private static final String ARG_PARAM_STOCK = "stock";

    private String stockName;
    private String stockSymbol;

    private Button btnBuy;
    private Button btnSell;

    private Stock stock;

    public BuySellFragment(Stock stock) {
        this.stock = stock;
    }


    
    public static BuySellFragment newInstance(Stock stock) {
        BuySellFragment fragment = new BuySellFragment(stock);
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM_STOCK, stock);
        fragment.setArguments(args);
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

        View view = inflater.inflate(R.layout.fragment_buy_sell, container, false);

        TextView tvStockName = view.findViewById(R.id.tvStockName);
        TextView tvStockSymbol = view.findViewById(R.id.tvStockSymbol);

        tvStockName.setText(stockName);
        tvStockSymbol.setText(stockSymbol);

        btnBuy = view.findViewById(R.id.btnBuy);
        btnSell = view.findViewById(R.id.btnSell);

        btnBuy.setOnClickListener(v -> {
            BuyPopUpFragment buyPopUpFragment = BuyPopUpFragment.newInstance(stock);
            buyPopUpFragment.show(getFragmentManager(), "BuyPopUpFragment");
            StockManager.getInstance().increase(stock.getName());
        });

        return view;
    }


    public boolean allowBackPress(){
        return true;
    }
}