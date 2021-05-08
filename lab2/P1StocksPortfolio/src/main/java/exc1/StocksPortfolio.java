package exc1;

import exc1.IStockmarketService;
import exc1.Stock;

import java.util.ArrayList;
import java.util.List;

public class StocksPortfolio {

    private String name;
    private IStockmarketService stockMarket;
    private List<Stock> stocks = new ArrayList<Stock>();


    public IStockmarketService getStockMarket() {
        return stockMarket;
    }

    public void setMarketService(IStockmarketService stockMarket) {
        this.stockMarket = stockMarket;
    }

    public double getTotalValue() {
        double value = 0;

        for (Stock s : stocks)
            value += (this.stockMarket.getPrice(s.getName()) * s.getQuantity());

        return value;
    }

    public void addStock(Stock stock) {
        this.stocks.add(stock);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}