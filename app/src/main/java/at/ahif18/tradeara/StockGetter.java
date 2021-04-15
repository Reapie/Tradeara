package at.ahif18.tradeara;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

public class StockGetter {

    public static ArrayList<Stock> getStocks(String... stockNames) {
        ArrayList<Stock> stocks = new ArrayList();
        Thread t = new Thread(() -> {
            Map<String, Stock> stocksM = new HashMap();
            try {
                stocksM = YahooFinance.get(stockNames, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (String s : stocksM.keySet()) {
                stocks.add(stocksM.get(s));
                System.out.println(stocksM.get(s).getQuote().getPrice());
            }
        }, "Stock Getter");
        t.start();
        return stocks;
    }

}
