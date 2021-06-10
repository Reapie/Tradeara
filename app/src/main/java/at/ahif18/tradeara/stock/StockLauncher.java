package at.ahif18.tradeara.stock;

import android.util.Log;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.fx.FxQuote;

public class StockLauncher {
    public static ArrayList<Stock> getStocks(String... stockSymbols) {
        ArrayList<Stock> stocks = new ArrayList();

        ExecutorService pool = Executors.newFixedThreadPool(10);
        //ExecutorService pool = Executors.newCachedThreadPool();
        List<Callable<Stock>> workerlist = new ArrayList<>();

        for (String stockSymbol : stockSymbols) {
            workerlist.add(new StockWorker(stockSymbol));
        }

        try {
            List<Future<Stock>> futures = pool.invokeAll(workerlist);

            pool.shutdown();

            for (Future<Stock> future : futures) {
                stocks.add(future.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return stocks;
    }
}
