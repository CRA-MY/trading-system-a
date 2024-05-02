package driver;

public interface IStockerBrockerDriver {
    // 로그인
    void login(String ID, String Password);

    // 구매
    void buy(String stockCode, int count , int price);

    // 판매
    void sell(String stockCode , int count , int price);

    // 가격확인
    int getPrice(String stockCode);

}
