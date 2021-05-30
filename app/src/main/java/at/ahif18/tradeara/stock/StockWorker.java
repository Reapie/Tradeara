package at.ahif18.tradeara.stock;

import java.util.concurrent.Callable;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

public class StockWorker implements Callable<Stock> {

    private String stockSymbol;

    public StockWorker (String stockName){
        this.stockSymbol = stockName;
    }

    @Override
    public Stock call() throws Exception {
        System.out.println("Load " + stockSymbol);
        return YahooFinance.get(stockSymbol, true);
    }
}
