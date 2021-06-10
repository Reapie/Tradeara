package at.ahif18.tradeara.stock;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.Callable;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.fx.FxQuote;

public class StockWorker implements Callable<Stock> {

    private static String fxEnding = "EUR=X";

    private String stockSymbol;


    public StockWorker (String stockName){
        this.stockSymbol = stockName;
    }

    @Override
    public Stock call() throws Exception {
        System.out.println("Load " + stockSymbol);
        Stock stock = YahooFinance.get(stockSymbol, true);

        Float price = stock.getQuote().getPrice().floatValue();
        String currency = stock.getCurrency();
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
        return stock;
    }
}
