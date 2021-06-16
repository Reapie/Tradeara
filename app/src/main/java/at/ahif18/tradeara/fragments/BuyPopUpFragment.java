package at.ahif18.tradeara.fragments;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import at.ahif18.tradeara.MainActivity;
import at.ahif18.tradeara.R;
import at.ahif18.tradeara.data.Account;
import at.ahif18.tradeara.data.Stock;


public class BuyPopUpFragment extends DialogFragment {

    private static final String TAG = "BuyPopUpFragment";
    private static final String ARG_PARAM_STOCK = "stock";

    private Stock stock;
    private String stockName;
    private String stockSymbol;
    private boolean changeThroughEditText;

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
        EditText etBuyAmount = view.findViewById(R.id.etBuyAmount);
        TextView tvBuyStockPrice = view.findViewById(R.id.tvBuyStockPrice);
        Button btnBuyBuy = view.findViewById(R.id.btnBuyBuy);
        Button btnBuyCancel = view.findViewById(R.id.btnBuyCancel);

        btnBuyCancel.setOnClickListener(v -> dismiss());

        etBuyAmount.setText("0");

        sb = view.findViewById(R.id.sbBuy);
        int max = (int)(mainActivity.getAccount().getBalance() / stock.getPrice());
        sb.setMax(max);
        changeThroughEditText = false;

        if(stock.getPrice() > stock.getPrice() * max){
            etBuyAmount.setEnabled(false);
        }else{
            etBuyAmount.setEnabled(true);
        }

        etBuyAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                    changeThroughEditText = true;
                    if(etBuyAmount.getText().toString().equals("")){
                        sb.setProgress(0);
                    }else{
                            sb.setProgress(Integer.parseInt(etBuyAmount.getText().toString()));
                    }
                    changeThroughEditText = false;

            }
        });


        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(!changeThroughEditText){
                    etBuyAmount.setText(progress + "");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnBuyBuy.setOnClickListener(v -> {
                Account account = mainActivity.getAccount();
                account.addStock(stock, Integer.parseInt(etBuyAmount.getText().toString()));
                account.setBalance(account.getBalance() - (stock.getPrice() * Integer.parseInt(etBuyAmount.getText().toString())));
                mainActivity.setAccount(account);
                dismiss();
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