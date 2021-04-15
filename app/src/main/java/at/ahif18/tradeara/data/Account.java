package at.ahif18.tradeara.data;

import java.util.HashMap;

public class Account {
    private double balance = 21;
    private HashMap<Stock, Integer> stocks = new HashMap();

    public double getBalance(){
        return balance;
    }

    public void addStock(Stock stock, int amount){
        stocks.put(stock, stocks.get(stock) == null ? amount: stocks.get(stock) +amount);
    }

    public void removeStock(Stock stock, int amount){
        if(stocks.containsKey(stock) && stocks.get(stock) - amount > 0) {
            stocks.replace(stock, stocks.get(stock) - amount);
        }else if(stocks.get(stock) - amount == 0){
            stocks.remove(stock);
        }else{
            System.out.println("Error");
        }
    }
}
