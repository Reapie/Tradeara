package at.ahif18.tradeara.data;

public class Stock {
    private String name;
    private double price;

    //private double minPrice;
    //private double maxPrice;

    public Stock(String name, double price){
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
