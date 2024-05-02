import api.KiwerAPI;
import driver.StockerBrockerDriverKiwer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DriverKiwer {

    @Mock
    KiwerAPI kiwerAPI;

    StockerBrockerDriverKiwer driverKiwer;

    @BeforeEach
    void setUp() {
        driverKiwer = new StockerBrockerDriverKiwer(kiwerAPI);
    }

    @Test
    void 로그인성공() {
        driverKiwer.login("dd", "222");

        verify(kiwerAPI, times(1)).login("dd", "222");
    }

    @Test
    void 매수성공() {
        driverKiwer.buy("삼성전자", 22, 10000);

        verify(kiwerAPI, times(1)).buy("삼성전자", 22, 10000);
    }

    @Test
    void 매도성공() {
        driverKiwer.sell("삼성전자", 22, 10000);

        verify(kiwerAPI, times(1)).sell("삼성전자", 22, 10000);
    }

    @Test
    void 현재가확인성공() {
        when(kiwerAPI.currentPrice("삼성전자")).thenReturn(5000);

        assertEquals(5000, driverKiwer.getPrice("삼성전자"));
    }
}