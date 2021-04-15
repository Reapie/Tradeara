package at.ahif18.tradeara.bl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

import at.ahif18.tradeara.R;
import at.ahif18.tradeara.data.Stock;

public class StockAdapter extends RecyclerView.Adapter<StockHolder> {

    private List<Stock> stocks = Arrays.asList(
            new Stock("Simon", 25.25),
            new Stock("David", 24.24)
    );

    //private List<Stock> stocks = StockGetter.getStocks("INTC","TSLA");
    //float price = stocks.get(0).getQuote().getPrice() Preis kann andere währung haben

    @NonNull
    @Override
    public StockHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stock_item,parent, false);
        TextView tvNameStock = view.findViewById(R.id.tvNameStock);
        TextView tvPriceStock = view.findViewById(R.id.tvPriceStock);
        return new StockHolder(view, tvNameStock, tvPriceStock);
    }

    @Override
    public void onBindViewHolder(@NonNull StockHolder holder, int position) {
        Stock stock = stocks.get(position);
        holder.getTvNameStock().setText(stock.getName());
        holder.getTvPriceStock().setText(String.format("%s", stock.getPrice()));
    }

    @Override
    public int getItemCount() {
        return stocks.size();
    }
}
