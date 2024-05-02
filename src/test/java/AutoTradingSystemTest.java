import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AutoTradingSystemTest {

    @Mock
    TradingApplication tradingApplication;

    AutoTradingSystem autoTradingSystem;
    String stockCode = "Naver";
    int price = 3000;
    int maximumStock = 30; //최대 수량 가정
    int stockCount =10;

    @BeforeEach
    void setUp(){
        autoTradingSystem = new AutoTradingSystem(tradingApplication);
    }

    @Test
    void 가격이올라가는추세면구매_SUCCESS() throws InterruptedException {


        when(tradingApplication.currentPrice(stockCode)).thenReturn(10).thenReturn(20);

        autoTradingSystem.buyNiceTiming(stockCode,price);

        verify(tradingApplication,times(2)).currentPrice(stockCode);
        verify(tradingApplication,times(1)).buy(stockCode,maximumStock,price);
    }

    @Test
    void 가격이올라가는추세면구매_FAIL() throws InterruptedException {
        AutoTradingSystem autoTradingSystem = new AutoTradingSystem(tradingApplication);
        String stockCode = "Naver";
        int price = 3000;

        when(tradingApplication.currentPrice(stockCode)).thenReturn(10).thenReturn(5);

        autoTradingSystem.buyNiceTiming(stockCode,price);

        verify(tradingApplication,times(2)).currentPrice(stockCode);
        verify(tradingApplication,times(0)).buy(stockCode,maximumStock,price);
    }

    @Test
    void 가격이내려가는추세면판매_SUCCESS() throws InterruptedException {
        when(tradingApplication.currentPrice(stockCode)).thenReturn(20).thenReturn(10).thenReturn(10);

        autoTradingSystem.sellNiceTiming(stockCode,stockCount);

        verify(tradingApplication,times(3)).currentPrice(stockCode);
        verify(tradingApplication,times(1)).sell(stockCode,stockCount, 10);
    }

    @Test
    void 가격이내려가는추세면판매_FAIL() throws InterruptedException {
        when(tradingApplication.currentPrice(stockCode)).thenReturn(10).thenReturn(20).thenReturn(10);

        autoTradingSystem.sellNiceTiming(stockCode,stockCount);

        verify(tradingApplication,times(2)).currentPrice(stockCode);
        verify(tradingApplication,times(0)).sell(stockCode,stockCount, 10);
    }
}