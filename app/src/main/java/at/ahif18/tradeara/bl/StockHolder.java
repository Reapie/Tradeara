package at.ahif18.tradeara.bl;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import at.ahif18.tradeara.MainActivity;
import at.ahif18.tradeara.R;
import at.ahif18.tradeara.fragments.BuySellFragment;

public class StockHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView tvNameStock;
    private TextView tvPriceStock;
    private TextView tvSymbolStock;
    private TextView tvDiffStock;
    private MainActivity mainActivity;

    public StockHolder(@NonNull View itemView, TextView tvNameStock, TextView tvPriceStock, TextView tvSymbolStock, TextView tvDiffStock, MainActivity mainActivity) {
        super(itemView);
        this.tvNameStock = tvNameStock;
        this.tvPriceStock = tvPriceStock;
        this.tvSymbolStock = tvSymbolStock;
        this.tvDiffStock = tvDiffStock;
        this.mainActivity = mainActivity;

        itemView.setOnClickListener(this);
    }

    public TextView getTvNameStock() {
        return tvNameStock;
    }

    public TextView getTvPriceStock() {
        return tvPriceStock;
    }

    public TextView getTvSymbolStock() {
        return tvSymbolStock;

    }

    public TextView getTvDiffStock() {
        return tvDiffStock;
    }

    @Override
    public void onClick(View v) {
        //mainActivity.buySellBottomDialog();
       Fragment buySellFragment = new BuySellFragment();

       mainActivity.makeCurrentFragment(buySellFragment);
    }
}
