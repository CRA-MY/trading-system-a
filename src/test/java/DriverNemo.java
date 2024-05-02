import api.NemoAPI;
import driver.StockerBrockerDriverNemo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DriverNemo {

    @Mock
    NemoAPI nemoAPI;

    StockerBrockerDriverNemo driverNemo;

    @BeforeEach
    void setUp() {
        driverNemo = new StockerBrockerDriverNemo(nemoAPI);
    }

    @Test
    void 로그인성공() {
        driverNemo.login("dd", "222");

        verify(nemoAPI, times(1)).certification("dd", "222");
    }

    @Test
    void 매수성공() {
        driverNemo.buy("SK하이닉스", 100, 100000);

        verify(nemoAPI, times(1)).purchasingStock("SK하이닉스", 100, 100000);
    }

    @Test
    void 매도성공() {
        driverNemo.sell("SK하이닉스", 100, 100000);

        verify(nemoAPI, times(1)).sellingStock("SK하이닉스", 100, 100000);
    }

    @Test
    void 현재가확인성공() throws InterruptedException {
        when(nemoAPI.getMarketPrice("SK하이닉스", 0)).thenReturn(50000);

        assertEquals(50000, driverNemo.getPrice("SK하이닉스"));
    }
}