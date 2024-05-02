public class AutoTradingSystem {
    static TradingApplication tradingApplication;

    public AutoTradingSystem(TradingApplication tradingApplication) {
        this.tradingApplication = tradingApplication;
    }

    public void buyNiceTiming(String stockCode, int price) {
        int maximumStock = 30; //최대 수량 가정
        if (isIncreasing(stockCode)) {
            tradingApplication.buy(stockCode, maximumStock, price); //종목코드, 수량, 가격
        }

    }

    private static boolean isIncreasing(String stockCode) {
        int firstPrice = tradingApplication.currentPrice(stockCode);
        int secondPrice = tradingApplication.currentPrice(stockCode);
        return firstPrice < secondPrice;
    }

    public void sellNiceTiming(String stockCode, int stockCount) {
        if (isDecreasing(stockCode)) {
            tradingApplication.sell(stockCode, stockCount, tradingApplication.currentPrice(stockCode)); //종목코드, 수량, 가격
        }

    }

    private static boolean isDecreasing(String stockCode) {
        int firstPrice = tradingApplication.currentPrice(stockCode);
        int secondPrice = tradingApplication.currentPrice(stockCode);
        return firstPrice > secondPrice;

    }
}
