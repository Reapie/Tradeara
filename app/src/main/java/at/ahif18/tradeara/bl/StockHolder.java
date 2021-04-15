package at.ahif18.tradeara.bl;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StockHolder extends RecyclerView.ViewHolder {
    private TextView tvNameStock;
    private TextView tvPriceStock;

    public StockHolder(@NonNull View itemView, TextView tvNameStock, TextView tvPriceStock) {
        super(itemView);
        this.tvNameStock = tvNameStock;
        this.tvPriceStock = tvPriceStock;
    }

    public TextView getTvNameStock() {
        return tvNameStock;
    }

    public void setTvNameStock(TextView tvNameStock) {
        this.tvNameStock = tvNameStock;
    }

    public TextView getTvPriceStock() {
        return tvPriceStock;
    }

    public void setTvPriceStock(TextView tvPriceStock) {
        this.tvPriceStock = tvPriceStock;
    }
}
