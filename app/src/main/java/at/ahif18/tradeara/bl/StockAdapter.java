package at.ahif18.tradeara.bl;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Handler;
import java.util.stream.Collectors;

import at.ahif18.tradeara.MainActivity;
import at.ahif18.tradeara.R;
import at.ahif18.tradeara.data.Stock;
import at.ahif18.tradeara.data.StockManager;

public class StockAdapter extends RecyclerView.Adapter<StockHolder> {

    private List<Stock> stocks;
    private List<Stock> stocksAll;
    private MainActivity mainActivity;

    public StockAdapter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        stocks = StockManager.getInstance().getStocks();

        stocksAll = new ArrayList<>(stocks);
    }

    //private List<Stock> stocks = StockGetter.getStocks("INTC","TSLA");
    //float price = stocks.get(0).getQuote().getPrice() Preis kann andere währung haben

    @NonNull
    @Override
    public StockHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stock_item, parent, false);
        TextView tvNameStock = view.findViewById(R.id.tvNameStock);
        TextView tvPriceStock = view.findViewById(R.id.tvPriceStock);
        TextView tvSymbolStock = view.findViewById(R.id.tvSymbolStock);
        TextView tvDiffStock = view.findViewById(R.id.tvDiffStock);
        return new StockHolder(view, tvNameStock, tvPriceStock, tvSymbolStock, tvDiffStock, mainActivity);
    }

    @Override
    public void onBindViewHolder(@NonNull StockHolder holder, int position) {
        Stock stock = stocks.get(position);
        holder.setStock(stocks.get(position));
        holder.getTvNameStock().setText(stock.getName());
        holder.getTvPriceStock().setText(stock.getFormattedPrice());
        holder.getTvSymbolStock().setText(stock.getSymbol());
        holder.getTvDiffStock().setText(String.format("%s", stock.getDiff()));
        holder.getTvDiffStock().setTextColor(stock.getDiff() < 0 ? Color.RED : Color.GREEN);
    }

    @Override
    public int getItemCount() {
        return stocks.size();
    }

    public void filter(String s) {
        stocks = stocksAll
                .stream()
                .filter(stock -> (stock.getName() + stock.getSymbol())
                        .toLowerCase()
                        .contains(s.toLowerCase()))
                .collect(Collectors.toList());

        notifyDataSetChanged();
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
        notifyDataSetChanged();
    }
}
