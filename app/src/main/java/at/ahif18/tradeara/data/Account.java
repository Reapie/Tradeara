package at.ahif18.tradeara.data;

import java.util.HashMap;

public class Account {
    public static double START_BALANCE = 40000;
    private double balance;
    private HashMap<Stock, Integer> stocks;
    private String name;

    public Account(String name) {
        this.balance = START_BALANCE;
        this.stocks = new HashMap();
        this.name = name;
    }

    public double getBalance(){
        return balance;
    }

    public void addStock(Stock stock, int amount){
        stocks.put(stock, stocks.get(stock) == null ? amount: stocks.get(stock) + amount);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
