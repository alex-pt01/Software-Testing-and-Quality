package exc1;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class StocksPortfolioTest {

    @Mock
    IStockmarketService mockMarket;

    @InjectMocks
    StocksPortfolio portfolio = new StocksPortfolio();


    @org.junit.jupiter.api.Test
    void getTotalValue() {
        IStockmarketService market = mock(IStockmarketService.class);

        portfolio.setMarketService(market);

        when(market.getPrice("EBAY")).thenReturn(4.0);
        when(market.getPrice("AMAZON")).thenReturn(2.0);

        this.portfolio.addStock(new Stock("EBAY", 2));
        this.portfolio.addStock(new Stock("AMAZON", 3));

        double result = portfolio.getTotalValue();


        assertEquals(14, result);
        verify(market, times(2)).getPrice(anyString());








    }

}