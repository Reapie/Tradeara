package at.ahif18.tradeara.bl;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import at.ahif18.tradeara.MainActivity;
import at.ahif18.tradeara.R;
import at.ahif18.tradeara.data.Stock;
import at.ahif18.tradeara.data.StockManager;

public class StockAdapter extends RecyclerView.Adapter<StockHolder> {

    private List<Stock> stocks;
    private List<Stock> stocksAll;
    private MainActivity mainActivity;
    private boolean loaded = false;

    private boolean showShimmer=true;
    private int SHIMMER_ITEM_NUMBER=5;

    public StockAdapter(MainActivity mainActivity, boolean loaded) {
        this.mainActivity = mainActivity;
        this.loaded = loaded;
        stocks = new ArrayList<>();
        stocksAll = new ArrayList<>(stocks);
    }

    //private List<Stock> stocks = StockGetter.getStocks("INTC","TSLA");
    //float price = stocks.get(0).getQuote().getPrice() Preis kann andere währung haben

    @NonNull
    @Override
    public StockHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shimmer_item_view, parent, false);
        TextView tvNameStock = view.findViewById(R.id.tvShimmerNameStock);
        TextView tvPriceStock = view.findViewById(R.id.tvShimmerPriceStock);
        TextView tvSymbolStock = view.findViewById(R.id.tvShimmerSymbolStock);
        TextView tvDiffStock = view.findViewById(R.id.tvShimmerDiffStock);
        return new StockHolder(view, tvNameStock, tvPriceStock, tvSymbolStock, tvDiffStock, mainActivity);
    }

    @Override
    public void onBindViewHolder(@NonNull StockHolder holder, int position) {
        if(showShimmer){
            holder.getShimmerFrameLayout().startShimmer();
            holder.setShimmer(true);

            if(position == 3){
                Thread t1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mainActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(!loaded){
                                    StockManager.getInstance().loadList(mainActivity);
                                    loaded = true;
                                }
                                stocks = StockManager.getInstance().getStocks();
                                stocksAll = new ArrayList<>(stocks);
                                setShowShimmer(false);
                                notifyDataSetChanged();
                            }
                        });
                    }
                });

                t1.start();
            }
        }else{
            holder.setShimmer(false);
            holder.getShimmerFrameLayout().stopShimmer();
            holder.getShimmerFrameLayout().setShimmer(null);

            holder.getTvNameStock().setBackground(null);
            holder.getTvPriceStock().setBackground(null);
            holder.getTvSymbolStock().setBackground(null);
            holder.getTvDiffStock().setBackground(null);

            Stock stock = stocks.get(position);
            holder.setStock(stocks.get(position));
            holder.getTvNameStock().setText(stock.getName());
            holder.getTvPriceStock().setText(stock.getFormattedPrice());
            holder.getTvSymbolStock().setText(stock.getSymbol());
            holder.getTvDiffStock().setText(String.format("%s", stock.getDiff()));
            holder.getTvDiffStock().setTextColor(stock.getDiff() < 0 ? Color.RED : Color.GREEN);
        }

    }

    @Override
    public int getItemCount() {
        return showShimmer ? SHIMMER_ITEM_NUMBER : stocks.size();
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

    public void setShowShimmer(boolean showShimmer) {
        this.showShimmer = showShimmer;
    }
}
