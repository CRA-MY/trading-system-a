import driver.IStockerBrockerDriver;

class DriverNotFoundException extends RuntimeException {
    public DriverNotFoundException(String msg) {
        super(msg);
    }
}

public class TradingApplication {
    IStockerBrockerDriver driver;

    public TradingApplication() {
    }


    public TradingApplication(IStockerBrockerDriver driver) {
        this.driver = driver;
    }

    public void checkDriver() {
        if(driver == null)
            throw new DriverNotFoundException("DRIVER NOT FOUND");
    }

    public void selectStockBrocker(IStockerBrockerDriver driver) {
        this.driver = driver;
    }

    public void login(String id, String pwd) {
        checkDriver();
        driver.login(id, pwd);
    }

    public void buy(String stockCode, int price, int count) {
        checkDriver();
        driver.buy(stockCode, price, count);
    }

    public void sell(String stockCode, int price, int count) {
        checkDriver();
        driver.sell(stockCode, price, count);
    }

    public int currentPrice(String stockCode) throws InterruptedException {
        checkDriver();
        return driver.getPrice(stockCode);
    }
}
