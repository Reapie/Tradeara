package at.ahif18.tradeara.bl;

import android.util.Log;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.fx.FxQuote;
import yahoofinance.quotes.stock.StockQuote;

public class StockGetter {

    private static String fxEnding = "EUR=X";

    public static ArrayList<Stock> getStocks(String...stockSymbols) {
        ArrayList<Stock> stocks = new ArrayList();
        Thread t = new Thread(() -> {
            Map<String, Stock> stocksM = new HashMap();
            try {
                stocksM = YahooFinance.get(stockSymbols, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (String s : stocksM.keySet()) {
                Stock stock = stocksM.get(s);
                Float price = stocksM.get(s).getQuote().getPrice().floatValue();
                String currency = stocksM.get(s).getCurrency();
                if (!currency.equals("EUR")) {
                    FxQuote factor;
                    try {
                        factor = YahooFinance.getFx(currency + fxEnding);
                        Float eurPrice = price * factor.getPrice().floatValue();
                        stock.getQuote().setPrice(BigDecimal.valueOf(eurPrice));
                        stock.setCurrency("EUR");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                stocks.add(stock);
                Log.d("Stock", stock.toString());
            }
        }, "Stock Getter");
        t.start();

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return stocks;
    }

}
