import driver.IStockerBrockerDriver;

class DriverNotFoundException extends RuntimeException {
    public DriverNotFoundException(String msg) {
        super(msg);
    }
}

class LoginStatusException extends RuntimeException {
    public LoginStatusException(String msg) {
        super(msg);
    }
}

public class TradingApplication {
    private IStockerBrockerDriver driver;
    private boolean isLoggin = false;

    public TradingApplication() {
    }


    public TradingApplication(IStockerBrockerDriver driver) {
        this.driver = driver;
    }

    public void checkDriver() {
        if(driver == null)
            throw new DriverNotFoundException("DRIVER NOT FOUND");
    }

    public void checkLogin() {
        if(isLoggin == false)
            throw new LoginStatusException("NOT LOGGED IN");
    }

    public void selectStockBrocker(IStockerBrockerDriver driver) {
        this.driver = driver;
        isLoggin = false;
    }

    public void login(String id, String pwd) {
        checkDriver();
        driver.login(id, pwd);
        isLoggin = true;
    }

    public void buy(String stockCode, int price, int count) {
        checkDriver();
        checkLogin();
        driver.buy(stockCode, price, count);
    }

    public void sell(String stockCode, int price, int count) {
        checkDriver();
        checkLogin();
        driver.sell(stockCode, price, count);
    }

    public int currentPrice(String stockCode) throws InterruptedException {
        checkDriver();
        checkLogin();
        return driver.getPrice(stockCode);
    }
}
