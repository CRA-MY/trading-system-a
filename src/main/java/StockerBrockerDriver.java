public interface StockerBrockerDriver {
    // 로그인
    void login();
    void certification();

    // 구매
    void buy();
    void purchasingStock();

    // 판매
    void sell();
    void sellingStock();

            // 가격확인
    int getPrice();
    int getMarketPrice ();

}
