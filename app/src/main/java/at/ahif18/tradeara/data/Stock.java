package at.ahif18.tradeara.data;

public class Stock {
    private String name;
    private String symbol;
    private double price;

    private double diff;

    //private double minPrice;
    //private double maxPrice;

    public Stock(String name, String symbol, double price, double diff){
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.diff = diff;
    }

    public String getFormattedPrice(){
        return String.format("%,.2f", price) + " EUR";
    }

    public String getName() {
        return name;
    }

    public String getSymbol(){
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public double getDiff(){
        return diff;
    }
}
