package at.ahif18.tradeara.bl;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StockHolder extends RecyclerView.ViewHolder {
    private TextView tvNameStock;
    private TextView tvPriceStock;
    private TextView tvSymbolStock;
    private TextView tvDiffStock;

    public StockHolder(@NonNull View itemView, TextView tvNameStock, TextView tvPriceStock, TextView tvSymbolStock, TextView tvDiffStock) {
        super(itemView);
        this.tvNameStock = tvNameStock;
        this.tvPriceStock = tvPriceStock;
        this.tvSymbolStock = tvSymbolStock;
        this.tvDiffStock = tvDiffStock;
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
}
