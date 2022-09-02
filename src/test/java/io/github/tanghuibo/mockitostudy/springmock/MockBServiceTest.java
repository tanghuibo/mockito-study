package io.github.tanghuibo.mockitostudy.springmock;

import io.github.tanghuibo.mockitostudy.service.AService;
import io.github.tanghuibo.mockitostudy.service.BService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import javax.annotation.Resource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MockBServiceTest {

    Logger log = LoggerFactory.getLogger(MockBServiceTest.class);

    @Resource
    BService bService;

    @SpyBean
    AService aService;

    @Test
    public void aDataIncrement() {
        // 拦截 st
        doNothing().when(aService).setAData(anyInt());
        Integer oldValue = bService.showAData();
        log.info("oldValue: {}", oldValue);
        bService.aDataIncrement();
        // aService.setAData
        assertThat(bService.showAData(), equalTo(oldValue));
        verify(aService, times(1)).setAData(oldValue + 1);
    }
}