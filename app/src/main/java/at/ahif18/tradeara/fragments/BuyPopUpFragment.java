package at.ahif18.tradeara.fragments;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import at.ahif18.tradeara.MainActivity;
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

    private SeekBar sb;
    private MainActivity mainActivity;

    public BuyPopUpFragment(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }


    public static BuyPopUpFragment newInstance(Stock stock, MainActivity mainActivity) {
        BuyPopUpFragment fragment = new BuyPopUpFragment(mainActivity);
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
        TextView tvBuyAmount = view.findViewById(R.id.tvBuyAmount);
        TextView tvBuyStockPrice = view.findViewById(R.id.tvBuyStockPrice);

        tvBuyAmount.setText("0");

        sb = view.findViewById(R.id.sbBuy);
        sb.setMax((int)(mainActivity.getAccount().getBalance() / stock.getPrice()));
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvBuyAmount.setText(progress + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        tvBuyStockPrice.setText(stock.getFormattedPrice());
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