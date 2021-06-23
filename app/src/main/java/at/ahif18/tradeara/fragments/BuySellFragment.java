package at.ahif18.tradeara.fragments;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
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

import at.ahif18.tradeara.MainActivity;
import at.ahif18.tradeara.R;
import at.ahif18.tradeara.data.Stock;
import at.ahif18.tradeara.data.StockManager;


public class BuySellFragment extends BottomSheetDialogFragment {

    private static final String TAG = "BuySellFragment";
    private static final String ARG_PARAM_STOCK = "stock";

    private String stockName;
    private String stockSymbol;
    private String stockPrice;

    private Button btnBuy;
    private Button btnSell;

    private Stock stock;
    private MainActivity mainActivity;

    public BuySellFragment(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }


    
    public static BuySellFragment newInstance(Stock stock, MainActivity mainActivity) {
        BuySellFragment fragment = new BuySellFragment(mainActivity);
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
        if (mainActivity.getAccount().getStocks().containsKey(stock)) {
            stock.setPrice(StockManager.getInstance().getCurrentPrice(stock));
        }
        stockPrice = stock.getFormattedPrice();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_buy_sell, container, false);

        TextView tvStockName = view.findViewById(R.id.tvStockName);
        TextView tvStockSymbol = view.findViewById(R.id.tvStockSymbol);
        TextView tvStockPrice = view.findViewById(R.id.tvStockPrice);

        tvStockName.setSelected(true);

        tvStockName.setText(stockName);
        tvStockSymbol.setText(stockSymbol);
        tvStockPrice.setText(stockPrice);

        btnBuy = view.findViewById(R.id.btnBuy);
        btnSell = view.findViewById(R.id.btnSell);

        btnBuy.setOnClickListener(v -> {
            btnBuy.setClickable(true);
            BuyPopUpFragment buyPopUpFragment = BuyPopUpFragment.newInstance(stock, mainActivity);
            buyPopUpFragment.show(getFragmentManager(), buyPopUpFragment.getTag());
            StockManager.getInstance().increase(stock.getName());
        });

        btnSell.setOnClickListener(v -> {
            if(mainActivity.getAccount().getStocks().get(stock) != null){
                SellPopUpFragment sellPopUpFragment = SellPopUpFragment.newInstance(stock, mainActivity);
                sellPopUpFragment.show(getFragmentManager(), sellPopUpFragment.getTag());
                StockManager.getInstance().increase(stockName);
                dismiss();
            }

        });

        return view;
    }


    public boolean allowBackPress(){
        return true;
    }
}