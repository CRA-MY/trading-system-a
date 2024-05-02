import driver.IStockerBrockerDriver;
import driver.StockerBrockerDriverKiwer;
import driver.StockerBrockerDriverNemo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplicationTest {
    // 기본적으로 드라이버를 사용하며, 키워 & 네모 모두에 대한 테스트를 보장한다
    // 각 드라이버의 행동은 mocking한다
    TradingApplication app;

    @Mock
    StockerBrockerDriverKiwer kiwer;
    @Mock
    StockerBrockerDriverNemo nemo;

    @BeforeEach
    void setUp() {
        app = new TradingApplication(kiwer);
        app.login("test","test");
    }

    @Test
    void 증권사선택() {
        app.selectStockBrocker(kiwer);
        app.selectStockBrocker(nemo);
    }

    @Test
    void 드라이버에러() {
        TradingApplication temp = new TradingApplication();
        assertThrows(DriverNotFoundException.class, () -> {
            temp.login("test","test");
        });
    }

    @Test
    void 로그인되지않음() {
        TradingApplication temp = new TradingApplication(kiwer);
        assertThrows(LoginStatusException.class, () -> {
            temp.sell("Test",3,3);
        });
    }

    @Test
    void 로그인정상() {
        verify(kiwer, times(1)).login("test","test");
    }

    @Test
    void 매수정상() {
        app.buy("STOCKCODE", 999, 1);
        verify(kiwer, times(1)).buy("STOCKCODE", 999, 1);
    }

    @Test
    void 매도정상() {
        app.sell("STOCKCODE", 999, 1);
        verify(kiwer, times(1)).sell("STOCKCODE", 999, 1);
    }

    @Test
    void 현재가확인정상() {
        doReturn(300).when(kiwer).getPrice("STOCKCODE");

        int actual = kiwer.getPrice("STOCKCODE");
        int expected = 300;

        assertEquals(expected, actual);
        verify(kiwer, times(1)).getPrice("STOCKCODE");
    }
}