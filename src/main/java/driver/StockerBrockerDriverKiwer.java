package driver;

import api.KiwerAPI;

public class StockerBrockerDriverKiwer implements IStockerBrockerDriver {
    KiwerAPI api;

    public StockerBrockerDriverKiwer() {
        this.api = new KiwerAPI();
    }

    public StockerBrockerDriverKiwer(KiwerAPI kiwer) {
        this.api = kiwer;
    }

    public void login(String ID, String Password) {
        api.login(ID, Password);
    }

    public void buy( String stockCode, int count , int price) {
        api.buy(stockCode, count, price);
    }

    public void sell( String stockCode , int count , int price) {
        api.sell(stockCode, count, price);
    }

    public int getPrice ( String stockCode) {
        return api.currentPrice(stockCode);
    }
}
