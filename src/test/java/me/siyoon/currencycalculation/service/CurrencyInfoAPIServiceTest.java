package me.siyoon.currencycalculation.service;

import me.siyoon.currencycalculation.dto.CurrencyInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyInfoAPIServiceTest {
    @Autowired
    private CurrencyInfoAPIServiceImpl currencyLayerAPIService;

    @Test
    public void API_사용하여_환율정보_가져오기() {
        CurrencyInfo currencyInfo = currencyLayerAPIService.getCurrencyInfo();
        Assert.assertNotNull(currencyInfo);
    }

    @Test
    public void 업데이트_주기_이전에_재요청하면_같은_환율정보객체_리턴하기() {
        CurrencyInfo currencyInfo1 = currencyLayerAPIService.getCurrencyInfo();
        CurrencyInfo currencyInfo2 = currencyLayerAPIService.getCurrencyInfo();
        Assert.assertSame(currencyInfo1, currencyInfo2);
    }
}
