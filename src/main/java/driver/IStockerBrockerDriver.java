package driver;

public interface IStockerBrockerDriver {
    // 로그인
    void login();

    // 구매
    void buy();

    // 판매
    void sell();

    // 가격확인
    int getPrice();

}
