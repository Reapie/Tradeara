package at.ahif18.tradeara.bl;

import android.os.AsyncTask;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import at.ahif18.tradeara.MainActivity;
import at.ahif18.tradeara.data.Stock;
import at.ahif18.tradeara.data.StockManager;

public class LoadStocksTask extends AsyncTask<Integer, String, List<Stock>> {

    private MainActivity mainActivity;
    private StockAdapter stockAdapter;
    private boolean isHome;

    public LoadStocksTask(MainActivity mainActivity, boolean isHome) {
        this.mainActivity = mainActivity;
        this.isHome = isHome;
    }


    @Override
    protected void onPreExecute() {
        if(isHome){
            if(!StockManager.getInstance().isHomeStockAdapterSet()){
                stockAdapter = new StockAdapter(mainActivity);
                StockManager.getInstance().setHomeStockAdapter(stockAdapter);
            }else{
                stockAdapter = StockManager.getInstance().getHomeStockAdapter();
            }
        }else{
            stockAdapter = new StockAdapter(mainActivity);
        }
    }

    @Override
    protected void onPostExecute(List<Stock> stocks) {
        stockAdapter.setShowShimmer(false);
    }

    @Override
    protected List<Stock> doInBackground(Integer... integers) {
        if(StockManager.getInstance().getStocks().isEmpty() && isHome){
            mainActivity.setNavStatus(false);
            StockManager.getInstance().loadList(mainActivity);
            mainActivity.setNavStatus(true);
        }
        List<Stock> stocks = StockManager.getInstance().getStocks();
        stockAdapter.setStocksAll(stocks);
        if(!isHome){
            stockAdapter.setStocks(stocks);
        }

        return stocks;
    }

    public StockAdapter getStockAdapter() {
        return stockAdapter;
    }
}
