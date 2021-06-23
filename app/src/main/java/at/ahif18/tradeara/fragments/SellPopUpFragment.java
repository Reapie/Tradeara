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

import at.ahif18.tradeara.MainActivity;
import at.ahif18.tradeara.R;
import at.ahif18.tradeara.data.Account;
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
    private boolean changeThroughEditText;

    private SeekBar sb;
    private MainActivity mainActivity;
    private int sbValue;


    public SellPopUpFragment(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
         sbValue = 1;
    }


    public static SellPopUpFragment newInstance(Stock stock, MainActivity mainActivity) {
        SellPopUpFragment fragment = new SellPopUpFragment(mainActivity);
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sell_pop_up, container, false);

        Account account = mainActivity.getAccount();


        TextView tvSellStockName = view.findViewById(R.id.tvSellStockName);
        TextView tvSellStockSymbol = view.findViewById(R.id.tvSellStockSymbol);
        TextView tvSellHoldingStocksAmount = view.findViewById(R.id.tvSellHoldingStocksAmount);
        TextView tvSellHoldingStocksValue = view.findViewById(R.id.tvSellHoldingStocksValue);
        EditText etSellAmount = view.findViewById(R.id.etSellAmount);
        TextView tvSellStockPrice = view.findViewById(R.id.tvSellStockPrice);
        Button btnSell = view.findViewById(R.id.btnSellSell);
        Button btnSellCancel = view.findViewById(R.id.btnSellCancel);

        tvSellStockName.setText(stock.getName());
        tvSellStockSymbol.setText(stock.getSymbol());
        tvSellStockPrice.setText(stock.getFormattedPrice());
        tvSellHoldingStocksAmount.setText(account.getStocks().get(stock) + " Stk.");
        tvSellHoldingStocksValue.setText(String.format("%,.2f EUR", account.getStocks().get(stock) * stock.getPrice()));


        btnSellCancel.setOnClickListener(v -> dismiss());

        etSellAmount.setText("1");

        sb = view.findViewById(R.id.sbSellAmount);
        int max = (int)mainActivity.getAccount().getStocks().get(stock);
        sb.setMax(max);
        sb.setMin(1);
        changeThroughEditText = false;

        if(stock.getPrice() > stock.getPrice() * max){
            etSellAmount.setEnabled(false);
        }else{
            etSellAmount.setEnabled(true);
        }

        etSellAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                changeThroughEditText = true;
                try{
                    if(etSellAmount.getText().toString().equals("")){
                        sb.setProgress(1);
                        sbValue = 1;
                    }else if(Integer.parseInt(etSellAmount.getText().toString()) > max){
                        sb.setProgress(max);
                        sbValue = max;
                    }else{
                        sb.setProgress(Integer.parseInt(etSellAmount.getText().toString()));
                        sbValue = Integer.parseInt(etSellAmount.getText().toString());
                    }
                }catch (Exception e){
                    sb.setProgress(1);
                    sbValue = 1;
                }
                changeThroughEditText = false;

            }
        });

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(!changeThroughEditText){
                    etSellAmount.setText(progress + "");
                    sbValue = Integer.parseInt(etSellAmount.getText().toString());
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnSell.setOnClickListener(v -> {
            account.removeStock(stock, sbValue);
            account.setBalance(account.getBalance() + (stock.getPrice() * sbValue));
            mainActivity.setAccount(account);
            if(mainActivity.currentActiveFragment().getClass() != HomeFragment.class){
                mainActivity.onBackPressed();
            }
            dismiss();

        });

        return view;
    }

    @Override
    public String toString() {
        return TAG;
    }
}