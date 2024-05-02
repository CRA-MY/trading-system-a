package driver;

import api.NemoAPI;

public class StockerBrockerDriverNemo implements IStockerBrockerDriver {
    NemoAPI api;

    public StockerBrockerDriverNemo() {
        this.api = new NemoAPI();
    }

    public void login( String ID, String Password) {
        api.certification(ID, Password);
    }

    public void buy( String stockCode, int count , int price) {
        api.purchasingStock(stockCode, count, price);
    }

    public void sell( String stockCode , int count , int price) {
        api.sellingStock(stockCode, count, price);
    }

    public int currentPrice ( String stockCode) throws InterruptedException {
        return api.getMarketPrice(stockCode, 0);
    }
}
